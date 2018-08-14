package com.yautumn.configuration;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.yautumn.common.ServletTest;
import com.yautumn.common.TimeFilter;

@Configuration
public class WebConfig {
	
	Logger logger = LoggerFactory.getLogger(WebConfig.class);
	
	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters() {
		 FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
		 FastJsonConfig fastJsonConfig = new FastJsonConfig();
		 fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
		 fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
		 HttpMessageConverter<?> converter = fastJsonHttpMessageConverter;
		 return new HttpMessageConverters(converter);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		return new ServletRegistrationBean(new ServletTest(),"/servlet");
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		TimeFilter timeFilter = new TimeFilter();
		registrationBean.setFilter(timeFilter);
		
		List<String> urls = new ArrayList<>();
		urls.add("/*");
		registrationBean.setUrlPatterns(urls);
		
		return registrationBean;
	}

}
