package com.learn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RedisLock {

	@Autowired
	private StringRedisTemplate redisTemplate;

	/**
	 * 加锁
	 * 
	 * @param key
	 *            productId
	 * @param value
	 *            当前时间 + 超时时间
	 * @return
	 */
	public boolean lock(String key, String value) {
		// 如果可以成功设置返回true
		// 如果在同一时间有多个线程访问这里，抢到的线程获取锁，没有抢到的锁会跳过这个判断
		if (redisTemplate.opsForValue().setIfAbsent(key, value)) {
			return true;
		}

		// 假设上一个key从redis中取出的值是A（currentValue = A），但是当前多个线程的值是B，或者有的也是A
		String currentValue = redisTemplate.opsForValue().get(key);
		
		// if lock overdue 如果锁过期了，超时了因为value=当前时间+超时时间。
		if (!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()) {
			
			// get the lock for the previous time and set the new value
			// 获取上一个锁的时间，比如是A（OldValue = A），第一个线程获取A设置B执行下面返回true；第二个线程获取的值却是B(oldValue = B)
			// 所以他返回false，只会是一个线程拿到锁
			String oldValue = redisTemplate.opsForValue().getAndSet(key, value);
			// 如果相等就返回true
			if (!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)) {
				return true;
			} // end if
		} // end if lock overdue

		return false;
	}
	
	
	/**
	 * 解锁
	 * @param key
	 * @param value
	 */
	public void unLock(String key, String value) {
		try {
			String currentValue = redisTemplate.opsForValue().get(key);
			if (!StringUtils.isEmpty(currentValue)
					&&currentValue.equals(value)) {
				redisTemplate.opsForValue().getOperations().delete(key);
			}
		} catch (Exception e) {
			log.error("{redis分布式锁} 解锁异常, {}",e);
		}
	}
	
	
}