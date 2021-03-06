package cn.com.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import cn.com.domain.GoodsPublish;
import cn.com.domain.GoodsSub;
import cn.com.domain.GoodsType;
import cn.com.domain.HotSearch;
import cn.com.domain.User;
import cn.com.domain.UserCollection;
import cn.com.mapper.GoodsPublishMapper;
import cn.com.mapper.GoodsSubMapper;
import cn.com.mapper.GoodsTypeMapper;
import cn.com.mapper.HotSearchMapper;
import cn.com.mapper.UserCollectionMapper;
import cn.com.mapper.UserMapper;
import cn.com.service.CommonService;
import cn.com.util.PropertyFactory;
import cn.com.util.StringUtil;


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
	@Autowired
	private UserCollectionMapper userCollectionMapper;
	@Autowired
	private HotSearchMapper hotSearchMapper;
	
	public ModelAndView homePage(HttpServletRequest request,HttpServletResponse response){
		
		ModelAndView model = new ModelAndView();
		ModelMap modelMap = new ModelMap();
		model.setViewName("/home/homePage");
		
		String forwordSearch = request.getParameter("forwordSearch");
		log.info("forwordSearch是"+forwordSearch);
		String forwordType = request.getParameter("forwordType");
		log.info("forwordType是"+forwordType);
		String forwordSub = request.getParameter("forwordSub");
		log.info("forwordSub是"+forwordSub);
		String order = request.getParameter("order");
		log.info("order是"+order);
		String page = request.getParameter("page");
		log.info("page是"+page);
		modelMap.put("forwordSearch", forwordSearch);
		modelMap.put("forwordType", forwordType);
		modelMap.put("forwordSub", forwordSub);
		modelMap.put("order", order);
		modelMap.put("page", page);
		
		ArrayList<GoodsType> type = goodsTypeMapper.selectAll();
		ArrayList<GoodsSub> sub = goodsSubMapper.selectAll();
		modelMap.put("type", type);
		modelMap.put("sub", sub);
		
		String logoutMsg = request.getParameter("logoutMsg");
		modelMap.put("logoutMsg", logoutMsg);
		
		model.addAllObjects(modelMap);
		return model;
	}
	
	public ModelAndView homePageList(HttpServletRequest request,HttpServletResponse response){
		
		String time_collection = request.getParameter("time_collection");
		if( StringUtil.isEmpty(time_collection) ){
			time_collection ="time";
		}
		String type = request.getParameter("type");
		String sub = request.getParameter("sub");
		String search = request.getParameter("search");
		String pageNum = request.getParameter("pageNum");
		if( StringUtil.isEmpty(pageNum) ){
			pageNum ="1";
		}
		
		ModelAndView model = new ModelAndView();
		model.setViewName("/home/list");
		
		ModelMap modelMap = goodsSearch(time_collection,type,sub,search,Integer.valueOf(pageNum));
		
		model.addAllObjects(modelMap);
		return model;
	}
	
	
	private ModelMap goodsSearch(String time_collection,String type,String sub,String search,Integer pageNum){
		log.info("请求time_collection为："+time_collection);
		log.info("请求type为："+type);
		log.info("请求sub为："+sub);
		log.info("请求search为："+search);
		
		ModelMap modelMap = new ModelMap();
		
		modelMap.put("time_collection", time_collection);
		modelMap.put("type", type);
		modelMap.put("sub", sub);
		modelMap.put("search", search);
		
		log.info("请求page为："+pageNum);//分页 ---第几页
		int pageAccount = 9;//分页 ---每页显示几条
		int begin = (pageNum-1)*pageAccount;//分页 ---从第几条开始
		int end = pageAccount;//分页 
		int pageNumber;//分页 --- 共多少页
		
		ArrayList<GoodsPublish> goods = new ArrayList<GoodsPublish>();
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("search", search);
		map.put("sub", sub);
		map.put("type", type);
		if("time".equals(time_collection)){
			map.put("time_collection", "create_time");
		}else if("collection".equals(time_collection)){
			map.put("time_collection", "collection_num");
		}
		map.put("begin", String.valueOf(begin));
		map.put("end", String.valueOf(end));
		
		int goodsNumber = goodsPublishMapper.selectGoodsSize(map);
		log.info("共有数据条数："+goodsNumber);
		modelMap.put("goodsSize", goodsNumber);//共搜索出物品数量
		goods = goodsPublishMapper.selectGoods(map);
		
		//如果使用的search搜索，且搜索出来的物品大于1，则插入搜索表
		if(goodsNumber > 1 && search != null){
			HotSearch hot = hotSearchMapper.selectByText(search);
			if(hot != null){
				hot.setHot(hot.getHot()+1);
			}else{
				hot = new HotSearch();
				hot.setId(UUID.randomUUID().toString());
				hot.setSearchText(search);
				hot.setHot(1);
			}
		}	
		
		//搜索显示第一个图片
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
		for (int i = 0; i < goods.size() ; i++) {
			GoodsPublish good = goods.get(i);
			String picName = good.getPic().split(",")[0];
			good.setPic(PropertyFactory.getProperty("serverPath")
					+PropertyFactory.getProperty("goodsImgPath")+picName+".png");
			//用户若登录，显示已收藏
			if(user != null){
				Map<String, String> ucmap = new HashMap<String, String>();
				ucmap.put("goodsid", good.getId());
				ucmap.put("userid", user.getId());
				UserCollection uc = userCollectionMapper.selectByGoodsidAndUserid(ucmap);
				if(uc == null){
					good.setIsCollection(false);
				}else{
					good.setIsCollection(true);
				}
			}else{
				good.setIsCollection(false);
			}
			
		}
		modelMap.put("goodsPublish", goods);
		
		if( goodsNumber % pageAccount == 0){
			pageNumber = goodsNumber/pageAccount ;
		}else{
			pageNumber = goodsNumber/pageAccount + 1;
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
		
		return modelMap;
	}
	
	
}
