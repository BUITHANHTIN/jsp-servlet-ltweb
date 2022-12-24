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
import com.laptrinhjavaweb.service.IBillService;
import com.laptrinhjavaweb.service.ICT_BillService;
import com.laptrinhjavaweb.service.ICardService;
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
		Bill new_bill = null;
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
			int idBill = bill.insertBill(new_bill);
			for (Card card : listCart) {
				CT_Bill ct_Bill = new CT_Bill(idBill, card.getId(), card.getName(), card.getImage(),
						String.valueOf(card.getPrice()), card.getCount());
				ctbill.insert_CT_Bill(ct_Bill);

			}
			SessionUtils.getInstance().removeValue(request, "giohangs");
			if (check) {
				card.DeleteCard(acount.getId());
			} else {
				card.DeleteCard(idOfCompute);
			}
			out.println("	<div class=\"shop_inner_inf\">\r\n" + "				<div class=\"privacy about\">\r\n"
					+ "					<h3>\r\n" + "						<span>Checkout</span>\r\n"
					+ "					</h3>\r\n" + "\r\n" + "					<div class=\"checkout-right\">\r\n"
					+ "						<h4>\r\n"
					+ "							Your shopping cart contains: <span>0 Products</span>\r\n"
					+ "						</h4>\r\n" + "						<table class=\"timetable_sub\">\r\n"
					+ "							<thead>\r\n" + "								<tr>\r\n"
					+ "									<th>Product</th>\r\n"
					+ "									<th>Quality</th>\r\n"
					+ "									<th>Product Name</th>\r\n"
					+ "									<th>Price</th>\r\n"
					+ "									<th>Remove</th>\r\n"
					+ "								</tr>\r\n" + "							</thead>\r\n" + "\r\n"
					+ "						</table>\r\n" + "					</div>\r\n"
					+ "					<div class=\"checkout-left\">\r\n"
					+ "						<div class=\"col-md-4 checkout-left-basket\">\r\n"
					+ "							<h4>Continue to basket</h4>\r\n"
					+ "							<ul>\r\n"
					+ "								<li>Product1 <i>-</i> <span>0</span></li>\r\n"
					+ "								<li>Product2 <i>-</i> <span>0 </span></li>\r\n"
					+ "								<li>Product3 <i>-</i> <span>0 </span></li>\r\n"
					+ "								<li>Total Service Charges <i>-</i> <span>0</span></li>\r\n"
					+ "								<li>Total <i>-</i> <span id=\"tongtien\">0</span></li>\r\n"
					+ "							</ul>\r\n" + "						</div>\r\n"
					+ "						<div class=\"col-md-8 address_form\">\r\n"
					+ "							<h4>Add a new Details</h4>\r\n"
					+ "							<form action=\"\" method=\"post\"\r\n"
					+ "								class=\"creditly-card-form agileinfo_form\">\r\n"
					+ "								<section class=\"creditly-wrapper wrapper\">\r\n"
					+ "									<div class=\"information-wrapper\">\r\n"
					+ "										<div class=\"first-row form-group\">\r\n"
					+ "											<div class=\"controls\">\r\n"
					+ "												<label class=\"control-label\">Full name: </label> <input\r\n"
					+ "													class=\"inputname billing-address-name form-control\"\r\n"
					+ "													type=\"text\" name=\"name\" placeholder=\"Full name\">\r\n"
					+ "											</div>\r\n"
					+ "											<div class=\"card_number_grids\">\r\n"
					+ "												<div class=\"card_number_grid_left\">\r\n"
					+ "													<div class=\"controls\">\r\n"
					+ "														<label class=\"control-label\">Mobile number:</label> <input\r\n"
					+ "															class=\"inputphone form-control\" type=\"number\"\r\n"
					+ "															placeholder=\"Mobile number\">\r\n"
					+ "													</div>\r\n"
					+ "												</div>\r\n"
					+ "												<div class=\"card_number_grid_right\">\r\n"
					+ "													<div class=\"controls\">\r\n"
					+ "														<label class=\"control-label\">Address: </label> <input\r\n"
					+ "															class=\"inputaddress form-control\" type=\"text\"\r\n"
					+ "															placeholder=\"Address\">\r\n"
					+ "													</div>\r\n"
					+ "												</div>\r\n"
					+ "												<div class=\"card_number_grid_right\">\r\n"
					+ "													<div class=\"controls\">\r\n"
					+ "														<label class=\"control-label\">Note: </label> <input\r\n"
					+ "															class=\"inputnote form-control\" type=\"text\"\r\n"
					+ "															placeholder=\"Note\">\r\n"
					+ "													</div>\r\n"
					+ "												</div>\r\n"
					+ "												<div class=\"clear\"></div>\r\n"
					+ "											</div>\r\n"
					+ "										</div>\r\n"
					+ "										<button id=\"payment\" class=\"submit check_out\">Delivery\r\n"
					+ "											to this Address</button>\r\n"
					+ "									</div>\r\n" + "								</section>\r\n"
					+ "							</form>\r\n" + "\r\n" + "						</div>\r\n" + "\r\n"
					+ "						<div class=\"clearfix\"></div>\r\n" + "\r\n" + "\r\n"
					+ "						<div class=\"clearfix\"></div>\r\n" + "					</div>\r\n"
					+ "				</div>\r\n" + "			</div>");
		}

	}

}
