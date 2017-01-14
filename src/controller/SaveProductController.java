package controller;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import validator.ProductValidator;

import com.service.ProductService;

import domain.Product;
import form.ProductForm;

public class SaveProductController implements Controller {
	private static final Log logger = LogFactory.getLog(SaveProductController.class);
	
	private ProductService productService;

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("SaveProductController called");
		ProductForm form = new ProductForm();
		form.setName(request.getParameter("name"));
		form.setDescription(request.getParameter("description"));
		form.setPrice(request.getParameter("price"));
		Field[] fields = form.getClass().getDeclaredFields();
		boolean formIsNull = true;
		for(Field f : fields){
			try {
				f.setAccessible(true);
				if(f.get(form)!=null){
					formIsNull = false;
					break;
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		if(!formIsNull){
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
				return new ModelAndView("ProductDetails", "product", product);
			}else{
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("errors", errors);
				model.put("form", form);
				return new ModelAndView("ProductForm", model);
			}
		}
		return new ModelAndView("ProductDetails");
	}

}
