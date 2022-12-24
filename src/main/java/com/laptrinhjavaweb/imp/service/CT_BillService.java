package com.laptrinhjavaweb.imp.service;

import java.util.List;

import javax.inject.Inject;

import com.laptrinhjavaweb.DAO.ICT_Bill;
import com.laptrinhjavaweb.model.CT_Bill;
import com.laptrinhjavaweb.service.ICT_BillService;

public class CT_BillService implements ICT_BillService {
	@Inject
	private ICT_Bill ctBill;

	@Override
	public boolean insert_CT_Bill(CT_Bill Bill) {
		if (ctBill.insert_CT_Bill(Bill)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<CT_Bill> getAllCTBillById(int id) {
		// TODO Auto-generated method stub
		return ctBill.getAllCTBillById(id);
	}
}
