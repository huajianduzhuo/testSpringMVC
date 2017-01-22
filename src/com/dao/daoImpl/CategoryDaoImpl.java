package com.dao.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dao.CategoryDao;

import domain.Category;
@Component
public class CategoryDaoImpl implements CategoryDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public List<Category> getAllCategorys() {
		return this.getCurrentSession().createQuery("from Category").list();
	}

}
