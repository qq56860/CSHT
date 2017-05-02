package cn.com.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import cn.com.domain.BuyComment;
import cn.com.domain.BuyReply;
import cn.com.domain.GoodsPublish;
import cn.com.domain.HotSearch;
import cn.com.domain.PublishComment;
import cn.com.domain.PublishReply;
import cn.com.domain.SeeInfoLog;
import cn.com.domain.User;
import cn.com.domain.UserCollection;
import cn.com.mapper.BuyCommentMapper;
import cn.com.mapper.BuyReplyMapper;
import cn.com.mapper.GoodsPublishMapper;
import cn.com.mapper.HotSearchMapper;
import cn.com.mapper.PublishCommentMapper;
import cn.com.mapper.PublishReplyMapper;
import cn.com.mapper.SeeInfoLogMapper;
import cn.com.mapper.UserCollectionMapper;
import cn.com.util.DateHelper;
import cn.com.util.PropertyFactory;
import cn.com.util.ResultModel;

@Service
public class GoodsLogic {
	protected static Logger log = Logger.getLogger(GoodsLogic.class);
	
	@Autowired
	private GoodsPublishMapper goodsPublishMapper;
	@Autowired
	private PublishCommentMapper publishCommentMapper;
	@Autowired
	private PublishReplyMapper publishReplyMapper;
	@Autowired
	private BuyCommentMapper buyCommentMapper;
	@Autowired
	private BuyReplyMapper buyReplyMapper;
	@Autowired
	private UserCollectionMapper userCollectionMapper;
	@Autowired
	private HotSearchMapper hotSearchMapper;
	@Autowired
	private SeeInfoLogMapper seeInfoLogMapper;
	
	public ModelAndView detail(HttpServletRequest request,HttpServletResponse response){
		String goodsid = request.getParameter("id");
		log.info("查看的物品id为："+goodsid);
		
		ModelAndView model = new ModelAndView();
		ModelMap modelMap = new ModelMap();
		model.setViewName("/goodsDetail/goodsDetail");
		
		GoodsPublish publish = goodsPublishMapper.selectByPrimaryKey(goodsid);
		
		User user = (User)request.getSession().getAttribute("user");
		if(user != null){
			Map<String, String> ucmap = new HashMap<String, String>();
			ucmap.put("goodsid", goodsid);
			ucmap.put("userid", user.getId());
			UserCollection uc = userCollectionMapper.selectByGoodsidAndUserid(ucmap);
			if(uc == null){
				log.info("用户已登录，未收藏");
				publish.setIsCollection(false);
			}else{
				log.info("用户已收藏");
				publish.setIsCollection(true);
			}
		}else{
			log.info("用户未登录");
			publish.setIsCollection(false);
		}
			
		publish.setVisited(publish.getVisited()+1);
		try {
			goodsPublishMapper.updateByPrimaryKey(publish);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("数据库更新错误");
			return model;
		}
		
		modelMap.put("goods", publish);
		System.out.println("---------------------------"+publish.getUser().getNickName());
		String picName[] = publish.getPic().split(",");
		List<String> picList = new ArrayList<String>();
		log.info("该物品有图片"+picName.length+"张");
		for (int i = 0; i < picName.length; i++) {
			picList.add(PropertyFactory.getProperty("serverPath")
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
		model.setViewName("/comment");
		
		ArrayList<PublishComment> commentList = publishCommentMapper.selectByGoodsId(goodsid);
		log.info("共"+commentList.size()+"条评论");
		modelMap.put("commentList", commentList);
		
		modelMap.put("publishOrBuy", "publish");
		modelMap.put("goodsid", goodsid);
		
		model.addAllObjects(modelMap);
		return model;
	}
	
	public ModelAndView publishReply(HttpServletRequest request,HttpServletResponse response){
		String commentUserid = request.getParameter("commentUserid");
		String commentNickName = request.getParameter("commentNickName");
		log.info("id为："+commentUserid+";昵称为："+commentNickName );
		String commentid = request.getParameter("commentid");
		log.info("回复的评论id为："+commentid);
		String publishOrBuy = request.getParameter("publishOrBuy");
		log.info("publishOrBuy-----"+publishOrBuy);
		
		ModelAndView model = new ModelAndView();
		ModelMap modelMap = new ModelMap();
		model.setViewName("/reply");
		
		
		if("publish".equals(publishOrBuy)){
			ArrayList<PublishReply> replyList = publishReplyMapper.selectByCommentId(commentid);
			log.info("本评论有"+replyList.size()+"条回复");
			modelMap.put("replyList", replyList);
		}else if("buy".equals(publishOrBuy)){
			ArrayList<BuyReply> replyList = buyReplyMapper.selectByCommentId(commentid);
			log.info("本评论有"+replyList.size()+"条回复");
			modelMap.put("replyList", replyList);
		}
		
		modelMap.put("commentid", commentid);
		modelMap.put("commentUserid", commentUserid);
		modelMap.put("commentNickName", commentNickName);
		
		
		model.addAllObjects(modelMap);
		return model;
	}
	
	
	public ModelAndView buyComment(HttpServletRequest request,HttpServletResponse response){
		String goodsid = request.getParameter("goodsid");
		log.info("求购商品---id为："+goodsid+"的评论");
		
		ModelAndView model = new ModelAndView();
		ModelMap modelMap = new ModelMap();
		model.setViewName("/comment");
		
		ArrayList<BuyComment> commentList = buyCommentMapper.selectByGoodsId(goodsid);
		log.info("共"+commentList.size()+"条评论");
		modelMap.put("commentList", commentList);
		
		modelMap.put("publishOrBuy", "buy");
		modelMap.put("goodsid", goodsid);
		
		model.addAllObjects(modelMap);
		return model;
	}
	
	public String addOrRemoveCollection(HttpServletRequest request,HttpServletResponse response){
		String goodsid = request.getParameter("goodsid");
		
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("goodsid", goodsid);
		map.put("userid", user.getId());
		UserCollection uc = userCollectionMapper.selectByGoodsidAndUserid(map);
		if(uc != null){
			log.info(user.getId()+"已经收藏过"+goodsid);
			log.info("取消收藏");
			try {
				userCollectionMapper.deleteByUseridAndGoodsid(map);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return JSONObject.fromObject(ResultModel.responseFaild("数据库删除userCollection错误")).toString();
			}
			int num = userCollectionMapper.selectByGoodsid(goodsid);
			log.info("物品id---"+goodsid+"收藏人数---"+num);
			GoodsPublish record = new GoodsPublish();
			record.setId(goodsid);
			record.setCollectionNum(num);
			try {
				goodsPublishMapper.updateByPrimaryKeySelective(record);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return JSONObject.fromObject(ResultModel.responseFaild("数据库更新goods错误")).toString();
			}
			log.info("取消收藏成功");
			JSONObject obj = JSONObject.fromObject(ResultModel.responseSuccess(""+num));
			obj.put("type", "remove");
			return obj.toString();
		}
		uc = new UserCollection();
		uc.setGoodsId(goodsid);
		uc.setUserId(user.getId());
		try {
			userCollectionMapper.insert(uc);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return JSONObject.fromObject(ResultModel.responseFaild("数据库添加userCollection错误")).toString();
		}
		
		
		int num = userCollectionMapper.selectByGoodsid(goodsid);
		log.info("物品id---"+goodsid+"收藏人数---"+num);
		GoodsPublish record = new GoodsPublish();
		record.setId(goodsid);
		record.setCollectionNum(num);
		try {
			goodsPublishMapper.updateByPrimaryKeySelective(record);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JSONObject.fromObject(ResultModel.responseFaild("数据库更新goods错误")).toString();
		}
		log.info("收藏成功");
		JSONObject obj = JSONObject.fromObject(ResultModel.responseSuccess(""+num));
		obj.put("type", "add");
		return obj.toString();
		
	}
	
	public String hotSearch(HttpServletRequest request,HttpServletResponse response){
		String keyword = request.getParameter("keyword");
		
		ArrayList<HotSearch> hotSearchList = hotSearchMapper.selectByKey5(keyword);
		
		return JSONArray.fromObject(hotSearchList).toString();
	}
	
	public String recordInfo(HttpServletRequest request,HttpServletResponse response){
		String viewedId = request.getParameter("viewedId");
		String goodsId = request.getParameter("goodsId");
		String type = request.getParameter("type");
		
		SeeInfoLog record = new SeeInfoLog();
		record.setId(UUID.randomUUID().toString());
		record.setTime(DateHelper.getyyyyMMddHHmmssString());
		record.setViewId(((User)request.getSession().getAttribute("user")).getId());
		record.setGoodsId(goodsId);
		record.setViewed(viewedId);
		record.setType(type);
		try {
			seeInfoLogMapper.insert(record);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("添加查看异常");
		}
		log.info("添加查看记录成功");
		return null;
	}
	
	
}
