package com.user.gps.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lvmama.comm.utils.DateUtil;

@JsonIgnoreProperties({"overDue", "zhUsed", "validTimeByCouponDefination", "overDueNew"})
public class MarkCouponCode implements Serializable {

	private Long couponCodeId;
	private Long couponId;
	private String couponCode;  // 优惠码,  couponCode = MarkCoupon.couponType + couponType.firstCode +n+couponid后四位;
	private String used = "false";
	private Date beginTime;
	private Date endTime;
	private Date createTime;
	private Date gotTime;
	private Long taskFileId;
    private Long m_dayAfter;
	private Long m_termOfValidity;
	private Date m_beginTime;
	private String validType;
	private String mobileNumber;
	private int couponNumbers;
	private Long userId;
	private Date updateTime;
	private String couponUserIds;  // mark_coupon_related_user 的主键ID的集合
	
	public Long getCouponCodeId() {
		return couponCodeId;
	}
	public void setCouponCodeId(Long couponCodeId) {
		this.couponCodeId = couponCodeId;
	}

	public Long getCouponId() {
		return couponId;
	}
	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}

	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode == null ? null : couponCode.trim();
	}

    public String getUsed() {
        return used;
    }
    public void setUsed(String used) {
        this.used = used == null ? null : used.trim();
    }

	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getCreateTime() {
		return createTime;
	}
	public void setCreatetime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getGotTime() {
		return gotTime;
	}
	public void setGotTime(Date gotTime) {
		this.gotTime = gotTime;
	}

	public Long getTaskFileId() {
		return taskFileId;
	}
	public void setTaskFileId(Long taskFileId) {
		this.taskFileId = taskFileId;
	}

	public Long getM_dayAfter() {
		return m_dayAfter;
	}
	public void setM_dayAfter(Long m_dayAfter) {
		this.m_dayAfter = m_dayAfter;
	}

	public Long getM_termOfValidity() {
		return m_termOfValidity;
	}
	public void setM_termOfValidity(Long m_termOfValidity) {
		this.m_termOfValidity = m_termOfValidity;
	}

	public Date getM_beginTime() {
		return m_beginTime;
	}
	public void setM_beginTime(Date m_beginTime) {
		this.m_beginTime = m_beginTime;
	}

	public String getValidType() {
		return validType;
	}
	public void setValidType(String validType) {
		this.validType = validType;
	}

    public String getMobileNumber() {
        return mobileNumber;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public int getCouponNumbers() {
        return couponNumbers;
    }
    public void setCouponNumbers(int couponNumbers) {
        this.couponNumbers = couponNumbers;
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCouponUserIds() {
        return couponUserIds;
    }
    public void setCouponUserIds(String couponUserIds) {
        this.couponUserIds = couponUserIds;
    }

    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

///////////////////////////////////////////////////////// customized getter method /////////////////////////////////////////////////////////

    /**
     * 优惠券号码是否过期
     * @return 是否过期
     * <p>判断此优惠券号码是否在有效时间之内,<code>true</code>代表过期，<code>false</code>代表未过期</p>
     */
    public boolean isOverDue() {
        if (null != beginTime && null != endTime) {
            Date now = new Date(System.currentTimeMillis());
            return !(now.after(beginTime) && now.before(endTime));
        }
        return true;
    }

    public String getZhUsed(){
        return "false".equals(this.used) ? "未使用" : "已使用";
    }


	public boolean isOverDueNew() {
		boolean result = true;
		if (StringUtils.isNotEmpty(validType)) {
			Date now = new Date(System.currentTimeMillis());
			Date startTime;
			Date endTime;
			if ("FIXED".equals(validType) && m_beginTime != null && m_termOfValidity != null) {
				endTime = DateUtil.getDateAfter2359Date(m_beginTime, m_termOfValidity.intValue());
				this.setEndTime(endTime);
				this.setBeginTime(m_beginTime);
				return !(now.after(m_beginTime) && now.before(endTime));
			}
			if ("UNFIXED".equals(validType)) {
				if (m_dayAfter != null && m_termOfValidity != null && createTime != null) {
					startTime = DateUtil.getDateAfter0000Date(createTime, m_dayAfter.intValue());
					endTime = DateUtil.getDateAfter2359Date(startTime, m_termOfValidity.intValue() - 1);
					this.setBeginTime(startTime);
					this.setEndTime(endTime);
					return !(now.after(startTime) && now.before(endTime));
				}
			}
			if ("GOT".equals(validType)) {
				if (m_dayAfter != null && m_termOfValidity != null && gotTime != null) {
					startTime = DateUtil.getDateAfter0000Date(gotTime, m_dayAfter.intValue());
					endTime = DateUtil.getDateAfter2359Date(startTime, m_termOfValidity.intValue() - 1);
					this.setBeginTime(startTime);
					this.setEndTime(endTime);
					return !(now.after(startTime) && now.before(endTime));
				}
			}
		}
		return result;
	}

}