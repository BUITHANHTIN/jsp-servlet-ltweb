package com.laptrinhjavaweb.wed.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.controller.wed.Key;
import com.laptrinhjavaweb.model.Account;
import com.laptrinhjavaweb.model.Card;
import com.laptrinhjavaweb.service.IAccountService;
import com.laptrinhjavaweb.service.IBillService;
import com.laptrinhjavaweb.service.ICT_BillService;
import com.laptrinhjavaweb.service.ICardService;
import com.laptrinhjavaweb.utils.SessionUtils;

/**
 * Servlet implementation class PaymentApi
 */
@WebServlet("/api-verify")
public class VerifyApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	IBillService bill;
	@Inject
	ICT_BillService ctbill;
	@Inject
	ICardService card;
	@Inject
	IAccountService accountService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VerifyApi() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/plain");
		String verify = request.getParameter("verify").trim();
		Account acount = (Account) SessionUtils.getInstance().getValue(request, "USERMODEL");
		List<Card> listCart = (List<Card>) SessionUtils.getInstance().getValue(request, "giohangs");
		Account ac = accountService.findbyId(acount.getId());
		int re = 0;
		try {
			byte[] m = Base64.getDecoder().decode(verify);
			re = Key.verify(Key.publicKeyFromBase64(ac.getPublicKey()), m);
		} catch (InvalidKeyException | NoSuchAlgorithmException | SignatureException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		if (re == 0) {
			out.print(0);
		} else if(re==1) {
			out.print(1);
			SessionUtils.getInstance().removeValue(request, "giohangs");
			card.DeleteCard(acount.getId());
		}

	}

}
