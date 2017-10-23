package com.user.gps.controller;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.gps.common.web.CouponApiUriTemplates;
import com.user.gps.common.web.RestApiException;
import com.user.gps.model.MarkCouponCode;
import com.user.gps.service.MarkCouponCodeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "coupon-code", description = "coupon-code")
@RequestMapping(CouponApiUriTemplates.V1 + "/coupon/code")
public class MarkCouponCodeController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MarkCouponCodeController.class);
	
	@Autowired
	private MarkCouponCodeService  markCouponCodeService;
	@ApiOperation("根据查询条件查询优惠券码的数量")
	@PostMapping("/selectMarkCouponCodeRowCount")
	public ResponseEntity<Integer> selectMarkCouponCodeRowCount(@RequestBody Map<String,Object> param){		
		Integer result = 0;
		try{
			result = this.markCouponCodeService.selectRowCount(param);
		}catch (Exception e) {
			LOGGER.error("selectMarkCouponCodeRowCount====failed===",e);
			throw new RestApiException(HttpStatus.EXPECTATION_FAILED,"-1",e.getMessage());
		}
		
		return new ResponseEntity<Integer>(result,HttpStatus.OK) ;
	}
	
	@ApiOperation("根据查询条件查询优惠券码")
	@PostMapping("/selectMarkCouponCodeByParam")
	public ResponseEntity<List<MarkCouponCode>> selectMarkCouponCodeByParam(@RequestBody Map<String, Object> param){
		List<MarkCouponCode> result =  new ArrayList<>();
		try{			
			result = this.markCouponCodeService.selectMarkCouponCodeByParam(param);
		}catch (Exception e) {
			LOGGER.error("selectMarkCouponCodeByParam====failed===",e);
			throw new RestApiException(HttpStatus.EXPECTATION_FAILED,"-1",e.getMessage());
		}		
		return new ResponseEntity<List<MarkCouponCode>>(result, HttpStatus.OK);
	}
	
	
	
	
	
	
}
