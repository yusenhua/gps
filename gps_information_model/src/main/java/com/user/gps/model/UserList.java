package com.user.gps.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.lvmama.comm.vo.Constant;

public class UserList implements Serializable {

	private static final long serialVersionUID = -3301256457424918313L;
	
	private Long userId;
	
	//用户名
	private String userName;
	
	//姓名
	private String realName;
	
	//性别
	private String genderId;
	
	//年龄
	private Long age;
	
	//生日
	private Date bithday;
	
	//会员等级
	private String grade;
	
	//手机
	private String mobile;
	
	//邮箱
	private String email;
	
	//微信
	private String weixinId;
	
	//QQ
	private String qqAccount;
	
	//登录方式
	private String loginTypeCode;
	
	//登录渠道
	private String loginChannelCode;
	
	//总订单数
	private Long orderCount;
	
	//支付总金额，消费总金额
	private Long salesAmount;
	
	//平均每次交易额，支付总金额/总订单数
	private Long avgAmount;
	
	//购买频率
	private Long payFrequency;
	
	//购买产品种类
	private String payItems;
	
	//体验点评数
	private Long experienceCommentCount;
	
	//免费点评数
	private Long freeCommentCount;
	
	//注册时间
	private Date registerTime;
	
	//最近一次登录时间
	private Date loginTimeLast;
	
	//最近购买时间
	private Date payDateLast;
	
	//单笔最高交易金额
	private Long maxAmount;
	
	//第一次登录时间
	private Date loginTimeFirst;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getGenderId() {
		return genderId;
	}

	public void setGenderId(String genderId) {
		this.genderId = genderId;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWeixinId() {
		return weixinId;
	}

	public void setWeixinId(String weixinId) {
		this.weixinId = weixinId;
	}

	public String getQqAccount() {
		return qqAccount;
	}

	public void setQqAccount(String qqAccount) {
		this.qqAccount = qqAccount;
	}

	public String getLoginTypeCode() {
		return loginTypeCode;
	}

	public void setLoginTypeCode(String loginTypeCode) {
		this.loginTypeCode = loginTypeCode;
	}

	public String getLoginChannelCode() {
		return loginChannelCode;
	}

	public void setLoginChannelCode(String loginChannelCode) {
		this.loginChannelCode = loginChannelCode;
	}

	public Long getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Long orderCount) {
		this.orderCount = orderCount;
	}

	public Long getSalesAmount() {
		return salesAmount;
	}

	public void setSalesAmount(Long salesAmount) {
		this.salesAmount = salesAmount;
	}

	public Long getAvgAmount() {
		return avgAmount;
	}

	public void setAvgAmount(Long avgAmount) {
		this.avgAmount = avgAmount;
	}

	public Long getPayFrequency() {
		return payFrequency;
	}

	public void setPayFrequency(Long payFrequency) {
		this.payFrequency = payFrequency;
	}

	public String getPayItems() {
		return payItems;
	}

	public void setPayItems(String payItems) {
		this.payItems = payItems;
	}

	public Long getExperienceCommentCount() {
		return experienceCommentCount;
	}

	public void setExperienceCommentCount(Long experienceCommentCount) {
		this.experienceCommentCount = experienceCommentCount;
	}

	public Long getFreeCommentCount() {
		return freeCommentCount;
	}

	public void setFreeCommentCount(Long freeCommentCount) {
		this.freeCommentCount = freeCommentCount;
	}

	public Date getBithday() {
		return bithday;
	}

	public void setBithday(Date bithday) {
		this.bithday = bithday;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public Date getLoginTimeLast() {
		return loginTimeLast;
	}

	public void setLoginTimeLast(Date loginTimeLast) {
		this.loginTimeLast = loginTimeLast;
	}

	public Date getPayDateLast() {
		return payDateLast;
	}

	public void setPayDateLast(Date payDateLast) {
		this.payDateLast = payDateLast;
	}
	
	public String getZhGenderId() {
		if(StringUtils.isNotEmpty(genderId)) {
			if("F".equalsIgnoreCase(genderId)) {
				return "女";
			} else {
				return "男";
			}
		}
		return "";
	}

	public Long getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(Long maxAmount) {
		this.maxAmount = maxAmount;
	}
	
	public String getZhGrade() {
		if(StringUtils.isNotEmpty(grade)) {
			return Constant.USER_GRADE.getCnName(grade);
		}
		return "";
	}
	
	public String getZhLoginTypeCode() {
		if(StringUtils.isNotEmpty(loginTypeCode)) {
			return Constant.LOGIN_TYPE_CODE.getCnName(loginTypeCode);
		}
		return "";
	}

	public Date getLoginTimeFirst() {
		return loginTimeFirst;
	}

	public void setLoginTimeFirst(Date loginTimeFirst) {
		this.loginTimeFirst = loginTimeFirst;
	}
}
