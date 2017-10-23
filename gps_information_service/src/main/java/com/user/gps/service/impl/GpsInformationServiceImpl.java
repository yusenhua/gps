package com.user.gps.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.gps.dao.GpsInformationDAO;
import com.user.gps.model.GpsInformation;
import com.user.gps.service.GpsInformationService;


@Service
public class GpsInformationServiceImpl implements GpsInformationService{
	
	@Autowired
	private GpsInformationDAO dao;
	
	@Override
	public int saveGpsInformation(GpsInformation gpsInformation) {
		return dao.insert(gpsInformation);
	}

	@Override
	public List<GpsInformation> queryList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GpsInformation selectByUserId(Long userId) {
		return dao.selectByUserId(userId);
	}

}
