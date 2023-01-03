package com.laptrinhjavaweb.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import com.laptrinhjavaweb.model.FilePdf;

public class CreatePdf {
	PDDocument invc;
	int n;
	Integer total = 0;
	Integer price;

	String name;
	String phone;
	String sumPrice;
	String address;
	List<String> listNameProduct = new ArrayList<>();
	List<String> listPriceProduct = new ArrayList<>();
	List<String> listCountProduct = new ArrayList<>();
	String invoiceTitle = new String("BoardStore Invoice");
	String subTitle = new String("Invoice");
	PDFont font;

	// Using the constructor to create a PDF with a blank page
	public CreatePdf(String pathTtf) throws IOException {
		// Create Document
		invc = new PDDocument();
		// Create Blank Page
		PDPage newpage = new PDPage();
		// Add the blank page
		invc.addPage(newpage);

		font = PDType0Font.load(invc, new File(pathTtf));
	}


	public void writeInvoice(String filePath, FilePdf pdf) {
		name = pdf.getName();
		phone = pdf.getPhone();
		sumPrice = pdf.getSumPrice();
		address = pdf.getAddress();
		// get the page
		PDPage mypage = invc.getPage(0);
		try {
			// Prepare Content Stream
			PDPageContentStream cs = new PDPageContentStream(invc, mypage);

			// Writing Single Line text
			// Writing the Invoice title
			cs.beginText();
			cs.setFont(font, 20);
			cs.newLineAtOffset(140, 750);
			cs.showText(invoiceTitle);
			cs.endText();

			cs.beginText();
			cs.setFont(font, 18);
			cs.newLineAtOffset(270, 690);
			cs.showText(subTitle);
			cs.endText();

			// Writing Multiple Lines
			// writing the customer details
			cs.beginText();
			cs.setFont(font, 14);
			cs.setLeading(20f);
			cs.newLineAtOffset(60, 610);
			cs.showText("Customer Name: ");
			cs.newLine();
			cs.showText("Phone Number: ");
			cs.newLine();
			cs.showText("Address: ");
			cs.endText();

			cs.beginText();
			cs.setFont(font, 14);
			cs.setLeading(20f);
			cs.newLineAtOffset(170, 610);
			cs.showText(name);
			cs.newLine();
			cs.showText(phone);
			cs.newLine();
			cs.showText(address);
			cs.endText();

			cs.beginText();
			cs.setFont(font, 14);
			cs.newLineAtOffset(80, 540);
			cs.showText("Product Name");
			cs.endText();

			cs.beginText();
			cs.setFont(font, 14);
			cs.newLineAtOffset(200, 540);
			cs.showText(" Price");
			cs.endText();

			cs.beginText();
			cs.setFont(font, 14);
			cs.newLineAtOffset(310, 540);
			cs.showText("Count");
			cs.endText();

//			cs.beginText();
//			cs.setFont(font, 14);
//			cs.newLineAtOffset(410, 540);
//			cs.showText("Price");
//			cs.endText();

			cs.beginText();
			cs.setFont(font, 12);
			cs.setLeading(20f);
			cs.newLineAtOffset(80, 520);
			for (int i = 0; i < listNameProduct.size(); i++) {
				cs.showText(listNameProduct.get(i));
				cs.newLine();
			}
			cs.endText();

			cs.beginText();
			cs.setFont(font, 12);
			cs.setLeading(20f);
			cs.newLineAtOffset(200, 520);
			for (int i = 0; i < listPriceProduct.size(); i++) {
				cs.showText(listPriceProduct.get(i).toString());
				cs.newLine();
			}
			cs.endText();

			cs.beginText();
			cs.setFont(font, 12);
			cs.setLeading(20f);
			cs.newLineAtOffset(310, 520);
			for (int i = 0; i < listCountProduct.size(); i++) {
				cs.showText(listCountProduct.get(i).toString());
				cs.newLine();
			}
			cs.endText();

//			cs.beginText();
//			cs.setFont(font, 12);
//			cs.setLeading(20f);
//			cs.newLineAtOffset(410, 520);
//			for (int i = 0; i < n; i++) {
//				price = productPrice.get(i) * productQty.get(i);
//				cs.showText(price.toString());
//				cs.newLine();
//			}
//			cs.endText();

			cs.beginText();
			cs.setFont(font, 14);
			cs.newLineAtOffset(310, (500 - (20 * listCountProduct.size())));
			cs.showText("Total: ");
			cs.endText();

			cs.beginText();
			cs.setFont(font, 14);
			// Calculating where total is to be written using number of products
			cs.newLineAtOffset(410, (500 - (20 * listCountProduct.size())));
			cs.showText(sumPrice);
			cs.endText();

			// Close the content stream
			cs.close();
			// Save the PDF
			invc.save(filePath);
			invc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}