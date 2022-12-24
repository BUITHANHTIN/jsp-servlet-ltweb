package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.model.Product;

public interface IProductService {
//	List<Product> getAllCateID();

	List<Product> getAllByCateID(String cateID);

	List<Product> getOneOrMoreSpecialDeals(int top, String cateID);

	List<Product> getAllBySearch(String searchTitle);

	Product getOneById(String id);

	List<Product> getAll();

	int countgetAllProduct();

	int countgetAllProduct(String cateID);

	List<Product> getProducByPaging(int input, int param,String cateID);

	boolean DeleteProductById(int idProduct);

	boolean updateProductById(Product p);

	boolean addOneProduct(Product p);

	public int countByImageAndId(String image,int id);

	public int countImage(String image);
}
