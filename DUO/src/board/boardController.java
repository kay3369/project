package board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dao.BoardDAO;
import board.dto.BoardCommentDTO;
import board.dto.BoardDTO;


@WebServlet("/board_servlet/*")
public class boardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet ok...");
		
		String url = request.getRequestURL().toString(); // 메모리에 맞는 타입전환
		
		System.out.println(url);
		
		BoardDAO dao = new BoardDAO(); // DB 처리하는 모듈
		
		// url 분석 후 관련 모듈 및 페이지 전환
				if (url.contains("list.do")) {
					System.out.println("list.do ok...");	
														
					// 요청한 페이지 번호 처리
					int curPage = 1;
					if (request.getParameter("curPage") != null) {
						curPage = Integer.parseInt(request.getParameter("curPage"));
					}
					
					// 페이지 나누기
					int count = dao.recordCount();	// 총 레코드 갯수
					System.out.println("count"+ count);
					int page_scale = 10;						// 한 페이지에 출력되는 레코드 갯수
					int totalPage = (int)Math.ceil(count*1.0/page_scale);	// 총 페이지수
					
				    System.out.println("총 페이지 수 : " + totalPage);
			/*
			 * int block_scale = 10; int total_block =
			 * (int)Math.ceil(totalPage*1.0/block_scale);
			 * 
			 * int cur_block = (int)Math.ceil(curPage)-1/block_scale+1; int block_start =
			 * (cur_block-1)*block_scale+1; int block_end = block_start+block_scale-1;
			 * 
			 * if (block_end > totalPage) block_end = totalPage; int prev_page = cur_block
			 * == 1? 1:(cur_block-1)*block_scale; int next_page = cur_block > total_block ?
			 * (cur_block*block_scale):(cur_block+1)*block_scale;
			 * 
			 * System.out.println("cur_block : " + cur_block);
			 * System.out.println("block_start : " + block_start);
			 * System.out.println("block_end : " + block_end);
			 * System.out.println("prev_page : " + prev_page);
			 * System.out.println("next_page : " + next_page);
			 * 
			 * request.setAttribute("cur_block", cur_block);
			 * request.setAttribute("total_block", total_block);
			 * request.setAttribute("blockstart", block_start);
			 * request.setAttribute("blockend", block_end);
			 * request.setAttribute("prev_page", prev_page);
			 * request.setAttribute("next_page", next_page);
			 */
					
					int start = (curPage-1)*page_scale + 1;
					int end = start + page_scale - 1;
					
					System.out.println("현재 : " + curPage);
					System.out.println("시작 : " + start);
					System.out.println("마지막 : " +end);
					
					List<BoardDTO> list = dao.list(start,end);
					request.setAttribute("list", list);	
					
					request.setAttribute("totalPage", totalPage);
					
					String page = "/board/list.jsp";
					RequestDispatcher rd = request.getRequestDispatcher(page);
					rd.forward(request, response);
					
				} else if(url.contains("insert.do")) {
					// System.out.println("insert do ok...");
					
					String writer = request.getParameter("writer");
					String subject = request.getParameter("subject");
					String content = request.getParameter("content");
					String passwd = request.getParameter("passwd");
					String ip      = request.getRemoteAddr();	// getRemoteAddr();	가 뭔지부터 보고 뒤에 NULL 값 처리하기!!! 
					
					BoardDTO dto = new BoardDTO();	// 넘어온 인자를 보관
					dto.setWriter(writer);
					dto.setSubject(subject);
					dto.setContent(content);
					dto.setPasswd(passwd);
					dto.setIp(ip);
					
					
					System.out.println("insertdto" + dto);
					
					dao.insert(dto);
					
					String page = request.getContextPath() + "/board_servlet/list.do"; 
					response.sendRedirect(page);
				}  else if(url.contains("write.do")) {
					System.out.println("write.do ok...");
					
					String page = request.getContextPath() + "/board/write.jsp";
					response.sendRedirect(page);
					
				} else if (url.contains("view.do")) {
					System.out.println("view.do ok...!");
					
					int num = Integer.parseInt(request.getParameter("num"));
					
					dao.plusReadCount(num);	// 게시물 조회수 증가
					
					BoardDTO dto = dao.view(num);
					request.setAttribute("dto", dto);
					
					String page = "/board/view.jsp";
					RequestDispatcher rd = request.getRequestDispatcher(page);
					rd.forward(request, response);
					
				} else if (url.contains("comment_reply.do")) {
					System.out.println("comment_reply ok...!");
					
					 int board_num = Integer.parseInt(request.getParameter("board_num"));
					 System.out.println("board_num" + board_num);
					
					List<BoardCommentDTO> list = dao.commentList(board_num);
					request.setAttribute("list", list);
					
					String page = "/board/comment_list.jsp";
					RequestDispatcher rd = request.getRequestDispatcher(page);
					rd.forward(request, response);
					
					} else if (url.contains("comment_add.do")) {
						System.out.println("comment_add ok...!");
						
						int board_num =  Integer.parseInt(request.getParameter("board_num"));
						String writer = request.getParameter("writer");
						String content = request.getParameter("content");
						
						System.out.println("board_num :" + board_num + "writer :" + writer + "content :" +content);
						
						BoardCommentDTO dto = new BoardCommentDTO();
						dto.setWriter(writer);
						dto.setContent(content);
						dto.setBoard_num(board_num);
						
						dao.commentAdd(dto);
					} else if (url.contains("pass_check.do")) {
						System.out.println("pass_check.do ok...!");
						
						int num = Integer.parseInt(request.getParameter("num"));
						String passwd = request.getParameter("passwd");
						System.out.println("num :" + num + "password :" + passwd);
						
						String result = dao.passCheck(num, passwd);	// 비밀번호 체크 요청 처리(비번이 틀리면 nul 값이 리턴)
						
						String page = "";	// view 페이지 경로
						
						if (result != null) {
							System.out.println("비밀번호 일치 합니다.");
							request.setAttribute("dto", dao.view(num));
							
							page = "/board/edit.jsp";
							RequestDispatcher rd = request.getRequestDispatcher(page);
							rd.forward(request, response);
						} else {
							System.out.println("비밀번호 불일치 합니다.");
							page = request.getContextPath() + "/board_servlet/view.do?num="+num+"&message=error";
							response.sendRedirect(page);
						}
						
					} else if (url.contains("update.do")) {
						System.out.println("update.do ok...!");
						
						BoardDTO dto = new BoardDTO();
						
						// 수정 폼에서 넘어온 자료 처리
						String writer    = request.getParameter("writer");
						String subject  = request.getParameter("subject");
						String content = request.getParameter("content");
						String passwd = request.getParameter("passwd");
						String ip = request.getRemoteAddr();
						
						int num = Integer.parseInt(request.getParameter("num"));	// 게시물번호
						
						dto.setNum(num);
						dto.setWriter(writer);
						dto.setSubject(subject);
						dto.setContent(content);
						dto.setPasswd(passwd);
						dto.setIp(ip);
						
						System.out.println("dto" + dto);
						
						// 수정 폼 안에서 비밀번호가 일치하지 않을 경우 나타나는 붉은 메시지 >> "비밀번호 틀렸습니다."
						String result = dao.passCheck(num, passwd);
						if (result != null) {
							System.out.println("수정/ 삭제 비밀번호 체크 일치");
							dao.update(dto);
							String page = "${path}/board_servlet/list.do";
							response.sendRedirect(page);
						} else  {
							System.out.println("수정 / 삭제 비밀번호 불일치");
							request.setAttribute("dto", dto);	// 수정폼에서 넘어온 데이터를 보관
							
							String page = "/board/edit.jsp?pass_error =y";
							RequestDispatcher rd = request.getRequestDispatcher(page);
							rd.forward(request, response);
						}
					} else if (url.contains("delete.do")) {
						System.out.println("delete.do ok...!");
						
						int num = Integer.parseInt(request.getParameter("num"));
						System.out.println("delete.do, num="+num);
						
						dao.delete(num);	// 게시물 번호 삭제 대한 요청
						
						String page = "${path}/board_servlet/list.do";
						response.sendRedirect(page);
					}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
		System.out.println("doPost ok...");
	}

}
