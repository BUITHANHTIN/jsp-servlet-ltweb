package com.laptrinhjavaweb.model;

public class Product {
	int id;
	String name,image;
	double price;
	String title,description;
	int cateID,SLHangTon;
	public Product(int id, String name, String image, double price, String title, String description, int cateID,
			int SLHangTon) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.price = price;
		this.title = title;
		this.description = description;
		this.cateID = cateID;
		this.SLHangTon = SLHangTon;
	}
	public Product() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCateID() {
		return cateID;
	}
	public void setCateID(int cateID) {
		this.cateID = cateID;
	}
	
	public int getSLHangTon() {
		return SLHangTon;
	}
	public void setSLHangTon(int sLHangTon) {
		SLHangTon = sLHangTon;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", image=" + image + ", price=" + price + ", title=" + title
				+ ", description=" + description + ", cateID=" + cateID + ", sellID=" + SLHangTon + "]";
	}
	

}
