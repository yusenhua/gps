package com.user.gps.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.user.gps.model.GpsInformation;
import com.user.gps.mybatis.BaseDAO;
import com.user.gps.util.CouponUtil;

@Repository
public class GpsInformationDAO extends BaseDAO{
	
	 public GpsInformationDAO() {
	        super("GPS_INFORMATION", "GPS_INFORMATION",32);  // total table num: 32
	    }
	 
	 
	 /**
	     * Save mark coupon code
	     * @param markCouponCode mark coupon code object
	     * @return affected rows
	     */
	    public int insert(GpsInformation gpsInformation) {
	        if (null == gpsInformation.getUserId() || 0 == gpsInformation.getUserId()) {
	            return 0;
	        }  	
	        		
	        gpsInformation.setId(CouponUtil.getCouponKeyId(Long.valueOf(gpsInformation.getUserId())));
	        Map<String, Object> params = new HashMap<>();
	        params.put("table_name",    super.getTableNameOf(gpsInformation.getUserId()));    // by couponId(优惠券活动ID)
	        params.put("id",gpsInformation.getId());
	        params.put("gpsInformation",      gpsInformation.getGpsInformation());
	        params.put("userId",    gpsInformation.getUserId());
	        return super.insert("insert", params);
	    }


	public GpsInformation selectByUserId(Long userId) {
		Map<String, Object> params = new HashMap<>();
        params.put("table_name", super.getTableNameOf(userId));
        params.put("userId", userId);

        return super.get("selectByUserId", params);
	}
	
	

}
