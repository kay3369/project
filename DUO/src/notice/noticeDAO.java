package notice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.ibatis.session.SqlSession;

import sqlMap.MybatisManager;

public class noticeDAO {
	private SqlSession session;
	//게시판 목록
	public List<noticeDTO> list(int start, int end){
		
		session=MybatisManager.getInstance().openSession();        
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start",start);
		map.put("end",end);
		
		List<noticeDTO> list = session.selectList("Notice.list",map);
		session.close();
		
		return list;
	}
	//2.게시글 글쓰기 처리
	public void insert(noticeDTO dto) {
		session=MybatisManager.getInstance().openSession();
		System.out.println(" insert : "+dto);
		session.insert("Notice.insertNotice", dto);
		System.out.println("insert : "+dto);
		session.commit(); //insert,delete,update 사용시 반드시 commit 수행!
		session.close();
		
		
		
		
	}

	//게시물 상세보기
	public noticeDTO  detailNotice(int n_no) {
	session = MybatisManager.getInstance().openSession();
	noticeDTO dto = session.selectOne("Notice.detailNotice",n_no);
	session.close();
	return dto; 
	}
	
	public void plusCount(int n_no) {
		session = MybatisManager.getInstance().openSession();
		session.update("Notice.plusCount",n_no);
		session.commit();
		session.close();
	}
	//게시물삭제
	public void delete(Integer n_no)  {
		session = MybatisManager.getInstance().openSession();
		session.delete("Notice.deleteNotice",n_no);
		session.commit();
		session.close();
	}

	//게시판 수정

	public void update(noticeDTO dto) {
		session = MybatisManager.getInstance().openSession();
		session.update("Notice.updateNotice",dto);
		session.commit();
		session.close();
	}
	public int recordCount() {
		session = MybatisManager.getInstance().openSession();
		int recordCount= session.selectOne("Notice.recordCount");
		session.close();
		return recordCount;
	}
	//공지사항 관리자모드
	public List<noticeDTO> listAll(){
		session = MybatisManager.getInstance().openSession();
		List<noticeDTO> listAll = session.selectList("Notice.listAll");
		session.close();
		return listAll;
		
	}
	//공지사항 삭제 관리자모드
	public void deleteone(Integer n_no)  {
		session = MybatisManager.getInstance().openSession();
		session.delete("Notice.deleteone",n_no);
		session.commit();
		session.close();
	}
	
}
