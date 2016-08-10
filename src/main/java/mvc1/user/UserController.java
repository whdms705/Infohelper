package mvc1.user;

import java.util.ArrayList;
import java.util.List;


import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import mvc1.news.RssDTO;
import mvc1.news.NewsService;
import mvc1.Result;
import mvc1.news.NewsDTO;



@Controller
public class UserController {


	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private EmailSender emailSender;
	@Autowired
	private MyAuthenticationProvider myAuthenticationProvider;
	@Autowired
	private UserService userService;
	@Autowired
	private NewsService newsService;
	
	@RequestMapping(value="disease.do",method=RequestMethod.GET)
	public String diseaseInsert()throws Exception{
		System.out.println("----------222222222222--------------");
		Document doc = Jsoup.connect("http://kvcc.kahis.go.kr/pms/web/disease/pmsDiseaseView.do?iNo=4&iType=01&diseaseNo=1&searchCondition=&searchKeyword=&searchKeyword1=&searchKeyword2=&searchKeyword3=&pageIndex=1").get();
		
		Elements titles = (Elements) doc.select("td.TD_color_left");
		//System.out.println(titles);
		//System.out.println(titles.toString());
		int i=1;
		for(Element e: titles){
            System.out.println("id >"+i+"download: " + e.text());
            i++;
        }

		return "main";
		
	}


	@RequestMapping("/send.do")
	public String sendEmailAction () throws Exception {

		Email email = new Email();

		String reciver = "whdms705@naver.com"; //받을사람의 이메일입니다.-> naver nate 등등 
		String subject = "이메일 제목";
		String content = "이메일 내용입니다.";

		email.setReciver(reciver);
		email.setSubject(subject);
		email.setContent(content);
		emailSender.SendEmail(email);

		return "main";
	}
	/*
	@ModelAttribute
	public void addLoginIdToModel(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String studentNum = (principal instanceof UserDetails) ? 
				((UserDetails)principal).getUsername() : principal.toString();
				model.addAttribute("studentNum", studentNum);
	}
	 */

	//user PART 

	@RequestMapping(value="main.do",method=RequestMethod.GET)
	public String main(@RequestParam("category")String category,Model model)throws Exception{
		String query=category;
		System.out.println("category >>>>> >>>>> >>>>> "+category);

		ArrayList<RssDTO> list=newsService.getCatCodeList(query);
		ArrayList<NewsDTO> infoList=newsService.getInfoList(list);


		model.addAttribute("infoList",infoList);
		model.addAttribute("member",userService.getCurrentUser());
		return "main";
	}

	@RequestMapping(value="/signUp.do",method=RequestMethod.GET)
	public String hello(Model model) {
		return "signUp";
	}

	@RequestMapping(value="/signUp.do", method=RequestMethod.POST)
	public String signUp(MemberDTO member, Model model)throws Exception{
		System.out.println(member.getPost()+" / "+member.getJibunAddress()+" / "+member.getJibunAddress());
		String road=member.getJibunAddress()+"("+member.getPost()+")";
		String jibun=member.getJibunAddress()+"("+member.getPost()+")";
		member.setJibunAddress(jibun);
		member.setRoadAddress(road);
		
		memberMapper.insert(member);
		return "redirect:/index.do";
	}
	
	 @RequestMapping(value="/userSave.do", method=RequestMethod.POST)
	    public @ResponseBody Result userSave(MemberDTO member)throws Exception {
	        if (StringUtils.isBlank(member.getStudentNum())) return new Result(false, "학번을 입력하세요.");
	        if (StringUtils.isBlank(member.getName())) return new Result(false, "이름을 입력하세요.");
	        if (StringUtils.isBlank(member.getEmail())) return new Result(false, "이메일을 입력하세요.");
	        if (StringUtils.isBlank(member.getPassword())) return new Result(false, "비밀번호을 입력하세요.");
	        else memberMapper.insert(member);
	        
	        return Result.SUCCESS;
	    }
	 

	@RequestMapping("/login.do")
	public String login(Model model){
		return "login";
	}
	
	@RequestMapping(value="/mlogin.do", method=RequestMethod.POST)
    public @ResponseBody MemberDTO login(@RequestParam("studentNum") String studentNum,
            @RequestParam("password") String password) {
		System.out.println("mobile----------------------------");
        if (studentNum == null || password == null) return null;
        MemberDTO user = memberMapper.selectByLoginId(studentNum);
        if (user == null) return null;
        if (user.getPassword().equals(password)) return user;
        return null;
    }

	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String loginPro(Model model,MemberDTO member)throws Exception{
		Authentication authentication = 
				myAuthenticationProvider.authenticate(member.getStudentNum(), member.getPassword());
		if (authentication != null)
			SecurityContextHolder.getContext().setAuthentication(authentication);
		return "test";
	}

	@RequestMapping(value="/index.do", method=RequestMethod.GET)
	public String index(Model model) {

		return "index";
	}

	@RequestMapping(value="/myPage.do",method=RequestMethod.GET)
	public String myPages(@RequestParam("mid")String studentNum,@RequestParam("category")String category,Model model){


		String category1,category2,category3;
		String query=category;

		List<NewsDTO> list=null;
		MemberDTO member=null;
		category1="myNews";
		category2="breakaway";
		category3="update";

		if(query.equals(category1)){
			System.out.println("111111111111111111111111");
			list=memberMapper.myNews(studentNum);
			model.addAttribute("infoList",list);
		}else if(query.equals(category2)){

		}else if(query.equals(category3)){
			member=memberMapper.selectByLoginId(studentNum);
			model.addAttribute("member", member);

		}

		return "myPage";

	}



	@RequestMapping(value="userManage.do",method=RequestMethod.GET)
	public String userManage(@RequestParam("pageNum")String pageN,Model model)throws Exception{

		int pageNum;



		if(pageN!=null){
			pageNum=Integer.parseInt(pageN);

		}else{
			pageNum=1;

		}

		int count=memberMapper.selectUserCount();
		
		System.out.println("conut >> "+count);

		if(count==0){
			count=1;
		}
		final int VIEW=10;
		final int PAGEVIEW=5;

		int start=(pageNum-1)*VIEW;

		int end=VIEW;
		
		double e_count=count;
		double exam=(count/10.0)%1.0;
		
		System.out.println("exam >> "+exam);
		
		double temp=Math.ceil(count/VIEW);
		System.out.println(" temp "+temp);
		int page;
		if(exam==0.0){
			System.out.println("================");
			page=(int)temp;
		}else{
			System.out.println("---------------");
			page=(int)temp+1;
		}


		
		
		System.out.println("page "+page);

		double pageNumTemp=pageNum;
		
		temp=Math.ceil(pageNumTemp/PAGEVIEW);
		
		System.out.println("pageNumTemp"+pageNumTemp +" temp "+temp);
		
		temp=temp-1;


		int pre=(int)temp*5;

		int next=((int)temp+1)*5;


		List<MemberDTO> member=memberMapper.selectForManage(start,end);
		


		List paging=new ArrayList();
		paging.add("<ul class='pagination'>");
		paging.add("<li><a href=userManage.do?pageNum=1>처음</a></li>");
		if(temp>=1){
			paging.add("<li><a href=userManage.do?pageNum="+pre+">이전</a></li>");
		}
		for(int i=pre;i<next;i++){
			if(i==page){
				break;
			}
			if((i+1)==pageNum){
				
				paging.add("<li class='active'><a>"+(i+1)+"</a></li>");
			}
			else{
				
				paging.add("<li><a href=userManage.do?pageNum="+(i+1)+">"+(i+1)+"</a></li>");
			}
		}
		if(next<page){
			paging.add("<li><a href=userManage.do?pageNum="+(next+1)+">다음</a></li>");
		}

		paging.add("<li><a href=userManage.do?pageNum="+page+">마지막</a></li>");
		paging.add("</ul>");

		
		
		
		//request.setAttribute("count", count);




		model.addAttribute("paging",paging);
		model.addAttribute("memberList",member);
		return "userManage";
	}





	@RequestMapping(value="myUpdate.do",method=RequestMethod.POST)
	public String myUpdate(@RequestParam("category")String category,MemberDTO member)throws Exception{
		memberMapper.myUpdate(member);
		String url="redirect:/myPage.do?mid="+member.getStudentNum()+"&category="+category;
		return url;
	}


	@RequestMapping("memberDelete.do")
	public String memberDelete(MemberDTO member)throws Exception{
		memberMapper.memberDelete(member.studentNum);
		String url="redirect:/index.do";
		return url;
	}

	@RequestMapping("userDelete.do")
	public String userDelete(MemberDTO member,@RequestParam("pageNum")int pageNum)throws Exception{
		//System.out.println("test              >>>>>>>>>>>>>.."+member.studentNum+" / "+pageNum);
		
		memberMapper.memberDelete(member.studentNum);
		String url="redirect:/userManage.do?pageNum="+pageNum;
		return url;
	}





}
