package com.uizhi.sell.Controller;

import com.uizhi.sell.VO.ResultVo;
import com.uizhi.sell.dto.OrderDto;
import com.uizhi.sell.enums.ResultEnum;
import com.uizhi.sell.exception.SellException;
import com.uizhi.sell.form.OrderForm;
import com.uizhi.sell.service.BuyerService;
import com.uizhi.sell.service.OrderService;
import com.uizhi.sell.utils.OrderForm2OrderDTOConverter;
import com.uizhi.sell.utils.ResultVoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DESCRIPTION :
 *
 * @author jiwei
 * @create 2017-10-05 16:30
 * @email charles_ji@icloud.com
 */
@Slf4j
@RestController
@RequestMapping("/buyer/order")
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    //创建订单
    @PostMapping("/create")
    public ResultVo<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】 参数不正确, orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDto orderDto = OrderForm2OrderDTOConverter.conver(orderForm);
        if (CollectionUtils.isEmpty(orderDto.getOrderDetails())) {
            log.error("【创建订单】 购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDto orderDtoResult = orderService.create(orderDto);
        Map<String, String> map = new HashMap<String, String>();
        map.put("orderId", orderDtoResult.getOrderId());
        return ResultVoUtil.success(map);
    }

    //订单列表
    @GetMapping("/list")
    public ResultVo<List<OrderDto>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】 openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        PageRequest pageRequest = new PageRequest(page, size);
        Page<OrderDto> orderDtoPage = orderService.findList(openid, pageRequest);
        return ResultVoUtil.success(orderDtoPage.getContent());
    }
    //订单详情

    /**
     * 传入参数
     * openid: 18eu2jwk2kse3r42e2e
     * orderId: 161899085773669363
     */

    @GetMapping("/detail")
    public ResultVo<OrderDto> orderDetail(@RequestParam(value = "openid") String openid,
                                          @RequestParam(value = "orderId") String orderId) {
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】 openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        if (StringUtils.isEmpty(orderId)) {
            log.error("【查询订单列表】 orderId为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        //TODO 存在安全性问题  改进
        OrderDto orderDto = buyerService.findOrderOne(openid,orderId);
        return ResultVoUtil.success(orderDto);
    }
    //取消订单
    /*
    openid: 18eu2jwk2kse3r42e2e
    orderId: 161899085773669363
     */
    @PostMapping("/cancel")
    public ResultVo cancel(@RequestParam(value = "openid") String openid,
                           @RequestParam(value = "orderId") String orderId){
       buyerService.cancelOrder(openid,orderId);
        return ResultVoUtil.success();
    }
}
