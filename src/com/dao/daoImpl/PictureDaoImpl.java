package com.dao.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dao.PictureDao;

import domain.Picture;
@Component
public class PictureDaoImpl implements PictureDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void savePicture(Picture picture) {
		this.getCurrentSession().save(picture);
	}

	@Override
	public Picture getPicture(Integer id) {
		return (Picture) this.getCurrentSession().get(Picture.class, id);
	}

	@Override
	public List<Picture> getPicturesByProduct(Integer proId) {
		String hql = "from Picture p where p.product.id = " + proId + " order by p.order";
		return this.getCurrentSession().createQuery(hql).list();
	}

}
