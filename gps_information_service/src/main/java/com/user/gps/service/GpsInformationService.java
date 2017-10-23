package com.user.gps.service;

import java.util.List;
import java.util.Map;

import com.user.gps.model.GpsInformation;

public interface GpsInformationService {
	
	 int saveGpsInformation(GpsInformation gpsInformation);
	 
	 List<GpsInformation> queryList(Map<String,Object>map);
	 
	 GpsInformation selectByUserId(Long userId);
	
	

}
