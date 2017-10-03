package com.uizhi.sell.dataObject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

@Entity
@DynamicUpdate
@Data
public class ProductCategory {
    @GeneratedValue
    @Id
    private Integer categoryId;
    private String categoryName;
    private Integer categoryType;
    private Date createTime;
    private Date updateTime;

    public ProductCategory() {

    }

    public ProductCategory(String categoryName, Integer categoryType, Date createTime, Date updateTime) {

        this.categoryName = categoryName;
        this.categoryType = categoryType;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public ProductCategory(String categoryName, Integer categoryType) {

        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
