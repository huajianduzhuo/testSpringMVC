package com.dao;

import java.util.List;

import domain.Product;

public interface ProductDao {
	public Integer saveProduct(Product product);
	public void updateProduct(Product product);
	public Product getProductById(Integer id);
	public List<Product> queryProducts(Product product);

}
