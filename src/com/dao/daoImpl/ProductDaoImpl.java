package com.dao.daoImpl;

import java.util.List;

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
	public Integer saveProduct(Product product) {
		return (Integer) this.getCurrentSession().save(product);
	}

	@Override
	public void updateProduct(Product product) {
		this.getCurrentSession().update(product);
	}

	@Override
	public Product getProductById(Integer id) {
		return (Product) this.getCurrentSession().get(Product.class, id);
	}

	@Override
	public List<Product> queryProducts(Product product) {
		String hql = "from Product p where 1=1 ";
		if(product.getCategory().getId() != null && product.getCategory().getId() != -1){
			hql += "and p.category.id = " + product.getCategory().getId() + " ";
		}
		if(product.getName() != null && !"".equals(product.getName().trim())){
			hql += "and p.name like '" + product.getName() + "' ";
		}
		return (List<Product>) this.getCurrentSession().createQuery(hql).list();
	}

}
