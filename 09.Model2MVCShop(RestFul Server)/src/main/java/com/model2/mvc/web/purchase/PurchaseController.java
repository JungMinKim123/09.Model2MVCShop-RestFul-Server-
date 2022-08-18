package com.model2.mvc.web.purchase;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;

@Controller
@RequestMapping("/purchase/")
public class PurchaseController {
	
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	public PurchaseController() {
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;

	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
//	@RequestMapping("/addPurchase.do")
	@RequestMapping(value="addPurchase", method = RequestMethod.POST)
	public ModelAndView addPurchase(@ModelAttribute("addVO") Purchase purchase,  String tranCode, 
																@ModelAttribute("user") User user, @ModelAttribute("prod") Product prod) throws Exception {

		System.out.println("/puchase/addPurchaes : POST");
		System.out.println("디버그:: purchase에 저장된 값 = "+purchase);
		
		purchase.setBuyer(user);
		purchase.setPurchaseProd(prod);
		purchaseService.addPurchase(purchase);
		
		String viewName = "forward:/purchase/addPurchaseReadView.jsp";
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(viewName);
		
		return modelAndView;
		
	}
	
//	@RequestMapping("/addPurchaseView.do")
	@RequestMapping(value = "addPurchase", method = RequestMethod.GET)
	public ModelAndView addPurchaseView(@RequestParam("prod_no") int prodNo, Model model) throws Exception{
		
		System.out.println("/purchase/addPurchase : GET");
		Product prod = productService.getProduct(prodNo);
		model.addAttribute("addview", prod);
		
		String viewName = "forward:/purchase/addPurchaseView.jsp";
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(viewName);
		
		return modelAndView;
	}
	
//	@RequestMapping("/getPurchase.do")
	@RequestMapping(value = "getPurchase", method = RequestMethod.GET)
	public ModelAndView getPurchase(@RequestParam int tranNo, Model model) throws Exception{
		
		System.out.println("/purchase/getPurchase : GET");
	
		Purchase purchase = purchaseService.getPurchase(tranNo);
		model.addAttribute("purVo", purchase);
	
		String viewName = "forward:/purchase/purchaseReadView.jsp";
	
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(viewName);
	
		return modelAndView;
	}
	
//	@RequestMapping("/updatePurchase.do")
	@RequestMapping(value = "updatePurchase")
	public ModelAndView updatePurchase(@ModelAttribute("updateVo") Purchase purchase, @ModelAttribute("userVo") User user,
																	 Model model) throws Exception{
		
		System.out.println("/purchase/updatePurchase : GET / POST");
		purchase.setBuyer(user);
		purchaseService.updatePurchase(purchase);
		
		Purchase purch = purchaseService.getPurchase(purchase.getTranNo());
		model.addAttribute("updateVO", purch);
		
		String viewName = "forward:/purchase/updatePurchaseReadView.jsp";
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(viewName);
		
		return modelAndView;
	}
	
//	@RequestMapping("/updatePurchaseView.do")
	@RequestMapping(value = "updatePurchase", method = RequestMethod.GET)
	public ModelAndView updatePurchaseView(@RequestParam int tranNo, Model model) throws Exception{
		
		System.out.println("/purchase/updatePurchaseView : GET");
		Purchase purchase = purchaseService.getPurchase(tranNo);
		model.addAttribute("updateview", purchase);
		
		String viewName = "forward:/purchase/updatePurchaseView.jsp";
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(viewName);
		
		return modelAndView;
	}
	
//	@RequestMapping("/updateTranCode.do")
	@RequestMapping(value = "updateTranCode", method = RequestMethod.GET)
	public ModelAndView updateTranCode(@ModelAttribute("tranCode") Purchase purchase, @ModelAttribute("prod") Product prod) throws Exception{
		
		System.out.println("/purchase/updateTranCode : GET");
		purchase.setPurchaseProd(prod);
		System.out.println("TranCode 저장 값 : "+purchase.getTranCode());
		purchaseService.updateTranCode(purchase);
		
		String viewName1 = "forward:/product/listProduct?menu=manage";
		String viewName2 = "forward:/product/listProduct?menu=search";
		
		ModelAndView modelAndView = new ModelAndView();
		if( purchase.getTranCode().equals("2")) {
			modelAndView.setViewName(viewName1);
		}else if( purchase.getTranCode().equals("3")) {
			modelAndView.setViewName(viewName2);
		}
		
		return modelAndView;
	}
	
//	@RequestMapping("/listPurchase.do")
	@RequestMapping(value = "listPurchase")
	public ModelAndView listPurchase(@ModelAttribute("Search") Search search, Model model) throws Exception{
		
		System.out.println("/purchase/listPurchase : GET / POST");
		
		if(search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		
		search.setPageSize(pageSize);
		
		Map<String, Object> map = purchaseService.getPurchaseList(search);
		
		Page resultPage = new Page(search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize );
		System.out.println(resultPage);
		
		model.addAttribute("list", map.get("purchaseLsist"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
//		System.out.println("List 저장된 값 : "+model.getAttribute("list").toString());
		
		String viewName = "forward:/purchase/purchaseList.jsp";
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(viewName);
		
		return modelAndView;
		
	}
	
}
