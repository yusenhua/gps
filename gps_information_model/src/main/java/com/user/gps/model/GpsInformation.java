package com.user.gps.model;

import java.io.Serializable;
import java.util.Date;

public class GpsInformation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Long userId;
	
	private String gpsInformation;
	
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getGpsInformation() {
		return gpsInformation;
	}

	public void setGpsInformation(String gpsInformation) {
		this.gpsInformation = gpsInformation;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
	
	

}

