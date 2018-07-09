package com.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.dataobject.ProductCategory;

import java.util.List;

/**
 * 2017-05-07 14:35
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
