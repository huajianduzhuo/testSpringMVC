package domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name="picture")
public class Picture implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer picId;
	private String picPath;
	private String picName;
	private Integer picOrder;//之前命名为order，属于mySQL关键字，insert sql无法执行成功（找了近两天，泪）
	private Product product;
	
	@GenericGenerator(name="generator1",strategy="increment")
	@Id
	@GeneratedValue(generator="generator1")
	@Column(name="pic_id",nullable=false,unique=true)
	public Integer getPicId() {
		return picId;
	}
	public void setPicId(Integer picId) {
		this.picId = picId;
	}
	@Column(name="pic_path")
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	@Column(name="pic_name")
	public String getPicName() {
		return picName;
	}
	public void setPicName(String picName) {
		this.picName = picName;
	}
	@Column(name="pic_order")
	public Integer getPicOrder() {
		return picOrder;
	}
	public void setPicOrder(Integer picOrder) {
		this.picOrder = picOrder;
	}
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="product_id")
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	

}
