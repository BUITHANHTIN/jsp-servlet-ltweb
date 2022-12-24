package com.laptrinhjavaweb.imp.service;

import java.util.List;

import javax.inject.Inject;

import com.laptrinhjavaweb.DAO.IProduct;
import com.laptrinhjavaweb.model.Product;
import com.laptrinhjavaweb.service.IProductService;

public class ProductService implements IProductService {
	@Inject
	private IProduct product;

	/*
	 * @Override public List<Product> getAllCateID() { return
	 * product.getAllCateID(); }
	 */

	@Override
	public List<Product> getAllByCateID(String cateID) {
		return product.getAllByCateID(cateID);
	}

	@Override
	public List<Product> getOneOrMoreSpecialDeals(int top, String cateID) {
		return product.getOneOrMoreSpecialDeals(top, cateID);
	}

	@Override
	public List<Product> getAllBySearch(String searchTitle) {
		return product.getAllBySearch(searchTitle);
	}

	@Override
	public Product getOneById(String id) {
		return product.getOneById(id);
	}

	@Override
	public List<Product> getAll() {
		// TODO Auto-generated method stub
		return product.getAll();
	}

	@Override
	public int countgetAllProduct() {
		return product.countgetAllProduct();
	}

	@Override
	public int countgetAllProduct(String cateID) {
		return product.countgetAllProduct(cateID);
	}

	@Override
	public List<Product> getProducByPaging(int input, int param,String cateID) {
		return product.getProducByPaging(input, param,cateID);
	}

	@Override
	public boolean DeleteProductById(int idProduct) {
		return product.DeleteProductById(idProduct);
	}

	@Override
	public boolean updateProductById(Product p) {
		return product.updateProductById(p);
	}

	@Override
	public boolean addOneProduct(Product p) {
		return product.addOneProduct(p);
	}

	@Override
	public int countImage(String image) {
		// TODO Auto-generated method stub
		return product.countImage(image);
	}

	@Override
	public int countByImageAndId(String image, int id) {
		// TODO Auto-generated method stub
		return product.countByImageAndId(image, id);
	}

	

}
