package com.laptrinhjavaweb.model;

public class Card {

	int idAccount, id;
	String name, image;
	double price;
	int count;

	public int getId() {
		return id;
	}

	public int getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Card() {
		super();
	}

	public Card(int idAccount, int id, String name, String image, double price, int count) {
		super();
		this.idAccount = idAccount;
		this.id = id;
		this.name = name;
		this.image = image;
		this.price = price;
		this.count = count;
	}

	@Override
	public String toString() {
		return "Card [idAccount=" + idAccount + ", id=" + id + ", name=" + name + ", image=" + image + ", price="
				+ price + ", count=" + count + "]";
	}

}
