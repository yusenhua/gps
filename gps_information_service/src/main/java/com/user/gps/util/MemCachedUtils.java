/**
 * 
 */
package com.user.gps.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.danga.MemCached.MemCachedClient;

/**
 * @author wufh
 *
 */
@Service
public class MemCachedUtils {

	private static MemCachedClient memCachedClient;
	
	@Autowired
	@Qualifier("memCachedClient")
	public void setMemCachedClient(MemCachedClient memCachedClient) {
		MemCachedUtils.memCachedClient = memCachedClient;
	}
	
	
	
	public static void delete(String key) {
		if(memCachedClient != null) 
			memCachedClient.delete(key);
	}
	
	public static void set(String key, Object value) {
		if(memCachedClient != null) 
			memCachedClient.set(key, value);
	}
	
	public static Object get(String key) {
		if(memCachedClient == null) 
			return null;
		return memCachedClient.get(key);
	}
	
	public static void set(String key, Object value, Integer time) {
		if(memCachedClient != null)
			memCachedClient.set(key, value,new Date(1000*time));
	}
	
	public static boolean  add(String key, Object value, Integer time) {
		if(memCachedClient != null)
			return memCachedClient.add(key, value,new Date(1000*time));
		return false ;
	}
	
	

}
