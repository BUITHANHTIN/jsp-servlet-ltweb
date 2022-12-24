package com.laptrinhjavaweb.DAO;

import java.util.List;

import com.laptrinhjavaweb.model.Card;

public interface ICard {

	boolean InsertCard(Card card);
	List<Card> getAllbyAccountId(int id);
	boolean UpdateCardByIdProduct(int count,int idAccount,int idProduct);
	boolean DeleteCardByIdProduct(int idAccount,int idProduct);
	void DeleteCard(int idAccount);
	
}
