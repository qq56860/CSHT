package cn.com.logic;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import cn.com.domain.GoodsPublish;
import cn.com.mapper.GoodsPublishMapper;
import cn.com.util.PropertyFactory;

@Service
public class GoodsLogic {
	protected static Logger log = Logger.getLogger(GoodsLogic.class);
	
	@Autowired
	private GoodsPublishMapper goodsPublishMapper;
	
	public ModelAndView detail(HttpServletRequest request,HttpServletResponse response){
		String goodsid = request.getParameter("id");
		log.info("查看的物品id为："+goodsid);
		
		ModelAndView model = new ModelAndView();
		ModelMap modelMap = new ModelMap();
		model.setViewName("/goods/goods");
		
		GoodsPublish publish = goodsPublishMapper.selectByPrimaryKey(goodsid);
		modelMap.put("goods", publish);
		
		String picName[] = publish.getPic().split(",");
		List<String> picList = new ArrayList<String>();
		log.info("该物品有图片"+picName.length+"张");
		for (int i = 0; i < picName.length; i++) {
			picList.add(PropertyFactory.getProperty("serverPath")+":"+PropertyFactory.getProperty("serverPort")
					+PropertyFactory.getProperty("goodsImgPath")+picName[i]+".png");
		}
		modelMap.put("picList", picList);
		
		model.addAllObjects(modelMap);
		return model;
	}
	
	public ModelAndView publishComment(HttpServletRequest request,HttpServletResponse response){
		String goodsid = request.getParameter("goodsid");
		log.info("发布商品---id为："+goodsid+"的评论");
		
		ModelAndView model = new ModelAndView();
		ModelMap modelMap = new ModelMap();
		model.setViewName("/goods/comment");
		
		
		model.addAllObjects(modelMap);
		return model;
	}
	
	public ModelAndView buyComment(HttpServletRequest request,HttpServletResponse response){
		String goodsid = request.getParameter("goodsid");
		log.info("求购商品---id为："+goodsid+"的评论");
		
		ModelAndView model = new ModelAndView();
		ModelMap modelMap = new ModelMap();
		model.setViewName("/goods/comment");
		
		
		model.addAllObjects(modelMap);
		return model;
	}
	
	
}
