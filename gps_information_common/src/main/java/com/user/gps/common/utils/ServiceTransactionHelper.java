package com.user.gps.common.utils;

/**
 * @author fengyonggang
 *
 */
public class ServiceTransactionHelper {

	private final static ThreadLocal<String> threadLocal = new ThreadLocal<>();
	
	public static void setTransactionId(String transactionId) {
		threadLocal.set(transactionId);
	}
	
	public static String getTransactionId() {
		return threadLocal.get();
	}
}
