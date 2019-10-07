package aticleBoard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import sqlMap.MybatisManager;

public class aticleBoardDAO {
// 게시글 목록
	public List<aticleBoardDTO> list(int start, int end) {
		SqlSession session = MybatisManager.getInstance().openSession();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		List<aticleBoardDTO> list = session.selectList("aticleboard.list",map);	
		session.close();
		
		return list;
	}
	
// view 페이지
	public aticleBoardDTO view(int num) {
		SqlSession session = MybatisManager.getInstance().openSession();
		
		aticleBoardDTO dto = session.selectOne("aticleboard.view", num);
		session.close();
		
		return dto;
	}

// 게시물 번호에 따른 답글달기
	public void reply(aticleBoardDTO dto) {
		SqlSession session = MybatisManager.getInstance().openSession();
		
		session.insert("aticleboard.reply",dto);
		
		session.commit();
		session.close();
	}

// 게시물과 답글 출력 순서 조정
	public void updateStep(int ref, int re_step) {
		SqlSession session = MybatisManager.getInstance().openSession();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("ref", ref);
		map.put("re_step", re_step);
		
		session.update("aticleboard.updateStep", map);
		session.commit();
		session.close();
	
	}
	
// 게시물 번호에 따른 수정
	public void update(aticleBoardDTO dto) {
		SqlSession session = MybatisManager.getInstance().openSession();
		session.update("aticleboard.update", dto);
		session.commit();
		session.close();
	}
	
// 게시물 번호에 따른 삭제
	public void delete(int num) {
		SqlSession session = MybatisManager.getInstance().openSession();
		session.delete("aticleboard.delete", num);
		session.commit();
		session.close();
	}
	
// 수정, 삭제 시 비밀번호 체크
	public String pwdCheck(int num, String passwd) {
		SqlSession session = MybatisManager.getInstance().openSession();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		map.put("passwd", passwd);
		
		String result = session.selectOne("aticleboard.pwdCheck", map);
		session.close();
		
		return result;
	}

// 게시물 추가
	public void insert(aticleBoardDTO dto) {
		SqlSession session = MybatisManager.getInstance().openSession();
		session.insert("aticleboard.insert", dto);
		session.commit();
		session.close();
	}
	
// 조건에 따른 게시물 검색
	public List<aticleBoardDTO> search(String option, String keyword){
		SqlSession session = MybatisManager.getInstance().openSession();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("option", option);
		map.put("keyword", "%"+keyword+"%");
		
		List<aticleBoardDTO> list = session.selectList("aticleboard.search", map);
		session.commit();
		session.close();
		
		return list;
	}
	
// 조회수 증가	
	public void readcount(int num) {
		SqlSession session = MybatisManager.getInstance().openSession();

		session.update("aticleboard.readcount", num);
		session.commit();
		session.close();
	}
	
// 테이블에 있는 총 레코드 수 계산
	public int recodeCount() {
		SqlSession session = MybatisManager.getInstance().openSession();
		int recodeCount = session.selectOne("aticleboard.recodeCount");
		session.close();
		return recodeCount;
	}
	
// 게시글 번호에 따른 댓글 추가
	public void commentAdd(aticleBoardCommentDTO dto) {
		SqlSession session = MybatisManager.getInstance().openSession();
		session.insert("aticleboard.commentAdd", dto);
		session.commit();
		session.close();
		
		}
	
// 댓글 조회처리
	public List<aticleBoardCommentDTO> commentList(int board_num){
		SqlSession session = MybatisManager.getInstance().openSession();
		List<aticleBoardCommentDTO> list = session.selectList("aticleboard.commentList", board_num);
		session.close();
		return list;
	
	}
}
