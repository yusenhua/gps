package com.user.gps.util;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class CouponUtil {

	/**
	 * 校验 是否是newCoupon系统代码
	 * 
	 * @param couponId
	 * @return
	 */
	public static boolean isNewCouponCode(String code) {
		if (StringUtils.isBlank(code)) {
			return false;
		}

		return code.indexOf("N") != -1;
	}

	public static Long getCouponIdAfterFour(String couponCode) {
		if (couponCode.indexOf("N") != -1) {
			String couponId = couponCode.substring(couponCode.indexOf("N") + 1, couponCode.indexOf("N") + 5);
			return Long.valueOf(couponId);
		}
		return 0L;
	}

	public static Long getCouponIdAfterFourByCouponCodeId(Long couponCodeId) {
		String couponId = couponCodeId.toString().substring(1, 5);
		return Long.valueOf(couponId);
	}

	public static Long getKeyId(Long couponId) {
		StringBuilder couponCodeIdstr = new StringBuilder();
		couponCodeIdstr.append("8").append(Constant.getCouponIdAferFour(couponId)).append(IdWorker.nextId());
		return Long.valueOf(couponCodeIdstr.toString());
	}
	
	public static Long getCouponKeyId(Long couponId) {
		StringBuilder couponCodeIdstr = new StringBuilder();
		String nextId= String.valueOf(IdWorker.nextId());
		couponCodeIdstr.append("8").append(Constant.getCouponIdAferFive(couponId)).append(nextId.substring(1));
		return Long.valueOf(couponCodeIdstr.toString());
	}
	
	
	public static String ListTokey(String indexKey,List<Long>  list){
		StringBuilder  key = new StringBuilder(indexKey);
		if(list == null || list.size() ==0){
			return key.toString();
		}
		for(Long id:list){
			key.append(id).append("_");
		}		
		
		if(key.length()>254){
			key = new StringBuilder(key.toString().substring(0,254));
		}
		return key.toString();
	}
	
	
	public static enum COUPON_INFO {
		
		BIND_ALREADY("您已经领取过优惠券了"),
		Exceed_DeliverTimes("超过领取次数");
		

		private String cnName;
		COUPON_INFO(String name){
			this.cnName=name;
		}
		public String getCode(){
			return this.name();
		}
		public String getCnName(){
			return this.cnName;
		}
		public static String getCnName(String code){
			for(COUPON_INFO item:COUPON_INFO.values()){
				if(item.getCode().equals(code))
				{
					return item.getCnName();
				}
			}
			return code;
		}
		@Override
        public String toString(){
			return this.name();
		}
	}
	
	public static String ListToStringkey(String indexKey,List<String>  list){
		StringBuilder  key = new StringBuilder(indexKey);
		if(list == null || list.size() ==0){
			return key.toString();
		}
		for(String id:list){
			key.append(id).append("_");
		}		
		
		if(key.length()>254){
			key = new StringBuilder(key.toString().substring(0,254));
		}
		return key.toString();
	}
	

}
