package com.dao;

import domain.Product;

public interface ProductDao {
	public Integer saveProduct(Product product);
	public void updateProduct(Product product);
	public Product getProductById(Integer id);

}
