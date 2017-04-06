package cn.com.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import cn.com.domain.GoodsPublish;
import cn.com.domain.GoodsSub;
import cn.com.domain.GoodsType;
import cn.com.domain.User;
import cn.com.mapper.BuyCommentMapper;
import cn.com.mapper.BuyReplyMapper;
import cn.com.mapper.GoodsBuyMapper;
import cn.com.mapper.GoodsPublishMapper;
import cn.com.mapper.GoodsSubMapper;
import cn.com.mapper.GoodsTypeMapper;
import cn.com.mapper.UserMapper;
import cn.com.util.PropertyFactory;
import cn.com.util.StringUtil;


@Service
public class BuyPageLogic {
	protected static Logger log = Logger.getLogger(BuyPageLogic.class);
	
	@Autowired
	private GoodsBuyMapper goodsBuyMapper;
	@Autowired
	private BuyCommentMapper buyCommentMapper;
	@Autowired
	private BuyReplyMapper buyReplyMapper;
	
	public ModelAndView buyPage(HttpServletRequest request,HttpServletResponse response){
		
		ModelAndView model = new ModelAndView();
		ModelMap modelMap = new ModelMap();
		model.setViewName("/goods/goodsBuy");
		
		
		
		model.addAllObjects(modelMap);
		
		return model;
	}
	
	
	
	
}
