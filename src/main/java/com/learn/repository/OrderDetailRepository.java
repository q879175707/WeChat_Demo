package com.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.dataobject.OrderDetail;

import java.util.List;

/**
 * 2017-06-11 17:28
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    List<OrderDetail> findByOrderId(String orderId);
}
