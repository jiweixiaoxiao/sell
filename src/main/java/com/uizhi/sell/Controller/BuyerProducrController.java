package com.uizhi.sell.Controller;

import com.uizhi.sell.VO.ProductCategoryVo;
import com.uizhi.sell.VO.ProductInfoVo;
import com.uizhi.sell.VO.ResultVo;
import com.uizhi.sell.dataObject.ProductCategory;
import com.uizhi.sell.dataObject.ProductInfo;
import com.uizhi.sell.service.ProductCategoryService;
import com.uizhi.sell.service.ProductInfoService;
import com.uizhi.sell.utils.ResultVoUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * DESCRIPTION :
 *
 * @author jiwei
 * @create 2017-10-04 16:59
 * @email charles_ji@icloud.com
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProducrController {
    @Autowired
    ProductInfoService productInfoService;
    @Autowired
    ProductCategoryService productCategoryService;

    @GetMapping("/list")
    public ResultVo list() {

        // 1 查询所有上架商品
        List<ProductInfo> productInfos =  productInfoService.findUpAll(0);
        // 2 查询所有类目
        List<ProductCategory> productCategories = productCategoryService.findAll();


        List<ProductCategoryVo> resultList = new ArrayList<ProductCategoryVo>();
        // 3 数据拼接


        for (ProductCategory productCategory:productCategories) {
            ProductCategoryVo productCategoryVo = new ProductCategoryVo();
            List<ProductInfoVo> productInfoVoList = new ArrayList<ProductInfoVo>();
            productCategoryVo.setCategoryName(productCategory.getCategoryName());
            productCategoryVo.setCategoryType(productCategory.getCategoryType());
            for(ProductInfo productInfo : productInfos){
                if(productCategory.getCategoryType().equals(productInfo.getCategoryType())){
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo,productInfoVo);
                    productInfoVoList.add(productInfoVo);
                }
            }
            productCategoryVo.setFoods(productInfoVoList);
            resultList.add(productCategoryVo);
        }
        return ResultVoUtil.success(resultList);
    }
}
