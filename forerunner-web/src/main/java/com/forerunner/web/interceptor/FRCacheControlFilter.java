package com.forerunner.web.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FRCacheControlFilter implements Filter {
	public void init(FilterConfig config) {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		String uri = request.getRequestURI();

		if (uri.endsWith(".gif") || uri.endsWith(".png") || // 
				uri.endsWith(".css") || uri.endsWith(".js")) {

			response.addHeader("Cache-Control", "max-age=315360000");
			response.addDateHeader("Expires", System.currentTimeMillis() + 365L * 24 * 3600 * 1000);
		}

		filterChain.doFilter(request, response);
	}
}
