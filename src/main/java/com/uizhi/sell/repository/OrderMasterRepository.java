package com.uizhi.sell.repository;

import com.uizhi.sell.dataObject.OrderMaster;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DESCRIPTION :
 *
 * @author jiwei
 * @create 2017-10-04 23:10
 * @email charles_ji@icloud.com
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String>{
    Page<OrderMaster> findByBuyerOpenid(String buyer, Pageable pageable);
    Page<OrderMaster> findByBuyerOpenidAndOrderId(String buyerOpenId,String orderId,Pageable pageable);
}
