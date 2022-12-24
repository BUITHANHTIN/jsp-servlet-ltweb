package com.laptrinhjavaweb.imp.service;

import java.util.List;

import javax.inject.Inject;

import com.laptrinhjavaweb.DAO.IAccount;
import com.laptrinhjavaweb.model.Account;
import com.laptrinhjavaweb.service.IAccountService;

public class AccountService implements IAccountService {
	@Inject
	private IAccount account;
	
	//1

	@Override
	public Account getOneAccount(String user, String pass) {
		return account.getOneAccount(user, pass).isEmpty() ? null : account.getOneAccount(user, pass).get(0);
	}

	@Override
	public String UsernameExist(String username) {
		return account.UsernameExist(username);
	}

	@Override
	public long InsertAccount(Account account1) {
		return account.InsertAccount(account1);
	}

	@Override
	public List<Account> listAccount(int isAdmin) {
		return account.listAccount(isAdmin);
	}

	@Override
	public boolean deleteAccount(int id) {
		return account.deleteAccount(id);
	}

	@Override
	public boolean updateAccount(Account ac) {
		
		return account.updateAccount(ac);
	}

	@Override
	public String UserExist(String user) {
		// TODO Auto-generated method stub
		return account.UserExist(user);
	}

	@Override
	public boolean updatePass(String user, String pass) {
		// TODO Auto-generated method stub
		return account.updatePass(user, pass);
	}
}
