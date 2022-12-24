package com.laptrinhjavaweb.DAO;

import java.util.List;

import com.laptrinhjavaweb.model.Bill;

public interface IBill {
	int insertBill(Bill bill);

	List<Bill> getAllByStatusID(String statusID);

	boolean DeleteBillById(int id);
	
	boolean updateBillById(int idBill,int idStatus );

}
