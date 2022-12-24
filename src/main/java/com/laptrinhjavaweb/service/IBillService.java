package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.model.Bill;

public interface IBillService {
	int insertBill(Bill bill);

	List<Bill> getAllByStatusID(String statusID);
	boolean DeleteBillById(int id);
	boolean updateBillById(int idBill,int idStatus );
}
