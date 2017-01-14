package com.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ProductDao;
import com.service.ProductService;

import domain.Product;
@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;
	
	@Override
	public void saveProduct(Product product) {
		productDao.saveProduct(product);
	}


	@Override
	public void updateProduct(Product product) {
		productDao.updateProduct(product);
	}

}