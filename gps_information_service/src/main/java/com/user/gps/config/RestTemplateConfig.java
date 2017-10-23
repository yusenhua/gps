package com.user.gps.config;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration  
public class RestTemplateConfig {  
  
  @Bean  
  public ClientHttpRequestFactory httpRequestFactory() {  
    return new HttpComponentsClientHttpRequestFactory(httpClient());  
  }  
  
  @Bean  
  public RestTemplate restTemplate() {  
    return new RestTemplate(httpRequestFactory());  
  }  
  
  @Bean  
  public HttpClient httpClient() {  
    Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory> create()  
        .register("http", PlainConnectionSocketFactory.getSocketFactory())  
        .register("https", SSLConnectionSocketFactory.getSocketFactory())  
        .build();  
    PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);  
    connectionManager.setMaxTotal(5);  
    connectionManager.setDefaultMaxPerRoute(5);  
  
    RequestConfig requestConfig = RequestConfig.custom()  
        .setSocketTimeout(8000)  
        .setConnectTimeout(8000)  
        .setConnectionRequestTimeout(8000)  
        .build();  
  
    return HttpClientBuilder.create()  
        .setDefaultRequestConfig(requestConfig)  
        .setConnectionManager(connectionManager)  
        .build();  
  }  
  
}  