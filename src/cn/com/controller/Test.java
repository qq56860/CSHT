package cn.com.controller;

import java.util.HashMap;
import java.util.Map;

import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("test")
public class Test {
	
	@RequestMapping("/haha")
	public String getSuggest(HttpServletRequest request,HttpServletResponse response){
		
		String str = "very good";
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("str", str);
		
		
		
		HttpSession session = request.getSession();
		session.setAttribute("admin", map);
		
		return "redirect:/MyJsp.jsp";
	}
	
}
