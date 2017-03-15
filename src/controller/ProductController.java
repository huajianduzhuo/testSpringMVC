package controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.service.CategoryService;
import com.service.ProductService;

import domain.Category;
import domain.Picture;
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
	 * @param file 上传文件
	 * @return
	 */
	@RequestMapping(value = "/product_save", method = RequestMethod.POST)
	public String saveProduct(Product product, BindingResult bindingResult, HttpServletRequest request,
			RedirectAttributes redirectAttributes) throws Exception {
		logger.info("saveProduct called");
		if(bindingResult.hasErrors()){
			FieldError fe = bindingResult.getFieldError();
			logger.info("Code : "+fe.getCode()+", Field : "+fe.getField());
			List<Category> categorys = categoryService.getAllCategorys();
			product.setCategorys(categorys);
			return "ProductForm";
		}
		List<MultipartFile> files = null;
		//得到多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		//判断request是否有文件上传，即多部分
		if(multipartResolver.isMultipart(request)){
			//转换为多部分request
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			//取得multipartRequest中的所有文件
			files = multipartRequest.getFiles("file");
		}
		/** 修改为：产品pojo类中删除图片路径字段，新增图片表  */
		/** 图片按类别存储 */
		/** 支持多图片上传 */
		if(files != null && files.size() > 0){
			int i = 0;
			//图片服务器路径
			String file_path = "D:\\Github\\uploadFiles\\";
			//类别名
			String catName = categoryService.getCategory(product.getCategory().getId()).getName();
			//根据类别名创建文件夹
			File dir = new File(file_path + catName);
			if(!dir.exists() || !dir.isDirectory()){
				dir.mkdir();
			}
			for(MultipartFile file : files){
				if(file != null && file.getOriginalFilename() != null && file.getOriginalFilename().length()>0){
					//原始文件名
					String originalFileName = file.getOriginalFilename();
					//新文件名，添加原始文件名后缀
					//String newFileName = UUID.randomUUID() + originalFileName.substring(originalFileName.lastIndexOf("."));
					//创建新文件，路径为：图片服务器路径+文件夹名+新文件名
					File newFile = new File(file_path + catName + "\\" + originalFileName);
					//将内存中的数据写入磁盘
					file.transferTo(newFile);
					//图片信息
					Picture picture = new Picture();
					picture.setPicName(originalFileName.substring(0, originalFileName.lastIndexOf(".")));
					picture.setPicPath(catName + "\\" + originalFileName);
					picture.setPicOrder(i);
					picture.setProduct(product);
					i++;
					product.getPictures().add(picture);
				}
			}
		}
		Integer id = productService.saveProduct(product);
		redirectAttributes.addFlashAttribute("message", "The product was successfully added");
		return "redirect:/product_view/" + id + ".do";
	}
	
	/**
	 * 重定向之后的方法
	 * 使用路径变量，需要在RequestMapping注解的value中添加一个变量，放在{}中间
	 * 方法参数中添加一个同名变量，使用PathVariable注解
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception 从service传来的异常，抛给全局异常处理器处理
	 */
	@RequestMapping(value = "/product_view/{id}")
	public String viewProduct(@PathVariable(value="id") Integer id, Model model) throws Exception{
		Product product = productService.getProductById(id);
		model.addAttribute("product", product);
		return "ProductDetails";
	}
	
	/**
	 * 调到查询页面
	 * @param model
	 * @return
	 */
	@RequestMapping("product_list")
	public String toQueryProduct(Model model){
		List<Category> categorys = categoryService.getAllCategorys();
		model.addAttribute("categorys", categorys);
		return "ProductList";
	}
	
	/**
	 * 通过ajax查询产品
	 * @param catId
	 * @param productName
	 * @return 以json形式返回产品列表
	 */
	@RequestMapping("product_query")
	public @ResponseBody List<Product> queryProduct(Integer catId, String productName){
		List<Product> products = null;
		try {
			products = productService.queryProduct(catId, productName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return products;
	}

}
