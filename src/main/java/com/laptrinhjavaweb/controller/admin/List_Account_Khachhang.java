package com.laptrinhjavaweb.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.model.Account;
import com.laptrinhjavaweb.service.IAccountService;

/**
 * Servlet implementation class Table
 */
@WebServlet("/admin-table-account-khachhang")
public class List_Account_Khachhang extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	IAccountService accountService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public List_Account_Khachhang() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int isAdmin = Integer.parseInt(request.getParameter("code"));
		List<Account> list = accountService.listAccount(isAdmin);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/views/admin/list_account_khachhang.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
