package cn.com.logic;

import java.util.ArrayList;
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
import cn.com.mapper.GoodsPublishMapper;
import cn.com.mapper.GoodsSubMapper;
import cn.com.mapper.GoodsTypeMapper;
import cn.com.mapper.UserMapper;
import cn.com.util.PropertyFactory;


@Service
public class HomePageLogic {
	protected static Logger log = Logger.getLogger(HomePageLogic.class);
	
	@Autowired
	private GoodsPublishMapper goodsPublishMapper;
	@Autowired
	private GoodsTypeMapper goodsTypeMapper;
	@Autowired
	private GoodsSubMapper goodsSubMapper;
	@Autowired
	private UserMapper userMapper;
	
	public ModelAndView homePage(HttpServletRequest request,HttpServletResponse response){
		
		ModelAndView model = new ModelAndView();
		ModelMap modelMap = new ModelMap();
		model.setViewName("/homePage");
		
		ArrayList<GoodsType> type = goodsTypeMapper.selectAll();
		ArrayList<GoodsSub> sub = goodsSubMapper.selectAll();
		modelMap.put("type", type);
		modelMap.put("sub", sub);
		
		
		ArrayList<GoodsPublish> publish = goodsSearch(null, null, null, null);
		for (int i = 0; i < publish.size() ; i++) {
			GoodsPublish good = publish.get(i);
			good.setPic(PropertyFactory.getProperty("serverPath")+":"+PropertyFactory.getProperty("serverPort")
					+PropertyFactory.getProperty("goodsImgPath")+publish.get(i).getPic());
//			System.out.println(good.getUser().getNickName());
		}
		modelMap.put("goodsPublish", publish);
		
		model.addAllObjects(modelMap);
		
		return model;
	}
	
	
	
	
	private ArrayList<GoodsPublish> goodsSearch(String time_collection,String type,String sub,String search){
		
		ArrayList<GoodsPublish> goods = goodsPublishMapper.selectByTime();
		
		return goods;
	}
	
	
}
