package com.firmaRehber.service;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class AutharizationSuccessRedirect implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
	       Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
	        if (roles.contains("ROLE_ADMIN")) {
	    	    HttpSession session = request.getSession(true);
	    	    session.setAttribute("userName", authentication.getName());
	    	    session.setAttribute("role", "ROLE_ADMIN");
	            response.sendRedirect("/admin/siteGenel/");
	        }else if(roles.contains("ROLE_COMPANY")){
	    	    HttpSession session = request.getSession(true);
	    	    session.setAttribute("userName", authentication.getName());
	    	    session.setAttribute("role", "ROLE_COMPANY");
	        	response.sendRedirect("/firma/admin/");
	        }else if(roles.contains("ROLE_USER")){
	    	    HttpSession session = request.getSession(true);
	    	    session.setAttribute("userName", authentication.getName());
	    	    session.setAttribute("role", "ROLE_USER");
	        	response.sendRedirect("/");
	        }
	        
		
	}
	
	
	

}
