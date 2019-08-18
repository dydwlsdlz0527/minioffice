package com.minioffice.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter {

	private String encoding;
	
	public CharacterEncodingFilter() {
		System.out.println("MiniOffice:characterEncodingFiler() 생성자 호출됨");
	}
	
	public void destroy() {
		System.out.println("MiniOffice:characterEncodingFiler() destoryed");
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("MiniOffice:doFilter()");
		request.setCharacterEncoding(encoding);
		chain.doFilter(request, response);
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("CharacterEncodingFilter의 init()호출됨");
		encoding = fConfig.getInitParameter("encoding");
	}
}
