package cn.com.logic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;





















import cn.com.domain.GoodsBuy;
import cn.com.domain.GoodsPublish;
import cn.com.domain.GoodsSub;
import cn.com.domain.GoodsType;
import cn.com.domain.PublishRule;
import cn.com.domain.User;
import cn.com.mapper.GoodsBuyMapper;
import cn.com.mapper.GoodsPublishMapper;
import cn.com.mapper.GoodsSubMapper;
import cn.com.mapper.GoodsTypeMapper;
import cn.com.mapper.PublishRuleMapper;
import cn.com.service.CommonService;
import cn.com.util.DateHelper;
import cn.com.util.PropertyFactory;

@Service
public class PublishLogic {
	protected static Logger log = Logger.getLogger(PublishLogic.class);
	
	@Autowired
	private GoodsPublishMapper goodsPublishMapper;
	@Autowired
	private GoodsBuyMapper goodsBuyMapper;
	@Autowired
	private GoodsTypeMapper goodsTypeMapper;
	@Autowired
	private GoodsSubMapper goodsSubMapper;
	@Autowired
	private PublishRuleMapper publishRuleMapper;
	
	public ModelAndView publish(HttpServletRequest request,HttpServletResponse response){
		
		ModelAndView model = new ModelAndView();
		ModelMap modelMap = new ModelMap();
		model.setViewName("/publish/sell");
		
		ArrayList<GoodsType> type = goodsTypeMapper.selectAll();
		ArrayList<GoodsSub> sub = goodsSubMapper.selectByTypeId(type.get(1).getId());
		modelMap.put("type", type);
		modelMap.put("sub", sub);
		
		model.addAllObjects(modelMap);
		return model;
	}
	
	public ModelAndView addPublish(HttpServletRequest request,HttpServletResponse response){
		
		ModelAndView model = new ModelAndView();
		ModelMap modelMap = new ModelMap();
		model.setViewName("redirect:/homePage");
		
		if(!ServletFileUpload.isMultipartContent(request)){
			log.info("表单传输格式错误");
			return model;
		}
		
		String path = System.getProperty("user.dir").replace("bin", "webapps")+PropertyFactory.getProperty("goodsImgPath");
		
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		((DiskFileItemFactory) factory).setRepository(new File(path));
		upload.setHeaderEncoding("UTF-8");
		
		GoodsPublish p = new GoodsPublish();
		
		StringBuffer picsb = new StringBuffer();
	    try {
			List<FileItem> items = upload.parseRequest(request);
			System.out.println("表单共有几个属性-----"+items.size());
			for(FileItem i:items){
	            if(i.isFormField()){
	            	log.info(i.getFieldName()+"~~~~~~~~");
	            	log.info(new String(i.getString().getBytes("ISO-8859-1"),"UTF-8"));
	            	if("goodsName".equals(i.getFieldName())){
	            		p.setGoodsName(new String(i.getString().getBytes("ISO-8859-1"),"UTF-8"));
	            	}
	            	if("goodsContent".equals(i.getFieldName())){
	            		p.setGoodsContent(new String(i.getString().getBytes("ISO-8859-1"),"UTF-8"));
	            	}
	            	if("tradePlace".equals(i.getFieldName())){
	            		p.setTradePlace(new String(i.getString().getBytes("ISO-8859-1"),"UTF-8"));
	            	}
	            	if("price".equals(i.getFieldName())){
	            	    p.setPrice(Float.valueOf(i.getString()) );
	            	}
	            	if("type".equals(i.getFieldName())){
	            	    p.setTypeId(Integer.valueOf(i.getString()));
	            	}
	            	if("sub".equals(i.getFieldName())){
	            		p.setSubId(Integer.valueOf(i.getString()));
	            	}
	            	if("isBargain".equals(i.getFieldName())){
	            		p.setIsBargain(i.getString()=="1"?true:false);
	            	}
	            	if("contact".equals(i.getFieldName())){
	            		 p.setContactMethod(new String(i.getString().getBytes("ISO-8859-1"),"UTF-8"));
	            	}
	            }else{
	            	log.info("图片-----"+i.getFieldName());
	            	if("pic0".equals(i.getFieldName())){
	            		String pic = "goods"+((int)(Math.random()*9000)+1000)+DateHelper.getyyyyMMddHHmmss(DateHelper.getyyyyMMddHHmmssString());
//		            	p.setPic(pic);
	            		System.out.println("path"+pic);
	            		if("".equals(picsb.toString())){
	            			picsb.append(pic);
	            		}else{
	            			picsb.append(","+pic);
	            		}
		            	i.write(new File(path,pic+".png"));
	            	}else if("pic1".equals(i.getFieldName())){
	            		String pic = "goods"+((int)(Math.random()*9000)+1000)+DateHelper.getyyyyMMddHHmmss(DateHelper.getyyyyMMddHHmmssString());
	            		System.out.println("path"+pic);
	            		if("".equals(picsb.toString())){
	            			picsb.append(pic);
	            		}else{
	            			picsb.append(","+pic);
	            		}
		            	i.write(new File(path,pic+".png"));
	            	}else if("pic2".equals(i.getFieldName())){
	            		String pic = "goods"+((int)(Math.random()*9000)+1000)+DateHelper.getyyyyMMddHHmmss(DateHelper.getyyyyMMddHHmmssString());
	            		System.out.println("path"+pic);
	            		if("".equals(picsb.toString())){
	            			picsb.append(pic);
	            		}else{
	            			picsb.append(","+pic);
	            		}
		            	i.write(new File(path,pic+".png"));
	            	}else if("pic3".equals(i.getFieldName())){
	            		String pic = "goods"+((int)(Math.random()*9000)+1000)+DateHelper.getyyyyMMddHHmmss(DateHelper.getyyyyMMddHHmmssString());
	            		System.out.println("path"+pic);
	            		if("".equals(picsb.toString())){
	            			picsb.append(pic);
	            		}else{
	            			picsb.append(","+pic);
	            		}
		            	i.write(new File(path,pic+".png"));
	            	}
	            	
//	            	String pic = "goods"+((int)Math.random()*1000)+DateHelper.getyyyyMMddHHmmss(DateHelper.getyyyyMMddHHmmssString());
//	            	p.setPic(pic);
//	            	i.write(new File(path,pic+".png"));
	            }
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	    p.setPic(picsb.toString());
	    
	    User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
	    p.setUserId(user.getId());
	    p.setCollectionNum(0);
	    p.setCreateTime(DateHelper.getyyyyMMddHHmmssString());
	    p.setId(UUID.randomUUID().toString());
	    p.setIsBuyed(false);
	    p.setVisited(0);
	    try {
			goodsPublishMapper.insert(p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return model;
		}
	    
		model.addAllObjects(modelMap);
		
		CommonService.addIntegral(user, 5);
		log.info("发布物品成功");
		
		return model;
	}
	
	public ModelAndView buy(HttpServletRequest request,HttpServletResponse response){
		ModelAndView model = new ModelAndView();
		ModelMap modelMap = new ModelMap();
		model.setViewName("/publish/buy");
		
		ArrayList<GoodsType> type = goodsTypeMapper.selectAll();
//		ArrayList<GoodsSub> sub = goodsSubMapper.selectAll();
		modelMap.put("type", type);
//		modelMap.put("sub", sub);
		
		model.addAllObjects(modelMap);
		return model;
	}
	
	public ModelAndView addBuy(HttpServletRequest request,HttpServletResponse response){
		ModelAndView model = new ModelAndView();
		ModelMap modelMap = new ModelMap();
		model.setViewName("redirect:/buyPage");
		
		if(!ServletFileUpload.isMultipartContent(request)){
			log.info("表单传输格式错误");
			return model;
		}
		
		String path = System.getProperty("user.dir").replace("bin", "webapps")+PropertyFactory.getProperty("goodsImgPath");
		
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		((DiskFileItemFactory) factory).setRepository(new File(path));
		upload.setHeaderEncoding("UTF-8");
		
		GoodsBuy b = new GoodsBuy();
		
	    try {
			List<FileItem> items = upload.parseRequest(request);
			System.out.println("表单共有几个属性-----"+items.size());
			for(FileItem i:items){
	            if(i.isFormField()){
	            	log.info(i.getFieldName()+"~~~~~~~~");
	            	log.info(new String(i.getString().getBytes("ISO-8859-1"),"UTF-8"));
	            	if("goodsName".equals(i.getFieldName())){
	            		b.setGoodsName(new String(i.getString().getBytes("ISO-8859-1"),"UTF-8"));
	            	}
	            	if("goodsContent".equals(i.getFieldName())){
	            		b.setGoodsContent(new String(i.getString().getBytes("ISO-8859-1"),"UTF-8"));
	            	}
	            	if("tradePlace".equals(i.getFieldName())){
	            		b.setTradePlace(new String(i.getString().getBytes("ISO-8859-1"),"UTF-8"));
	            	}
	            	if("price".equals(i.getFieldName())){
	            	    b.setWishPrice(Float.valueOf(i.getString()));
	            	}
	            	if("type".equals(i.getFieldName())){
	            	    b.setTypeId(Integer.valueOf(i.getString()));
	            	}
	            	if("contact".equals(i.getFieldName())){
	            		b.setContactMethod(new String(i.getString().getBytes("ISO-8859-1"),"UTF-8"));
	            	}
	            }else{
	            	
	            }
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	    User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
		b.setCreateTime(DateHelper.getyyyyMMddHHmmssString());
		b.setId(UUID.randomUUID().toString());
		b.setIsBuy(false);
		b.setUserId(user.getId());
	    
		try {
			goodsBuyMapper.insert(b);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return model;
		}
	    
		model.addAllObjects(modelMap);
		
		CommonService.addIntegral(user, 5);
		log.info("发布求购成功");
		
		return model;
	}
	
	public ModelAndView rule(HttpServletRequest request,HttpServletResponse response){
		ModelAndView model = new ModelAndView();
		ModelMap modelMap = new ModelMap();
		model.setViewName("/rule");
		
		ArrayList<PublishRule> ruleList = publishRuleMapper.selectAll();
		modelMap.put("ruleList", ruleList);
		
		model.addAllObjects(modelMap);
		
		return model;
	}
	
	public String typeChange(HttpServletRequest request,HttpServletResponse response){
		String type = request.getParameter("type");
		
		ArrayList<GoodsSub> sub = goodsSubMapper.selectByTypeId(Integer.valueOf(type));
		
		return JSONArray.fromObject(sub).toString();
	}
	
}
