package com.laptrinhjavaweb.utils;

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
	String name;
	String phone;
	String sumPrice;
	String address;
	List<String> listNameProduct = new ArrayList<>();
	List<String> listPriceProduct = new ArrayList<>();
	List<String> listCountProduct = new ArrayList<>();
	String invoiceTitle = new String("Web ban hang online");
	String subTitle = new String("Bill");
	PDFont font;

	// Using the constructor to create a PDF with a blank page
	public CreatePdf() throws IOException {
		// Create Document
		invc = new PDDocument();
		// Create Blank Page
		PDPage newpage = new PDPage();
		// Add the blank page
		invc.addPage(newpage);
		font = PDType0Font.load(invc,
				PDDocument.class.getResourceAsStream("/org/apache/pdfbox/resources/ttf/LiberationSans-Regular.ttf"));
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
			cs.newLineAtOffset(300, 540);
			cs.showText(" Price");
			cs.endText();

			cs.beginText();
			cs.setFont(font, 14);
			cs.newLineAtOffset(410, 540);
			cs.showText("Count");
			cs.endText();


			cs.beginText();
			cs.setFont(font, 12);
			cs.setLeading(20f);
			cs.newLineAtOffset(80, 520);
			for (int i = 0; i < pdf.getListNameProduct().size(); i++) {
				cs.showText(pdf.getListNameProduct().get(i));
				cs.newLine();
			}
			cs.endText();

			cs.beginText();
			cs.setFont(font, 12);
			cs.setLeading(20f);
			cs.newLineAtOffset(300, 520);
			for (int i = 0; i < pdf.getListPriceProduct().size(); i++) {
				cs.showText(pdf.getListPriceProduct().get(i).toString());
				cs.newLine();
			}
			cs.endText();

			cs.beginText();
			cs.setFont(font, 12);
			cs.setLeading(20f);
			cs.newLineAtOffset(410, 520);
			for (int i = 0; i < pdf.getListCountProduct().size(); i++) {
				cs.showText(pdf.getListCountProduct().get(i).toString());
				cs.newLine();
			}
			cs.endText();

			cs.beginText();
			cs.setFont(font, 14);
			cs.newLineAtOffset(310, (500 - (20 * pdf.getListCountProduct().size())));
			cs.showText("Total: ");
			cs.endText();

			cs.beginText();
			cs.setFont(font, 14);
			// Calculating where total is to be written using number of products
			cs.newLineAtOffset(410, (500 - (20 * pdf.getListCountProduct().size())));
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