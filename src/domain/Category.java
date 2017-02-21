package domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="category")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	//private List<Product> products;	//此字段导致springmvc将查询得到的产品集合转化为	JSON字符串时出错
	
	@GenericGenerator(name="generator", strategy="increment")
	@Id
	@GeneratedValue(generator="generator")
	@Column(name="id", nullable=false, unique=true)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="name", nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * mappedBy 只有OneToOne，OneToMany，ManyToMany上才有mappedBy属性
	 * mappedBy 一定存在在 被拥有方，指向 拥有方
	 * mappedBy 的含义为，拥有方能够自动维护跟被拥有方的关系
	 * mappedBy 的值，为ManyToOne中的对象名
	 * @return
	 */
	/*@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="category")
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}*/
	

}
