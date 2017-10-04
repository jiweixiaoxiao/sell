package com.uizhi.sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * DESCRIPTION :
 *
 * @author jiwei
 * @create 2017-10-04 16:47
 * @email charles_ji@icloud.com
 */
@Data
public class ProductCategoryVo {
    @JsonProperty("name")
    private  String categoryName;
    @JsonProperty("type")
    private Integer categoryType;
    @JsonProperty("foods")
    private List<ProductInfoVo> foods;

}
