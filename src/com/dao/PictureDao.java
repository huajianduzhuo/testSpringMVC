package com.dao;

import java.util.List;

import domain.Picture;

public interface PictureDao {
	public void savePicture(Picture picture);
	public Picture getPicture(Integer id);
	public List<Picture> getPicturesByProduct(Integer proId);

}
