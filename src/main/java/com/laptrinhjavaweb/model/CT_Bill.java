package com.laptrinhjavaweb.model;

public class CT_Bill {
	int idBill, idProduct;
	String name, image;
	String price;
	int count;
	public CT_Bill(int idBill, int idProduct, String name, String image, String price, int count) {
		super();
		this.idBill = idBill;
		this.idProduct = idProduct;
		this.name = name;
		this.image = image;
		this.price = price;
		this.count = count;
	}
	public int getIdBill() {
		return idBill;
	}
	public void setIdBill(int idBill) {
		this.idBill = idBill;
	}
	public int getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public CT_Bill() {
		super();
	}
	@Override
	public String toString() {
		return "CT_Bill [idBill=" + idBill + ", idProduct=" + idProduct + ", name=" + name + ", image=" + image
				+ ", price=" + price + ", count=" + count + "]";
	}

	

}
