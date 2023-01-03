package com.laptrinhjavaweb.model;

import java.util.ArrayList;
import java.util.List;

public class FilePdf {
	String name, phone, sumPrice, address;
	List<String> listNameProduct = new ArrayList<>();
	List<String> listPriceProduct = new ArrayList<>();
	List<String> listCountProduct = new ArrayList<>();

	public List<String> getListCountProduct() {
		return listCountProduct;
	}

	public void setListCountProduct(List<String> listCountProduct) {
		this.listCountProduct = listCountProduct;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<String> getListNameProduct() {
		return listNameProduct;
	}

	public void setListNameProduct(List<String> listNameProduct) {
		this.listNameProduct = listNameProduct;
	}

	public List<String> getListPriceProduct() {
		return listPriceProduct;
	}

	public void setListPriceProduct(List<String> listPriceProduct) {
		this.listPriceProduct = listPriceProduct;
	}

}
