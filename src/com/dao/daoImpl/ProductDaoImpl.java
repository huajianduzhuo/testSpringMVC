package com.dao.daoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dao.ProductDao;

import domain.Product;
@Component
public class ProductDaoImpl implements ProductDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void saveProduct(Product product) {
		this.getCurrentSession().save(product);
	}

	@Override
	public void updateProduct(Product product) {
		this.getCurrentSession().update(product);
	}

}
