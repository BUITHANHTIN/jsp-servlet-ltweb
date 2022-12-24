package com.laptrinhjavaweb.DAO;

import java.util.List;

import com.laptrinhjavaweb.model.Account;

public interface IAccount {
	List<Account> getOneAccount(String user, String pass);

	String UsernameExist(String username);

	String UserExist(String user);

	long InsertAccount(Account account);

	boolean deleteAccount(int id);

	List<Account> listAccount(int isAdmin);

	boolean updateAccount(Account account);

	boolean updatePass(String user, String pass);
}
