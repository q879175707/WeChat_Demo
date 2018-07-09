package com.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.dataobject.SellerInfo;

/**
 * 2017-07-23 23:04
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo, String> {
    SellerInfo findByOpenid(String openid);
}
