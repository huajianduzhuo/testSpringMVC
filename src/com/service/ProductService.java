package com.service;

import java.util.List;

import domain.Product;

public interface ProductService {
	public Integer saveProduct(Product product);
	public void updateProduct(Product product);
	public Product getProductById(Integer id) throws Exception;
	public List<Product> queryProduct(Integer catId, String productName);

}
