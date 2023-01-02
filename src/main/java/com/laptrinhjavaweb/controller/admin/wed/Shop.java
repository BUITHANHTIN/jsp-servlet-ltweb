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
import com.laptrinhjavaweb.model.Product;
import com.laptrinhjavaweb.service.IProductService;
import com.laptrinhjavaweb.utils.SessionUtils;

/**
 * Servlet implementation class Shop
 */
@WebServlet(urlPatterns = { "/shop" })
public class Shop extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	IProductService product;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Shop() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// hiển thi 3 ảnh
		int paging = 3;
		int endPage = 0;
		// có CateId hay k để hiện paging tương ứng
		int param = 0;
		String getAllPro = request.getParameter("getAllPro");
		String cateID = request.getParameter("code");

		String pagingID = request.getParameter("paging");

		request.setCharacterEncoding("utf-8");
		String search = request.getParameter("Search");

		List<Product> list = null;
		if (pagingID == null) {
			pagingID = "1";
		}
		if (getAllPro != null && getAllPro.equals("getAll")) {
			int count = product.countgetAllProduct();
			endPage = count / paging;
			if (count % paging != 0) {
				endPage++;
			}
			// paging k ràng buộc
			param = 1;

		} else if (search != null) {
			List<Product> listSearch = product.getAllBySearch(search);
			int count = listSearch.size();
			endPage = count / paging;
			if (count % paging != 0) {
				endPage++;
			}
			//// paging bởi search
			param = 2;
		} else {
			int count = product.countgetAllProduct(cateID);
			endPage = count / paging;
			if (count % paging != 0) {
				endPage++;
			}
			// paging bơi cateID
			param = 0;
		}
		list = product.getProducByPaging(Integer.parseInt(pagingID), paging, cateID);

		request.setAttribute("search", search);
		request.setAttribute("cateID", cateID);
		request.setAttribute("checkPaging", param);
		request.setAttribute("pagingID", pagingID);
		request.setAttribute("endPaging", endPage);
		request.setAttribute("list", list);

		List<Product> listGetTwo = product.getOneOrMoreSpecialDeals(2, cateID);
		request.setAttribute("listTwo", listGetTwo);
		if (SessionUtils.getInstance().getValue(request, "giohangs") != null) {
			List<Card> listSize = (List<Card>) SessionUtils.getInstance().getValue(request, "giohangs");
			request.setAttribute("listSize", listSize.size());
		}
		request.getRequestDispatcher("/views/client/shop.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
