package cn.com.logic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import cn.com.mapper.GoodsPublishMapper;

@Service
public class GoodsLogic {
	protected static Logger log = Logger.getLogger(GoodsLogic.class);
	
	@Autowired
	private GoodsPublishMapper goodsPublishMapper;
	
	public ModelAndView detail(HttpServletRequest request,HttpServletResponse response){
		String goodsid = request.getParameter("id");
		System.out.println("物品id为"+goodsid);
		return null;
	}
	
	
}
