package com.user.gps.controller;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.user.gps.common.web.CouponApiUriTemplates;
import com.user.gps.common.web.RestApiException;
import com.user.gps.model.GpsInformation;
import com.user.gps.service.GpsInformationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "gps_information", description = "gps_information")
@RequestMapping(CouponApiUriTemplates.V1 + "/gps/information")
public class GpsInformationController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GpsInformationController.class);
	
	@Autowired
	private GpsInformationService  gpsInformationService;
	
	@ApiOperation("save gpsInformation")
	@PostMapping("/")
	public ResponseEntity<Integer> saveMarkCoupon(@RequestBody GpsInformation gpsInformation){
		int rs =0;
		try{
			rs = this.gpsInformationService.saveGpsInformation(gpsInformation);
		}catch (Exception e) {
			LOGGER.error("insert=====failed====", e);
			throw new RestApiException(HttpStatus.EXPECTATION_FAILED,"-1",e.getMessage());
		}
		return new ResponseEntity<>(rs, HttpStatus.OK);
	}
	
	@ApiOperation("get gpsInformation by userId")
	@GetMapping("/")
	public ResponseEntity<GpsInformation> getMarkCouponCodeByCouponIdAndCode(
			@RequestParam(required=false,value="userId") String userId){
		GpsInformation gpsInformation = new GpsInformation();
		try{
			Long userIdReal = null;
			if(StringUtils.isNotBlank(userId) && !userId.equals("userId")){
				userIdReal = Long.valueOf(userId);
			}			
			gpsInformation = this.gpsInformationService.selectByUserId(userIdReal);
		}catch (Exception e) {
			LOGGER.error("getMarkCouponCodeByCouponIdAndCode=====failed", e);
			throw new RestApiException(HttpStatus.EXPECTATION_FAILED,"-1",e.getMessage());
		}
		return new ResponseEntity<GpsInformation>(gpsInformation,HttpStatus.OK);
	}
	
	
	
	
	
	
	
}
