package com.laptrinhjavaweb.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.model.Category;
import com.laptrinhjavaweb.service.ICategoryService;

/**
 * Servlet implementation class Table
 */
@WebServlet("/admin-table-product")
public class List_Product extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	ICategoryService cate;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public List_Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Category> list = cate.getAllCateID();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/views/admin/list_product.jsp").forward(request, response);
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
