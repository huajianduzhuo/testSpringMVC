package controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.service.CategoryService;
import com.service.ProductService;

import domain.Category;
import domain.Product;

/**
 * 使用controller注解，一个控制器可以包含多个请求处理方法
 * 将InputProductController和SaveProductController合成一个
 * @author yujin
 *
 */
@Controller
public class ProductController {
	private static final Log logger = LogFactory.getLog(ProductController.class);
	
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	
	/**
	 * 原InputProductController类
	 * 直接返回视图路径
	 * @return
	 */
	@RequestMapping("/product_input")
	public String inputProduct(Model model){
		logger.info("inputProduct called");
		List<Category> categorys = categoryService.getAllCategorys();
		Product product = new Product();
		product.setCategorys(categorys);
		model.addAttribute(product);
		return "ProductForm";
	}

	/**
	 * 原SaveProductController类
	 * 为了保证保存之后刷新页面时，save方法不被重复调用，数据库存值重复，返回路径使用重定向
	 * @param productForm
	 * @param model
	 * @param bindingResult 绑定错误信息
	 * @return
	 */
	@RequestMapping(value = "/product_save", method = RequestMethod.POST)
	public String saveProduct(Product product, BindingResult bindingResult,RedirectAttributes redirectAttributes) {
		logger.info("saveProduct called");
		if(bindingResult.hasErrors()){
			FieldError fe = bindingResult.getFieldError();
			logger.info("Code:"+fe.getCode()+", Field:"+fe.getField());
			List<Category> categorys = categoryService.getAllCategorys();
			product.setCategorys(categorys);
			return "ProductForm";
		}
		Integer id = productService.saveProduct(product);
		redirectAttributes.addFlashAttribute("message", "The product was successfully added");
		return "redirect:/product_view/" + id;
	}
	
	/**
	 * 重定向之后的方法
	 * 使用路径变量，需要在RequestMapping注解的value中添加一个变量，放在{}中间
	 * 方法参数中添加一个同名变量，使用PathVariable注解
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/product_view/{id}")
	public String viewProduct(@PathVariable(value="id") Integer id, Model model){
		Product product = productService.getProductById(id);
		model.addAttribute("product", product);
		return "ProductDetails";
	}

}
