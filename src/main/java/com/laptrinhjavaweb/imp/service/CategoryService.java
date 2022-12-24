package com.laptrinhjavaweb.imp.service;

import java.util.List;

import javax.inject.Inject;

import com.laptrinhjavaweb.DAO.ICategory;
import com.laptrinhjavaweb.model.Category;
import com.laptrinhjavaweb.service.ICategoryService;

public class CategoryService implements ICategoryService {
	@Inject
	private ICategory cate;

	@Override
	public List<Category> getAllCateID() {
		return cate.getAllCateID();
	}

}
