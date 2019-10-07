package board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import board.dto.BoardCommentDTO;
import board.dto.BoardDTO;
import sqlMap.MybatisManager;

public class BoardDAO {

	// 1. 게시목록	
		public List<BoardDTO> list(int start, int end){
			// 1. DB처리하는 SqlSession 객체 생성
			SqlSession session = MybatisManager.getInstance().openSession(); 	

			// 2. sql 명령어 처리
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("start", start);
			map.put("end", end);
			
			List<BoardDTO> list = session.selectList("board.list", map);			
			
			session.close();
			
			return list;
		}
		
		// 2. 게시글 글쓰기 처리
		public void insert(BoardDTO dto) {
			SqlSession session = MybatisManager.getInstance().openSession();
			session.insert("board.insert", dto);
			session.commit(); // insert, delete, update 명령어는 commit() 수행 해야됨.
			session.close();  // 자원 해제
		}
		
		// 3. 게시물 상세 내용 조회 - view.jsp
		public BoardDTO view(int num) {
			SqlSession session = MybatisManager.getInstance().openSession();
			BoardDTO dto = session.selectOne("board.view", num);
			session. close();
			
			return dto;			
		}
		
		// 4. 게시물 조회수 증가
		public void plusReadCount(int num) {
			SqlSession session = MybatisManager.getInstance().openSession();
			session.update("board.plusReadCount", num);
			session.commit();
			session.close();
			}
		
		// 5. 게시물 번호에 대한 댓글 조회 처리
		public List<BoardCommentDTO> commentList(int board_num){
			SqlSession session = MybatisManager.getInstance().openSession();
			List<BoardCommentDTO> list = session.selectList("board.commentList", board_num);
			session.close();
			
			return list;
		}
		
		// 6. 게시물 번에 대한 댓글 입력 처리
		public void commentAdd(BoardCommentDTO dto) {
			SqlSession session = MybatisManager.getInstance().openSession();
			session.insert("board.commentAdd", dto);
			session.commit();
			session.close();
		}
		
	
		// 7. 게시물 내용 수정 처리
		public void update(BoardDTO dto) {
			SqlSession session = MybatisManager.getInstance().openSession();
			session.update("board.update", dto);
			session.commit();
			session.close();
		}
		
		// 8. 작성자와 비밀번호에 대한 값을 넘겨줌
		public String passCheck(int num, String passwd) {
			SqlSession session = MybatisManager.getInstance().openSession();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("num", num);
			map.put("passwd", passwd);
			
			String result = session.selectOne("board.pass_check", map);
			session.close();
			
			return result;
		}
		
		// 9. 게시물 번호 삭제
		public void delete(int num) {
			SqlSession session = MybatisManager.getInstance().openSession();
			session.delete("board.delete", num);
			session.commit();
			session.close();
		}
		
		// 10. board 테이블 총 레코드 갯수 처리
		public int recordCount() {
			SqlSession session = MybatisManager.getInstance().openSession();
			int recordCount = session.selectOne("board.recordCount");
			session.close();
			
			return recordCount;
		}
}
		


















