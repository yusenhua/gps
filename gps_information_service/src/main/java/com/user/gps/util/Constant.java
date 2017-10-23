package com.user.gps.util;

public class Constant {
	
	/**
	 * 子查询 最大记录数 100
	 */
	public static final Integer IN_MAX = 100;
	public static final Integer TOTAL_TALBE_NUM = 32;	
	/**
	 * 根据couponId  截取后四位
	 * @param couponId
	 * @return
	 */
	public static String getPartCouponCode(Long couponId){
	
		String partCode = getCouponIdAferFour(couponId);
		return "N"+partCode.toString();
	}
	
	public static String getCouponIdAferFour(Long couponId){
		if(couponId == null || couponId == 0){
			return "";
		}
		String couponIdStr = String.valueOf(couponId);
		StringBuilder partCode;
		if (couponIdStr.length() >= 4){
			partCode = new StringBuilder(couponIdStr.substring(couponIdStr.length() - 4, couponIdStr.length()));
		} else {
			partCode = new StringBuilder(couponIdStr);
			for(int i=0 ;i<4-couponIdStr.length();i++){
				partCode.insert(0, "0");
			}
		}
		return partCode.toString();
	}
	
	public static String getCouponIdAferFive(Long couponId){
		if(couponId == null || couponId == 0){
			return "";
		}
		String couponIdStr = String.valueOf(couponId);
		StringBuilder partCode;
		if (couponIdStr.length() >= 5){
			partCode = new StringBuilder(couponIdStr.substring(couponIdStr.length() - 5, couponIdStr.length()));
		} else {
			partCode = new StringBuilder(couponIdStr);
			for(int i=0 ;i<5-couponIdStr.length();i++){
				partCode.insert(0, "0");
			}
		}
		return partCode.toString();
	}
	
	
}
