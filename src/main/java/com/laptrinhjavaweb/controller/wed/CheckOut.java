package com.laptrinhjavaweb.controller.wed;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.model.Card;
import com.laptrinhjavaweb.service.ICardService;
import com.laptrinhjavaweb.utils.SessionUtils;

/**
 * Servlet implementation class CheckOut
 */
@WebServlet("/checkout")
public class CheckOut extends HttpServlet {
	@Inject
	ICardService card;
	private static final long serialVersionUID = 1L;

	public CheckOut() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Card> listCart = (List<Card>) SessionUtils.getInstance().getValue(request, "giohangs");
		if (SessionUtils.getInstance().getValue(request, "giohangs") != null) {
			request.setAttribute("listSize", listCart.size());
		}
		request.getRequestDispatcher("/views/client/checkout.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
