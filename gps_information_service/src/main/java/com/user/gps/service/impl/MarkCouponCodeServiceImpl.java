package com.user.gps.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.user.gps.dao.MarkCouponCodeDAO;
import com.user.gps.model.MarkCouponCode;
import com.user.gps.service.MarkCouponCodeService;

/**
 * MarkCouponCodeService implementation Created by yusenhua on 2017/2/22.
 */
@Service
public class MarkCouponCodeServiceImpl implements MarkCouponCodeService {

	private static final Log LOG = LogFactory.getLog(MarkCouponCodeServiceImpl.class);

	private MarkCouponCodeDAO markCouponCodeDAO;

	@Autowired
	public MarkCouponCodeServiceImpl(MarkCouponCodeDAO markCouponCodeDAO) {
		this.markCouponCodeDAO = markCouponCodeDAO;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Long> selectCouponIdByCouponCode(String couponCode) {

		return this.markCouponCodeDAO.selectCouponIdByCouponCode(couponCode);
	}

	@Override
	@Transactional(readOnly = true)
	public Integer selectRowCount(Map<String, Object> param) {
		return this.markCouponCodeDAO.selectRowCount(param);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MarkCouponCode> selectMarkCouponCodeByParam(Map<String, Object> param) {
		// TODO 参数最有couponId
		return this.markCouponCodeDAO.selectByParam(param);
	}

	@Override
	@Transactional(readOnly = false)
	public MarkCouponCode updateMarkCouponCode(MarkCouponCode markCouponCode, boolean changeCode) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("couponCode", markCouponCode.getCouponCode());
		List<Long> couponCodeIds = this.selectCouponIdByCouponCode(markCouponCode.getCouponCode());
		if (changeCode && couponCodeIds != null && !couponCodeIds.isEmpty() && couponCodeIds.size() != 0) {
			return null;
		} else {
			this.markCouponCodeDAO.updateByPrimaryKey(markCouponCode);
			LOG.info("S5263 interface=MarkCouponServiceImpl.updateMarkCouponCode(code,boolean) code="
					+ markCouponCode.getCouponCode() + "_" + markCouponCode.getUsed() + "_changeCode" + changeCode);
			return markCouponCode;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Integer selectMarkCouponCodeRowCount(final Map<String, Object> param) {
		return this.markCouponCodeDAO.selectRowCount(param);
	}

	@Override
	@Transactional(readOnly = false)
	public int saveMarkCouponCode(MarkCouponCode markCouponCode) {
		int result = this.markCouponCodeDAO.saveMarkCouponCode(markCouponCode);
		return result;
	}

	@Override
	@Transactional(readOnly = false)
	public int deleteByPrimaryKey(Long couponId, Long couponCodeId) {
		return this.markCouponCodeDAO.deleteByPrimaryKey(couponId, couponCodeId);
	}

	@Override
	@Transactional(readOnly = false)
	public int insertMarkCouponCodeBatch(List<MarkCouponCode> couponCodeList) {
		int result = this.markCouponCodeDAO.insertMarkCouponCodeBatch(couponCodeList);
		LOG.info("----------------insertMarkCouponCodeBatch");
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public MarkCouponCode selectByPrimaryKey(Long couponCodeId) {
		return this.markCouponCodeDAO.selectByPrimaryKey(couponCodeId);
	}

}
