package com.spring.poc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestInterceptor extends HandlerInterceptorAdapter {
	private static final Logger LOGGER = LoggerFactory.getLogger(RequestInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		LOGGER.debug("Interceptor - preHandle Called to intercept request param");
		// request.setAttribute("blogName", "SpringBoot");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		LOGGER.debug("Interceptor - postHandle Called to intercept modelAndView param");
		// String blogName = (String) request.getAttribute("blogName");
		// modelAndView.addObject("blogName", blogName);
		// modelAndView.addObject("authorName", "Jack");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		LOGGER.debug("Interceptor - afterCompletion Called to inspect request /response param");
		/*
		 * String blogName = (String) request.getAttribute("blogName"); String
		 * authorName = (String) request.getAttribute("authorName");
		 * System.out.println("Request URL::" +
		 * request.getRequestURL().toString());
		 * System.out.println("Blog name : " + blogName);
		 * System.out.println("Author Name : " + authorName);
		 */
	}
}
