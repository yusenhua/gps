/**
 * 
 */
package com.user.gps.config;

import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.user.gps.common.utils.ServiceTransactionHelper;

/**
 * @author fengyonggang
 *
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**");
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addWebRequestInterceptor(serviceTransactionInterceptor());
	}
	
	@Bean
	public WebRequestInterceptor serviceTransactionInterceptor() {
		return new WebRequestInterceptor() {
			@Override
			public void preHandle(WebRequest request) throws Exception {
				String transactionId = request.getHeader("AppTransactionId");
				if(transactionId == null)
					transactionId = UUID.randomUUID().toString();
				ServiceTransactionHelper.setTransactionId(transactionId);
			}
			@Override
			public void postHandle(WebRequest request, ModelMap model) throws Exception {

			}
			@Override
			public void afterCompletion(WebRequest request, Exception ex) throws Exception {
			}
		};
	}

}
