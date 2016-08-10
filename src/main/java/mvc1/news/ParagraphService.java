package mvc1.news;

import java.util.*;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class ParagraphService {

	public String Summary(String title,String content){

		Scanner st=new Scanner(title);
		String[] arrTitle=new String[15];
		int number=0;
		while(st.hasNext()){
			arrTitle[number]=st.next().replaceAll("'","").replaceAll("\"","");
			number++;
		}





		//기사 부분
		Scanner sc=new Scanner(content);

		String[] arr=new String[800];
		int count=0;
		while(sc.hasNext()){
			arr[count]=sc.next();
			count++;
		}

		String[] temp=new String[400];
		int c=0;
		for(int i=0;i<count;i++){

			if(arr[i].endsWith("는")){
				//System.out.println(arr[i].replace("는","")+"/");
				temp[c]=arr[i].replace("는","");
				c++;
			}else if(arr[i].endsWith("서")){
				//System.out.println(arr[i].replace("서","").replace("에","")+"/");
				temp[c]=arr[i].replace("서","").replace("에","");
				c++;
			}else if(arr[i].endsWith("와")){
				//System.out.println(arr[i].replace("와","")+"/");
				temp[c]=arr[i].replace("와","");
				c++;
			}else if(arr[i].endsWith("가")){
				//System.out.println(arr[i].replace("가","")+"/");
				temp[c]=arr[i].replace("가","");
				c++;
			}else if(arr[i].endsWith("과")){
				//System.out.println(arr[i].replace("과","")+"/");
				temp[c]=arr[i].replace("과","");
				c++;
			}else if(arr[i].endsWith("을")){
				//System.out.println(arr[i].replace("을","")+"/");
				temp[c]=arr[i].replace("을","");
				c++;
			}else if(arr[i].endsWith("를")){
				//System.out.println(arr[i].replace("를","")+"/");
				temp[c]=arr[i].replace("를","");
				c++;
			}else if(arr[i].endsWith("에")){
				//System.out.println(arr[i].replace("에","")+"/");
				temp[c]=arr[i].replace("에","");
				c++;
			}else if(arr[i].endsWith("은")){
				//System.out.println(arr[i].replace("은","")+"/");
				temp[c]=arr[i].replace("은","");
				c++;
			}else if(arr[i].endsWith("이")){
				//System.out.println(arr[i].replace("이","")+"/");
				temp[c]=arr[i].replace("이","");
				c++;
			}else if(arr[i].endsWith("로")){
				//System.out.println(arr[i].replace("로","")+"/");
				temp[c]=arr[i].replace("로","");
				c++;
			}


		}


/*
		String[] temp2=new String[200]; //temp2는 중복이 없는 단어의 배열 
		TreeSet<String> treeSet= new TreeSet<String>(); // treeSet는 중복 (x)


		for(int i=0;i<c;i++){
			treeSet.add(temp[i]);
		}


		int count2=0;// 중복을 줄인 temp2의 인덱스 크기
		Iterator it  = treeSet.iterator();
		while ( it.hasNext() ) {
			temp2[count2]= (String)it.next();
			count2++;

		}
		*/

		/*temp2는 중복없는 temp 는 모두 있는 배열  */

		Map<String,Integer> map=new HashMap<>();


		int num2=0;

		for(int i=0;i<c;i++){


			if(map.get(arr[i])==null){
				map.put(arr[i], 1);
				num2++;
			}else{
				map.put(arr[i],map.get(arr[i])+1);
			}


		}

		



		//number 제목단어 수 
				for(int i=0;i<number;i++){

					if(map.get(arrTitle[i])==null){
						map.put(arrTitle[i], 1);
						num2++;
					}else{
						map.put(arrTitle[i],map.get(arrTitle[i])+2);
					}

				}



		// 빈도수단어 (map value 기준으로) 오름차순으로 정렬 
		Set<Entry<String, Integer>> set = map.entrySet();

		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);

		Collections.sort( list, new Comparator<Map.Entry<String, Integer>>()
		{
			public int compare( Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2 )
			{
				return (o2.getValue()).compareTo( o1.getValue() );
			}
		} );


		int rank=0;
		String[] arrRank=new String[5]; // 빈도수를 추출하고 상위 n개단어만 활용

		for(Map.Entry<String, Integer> entry:list){
			//System.out.println(entry.getKey()+" ==== "+entry.getValue());
			if(rank<4){
				arrRank[rank]=entry.getKey();
				rank++;
			}

		}



		//문단 파트 

		Map<String, Integer> mapp = new TreeMap<String,Integer>();
		// 문단과 우선순위를 담을 것이다.

		Scanner sp=new Scanner(content);
		String[] paragraph=sp.nextLine().split("\\.");


		for(int i=0;i<paragraph.length;i++){
			int num_p=0;
			if(paragraph[i].contains(arrRank[0])){
				
				num_p++;
				
				mapp.put(paragraph[i],num_p);
			}
			if(paragraph[i].contains(arrRank[1])){
				
				num_p++;
				
				mapp.put(paragraph[i],num_p);
			}
			if(paragraph[i].contains(arrRank[2])){
				
				num_p++;
				
				mapp.put(paragraph[i],num_p);
			}
			if(paragraph[i].contains(arrRank[3])){
				
				num_p++;
				
				mapp.put(paragraph[i],num_p);
			}
			
		}


		Set<Entry<String, Integer>> set_p = mapp.entrySet();

		List<Entry<String, Integer>> list_p = new ArrayList<Entry<String, Integer>>(set_p);

		Collections.sort( list_p, new Comparator<Map.Entry<String, Integer>>()
		{
			public int compare( Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2 )
			{
				return (o2.getValue()).compareTo( o1.getValue() );
			}
		} );


		int rank_p=0;
		String[] arrRank_p=new String[3]; // 빈도수를 추출하고 상위 n개단어만 활용



		for(Map.Entry<String, Integer> entry:list_p){
			//System.out.println(entry.getKey()+" ==== "+entry.getValue());
			if(rank_p<3){
				arrRank_p[rank_p]=entry.getKey();
				rank_p++;
			}
		}
		/*System.out.println();
		System.out.println();
		System.out.println("--------3줄 요약----------");
		System.out.println(arrRank_p[0]);
		System.out.println(arrRank_p[1]);
		System.out.println(arrRank_p[2]);
		*/

		String result=arrRank_p[0]+"\n\n"+arrRank_p[1];





		return result;
	}

}
