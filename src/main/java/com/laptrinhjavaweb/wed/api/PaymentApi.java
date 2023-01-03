package com.laptrinhjavaweb.wed.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.model.Account;
import com.laptrinhjavaweb.model.Bill;
import com.laptrinhjavaweb.model.CT_Bill;
import com.laptrinhjavaweb.model.Card;
import com.laptrinhjavaweb.service.IAccountService;
import com.laptrinhjavaweb.service.IBillService;
import com.laptrinhjavaweb.service.ICT_BillService;
import com.laptrinhjavaweb.service.ICardService;
import com.laptrinhjavaweb.service.IFilePdfService;
import com.laptrinhjavaweb.utils.CreatePdf;
import com.laptrinhjavaweb.utils.SessionUtils;

/**
 * Servlet implementation class PaymentApi
 */
@WebServlet("/api-payment")
public class PaymentApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	IBillService bill;
	@Inject
	ICT_BillService ctbill;
	@Inject
	ICardService card;
	@Inject
	IAccountService accountService;
	@Inject
	IFilePdfService filePdfService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PaymentApi() {
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
		int idOfCompute = 0;
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/plain");
		String note = request.getParameter("note");
		String user = request.getParameter("user");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String tongtien = request.getParameter("tongtien");
		Account acount = (Account) SessionUtils.getInstance().getValue(request, "USERMODEL");
		List<Card> listCart = (List<Card>) SessionUtils.getInstance().getValue(request, "giohangs");
		Account ac = accountService.findbyId(acount.getId());

		Bill new_bill = null;
		int idBill = 0;
		// trang thai cua hang(ch giao)
		int idStatus = 1;
		boolean check = true;
		if (!listCart.isEmpty()) {
			if (acount != null) {
				new_bill = new Bill(acount.getId(), user, phone, tongtien, listCart.size(), address, note, idStatus);
				check = true;
			} else {
				new_bill = new Bill(idOfCompute, user, phone, tongtien, listCart.size(), address, note, idStatus);
				check = false;
			}
			idBill = bill.insertBill(new_bill);
			for (Card card : listCart) {
				CT_Bill ct_Bill = new CT_Bill(idBill, card.getId(), card.getName(), card.getImage(),
						String.valueOf(card.getPrice()), card.getCount());
				ctbill.insert_CT_Bill(ct_Bill);

			}
		}
		// TAO FILE PDF

		String path = "C:\\Users\\Admin\\Desktop\\Git-ATBM\\jsp-servlet-ltweb\\src\\main\\webapp\\Pdf\\";
		path+=idBill+"file.pdf";
		
		CreatePdf createPdf = new CreatePdf();
		createPdf.writeInvoice(path, filePdfService.findById(idBill));
		out.print(idBill);
		

	}

}
