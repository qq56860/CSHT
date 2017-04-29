package cn.com.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import cn.com.domain.BuyComment;
import cn.com.domain.BuyReply;
import cn.com.domain.GoodsBuy;
import cn.com.domain.GoodsPublish;
import cn.com.domain.PublishComment;
import cn.com.domain.PublishReply;
import cn.com.domain.User;
import cn.com.domain.UserNews;
import cn.com.mapper.BuyCommentMapper;
import cn.com.mapper.BuyReplyMapper;
import cn.com.mapper.GoodsBuyMapper;
import cn.com.mapper.GoodsPublishMapper;
import cn.com.mapper.PublishCommentMapper;
import cn.com.mapper.PublishReplyMapper;
import cn.com.mapper.UserNewsMapper;
import cn.com.service.CommonService;
import cn.com.util.DateHelper;
import cn.com.util.PropertyFactory;

@Service
public class CommentLogic {
	protected static Logger log = Logger.getLogger(CommentLogic.class);
	
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
	private UserNewsMapper userNewsMapper;
	@Autowired
	private GoodsBuyMapper goodsBuyMapper;
	
	
	public ModelAndView addComment(HttpServletRequest request,HttpServletResponse response){
		String goodsid = request.getParameter("goodsid");
		String publishOrBuy = request.getParameter("publishOrBuy");
		log.info(publishOrBuy+"评论的物品id为："+goodsid);
		
		ModelAndView model = new ModelAndView();
		ModelMap modelMap = new ModelMap();
		
		User user = (User)SecurityUtils.getSubject().getSession().getAttribute("user");
		String content = request.getParameter("content");
		String id = UUID.randomUUID().toString();
		if("publish".equals(publishOrBuy)){
			model.setViewName("redirect:/goods/publish/commentList?goodsid="+goodsid);
			PublishComment p = new PublishComment();
			p.setContent(content);
			p.setGoodsId(goodsid);
			p.setId(id);
			p.setObserverId(user.getId());
			p.setTime(DateHelper.getyyyyMMddHHmmssString());
			
			try {
				publishCommentMapper.insert(p);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return model;
			}
			
			UserNews un = new UserNews();
			un.setId(UUID.randomUUID().toString());
			un.setIsRead(false);
			un.setContent(content);
			un.setTime(DateHelper.getyyyyMMddHHmmssString());
			un.setArea("publish");
			un.setWho(user.getId());
			un.setGoodsId(goodsid);
			GoodsPublish pub = goodsPublishMapper.selectByPrimaryKey(goodsid);
			un.setUserId(pub.getUserId());
			
			try {
				userNewsMapper.insert(un);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return model;
			}
			
		}else if("buy".equals(publishOrBuy)){
			model.setViewName("redirect:/goods/buy/commentList?goodsid="+goodsid);
			BuyComment b = new BuyComment();
			b.setContent(content);
			b.setGoodsId(goodsid);
			b.setId(id);
			b.setObserverId(user.getId());
			b.setTime(DateHelper.getyyyyMMddHHmmssString());
			
			try {
				buyCommentMapper.insert(b);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return model;
			}
			
			UserNews un = new UserNews();
			un.setId(UUID.randomUUID().toString());
			un.setIsRead(false);
			un.setContent(content);
			un.setTime(DateHelper.getyyyyMMddHHmmssString());
			un.setArea("buy");
			un.setWho(user.getId());
			un.setGoodsId(goodsid);
			GoodsBuy bu = goodsBuyMapper.selectByPrimaryKey(goodsid);
			un.setUserId(bu.getUserId());
			
			try {
				userNewsMapper.insert(un);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return model;
			}
		}
		
		model.addAllObjects(modelMap);
		
		CommonService.addIntegral(user, 1);
		log.info("添加评论成功");
		
		return model;
	}
	
	public ModelAndView addReply(HttpServletRequest request,HttpServletResponse response){
		String goodsid = request.getParameter("goodsid");
		String commentid = request.getParameter("commentid");
		String publishOrBuy = request.getParameter("publishOrBuy");
		String replayid = request.getParameter("replayid");
		log.info(publishOrBuy+"回复的评论id为："+commentid);
		
		ModelAndView model = new ModelAndView();
		
		
		User user = (User)SecurityUtils.getSubject().getSession().getAttribute("user");
		String content = request.getParameter("content");
		String id = UUID.randomUUID().toString();
		if("publish".equals(publishOrBuy)){
			model.setViewName("redirect:/goods/publish/commentList?goodsid="+goodsid);
			PublishReply r = new PublishReply();
			r.setCommentId(commentid);
			r.setId(id);
			r.setReplay(content);
			r.setReplayId(replayid);
			r.setReplayerId(user.getId());
			r.setReplayTime(DateHelper.getyyyyMMddHHmmssString());
			
			try {
				publishReplyMapper.insert(r);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return model;
			}
			
		}else if("buy".equals(publishOrBuy)){
			model.setViewName("redirect:/goods/buy/commentList?goodsid="+goodsid);
			BuyReply b = new BuyReply();
			b.setCommentId(commentid);
			b.setId(id);
			b.setReplay(content);
			b.setReplayId(replayid);
			b.setReplayerId(user.getId());
			b.setReplayTime(DateHelper.getyyyyMMddHHmmssString());
			
			try {
				buyReplyMapper.insert(b);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return model;
			}
			
		}
		
		CommonService.addIntegral(user, 1);
		log.info("添加回复成功");
		
		return model;
		
	}
	
	
}
