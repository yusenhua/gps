package com.user.gps.model;

public class SendCouponOrder {
	
	private Integer id;
	
	private String code;
	
	private String mkey;
	
	private String muser;
	
	private Integer orderNumber;
	
	private String mobile;
	
	private String product;
	
	private Integer price;
	
	private Integer paid;
	
	private Integer couponValue;
	
	private String orderSendStatus;
	
	private String chargebackSendStatus;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMkey() {
		return mkey;
	}

	public void setMkey(String mkey) {
		this.mkey = mkey;
	}

	public String getMuser() {
		return muser;
	}

	public void setMuser(String muser) {
		this.muser = muser;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getPaid() {
		return paid;
	}

	public void setPaid(Integer paid) {
		this.paid = paid;
	}

	public Integer getCouponValue() {
		return couponValue;
	}

	public void setCouponValue(Integer couponValue) {
		this.couponValue = couponValue;
	}

	public String getOrderSendStatus() {
		return orderSendStatus;
	}

	public void setOrderSendStatus(String orderSendStatus) {
		this.orderSendStatus = orderSendStatus;
	}

	public String getChargebackSendStatus() {
		return chargebackSendStatus;
	}

	public void setChargebackSendStatus(String chargebackSendStatus) {
		this.chargebackSendStatus = chargebackSendStatus;
	}
}
