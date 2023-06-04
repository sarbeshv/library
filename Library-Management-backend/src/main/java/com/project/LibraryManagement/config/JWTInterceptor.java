package com.project.LibraryManagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;

import com.project.LibraryManagement.util.JwtUtils;

@Component
public class JWTInterceptor extends WebRequestHandlerInterceptorAdapter {

	public JWTInterceptor(WebRequestInterceptor requestInterceptor) {
		super(requestInterceptor);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private JwtUtils jwtUtils;

//	@Override
//	public boolean preHandle(jakarta.servlet.http.HttpServletRequest request,
//			jakarta.servlet.http.HttpServletResponse response, Object handler) throws Exception {
//
//		String auth = request.getHeader("authorization");
//		if (!(request.getRequestURI().contains("Login") || request.getRequestURI().contains("signup")
//				|| request.getRequestURI().contains("swagger-ui"))) {
//			jwtUtils.verify(auth);
//		}
//
//		return super.preHandle(request, response, handler);
//	}
//
}
