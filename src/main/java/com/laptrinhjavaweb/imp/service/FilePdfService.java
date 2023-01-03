package com.laptrinhjavaweb.imp.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.laptrinhjavaweb.DAO.IBill;
import com.laptrinhjavaweb.DAO.ICT_Bill;
import com.laptrinhjavaweb.model.Bill;
import com.laptrinhjavaweb.model.CT_Bill;
import com.laptrinhjavaweb.model.FilePdf;
import com.laptrinhjavaweb.service.IFilePdfService;

public class FilePdfService implements IFilePdfService {

	@Inject
	private IBill bill;

	@Inject
	private ICT_Bill ct_bill;

	@Override
	public FilePdf findById(int id) {
		List<Bill> listBill = bill.getAllByIdBill(id);
		List<CT_Bill> listCtBill = ct_bill.getAllCTBillById(id);
		List<String> listName = new ArrayList<>();
		List<String> listPrice = new ArrayList<>();
		List<String> listCount = new ArrayList<>();
		FilePdf filePdf = new FilePdf();
		for (CT_Bill ct_Bill : listCtBill) {
			listName.add(ct_Bill.getName());
			listPrice.add(ct_Bill.getPrice());
			listCount.add(String.valueOf(ct_Bill.getCount()));
		}
		filePdf.setListNameProduct(listName);
		filePdf.setListPriceProduct(listPrice);
		filePdf.setListCountProduct(listCount);
		filePdf.setAddress(listBill.get(0).getAddress());
		filePdf.setName(listBill.get(0).getUserName());
		filePdf.setPhone(listBill.get(0).getPhone());
		filePdf.setSumPrice(listBill.get(0).getSumPrice());

		return filePdf;
	}

}
