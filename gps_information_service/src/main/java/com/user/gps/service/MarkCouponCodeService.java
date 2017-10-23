package com.user.gps.service;

import java.util.List;
import java.util.Map;

import com.user.gps.model.MarkCouponCode;

/**
 * MarkCouponCodeService helps resolving dividied table issue. etc...
 * Created by yusenhua on 2017/2/22.
 */
public interface MarkCouponCodeService {

	List<Long> selectCouponIdByCouponCode(String couponCode);

	MarkCouponCode selectByPrimaryKey(Long couponCodeId);

	Integer selectRowCount(Map<String, Object> param);

	List<MarkCouponCode> selectMarkCouponCodeByParam(Map<String, Object> param);

	MarkCouponCode updateMarkCouponCode(MarkCouponCode markCouponCode, boolean changeCode);

	Integer selectMarkCouponCodeRowCount(Map<String, Object> param);

	int insertMarkCouponCodeBatch(List<MarkCouponCode> couponCodeList);

	int deleteByPrimaryKey(Long couponId, Long couponCodeId);

	int saveMarkCouponCode(MarkCouponCode markCouponCode);

	 
	
	
}
