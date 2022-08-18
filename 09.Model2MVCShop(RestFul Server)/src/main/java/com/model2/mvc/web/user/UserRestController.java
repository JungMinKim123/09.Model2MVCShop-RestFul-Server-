package com.model2.mvc.web.user;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;


//==> 회원관리 RestController
@RestController
@RequestMapping("/user/*")
public class UserRestController {
	
	///Field
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	//setter Method 구현 않음
		
	public UserRestController(){
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	@RequestMapping(value = "json/addUser", method = RequestMethod.POST)
	public User addUser(@RequestBody User user, Model model) throws Exception{
		
		System.out.println("/user/json/addUser : POST");
		
		userService.addUser(user);
		
		return userService.getUser(user.getUserId());
	}
	
	@RequestMapping( value="json/getUser/{userId}", method=RequestMethod.GET )
	public User getUser( @PathVariable String userId ) throws Exception{
		
		System.out.println("/user/json/getUser : GET");
		
		//Business Logic
		return userService.getUser(userId);
	}

	@RequestMapping( value="json/getUser", method=RequestMethod.POST )
	public User getUser( @RequestBody User user ) throws Exception{
		
		System.out.println("/user/json/getUser : POST");
		
		//Business Logic
		return userService.getUser(user.getUserId());
	}
	
	@RequestMapping(value = "json/updateUser/{userId}", method = RequestMethod.GET)
	public User updateUser(@PathVariable String userId) throws Exception {
		
		System.out.println("/user/json/updateUser : GET");
		
		return userService.getUser(userId);
	}
	
	@RequestMapping(value = "json/updateUser", method = RequestMethod.POST)
	public User updateUser(@RequestBody User user, HttpSession session) throws Exception{
		
		System.out.println("/user/json/updateUser : POST");
		
		userService.updateUser(user);
		
		String sessoinId = ((User)session.getAttribute("user")).getUserId();
		if(session.equals(user.getUserId())) {
			session.setAttribute("user", user);
		}
		
		return userService.getUser(user.getUserId());
	}
	
	@RequestMapping( value="json/login", method=RequestMethod.POST )
	public User login(	@RequestBody User user,
									HttpSession session ) throws Exception{
	
		System.out.println("/user/json/login : POST");
		//Business Logic
		System.out.println("::"+user);
		User dbUser=userService.getUser(user.getUserId());
		
		if( user.getPassword().equals(dbUser.getPassword())){
			session.setAttribute("user", dbUser);
		}
		
		return dbUser;
	}
	
	@RequestMapping(value = "json/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception{
		
		System.out.println("/user/json/logout : POST");
		
		session.invalidate();
		String sessionValue = (String) session.getAttribute("user");
		
		return sessionValue;
	}
	
//	@RequestMapping(value = "json/checkDuplication", method = RequestMethod.POST)
//	public User checkDuplication(@RequestBody User user) throws Exception{
//	
//		System.out.println("user/json/checkDuplication : POST");
//		
//		boolean result = userService.checkDuplication(user.getUserId());
//		
//		return result;
//	}
	
	@RequestMapping(value = "json/listUser", method = RequestMethod.POST)
	public Map listUser(@RequestBody Search search) throws Exception{
		System.out.println("user/json/listUser : POST");
		if(search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		Map<String, Object> map = userService.getUserList(search);
		
		Page resultPage = new Page(search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit,pageSize);
		
		map.put("search", search);
		map.put("resultPage", resultPage);
		
		return map;
	}
}