package controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import validator.ProductValidator;

import com.service.ProductService;

import domain.Product;
import form.ProductForm;

/**
 * 使用controller注解，一个控制器可以包含多个请求处理方法
 * 将InputProductController和SaveProductController合成一个
 * @author yujin
 *
 */
@Controller
public class ProductController {
	private static final Log logger = LogFactory.getLog(ProductController.class);
	
	private ProductService productService;
	
	/**
	 * 原InputProductController类
	 * 直接返回视图路径
	 * @return
	 */
	@RequestMapping("/product_input")
	public String inputProduct(){
		logger.info("inputProduct called");
		return "ProductForm";
	}

	/**
	 * 原SaveProductController类
	 * @param productForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/product_save")
	public String saveProduct(ProductForm form, Model model) {
		logger.info("saveProduct called");
		//不必再创建ProductForm类并赋值，springmvc会自动绑定输入，通过参数传入
		/**ProductForm form = new ProductForm();
		form.setName(request.getParameter("name"));
		form.setDescription(request.getParameter("description"));
		form.setPrice(request.getParameter("price"));*/
		ProductValidator productValidator = new ProductValidator();
		List<String> errors = productValidator.validate(form);
		if(errors.isEmpty()){
			Product product = new Product();
			product.setName(form.getName());
			product.setDescription(form.getDescription());
			try {
				product.setPrice(Float.parseFloat(form.getPrice()));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			ApplicationContext cxt = new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
			productService = (ProductService) cxt.getBean("productService");
			productService.saveProduct(product);
			//Model用于增加显示在视图中的属性，保存成功，将product放入model，在ProductDetails页面可以像放在request中一样使用
			model.addAttribute("product", product);
			//model不用放在返回值中，只返回视图路径
			return "ProductDetails";
		}else{
			model.addAttribute("errors", errors);
			model.addAttribute("form", form);
			return "ProductForm";
		}
	}

}
