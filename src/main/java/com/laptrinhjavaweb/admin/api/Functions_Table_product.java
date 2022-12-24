package com.laptrinhjavaweb.admin.api;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.laptrinhjavaweb.model.Product;
import com.laptrinhjavaweb.service.IProductService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
@WebServlet("/api-table-product")
public class Functions_Table_product extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	IProductService product;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Functions_Table_product() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String cateID = request.getParameter("cateId");

		ServletContext servletContext = this.getServletConfig().getServletContext();
		String path = servletContext.getRealPath("/uploads");

		List<Product> list = null;

		list = product.getAllByCateID(cateID);

		printListOnTable(list, out, path);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain	");

		ServletContext servletContext = this.getServletConfig().getServletContext();
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);

		// tao uploads
		String path = servletContext.getRealPath("/uploads");
		if (!Files.exists(Paths.get(path))) {		// ko tồn tại
			Files.createDirectory(Paths.get(path));		// tạo file uploads
		}

		Product p = new Product();
		String code = "";
		boolean check = true;
		boolean checkImageEdit = true;
		try {

			List<FileItem> items = servletFileUpload.parseRequest(request);
			for (FileItem Item : items) {
				if (Item.getFieldName().equals("code")) {
					code = Item.getString();
				} else if (Item.getFieldName().equals("price")) {
					p.setPrice(Double.parseDouble(Item.getString()));
				} else if (Item.getFieldName().equals("name")) {
					p.setName(Item.getString());
				} else if (Item.getFieldName().equals("title")) {
					p.setTitle(Item.getString());
				} else if (Item.getFieldName().equals("categoryId")) {
					p.setCateID(Integer.parseInt(Item.getString()));
				} else if (Item.getFieldName().equals("id")) {
					p.setId(Integer.parseInt(Item.getString()));
				} else if (Item.getFieldName().equals("description")) {
					p.setDescription(Item.getString());
				} else if (Item.getFieldName().equals("SLHangTon")) {
					p.setSLHangTon(Integer.parseInt(Item.getString()));
				} else if (Item.getFieldName().equals("image")) {
					if (Item.getSize() > 0) {// neu co file
						String originalFileName = Item.getName();

						p.setImage(originalFileName);

						if (product.countImage(originalFileName) > 0) {
							// đã tồn tai ảnh trong db
							check = true;

						} else {
							File file = new File(path + "/" + originalFileName);
							Item.write(file);		// viết file lên upload
							check = false;
						}

					}
				} else if (Item.getFieldName().equals("getImage")) {

					String originalFileName = Item.getString();

					p.setImage(originalFileName);
					if (product.countImage(originalFileName) > 0) {
						// đã tồn tai ảnh trong db
						check = true;

					} else {
						File file = new File(path + "/" + originalFileName);
						Item.write(file);
						check = false;
					}
				}

			}

		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (check) {
			if (code.equals("edit")) {
				// xét ảnh đó là ảnh cũ hay ảnh đã tồn tại trong table rr
				if (product.countByImageAndId(p.getImage(), p.getId()) > 0) {
					checkImageEdit = true;
				} else {
					checkImageEdit = false;
				}

				if (checkImageEdit) {
					boolean checkUpdateEdit = product.updateProductById(p);

					if (checkUpdateEdit) {
						List<Product> list = product.getAllByCateID(String.valueOf(p.getCateID()));
						printListOnTable(list, out, path);
					}
				} else {
					int fail = 0;
					out.println(fail); 
				}

			} else if (code.equals("add")) {
				int fail = 0;
				out.println(fail);// đã tồn tại

			}
		} else {
			if (code.equals("add")) { // ko tồn tại viết bình thường

				boolean checkAddProduct = product.addOneProduct(p);

				if (checkAddProduct) {
					List<Product> list = product.getAllByCateID(String.valueOf(p.getCateID()));

					printListOnTable(list, out, path);
				}

			} else if (code.equals("edit")) {
				boolean checkUpdateEdit = product.updateProductById(p);

				if (checkUpdateEdit) {
					List<Product> list = product.getAllByCateID(String.valueOf(p.getCateID()));
					printListOnTable(list, out, path);
				}
			}

		}

	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");			// lấy id
		boolean checkDelete = product.DeleteProductById(Integer.parseInt(id));			// xóa id	
		PrintWriter out = response.getWriter();
		out.println(checkDelete);

	}

	public void printListOnTable(List<Product> list, PrintWriter out, String path) {
		for (Product product : list) {

			String localhostImage = "http://localhost:8080/do-an-cuoi-ki/uploads/" + product.getImage();

			out.println("<tr>\r\n" + "						<td><div class=\"checkbox\">\r\n"
					+ "								<label><input type=\"checkbox\" name=\"check\" value=\""
					+ product.getId() + "\"></label>\r\n" + "							</div></td>\r\n"
					+ "						<td>" + product.getId() + "</td>\r\n"
					+ "						<td class=\"name\">" + product.getName() + "</td>\r\n"
					+ "						<td class=\"invert-image\" style=\"width:20%\" data-value='"
					+ localhostImage + "' >\r\n" + "						<a>\r\n"
					+ "						<img style=\"width:50%\"  alt=\"\" class=\"img-responsive\" src='uploads/"
					+ product.getImage() + "'>\r\n" + "						</a>\r\n"
					+ "						</td>\r\n" + "						<td class=\"price\">"
					+ product.getPrice() + "</td>\r\n" + "						<td data-value=\"" + product.getCateID()
					+ "\" class=\"description\">" + product.getDescription() + "</td>\r\n"
					+ "						<td class=\"slht\">" + product.getSLHangTon() + "</td>\r\n" + "</tr>");
		}
	}

}
