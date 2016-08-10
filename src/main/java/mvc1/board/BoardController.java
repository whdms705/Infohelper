package mvc1.board;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import mvc1.Result;
import mvc1.news.NewsDTO;
import mvc1.news.RssDTO;
import mvc1.user.MemberDTO;



@Controller
public class BoardController {
	@Autowired
	private BoardMapper boardMapper;
	
	
	@RequestMapping(value="calendar.do",method=RequestMethod.GET)
	public String calendarView()throws Exception{

		return "calendar";
	}
	
	@RequestMapping(value="excelview.do",method=RequestMethod.GET)
	public String excelView()throws Exception{
	
		List<NoticeDTO> noticelist=boardMapper.noticeList();
		WriteListToExcelFile.writeNoticeListToFile("cordova.xls", noticelist);
		
		return "main";
	}
	
	@RequestMapping(value="excelread.do",method=RequestMethod.GET)
	public String excelRead(Model model)throws Exception{	
		List<NoticeDTO> list = ReadExcelFileToList.readExcelData("C:\\project\\softproject\\InfoHelper\\sample.xlsx");
	    model.addAttribute("noticeList",list);
		
		return "excelRead";
	}
	
	
	@RequestMapping(value="address.do",method=RequestMethod.GET)
	public String address()throws Exception{
		System.out.println("1");
	
		return "address";
	}
	
	@RequestMapping(value="fund.do",method=RequestMethod.GET)
	public String department()throws Exception{

		return "department";

	}
	
	@RequestMapping(value="fundview.do", method=RequestMethod.GET)
	public @ResponseBody List<CategoryDTO> category(@RequestParam("id") int id,@RequestParam("category") String category)throws Exception{

		System.out.println("---------------------------end >"+category);
		System.out.println("---------------------------end+++++++++++++"+id+"+++++++++++= >");

		List<CategoryDTO> fundList=null;
		fundList=boardMapper.fundView(id,category);
		return fundList;
	}
	
////////
	@RequestMapping(value="introduction.do",method=RequestMethod.GET)
	public String introduction()throws Exception{


		return "introduction";



	}

	@RequestMapping(value="mQna.do", method=RequestMethod.GET)
	public @ResponseBody List<NoticeDTO> qnaLoad(@RequestParam("end") int end)throws Exception{

		System.out.println("end >"+end);

		List<NoticeDTO> mQnaList=null;
		mQnaList=boardMapper.selectMQna(end);
		return mQnaList;
	}

	@RequestMapping(value="allMQna.do", method=RequestMethod.GET)
	public @ResponseBody List<NoticeDTO> qnaAll()throws Exception{



		List<NoticeDTO> mQnaList=null;
		mQnaList=boardMapper.noticeList();
		return mQnaList;
	}


	@RequestMapping(value="qnaPage.do",method=RequestMethod.GET)
	public String qnaPage(@RequestParam("pageNum")String pageN,Model model)throws Exception{



		int pageNum;



		if(pageN!=null){
			pageNum=Integer.parseInt(pageN);

		}else{
			pageNum=1;

		}

		int count=boardMapper.selectBoardCount();

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


		List<NoticeDTO> notice=boardMapper.selectForQna(start,end);
		System.out.println("***********************************");




		List paging=new ArrayList();
		paging.add("<ul class='pagination'>");
		paging.add("<li><a href=qnaPage.do?pageNum=1>처음</a></li>");
		if(temp>=1){
			paging.add("<li><a href=qnaPage.do?pageNum="+pre+">이전</a></li>");
		}
		for(int i=pre;i<next;i++){
			if(i==page){
				break;
			}
			if((i+1)==pageNum){

				paging.add("<li class='active'><a>"+(i+1)+"</a></li>");
			}
			else{

				paging.add("<li><a href=qnaPage.do?pageNum="+(i+1)+">"+(i+1)+"</a></li>");
			}
		}
		if(next<page){
			paging.add("<li><a href=qnaPage.do?pageNum="+(next+1)+">다음</a></li>");
		}

		paging.add("<li><a href=qnaPage.do?pageNum="+page+">마지막</a></li>");
		paging.add("</ul>");




		//request.setAttribute("count", count);




		model.addAttribute("paging",paging);
		model.addAttribute("noticeList",notice);


		/*
			 List<NoticeDTO> boardList=boardMapper.noticeList();
			model.addAttribute("boardList",boardList);
		 */





		return "qnaPage";



	}

	@RequestMapping(value="qnaWrite.do",method=RequestMethod.GET)
	public String qnaWrite()throws Exception{
		return "qnaWrite";
	}

	@RequestMapping(value="/mQnaSave.do", method=RequestMethod.POST)
	public @ResponseBody Result articleSave(NoticeDTO qna) {
		System.out.println("---------------------------------------");
		System.out.println(qna.getMember_id()+" / "+qna.getNotice_title()+" / "+qna.getNotice_content());
		if (StringUtils.isBlank(qna.getNotice_title())) return new Result(false, "제목을 입력하세요.");
		if (StringUtils.isBlank(qna.getNotice_content())) return new Result(false, "내용을 입력하세요.");
		boardMapper.qnaInsert(qna);
		return Result.SUCCESS;
	}


	@RequestMapping(value="mQnaDelete.do", method=RequestMethod.GET)
	public @ResponseBody Result qnaDelete(@RequestParam("id") int id)throws Exception{

		boardMapper.noticeDelete(id);
		return Result.SUCCESS;
	}
	
	
	@RequestMapping(value="/mQnaUpdate.do", method=RequestMethod.POST)
	public @ResponseBody Result qnaUpdate(NoticeDTO qna) {
		System.out.println("---------------------------------------");
		System.out.println(qna.getNotice_id()+" / "+qna.getNotice_title()+" / "+qna.getNotice_content());
		
		boardMapper.noticeUpdate(qna);
		return Result.SUCCESS;
	}


	@RequestMapping(value="qnaInsert.do",method=RequestMethod.POST)
	public String qnaInsert(NoticeDTO notice)throws Exception{

		boardMapper.qnaInsert(notice);


		String url="redirect:/qnaPage.do?pageNum=1";
		return  url;
	}






}
