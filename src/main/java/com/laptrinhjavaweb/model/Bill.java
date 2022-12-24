package com.laptrinhjavaweb.model;

public class Bill {
	int id;
	int idAccount;
	String userName;
	String phone;
	String sumPrice;
	int sumCount;
	String address;
	String note;
	int status;
	String date;
	public Bill(int id, int idAccount, String userName, String phone, String sumPrice, int sumCount, String address,
			String note, int status, String date) {
		super();
		this.id = id;
		this.idAccount = idAccount;
		this.userName = userName;
		this.phone = phone;
		this.sumPrice = sumPrice;
		this.sumCount = sumCount;
		this.address = address;
		this.note = note;
		this.status = status;
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Bill(int idAccount, String userName, String phone, String sumPrice, int sumCount, String address,
			String note, int status) {
		super();
		this.idAccount = idAccount;
		this.userName = userName;
		this.phone = phone;
		this.sumPrice = sumPrice;
		this.sumCount = sumCount;
		this.address = address;
		this.note = note;
		this.status = status;
	}
	public int getIdAccount() {
		return idAccount;
	}
	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSumPrice() {
		return sumPrice;
	}
	public void setSumPrice(String sumPrice) {
		this.sumPrice = sumPrice;
	}
	public int getSumCount() {
		return sumCount;
	}
	public void setSumCount(int sumCount) {
		this.sumCount = sumCount;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Bill() {
		super();
	}
	
}
