package mvc1.user;

import java.util.*;
import org.apache.ibatis.annotations.Param;

import mvc1.news.NewsDTO;





public interface MemberMapper {
	
    MemberDTO selectByLoginId(String studentNum);
	
	MemberDTO loginPro(@Param("studentNum")String studentNum,@Param("password")String password);
	
	List<NewsDTO> myNews(String mid); //본인이 저장한 기사 가져오기
	List<MemberDTO> selectForManage(@Param("start")int start,@Param("end")int end);// 회원관리
	
	int selectUserCount();
	
    void insert(MemberDTO member);
    void myUpdate(MemberDTO member);
    void memberDelete(String studentNum);//회원탈퇴

}
