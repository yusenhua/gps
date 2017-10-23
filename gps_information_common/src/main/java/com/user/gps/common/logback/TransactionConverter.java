/**
 * 
 */
package com.user.gps.common.logback;

import com.user.gps.common.utils.ServiceTransactionHelper;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * @author fengyonggang
 *
 */
public class TransactionConverter extends ClassicConverter {

	private static final String TRANSACTIONID = "TransactionId";
	
	@Override
	public String convert(ILoggingEvent event) {
		String transactionId = ServiceTransactionHelper.getTransactionId();
		if(transactionId == null) 
			transactionId = TRANSACTIONID;
		return transactionId;
	}
	
}
