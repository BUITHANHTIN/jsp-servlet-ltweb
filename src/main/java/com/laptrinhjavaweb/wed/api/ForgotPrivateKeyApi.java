package com.laptrinhjavaweb.wed.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.model.Account;
import com.laptrinhjavaweb.service.IAccountService;
import com.laptrinhjavaweb.utils.SessionUtils;

@WebServlet(urlPatterns = { "/api-forgotprivate" })
public class ForgotPrivateKeyApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	IAccountService accountService;

	public ForgotPrivateKeyApi() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/plain");

		Account acount = (Account) SessionUtils.getInstance().getValue(request, "USERMODEL");
		

	}

}
