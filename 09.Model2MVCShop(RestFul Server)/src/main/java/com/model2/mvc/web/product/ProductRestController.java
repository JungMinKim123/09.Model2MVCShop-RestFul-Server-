package com.model2.mvc.web.product;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;


@RestController
@RequestMapping("/product/*")
public class ProductRestController {

	//Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	public ProductRestController() {
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;

	@Value("#{commonProperties['uploadTempDir']}")
	String uploadTempDir;
	
//	@RequestMapping("/addProduct.do")
	@RequestMapping(value="json/addProduct", method = RequestMethod.POST)
	public Map<String, Object> addProduct(@RequestBody Product product) throws Exception{
		
		System.out.println("/product/json/addProduct : POST");
		
//		if(file != null & file.getSize() >0) {
//			
//			file.transferTo(new File(uploadTempDir, file.getOriginalFilename()));
//			product.setFileName(file.getOriginalFilename());
//		}
		
		String manuDate = product.getManuDate().replace("-", "");
		product.setManuDate(manuDate);
		System.out.println(manuDate);
		
		Map<String,Object> map = new HashMap<String, Object>();
		productService.addProduct(product);
		
		map.put("Product", product);
		
		//map.put("")
		
//		String md = product.getManuDate(); 
//		String[] manu = md.split("-");
//		String manudate = manu[0]+manu[1]+manu[2];
//		product.setManuDate(manudate);
		
		return map;
	}

//	@RequestMapping("/getProduct.do")
	@RequestMapping(value = "json/getProduct/{prodNo}", method = RequestMethod.GET)
	public Product getProduct(@PathVariable int prodNo, HttpServletRequest request) throws Exception{
		
		System.out.println("/product/json/getProduct : GET");
		
		String history = "";
		Cookie[] cookies = request.getCookies();
		if(cookies != null && cookies.length > 0) {
			for(int i=0; i<cookies.length; i++) {
				Cookie cookie = cookies[i];
				if(cookie.getName().equals("history")) {
					history = URLDecoder.decode(","+cookie.getValue());
				}
			}
		}
		
		Cookie cookie = new Cookie("history", URLEncoder.encode(prodNo+history));
		
		return productService.getProduct(prodNo);
	}
	
//	@RequestMapping("/updateProduct.do")
	@RequestMapping(value = "json/updateProduct", method = RequestMethod.POST)
	public Map<String, Object> updateProduct(@RequestBody Product prod) throws Exception{
		
		System.out.println("/product/updateProduct : POST");
//		if( file != null & file.getSize() >0) {
//			file.transferTo(new File(uploadTempDir, file.getOriginalFilename()));
//			prod.setFileName(file.getOriginalFilename());
//		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		productService.updateProduct(prod);
		map.put("updateProduct", prod);
		
		return map;
	}
	
//	@RequestMapping("/updateProductView.do")
	@RequestMapping(value = "json/updateProduct", method = RequestMethod.GET)
	public String updateProductView(@RequestParam int prodNo, Model model) throws Exception{
		
		System.out.println("/product/updateProduct : GET");
		Product prod = productService.getProduct(prodNo);
		model.addAttribute("UpdateProdVO", prod);
		
		return "forward:/product/updateProduct.jsp";
	}
	
//	@RequestMapping("/listProduct.do")
	@RequestMapping(value = "json/listProduct")
	public Map<String, Object> listProduct(@RequestBody Search search) throws Exception{
		
		System.out.println("json/product/listProduct : GET / POST");
		
		if(search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		
		search.setPageSize(pageSize);
		
		Map<String, Object> map = productService.getProductList(search);
		
		Page resultPage = new Page(search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue() , pageUnit, pageSize);
		System.out.println(resultPage);
		
		map.put("resultPage", resultPage);
		map.put("search", search);
		
		return map;
	}
}
