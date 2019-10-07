package notice;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import sqlMap.MybatisManager;




@WebServlet("/notice_servlet/*")
public class noticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public noticeController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		  System.out.println("Controller...ok"); noticeDAO dao = new noticeDAO();
		  noticeDTO dto = new noticeDTO(); 
		  String url= request.getRequestURL().toString(); //주소를 문자화 해서 찍어본다
		  
		  System.out.println(url);
		  
		  System.out.println("Controller dao : "+dao);
		  
		  //공지쓰기
		  if (url.contains("write.do")) {
		  
		  System.out.println("write.do....ok");
		  
		  String page=request.getContextPath()+"/notice/noticeWrite.jsp";
		 System.out.println("주소"+page); response.sendRedirect(page);
		  
		  }
		  //공지사항 DB 처리
		  else if(url.contains("insert.do")){
			  System.out.println("공지사항 insert.do ..ok");
			String n_writer = request.getParameter("n_writer");
			String n_title= request.getParameter("n_title");
			String n_detail= request.getParameter("n_detail");
			System.out.println("insert writer : "+n_writer);
			dto.setN_title(n_title);
			dto.setN_detail(n_detail);
			dto.setN_writer(n_writer);
			
			dao.insert(dto);
			//공지사항 목록으로 이동
			
			String page=request.getContextPath()+"/notice_servlet/list.do";
			response.sendRedirect(page);
			
			
		  }
		  //공지사항 목록
		  else if(url.contains("list.do")) {
			  System.out.println("공지사항 목록");
			
			  //공지사항 페이징 start!
				int curPage =1;
				if(request.getParameter("curPage")!=null) {
					curPage = Integer.parseInt(request.getParameter("curPage"));
				}
				
				int count =dao.recordCount(); //게시물 갯수							//총 레코드 갯수 추출
				int page_scale = 5; 	//한페이지 표시되는 레코드 갯수 
				int totPage= (int)Math.ceil(count*1.0/page_scale); // 필요한 페이지 수
				
				System.out.println("총 페이지 수: "+totPage);
				
				//페이지 블록 페이징
				int block_scale = 3;									// 한 블록에 해당 되는 페이지 수 
				int tot_block =(int)Math.ceil(totPage*1.0/block_scale);	// 총 블록의 개수
				
				int cur_block=(int)Math.ceil(curPage-1)/block_scale+1;	//편재 페이지 블록 번호
				int block_start =(cur_block-1)*block_scale+1;			//현재 페이지의 블록 시작 번호
				int block_end=block_start + block_scale-1;//현재 페이지의 블록 마지막 번호를 추출
				
				if(block_end>totPage) block_end = totPage; //블록 스케일이 딱 떨어지지않을 때
				int prev_page=
						cur_block == 1 ? 1:(cur_block-1)*block_scale; //이전 블럭페이지
				int next_page=
						cur_block > tot_block ? (cur_block*block_scale):(cur_block+1)*block_scale;
				
				if(next_page>=totPage) next_page = totPage;
				
				
				int start=(curPage-1)*page_scale+1;		//게시물 블럭 레코드 시작 번호 : (현제페이지 번호 -1)*페이지당 레코드갯수 +1 
				int end =start+page_scale-1; 
				
				
				
			  List<noticeDTO> list = dao.list(start,end);
			  request.setAttribute("list", list);
			
			  	request.setAttribute("totPage", totPage);
				request.setAttribute("start", start);
				request.setAttribute("end", end);
				request.setAttribute("block_scale", block_scale);
				
				request.setAttribute("blockStart", block_start);
				request.setAttribute("blockEnd", block_end);
				request.setAttribute("curBlock", cur_block);
				request.setAttribute("totBlock", tot_block);
				request.setAttribute("prev_page", prev_page);
				request.setAttribute("next_page", next_page);
			  
				String page ="/notice/noticeList.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(page);
				rd.forward(request, response);
		  
		  
		  
		  }
		  //공지사항 게시글 보기
		  else if(url.contains("read.do")) {
			  System.out.println("공지사항 자세히보기 입니다");
			  
			  int n_no = Integer.parseInt(request.getParameter("n_no"));
			  dao.plusCount(n_no);
			  
			  dto =dao.detailNotice(n_no);
			  request.setAttribute("dto", dto);
				
		
			  String page="/notice/noticeRead.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(page);
				rd.forward(request, response);
			  
			  
		  }
		  //공지사항 수정 페이지
		  else if (url.contains("edit.do")) {
			  System.out.println("공지사항 수정 목록입니다");
			  int n_no = Integer.parseInt(request.getParameter("n_no"));
				request.setAttribute("dto", dao.detailNotice(n_no));
				
				
				String page="/notice/noticeEdit.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(page);
				rd.forward(request, response);
		  }
		  //공지사항 수정 DB처리
		  else if(url.contains("update.do")) {
			  System.out.println("공지사항 수정 처리됩니다");
			  
			  
			  
			  String n_title = request.getParameter("n_title");
			  String n_detail = request.getParameter("n_detail");
			  
			  int n_no = Integer.parseInt(request.getParameter("n_no"));
			 System.out.println("수정할 글 번호 : "+n_no);
			  dto.setN_title(n_title);
			  dto.setN_detail(n_detail);
			  dto.setN_no(n_no);
			  dao.update(dto);
			  
				String page = "${path}/notice_servlet/list.do";	// 게시물 목록 view 페이지 이동
				response.sendRedirect(page);
			 
			  
			  
		  }
		  //공지사항 삭제
		  else if (url.contains("delete.do")) {
			  System.out.println("공지 삭제");
			  
			  int n_no = Integer.parseInt(request.getParameter("n_no"));
			  dao.delete(n_no);

				String page = "${path}/notice_servlet/list.do";	// 게시물 목록 view 페이지 이동
				response.sendRedirect(page);
		  }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);

	}

}
