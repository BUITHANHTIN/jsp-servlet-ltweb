package com.laptrinhjavaweb.admin.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.model.Account;
import com.laptrinhjavaweb.service.IAccountService;

@WebServlet("/api-table-accountKH")
public class Functions_Table_AccoutKH extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	IAccountService accountService;

	public Functions_Table_AccoutKH() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain	");

		String code = request.getParameter("code");
		String name = request.getParameter("name");
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");

		Account ac = new Account();
		ac.setPass(pass);
		ac.setUser(user);
		ac.setUsername(name);

		if (code.equals("add")) {
			// button add
			// isAdmin=0
			int isAdmin = 0;
			ac.setIsAdmin(isAdmin);
			long keyId = accountService.InsertAccount(ac);
			out.println(keyId);

		} else if (code.equals("edit")) {
			// button edit
			int idEdit = Integer.parseInt(request.getParameter("id"));
			ac.setId(idEdit);
			accountService.updateAccount(ac);

		}

	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		boolean checkDelete = accountService.deleteAccount(Integer.parseInt(id));
		PrintWriter out = response.getWriter();
		out.println(checkDelete);

	}

}
