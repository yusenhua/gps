package com.user.gps.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 *  分布式自增ID
 * @author wufahang
 *
 */
@Configuration
@PropertySource("classpath:const.properties")
public class IdWorker {

	  private static final long twepoch = 1288834974678L;
	  private static final long workerIdBits = 3L;
	  private static final long datacenterIdBits = 1L;
	  private static final long maxWorkerId = -1L ^ (-1L <<  workerIdBits);
	  private static final long maxDatacenterId = -1L ^ (-1L <<  datacenterIdBits);
	  private static final long sequenceBits = 4L;
	  private static final long workerIdShift =  sequenceBits;
	  private static final long datacenterIdShift =  sequenceBits +  workerIdBits;
	  private static final long timestampLeftShift =  sequenceBits +  workerIdBits +  datacenterIdBits;
	  private static final long sequenceMask = -1L ^ (-1L <<  sequenceBits);	
	  private static long workerId;
	  private static long datacenterId;
	  private static long sequence = 0L;
	  private static long lastTimestamp = -1L;
	 
	 
	 
	  public static synchronized long nextId() {
	    long timestamp = timeGen();
	    if (timestamp <  lastTimestamp) {
	      throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",  lastTimestamp - timestamp));
	    }
	    if ( lastTimestamp == timestamp) {
	       sequence = ( sequence + 1) &  sequenceMask;
	      if ( sequence == 0) {
	        timestamp = tilNextMillis( lastTimestamp);
	      }
	    } else {
	       sequence = 0L;
	    }
	 
	     lastTimestamp = timestamp;
	 
	    return ((timestamp -  twepoch) <<  timestampLeftShift) | ( datacenterId <<  datacenterIdShift) | ( workerId <<  workerIdShift) |  sequence;
	  }
	 
	  private static long tilNextMillis(long lastTimestamp) {
	    long timestamp = timeGen();
	    while (timestamp <= lastTimestamp) {
	      timestamp = timeGen();
	    }
	    return timestamp;
	  }
	 
	  private static long timeGen() {
	    return System.currentTimeMillis();
	  }

	@Value("${coupon.idworker.workerid}")
	public  void setWorkerId(long workerId) {
		IdWorker.workerId = workerId;
	}
	
      
}
