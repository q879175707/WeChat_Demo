package com.learn.service;

import java.util.List;

import com.learn.dataobject.ProductCategory;

/**
 * 类目
 * 2017-05-09 10:12
 */
public interface CategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
