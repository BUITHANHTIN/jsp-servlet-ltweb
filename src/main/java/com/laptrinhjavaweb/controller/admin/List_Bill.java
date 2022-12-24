package com.laptrinhjavaweb.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.model.Status;
import com.laptrinhjavaweb.service.IStatusService;

/**
 * Servlet implementation class Table
 */
@WebServlet("/admin-table-bill")
public class List_Bill extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	IStatusService status;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public List_Bill() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Status> list = status.getAllStatusID();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/views/admin/list_bill.jsp").forward(request, response);
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
