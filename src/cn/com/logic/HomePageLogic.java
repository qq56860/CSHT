package cn.com.logic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import cn.com.domain.GoodsPublish;
import cn.com.domain.GoodsType;
import cn.com.mapper.GoodsPublishMapper;
import cn.com.mapper.GoodsTypeMapper;


@Service
public class HomePageLogic {
	protected static Logger log = Logger.getLogger(HomePageLogic.class);
	
	@Autowired
	private GoodsPublishMapper goodsPublishMapper;
	@Autowired
	private GoodsTypeMapper goodsTypeMapper;
	
	
	public String homePage(HttpServletRequest request,HttpServletResponse response){
		
		return null;
	}
	
}
