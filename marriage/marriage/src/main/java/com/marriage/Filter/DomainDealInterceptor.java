package com.marriage.Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * node请求后台跨域问题解决拦截器
 * @author duxury
 * @version 2016年5月5日
 */
public class DomainDealInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		response.addHeader("Access-Control-Allow-Origin", "*"); // 允许跨域的url
		response.addHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS"); // 允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
		response.addHeader("Access-Control-Allow-Headers", "X-Requested-With,content-type,accept,rsp-auth,req-auth"); // 允许跨域的请求头
		response.addHeader("Access-Control-Max-Age", "3600");
		if("OPTIONS".equals(request.getMethod())){
			return false;
		}
		return true;
	}

}
