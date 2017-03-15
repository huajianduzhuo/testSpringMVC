package com.dao;

import java.util.List;

import domain.Category;

public interface CategoryDao {
	public List<Category> getAllCategorys();
	public Category getCategory(Integer catId);

}
