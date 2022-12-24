package com.laptrinhjavaweb.admin.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.model.Bill;
import com.laptrinhjavaweb.model.CT_Bill;
import com.laptrinhjavaweb.service.IBillService;
import com.laptrinhjavaweb.service.ICT_BillService;

/**
 * Servlet implementation class Table_product
 */
@WebServlet("/api-table-bill")
public class Functions_Table_Bill extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	IBillService bill;
	@Inject
	ICT_BillService ct_bill;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Functions_Table_Bill() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String statusId = request.getParameter("statusId");

		List<Bill> list = bill.getAllByStatusID(statusId);

		printListOnTable(list, out);
		

	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain	");

		int id = Integer.parseInt(request.getParameter("id"));
		String code = request.getParameter("code");
		if (code.equals("morong")) {
			List<CT_Bill> list = ct_bill.getAllCTBillById(id);
			printListModalMoRong(list, out);
		} else if (code.equals("edit")) {
			int statusID = Integer.parseInt(request.getParameter("statusId"));
			boolean checkUpdateEdit = bill.updateBillById(id, statusID);

			if (checkUpdateEdit) {
				List<Bill> list = bill.getAllByStatusID(request.getParameter("statusId"));
				printListOnTable(list, out);

			}
		}

	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		bill.DeleteBillById(Integer.parseInt(id));

	}

	public void printListOnTable(List<Bill> list, PrintWriter out) {
		for (Bill bill : list) {
			out.println(" <tr>         \r\n" + "<td><div class=\"checkbox\">\r\n"
					+ "								<label><input type=\"checkbox\" name=\"check\"\r\n"
					+ "									value=\"" + bill.getId() + "\"></label>\r\n"
					+ "							</div></td>\r\n" + "      <td data-value="+bill.getId()+" class=\"id\">" + bill.getId()
					+ "</td>\r\n" + "      <td class=\"name\">" + bill.getUserName() + "</td>\r\n"
					+ "      <td class=\"phone\">" + bill.getPhone() + "</td>\r\n" + "      <td class=\"price\">"
					+ bill.getSumPrice() + "</td>\r\n" + "      <td class=\"sumcount\">" + bill.getSumCount()
					+ "</td>\r\n" + "      <td class=\"address\">" + bill.getAddress() + "</td>\r\n"
					+ "      <td data-value="+bill.getStatus()+" class=\"note\">" + bill.getNote() + "</td>\r\n" + "      <td class=\"date\">"
					+ bill.getDate() + "</td>\r\n" + "<td>"
					+ "<button class=\"submitMorong\" data-toggle=\"tooltip\"\r\n"
					+ "				title=\"Mo rong\">\r\n"
					+ "				<span><i class=\"fa fa-list-ul bigger-150\" aria-hidden=\"true\"></i></span>\r\n"
					+ "			</button>" + "</td>\r\n" + "    </tr>");
		}
	}

	public void printListModalMoRong(List<CT_Bill> list, PrintWriter out) {
		for (CT_Bill bill : list) {
			out.println(" <tr>\r\n" + 
					"									<td class=\"invert-name\" >"+bill.getName()+"</td>\r\n" + 
					"										<td style=\"width: 100px\" class=\"invert-image\"><img\r\n" + 
					"												src=\"uploads/"+bill.getImage()+"\" alt=\" \" class=\"img-responsive\"></td>\r\n" + 
					"										<td class=\"invert-price\" >"+bill.getPrice()+"</td>\r\n" + 
					"										<td class=\"invert-sl\" >"+bill.getCount()+"</td>\r\n" + 
					"										\r\n" + 
					"									</tr>");
		}
	}

}
