package mvc1.news;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import mvc1.Result;
import mvc1.user.MemberDTO;
import mvc1.user.MemberMapper;
import mvc1.user.UserService;

@Controller
public class NewsController {
	
	@Autowired
	private NewsMapper newsMapper;
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private NewsService newsService;
	
	@RequestMapping(value="mdelnews.do",method=RequestMethod.POST)
	public @ResponseBody Result delNews(@RequestParam("news_id")int id){
		System.out.println("id > "+id);
		System.out.println("-------Del---------");
		newsMapper.newsDelete(id);
		return Result.SUCCESS;
	}
	
	
	 @RequestMapping(value="/msaveNews.do", method=RequestMethod.POST)
	    public @ResponseBody Result saveNews(NewsDTO news) {
	        newsMapper.newsInsert(news);
	        return Result.SUCCESS;
	    }
	 
	 @RequestMapping("/mMyNews.do")
	    public @ResponseBody List<NewsDTO> list(@RequestParam("studentNum")String studentNum) {
		 System.out.println("+++++++++++mmynews++++++++");
		 List<NewsDTO> mynewsList=memberMapper.myNews(studentNum);
	        return mynewsList;
	    }
	 
	
	@RequestMapping(value="/mnews.do", method=RequestMethod.GET)
    public @ResponseBody ArrayList<NewsDTO> login(@RequestParam("category") String category)throws Exception{
		String query=category;
System.out.println("newmobile--------------------");
System.out.println(category);
		ArrayList<RssDTO> list=newsService.getCatCodeList(query);
		ArrayList<NewsDTO> infoList=newsService.getInfoList(list);
		return infoList;
    }
	
	@RequestMapping(value="newsInsert.do",method=RequestMethod.POST)
	public String newsInsert(@RequestParam("category")String category,NewsDTO news)throws Exception{
		newsMapper.newsInsert(news);
		category= URLEncoder.encode(category, "UTF-8");//redirect 한글깨짐현상 해결
		String url="redirect:/main.do?category="+category;
		return url;
		
	}
	
	@RequestMapping(value="newsDelete.do",method=RequestMethod.POST)
	public String newsDelete(@RequestParam("category")String category,NewsDTO news)throws Exception{
		String studentNum=news.getMember_id();
		int nid=news.getNews_id();
		newsMapper.newsDelete(nid);
		
		String url="redirect:/myPage.do?mid="+studentNum+"&category="+category;
		return url;
	}

}
