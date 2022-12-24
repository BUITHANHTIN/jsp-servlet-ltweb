package com.laptrinhjavaweb.imp.service;

import java.util.List;

import javax.inject.Inject;

import com.laptrinhjavaweb.DAO.IBill;
import com.laptrinhjavaweb.model.Bill;
import com.laptrinhjavaweb.service.IBillService;

public class BillService implements IBillService {
	@Inject
	private IBill bill;

	@Override
	public int insertBill(Bill billUser) {

		return bill.insertBill(billUser);

	}

	@Override
	public List<Bill> getAllByStatusID(String statusID) {
		return bill.getAllByStatusID(statusID);
	}

	@Override
	public boolean DeleteBillById(int id) {
		// TODO Auto-generated method stub
		return bill.DeleteBillById(id);
	}

	@Override
	public boolean updateBillById(int idBill, int idStatus) {
		// TODO Auto-generated method stub
		return bill.updateBillById(idBill, idStatus);
	}

}
