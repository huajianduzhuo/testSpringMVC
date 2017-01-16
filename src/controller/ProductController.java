package controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@Autowired
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
	 * 为了保证保存之后刷新页面时，save方法不被重复调用，数据库存值重复，返回路径使用重定向
	 * @param productForm
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/product_save", method = RequestMethod.POST)
	public String saveProduct(ProductForm form, RedirectAttributes redirectAttributes) {
		logger.info("saveProduct called");
		//测试重定向，将校验去掉
		/**ProductValidator productValidator = new ProductValidator();
		List<String> errors = productValidator.validate(form);
		if(errors.isEmpty()){*/
			Product product = new Product();
			product.setName(form.getName());
			product.setDescription(form.getDescription());
			try {
				product.setPrice(Float.parseFloat(form.getPrice()));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			//productService由spring注入（只有在控制器类使用@Controller注解时，service才能依赖注入）
			Integer id = productService.saveProduct(product);
			//使用重定向，经过客户端，model中的一切会在重定向时丢失，所以不使用model
			/**model.addAttribute("product", product);*/
			//为了传值给客户端，spring3.1及以上版本通过flash属性提供传值方法，必须在参数中添加redirectAttributes
			redirectAttributes.addFlashAttribute("message", "The product was successfully added");
			//重定向至/product_view
			return "redirect:/product_view/" + id;
		/**}else{
			model.addAttribute("errors", errors);
			model.addAttribute("form", form);
			return "ProductForm";
		}*/
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
