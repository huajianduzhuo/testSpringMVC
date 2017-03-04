package com.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ProductDao;
import com.exception.CustomException;
import com.service.ProductService;

import domain.Category;
import domain.Product;
@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;
	
	@Override
	public Integer saveProduct(Product product) {
		return productDao.saveProduct(product);
	}


	@Override
	public void updateProduct(Product product) {
		productDao.updateProduct(product);
	}


	@Override
	public Product getProductById(Integer id) throws Exception {
		Product product = productDao.getProductById(id);
		if(product == null){
			throw new CustomException("没有找到该商品信息！");
		}
		return product;
	}


	@Override
	public List<Product> queryProduct(Integer catId, String productName) {
		Product product = new Product();
		Category cat = new Category();
		cat.setId(catId);
		product.setCategory(cat);
		if(productName != null && !"".equals(productName.trim())){
			product.setName("%" + productName + "%");
		}
		return productDao.queryProducts(product);
	}

}
