package com.laptrinhjavaweb.controller.wed;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.model.Account;
import com.laptrinhjavaweb.model.Card;
import com.laptrinhjavaweb.model.Category;
import com.laptrinhjavaweb.service.ICardService;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.utils.SessionUtils;

@WebServlet(urlPatterns = { "/trangchu" })
public class Trangchu extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@Inject
	ICategoryService cate;
	@Inject
	ICardService card;

	public Trangchu() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// id computer=0
		int idOfCompute = 0;
		if ((Account) SessionUtils.getInstance().getValue(request, "USERMODEL") != null) {
			SessionUtils.getInstance().removeValue(request, "giohangs");
			Account oneAccount = (Account) SessionUtils.getInstance().getValue(request, "USERMODEL");
			List<Card> listCard = card.getAllbyAccountId(oneAccount.getId());
			if (!listCard.isEmpty()) {
				SessionUtils.getInstance().putValue(request, "giohangs", listCard);
				List<Card> listSize = (List<Card>) SessionUtils.getInstance().getValue(request, "giohangs");
				request.setAttribute("listSize", listSize.size());
			}
			List<Category> list = cate.getAllCateID();
			request.setAttribute("list", list);
			request.getRequestDispatcher("/views/client/index.jsp").forward(request, response);
		} else {
			List<Category> list = cate.getAllCateID();
			request.setAttribute("list", list);
			List<Card> listCard = card.getAllbyAccountId(idOfCompute);
			if (!listCard.isEmpty()) {
				SessionUtils.getInstance().putValue(request, "giohangs", listCard);
				List<Card> listSize = (List<Card>) SessionUtils.getInstance().getValue(request, "giohangs");
				request.setAttribute("listSize", listSize.size());
			}

			request.getRequestDispatcher("/views/client/index.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
