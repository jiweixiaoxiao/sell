package com.uizhi.sell.dataObject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * product_id   varchar(32) NOT NULL
 product_name   varchar(64) NOT NULL商品名称
 product_price  decimal(8,2) NOT NULL单价
 product_stock  int(11) NOT NULL库存
 product_description    varchar(64) NULL描述
 product_icon   varchar(512) NULL小图
 product_status tinyint(3) NULL商品状态,0正常1下架
 category_type  int(11) NOT NULL类目编号
 create_time    timestamp NOT NULL创建时间
 update_time
 */

@Entity
@Data
public class ProductInfo {
    @Id
    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productStock;
    private String productDescription;
    private String productIcon;
    private Integer productStatus;
    private Integer categoryType;
}
