package com.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.dataobject.ProductInfo;

import java.util.List;

/**
 * 2017-05-09 11:39
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    List<ProductInfo> findByProductStatus(Integer productStatus);
}
