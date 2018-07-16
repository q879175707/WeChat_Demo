package com.learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.service.SeckillService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/skill")
@Slf4j
public class SecKillController {
	
	@Autowired
	private SeckillService seckillService;
	
	/**
	 * 查询秒杀活动特价商品的信息
	 */
	@GetMapping("/query/{productId}")
	public String query(@PathVariable String productId) throws Exception {
		return seckillService.querySecKillProductInfo(productId);
	}
	
	/**
	 * 秒杀，没有抢到的获得"哎呦喂，xxxx"，抢到了会返回剩余的库存量
	 */
	@GetMapping("/order/{productId}")
	public String skill(@PathVariable String productId) throws Exception {
		log.info("@skill request, productId:" + productId);
		seckillService.orderProductMockDiffUser(productId);
		return seckillService.querySecKillProductInfo(productId);
	}
}
