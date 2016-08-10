package mvc1.news;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.SystemPropertyUtils;

import mvc1.news.ParagraphService;
import mvc1.user.MemberMapper;

@Service
public class NewsService {
	
	@Autowired
	private ParagraphService paragraphService;

	public ArrayList<RssDTO> getCatCodeList(String query) {
		Scanner sc = null;
		ArrayList<RssDTO> list = new ArrayList<RssDTO>(100);
		String id="", name="",type="",cat="",rss="";
		System.out.println("------------------service "+query);
		try {

			sc =new Scanner(new File("C:\\project\\softproject\\InfoHelper\\rssurl.txt"),"UTF-8");


			while(sc.hasNextLine()){

				String line = sc.nextLine();
				StringTokenizer st = new StringTokenizer(line);
				id = st.nextToken();
				name = st.nextToken();
				type = st.nextToken();
				cat = st.nextToken();
				rss = st.nextToken();



				if(query.equals(cat)){
					System.out.println("일치 일치 > "+cat);

					RssDTO url = new RssDTO(id, name, type, cat, rss);
					list.add(url);
				}//선택한 종류 정보만  list add 
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return list;
	}



	public ArrayList<NewsDTO> getInfoList(ArrayList<RssDTO> list)throws Exception{
		int count=0;
		int i=0;
		int j=0;
		int z=0;
		ArrayList<NewsDTO> newsList = new ArrayList<NewsDTO>(10);

		NewsDTO news=null;



		for(RssDTO u:list){
			
			Document doc = Jsoup.connect(u.getRss()).get();
			
			//System.out.println("rss > "+u.getRss());


			Elements name=doc.select("item title");

			for(Element n: name){

				String title=n.text();

				news=new NewsDTO();
				news.setNews_title(title);
				newsList.add(news);

			}




			Elements url=doc.select("item link");



			for(Element r: url){

				String articleUrl=r.text();

				NewsDTO in=newsList.get(i);
				in.setNews_url(articleUrl);
				newsList.set(i, in);
				i++;

			}

			//Elements des=doc.select("item description");
			//Elements d_url=doc.select("item link"); // rink를 통해 descript을 통째로 가져오기 위해
			
			
			
          if(u.getCat().equals("IT")){


			for(Element d:url){

				String rink=d.text();
			
				 Document doc2 = Jsoup.connect(rink).get(); //이부분때문에 IT말고 다른분야는 에러발생
			        Elements des = doc2.select("#newsView");
				
			        

				NewsDTO in=newsList.get(j);
				String descript=des.text();
				//System.out.println("des > "+descript);
				 //paragraphService.Summary(in.getNews_title(), descript);
				 //System.out.println("title >"+in.getNews_title());
				 //System.out.println("test > "+paragraphService.Summary(in.getNews_title(), descript));
				in.setNews_description(paragraphService.Summary(in.getNews_title(), descript));
				newsList.set(j, in);
				j++;

			}
			
          }if(u.getCat().equals("society")){
        	  
        	  for(Element d:url){

  				String rink=d.text();
  			
  				 Document doc2 = Jsoup.connect(rink).get(); //이부분때문에 IT말고 다른분야는 에러발생
  			        Elements des = doc2.select("#newsView");
  			        

  				NewsDTO in=newsList.get(j);
  				String descript=des.text();
  				//System.out.println("des > "+descript);
  				 //paragraphService.Summary(in.getNews_title(), descript);
  				 //System.out.println("title >"+in.getNews_title());
  				 //System.out.println("test > "+paragraphService.Summary(in.getNews_title(), descript));
  				in.setNews_description(paragraphService.Summary(in.getNews_title(), descript));
  				newsList.set(j, in);
  				j++;

  			}
        	  
          }
          if(u.getCat().equals("conference")){
        	  
        	  System.out.println("------conference");
        	  
        	  Elements description=doc.select("item description");



  			for(Element d: description){

  				//String descript=d.text().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
  				String descript=d.text();
  				System.out.println(descript);
  				

  				NewsDTO in=newsList.get(j);
  				in.setNews_description(descript);
  				newsList.set(j, in);
  				j++;

  			}
        	  
          }

			Elements date=doc.select("item pubDate");



			for(Element t:date){

				String pubdate=t.text();

				NewsDTO in=newsList.get(z);
				in.setNews_date(pubdate);
				newsList.set(z, in);
				z++;

			}


		}


		return newsList;
	}
	


}
