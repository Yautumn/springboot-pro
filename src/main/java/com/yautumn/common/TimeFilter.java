package com.yautumn.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeFilter implements Filter{
	
	Logger logger = LoggerFactory.getLogger(TimeFilter.class);
	
	public void init(FilterConfig filterConfig) {
		logger.info("======初始化过滤器=======");
	}

	@Override
	public void destroy() {
		logger.info("======销毁过滤器=======");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		Long start = System.currentTimeMillis();
		filterChain.doFilter(request, response);
		logger.info("filter耗时："+(System.currentTimeMillis()-start));
	}

}
