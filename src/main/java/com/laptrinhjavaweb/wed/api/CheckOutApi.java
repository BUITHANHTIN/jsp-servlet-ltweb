package com.laptrinhjavaweb.wed.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.model.Account;
import com.laptrinhjavaweb.model.Card;
import com.laptrinhjavaweb.service.ICardService;
import com.laptrinhjavaweb.service.IProductService;
import com.laptrinhjavaweb.utils.SessionUtils;

@WebServlet(urlPatterns = { "/api-checkout" })
public class CheckOutApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	IProductService product;
	@Inject
	ICardService card;

	public CheckOutApi() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/plain");
		String id = request.getParameter("id");
		int idOfCompute = 0;
		List<Card> listCart = (List<Card>) SessionUtils.getInstance().getValue(request, "giohangs");
		Account acount = (Account) SessionUtils.getInstance().getValue(request, "USERMODEL");
		if (SessionUtils.getInstance().getValue(request, "giohangs") != null) {
			int vitri = kiemTraSanPhamDaTonTai(request, Integer.parseInt(id));
			listCart.remove(vitri);
			if (acount != null) {
				card.DeleteCardByIdProduct(acount.getId(), Integer.parseInt(id));
			} else {
				card.DeleteCardByIdProduct(idOfCompute, Integer.parseInt(id));
			}
			if (listCart.size() == 0) {
				SessionUtils.getInstance().removeValue(request, "giohangs");
			}

		}
		out.println(listCart.size());

	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		Card newCart = mapper.readValue(request.getInputStream(), Card.class);
		Account acount = (Account) SessionUtils.getInstance().getValue(request, "USERMODEL");
		if (SessionUtils.getInstance().getValue(request, "giohangs") != null) {
			List<Card> listCart = (List<Card>) SessionUtils.getInstance().getValue(request, "giohangs");
			int vitri = kiemTraSanPhamDaTonTai(request, newCart.getId());
			listCart.get(vitri).setCount(newCart.getCount());
			// insert user
			checkLoginUserToUpdateCount(newCart.getCount(), newCart, card, acount);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//lay data Card dc gui tu ajax
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/plain");
		Card newCart = mapper.readValue(request.getInputStream(), Card.class);

		Account acount = (Account) SessionUtils.getInstance().getValue(request, "USERMODEL");
		if (SessionUtils.getInstance().getValue(request, "giohangs") == null) {
			ArrayList<Card> list = new ArrayList<>();
			list.add(newCart);
			// insert user
			SessionUtils.getInstance().putValue(request, "giohangs", list);
			checkLoginUserToAdd(acount, newCart, card);
		} else {
			int vitri = kiemTraSanPhamDaTonTai(request, newCart.getId());
			if (vitri == -1) {
				List<Card> listCart = (List<Card>) SessionUtils.getInstance().getValue(request, "giohangs");
				listCart.add(newCart);
				// insert user
				checkLoginUserToAdd(acount, newCart, card);
			} else {
				List<Card> listCart = (List<Card>) SessionUtils.getInstance().getValue(request, "giohangs");
				int soluongmoi = listCart.get(vitri).getCount() + 1;
				listCart.get(vitri).setCount(soluongmoi);
				// update count
				checkLoginUserToUpdateCount(soluongmoi, newCart, card, acount);

			}
		}
		List<Card> listCart = (List<Card>) SessionUtils.getInstance().getValue(request, "giohangs");
		PrintWriter out = response.getWriter();
		out.print(listCart.size());

	}

	private void checkLoginUserToUpdateCount(int soluongmoi, Card newCart, ICardService card, Account acount) {
		if (acount != null) {
			newCart.setIdAccount(acount.getId());
			card.UpdateCardByIdProduct(soluongmoi, newCart.getIdAccount(), newCart.getId());
		} else {
			newCart.setIdAccount(0);
			card.UpdateCardByIdProduct(soluongmoi, newCart.getIdAccount(), newCart.getId());
		}

	}

	private void checkLoginUserToAdd(Account acount, Card newCart, ICardService card) {
		if (acount != null) {
			newCart.setIdAccount(acount.getId());
			card.InsertCard(newCart);
		} else {
			newCart.setIdAccount(0);
			card.InsertCard(newCart);
		}

	}

	private int kiemTraSanPhamDaTonTai(HttpServletRequest request, int id) {
		List<Card> listCart = (List<Card>) SessionUtils.getInstance().getValue(request, "giohangs");
		for (int i = 0; i < listCart.size(); i++) {
			if (listCart.get(i).getId() == id) {
				return i;
			}

		}

		return -1;

	}

}
