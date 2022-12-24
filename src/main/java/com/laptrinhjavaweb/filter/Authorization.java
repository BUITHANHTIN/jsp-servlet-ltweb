package com.laptrinhjavaweb.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.model.Account;
import com.laptrinhjavaweb.utils.SessionUtils;



public class Authorization implements Filter {
	private ServletContext context;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.context = filterConfig.getServletContext();

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse rep = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		if (uri.startsWith(req.getContextPath() + "/admin")) {
			Account oneAccount = (Account) SessionUtils.getInstance().getValue(req, "USERMODEL");
			if (oneAccount != null) {
				if (oneAccount.getIsAdmin()==1) {
					chain.doFilter(request, response);
				} else if (oneAccount.getIsAdmin()==0) {
					rep.sendRedirect(
							req.getContextPath() + "/j_security_check?code=login&message=not_permission&alert-danger=alert");
				}

			} else {
				rep.sendRedirect(req.getContextPath() + "/j_security_check?code=login&message=not_login&alert-danger=alert");
			}

		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
