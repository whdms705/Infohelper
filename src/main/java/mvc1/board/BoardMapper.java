package mvc1.board;

import java.util.*;
import org.apache.ibatis.annotations.Param;

import mvc1.user.MemberDTO;

public interface BoardMapper {
	
	void noticeDelete(int nid);
	void noticeUpdate(NoticeDTO notice);
	void qnaInsert(NoticeDTO notice);
	List<NoticeDTO> noticeList();
	NoticeDTO noticeDetail(int nid);
	
	int selectBoardCount();
	List<NoticeDTO> selectForQna(@Param("start")int start,@Param("end")int end);
	
	List<NoticeDTO> selectMQna(int end);
	
	List<CategoryDTO> fundView(@Param("id")int id,@Param("category")String category);
	
	void noticeTest(NoticeDTO notice);

}
