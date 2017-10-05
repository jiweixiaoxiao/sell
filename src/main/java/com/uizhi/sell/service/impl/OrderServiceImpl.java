package com.uizhi.sell.service.impl;

import com.sun.xml.internal.bind.v2.TODO;
import com.uizhi.sell.dataObject.OrderDetail;
import com.uizhi.sell.dataObject.OrderMaster;
import com.uizhi.sell.dataObject.ProductInfo;
import com.uizhi.sell.dto.CartDto;
import com.uizhi.sell.dto.OrderDto;
import com.uizhi.sell.enums.OrderStatusEnum;
import com.uizhi.sell.enums.PayStatusEnum;
import com.uizhi.sell.enums.ResultEnum;
import com.uizhi.sell.exception.OrderStatusException;
import com.uizhi.sell.exception.ResultException;
import com.uizhi.sell.exception.SellException;
import com.uizhi.sell.repository.OrderDetailRepository;
import com.uizhi.sell.repository.OrderMasterRepository;
import com.uizhi.sell.service.OrderService;
import com.uizhi.sell.service.ProductCategoryService;
import com.uizhi.sell.service.ProductInfoService;
import com.uizhi.sell.utils.OrderMaster2OrderDTO;
import com.uizhi.sell.utils.UUIDUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DESCRIPTION :
 *
 * @author jiwei
 * @create 2017-10-04 23:54
 * @email charles_ji@icloud.com
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
    OrderMasterRepository orderMasterRepository;
    @Autowired
    ProductInfoService productInfoService;

    @Override
    @Transactional
    public OrderDto create(OrderDto orderDto) {
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        String orderId = UUIDUtil.getUuid();
        //查询商品（单价，数量）
        for (OrderDetail detail : orderDto.getOrderDetails()) {
            ProductInfo productInfo = productInfoService.findOne(detail.getProductId());
            if (productInfo == null) {
                throw new ResultException(ResultEnum.PRODUCT_NOT_EXIT);
            }
            //计算总价  单价*数量
            orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(detail.getProductQuantity())).add(orderAmount);
            //写入订单数据库 OrderMaster和OrderDetail
            BeanUtils.copyProperties(productInfo, detail);
            detail.setOrderId(orderId);
            detail.setDetailId(UUIDUtil.getUuid());
            orderDetailRepository.save(detail);
        }
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto, orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        //扣库存
        List<CartDto> cartDtoList =
                orderDto.getOrderDetails().stream().map(e -> new CartDto(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());

        productInfoService.decreaseStock(cartDtoList);
        orderDto.setOrderId(orderId);
        return orderDto;
    }

    @Override
    public OrderDto findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if (orderMaster == null) {
            throw new ResultException(ResultEnum.ORDER_NOT_EXIT);
        }
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new ResultException(ResultEnum.ORDERDETAIL_NOT_EXIT);
        }
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster, orderDto);
        orderDto.setOrderDetails(orderDetailList);
        return orderDto;
    }

    @Override
    public Page<OrderDto> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> page = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderDto> orderDtoList = OrderMaster2OrderDTO.convert(page.getContent());
        Page<OrderDto> orderDtoPage = new PageImpl<OrderDto>(orderDtoList, pageable, page.getTotalElements());
        return orderDtoPage;
    }

    @Override
    @Transactional
    public OrderDto chancel(String openId,String orderId) {
        OrderDto orderDto = new OrderDto();
        // 1. 判断订单状态
        /*
         NEW(0,"新订单"),
    FINISHED(1,"完结"),
    CANCEL(2,"已取消"),
    exception(3,"状态异常")

    openid: 18eu2jwk2kse3r42e2e
orderId: 161899085773669363
         */
        PageRequest pageRequest = new PageRequest(0, 2);
        Page<OrderMaster> page = orderMasterRepository.findByBuyerOpenidAndOrderId(openId, orderId, pageRequest);
        //orderMasterRepository.findByBuyerOpenidAndOrderIdAndPayStatus()
        List<OrderMaster> orderMasterList = page.getContent();
        for (OrderMaster orderMaster : orderMasterList) {
            if (orderMaster.getOrderStatus().equals(OrderStatusEnum.PAIED.getCode())) {
                //TODO:"办理退款";
                return null;
            }
            if (orderMaster.getOrderStatus().equals(OrderStatusEnum.CANCEL.getCode())) {
                throw new OrderStatusException(OrderStatusEnum.CANCEL);
            }
            if (orderMaster.getOrderStatus().equals(OrderStatusEnum.FINISHED.getCode())) {
                throw new OrderStatusException(OrderStatusEnum.FINISHED);
            }
            // 2. 修改订单状态
            // 2.1 拿到订单详情表中的数量
            // 2.2 拿到商品表中的库存 + 订单详情表中数量
            // 2.3 将加过的和设置到商品表中
            if (orderMaster.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
                orderMaster.setOrderStatus(2);
                List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
                List<CartDto> cartDtoList = new ArrayList();
                for (OrderDetail orderDetail : orderDetailList) {
                    CartDto cartDto = new CartDto(orderDetail.getProductId(),orderDetail.getProductQuantity());
                    cartDtoList.add(cartDto);
                }
                productInfoService.increaseStock(cartDtoList);
                orderMasterRepository.save(orderMaster);
            }


        }


        // 3. 返回库存

        // 4. 如果已支付，需要退款

        return orderDto;
    }

    @Override
    @Transactional
    public OrderDto finish(OrderDto orderDto) {
        if(!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            throw new SellException(ResultEnum.ORDER_EXCEPTION);
        }
        orderDto.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if(updateResult == null){
            throw  new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDto;
    }

    @Override
    @Transactional
    public OrderDto paid(OrderDto orderDto) {
        //判断订单状态
        if(!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            throw new SellException(ResultEnum.ORDER_EXCEPTION);
        }
        //判断支付状态
        if(!orderDto.getOrderStatus().equals(PayStatusEnum.WAIT.getCode())){
            throw new SellException(ResultEnum.PAIED_ERROR);
        }
        //修改支付状态
        orderDto.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if(updateResult == null){
            throw  new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDto;
    }
}
