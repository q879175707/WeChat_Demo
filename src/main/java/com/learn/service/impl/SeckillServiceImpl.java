package com.learn.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.exception.SellException;
import com.learn.service.RedisLock;
import com.learn.service.SeckillService;
import com.learn.utils.KeyUtil;

@Service
public class SeckillServiceImpl implements SeckillService{
	
	// 超时时间10s
	private static final int TIMEOUT = 10 * 1000;
	
	@Autowired
	private RedisLock redisLock;
	
	/**
	 * 秒杀活动，商品特价，限量100000份
	 */
	static Map<String, Integer> products;
	static Map<String, Integer> stock;
	static Map<String, String> orders;
	
	{
		/**
		 * 模拟多个表，商品信息表，库存表，秒杀成功订单表
		 */
		products = new HashMap<>();
		stock = new HashMap<>();
		orders = new HashMap<>();
		products.put("123456", 100000);
		stock.put("123456", 100000);
	}
	
	private String queryMap(String productId) {
		return "国庆活动，商品特价，限量份"
				+ products.get(productId)
				+" 还剩:" + stock.get(productId) + " 份"
				+ " 该商品成功下单用户数目: "
				+ orders.size() + " 人";
	}
	
	@Override
	public String querySecKillProductInfo(String productId) {
		return this.queryMap(productId);
	}

	@Override
	public void orderProductMockDiffUser(String productId) {
		// 加锁
		long time = System.currentTimeMillis() + TIMEOUT;
		if(!redisLock.lock(productId, String.valueOf(time))) {
			throw new SellException(101, "哎呦喂，人也太多了，换个姿势再试试~~");
		}
		// 1.查询该商品库存，为0则活动结束。
		int stockNum = stock.get(productId);
		if (stockNum == 0) {
			throw new SellException(100,"活动结束");
		} else {
			// 2.下单（模拟不同用户openId不同）
			orders.put(KeyUtil.genUniqueKey(), productId);
			// 3.减库存
			stockNum = stockNum - 1;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			stock.put(productId, stockNum);
		}
		// 解锁
		
		redisLock.unLock(productId, String.valueOf(time));
	}
}
