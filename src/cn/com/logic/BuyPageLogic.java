package cn.com.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import cn.com.domain.GoodsBuy;
import cn.com.mapper.BuyCommentMapper;
import cn.com.mapper.BuyReplyMapper;
import cn.com.mapper.GoodsBuyMapper;
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
		
		String buySearch = request.getParameter("buySearch");
		log.info("search key---"+buySearch);
		String page = request.getParameter("page");
		if( StringUtil.isEmpty(page) ){
			page ="1";
		}
		Integer pageNum = Integer.valueOf(page);
		log.info("请求page为："+pageNum);//分页 ---第几页
		int pageAccount = 9;//分页 ---每页显示几条
		int begin = (pageNum-1)*pageAccount;//分页 ---从第几条开始
		int end = pageAccount;//分页 
		int pageNumber;//分页 --- 共多少页
		
		ModelAndView model = new ModelAndView();
		ModelMap modelMap = new ModelMap();
		model.setViewName("/buyArea/buyPage");
		modelMap.put("buySearch", buySearch);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("search", buySearch);
		map.put("begin", String.valueOf(begin));
		map.put("end", String.valueOf(end));
		ArrayList<GoodsBuy> buyList = goodsBuyMapper.selectBuyBysearch(map);
		modelMap.put("buyList", buyList);
		
		int buyListNumber = goodsBuyMapper.selectAllNum(map);
		if( buyListNumber % pageAccount == 0){
			pageNumber = buyListNumber/pageAccount ;
		}else{
			pageNumber = buyListNumber/pageAccount + 1;
		}
		log.info("共有数据页数："+pageNumber);
		int endPage;//分页 --- 分页显示到第几页
		if(pageNum > 3){
			endPage = pageNum + 2;
		}else{
			endPage = 5;
		}
		if(endPage > pageNumber){
			endPage = pageNumber;
		}
		int beginPage =  pageNum - 2;
		if(beginPage < 1){
			beginPage = 1;
		}
		modelMap.put("pageNum", pageNum );//分页 --- 当前页
		modelMap.put("beginPage", beginPage );//分页 --- 显示开始页
		modelMap.put("endPage", endPage);//分页 --- 显示结束页
		modelMap.put("pageNumber", pageNumber);//分页 --- 共有多少页
		
		model.addAllObjects(modelMap);
		
		return model;
	}
	
	
	
	
}
