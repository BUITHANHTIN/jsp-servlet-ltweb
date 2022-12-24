package com.laptrinhjavaweb.utils;

import javax.servlet.http.HttpServletRequest;

public class SessionUtils {
	public static SessionUtils session = null;

	public static SessionUtils getInstance() {
		if (session == null) {
			session = new SessionUtils();
		}
		return session;
	}

	public void putValue(HttpServletRequest req, String key, Object value) {
		req.getSession().setAttribute(key, value);
	}

	public Object getValue(HttpServletRequest req, String key) {
		return req.getSession().getAttribute(key);
	}

	public void removeValue(HttpServletRequest req, String key) {
		req.getSession().removeAttribute(key);
		;
	}

}
