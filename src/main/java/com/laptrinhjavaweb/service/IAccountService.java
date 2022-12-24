package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.model.Account;

public interface IAccountService {
	Account getOneAccount(String user, String pass);

	String UsernameExist(String username);

	long InsertAccount(Account account);

	List<Account> listAccount(int isAdmin);

	boolean deleteAccount(int id);

	boolean updateAccount(Account account);
	
	String UserExist(String user);
	
	boolean updatePass(String user,String pass);

}
