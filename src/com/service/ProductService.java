package com.service;

import domain.Product;

public interface ProductService {
	public Integer saveProduct(Product product);
	public void updateProduct(Product product);
	public Product getProductById(Integer id);

}
