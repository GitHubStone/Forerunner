package com.forerunner.web.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.EnumerationUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 拦截所有请求
 * @author GQ
 */
@Component("frContextFilter")
public class FRContextFilter extends OncePerRequestFilter implements Filter {
	Log log = LogFactory.getLog(getClass());

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		logReqInfo(request);

		if (log.isDebugEnabled()) {
			System.out.println();//FIXME for debug
		}
	}

	/**
	 * 记录请求信息
	 */
	private void logReqInfo(HttpServletRequest request) {
		if (log.isInfoEnabled()) {
			StringBuffer info = new StringBuffer();

			info.append("[user:" + request.getRemoteUser() + "]");
			info.append("[" + request.getMethod() + " " + request.getProtocol() + " " + request.getRequestURI() + "]");
			info.append("[args:" + EnumerationUtils.toList(request.getParameterNames()) + "]");

			log.info(info.toString());
		}
	}
}