package member;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import member.dao.memberDAO;
import member.dto.memberDTO;


@WebServlet("/member_servlet/*")
public class memberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("controller");
	
		String url = request.getRequestURL().toString();	// request.getRequestURL()로 읽어서 toString()으로 문자화 시킴
		memberDAO dao = new memberDAO();	// DB 처리하는 모듈
		
		// 회원가입
		if(url.contains("addMember.do")) {
			System.out.println("addMember.do Ok...");
			
			memberDTO dto = new memberDTO();	// 넘어온 인자를 보관하기 위해 만든 객체
			
			/* File uploadDir = new File(common.Constants.UPLOAD_PATH); */	// upload할 폴더
			File uploadDir = new File(common.Constans.UPOAD_PATH);
			
			
			if(!uploadDir.exists()) {	// upload할 폴더 유무 체크
				uploadDir.mkdir();	// 굳이 내가 폴더를 안 만들어도 경로가 없으면 경로를 만들어줌.
			}
			
			// upload라이브러리 활용 : cos.jar(서블릿 사이트 맨 아래에서 cos-20.08.zip 다운 후 압출 풀고 lib에 cos.jar를 WEB-INF 아래 lib에 복사)
			MultipartRequest multi = new MultipartRequest(	// request 기능+파일 업로드 기능
					request, 	// 기존의 request를 인자로 넘김
					common.Constans.UPOAD_PATH, 	// 업로드 할 몰더 경로
					common.Constans.MAX_UPLOAD, 	// 업로드 할 파일 용량 제한
					"utf-8", 	// 한글 인코딩
					new DefaultFileRenamePolicy() 	// 업로드시 중복된 파일 이름 자동 번호매김
			);
			String filename = "";
			int filesize=0;
			
			// 첨부파일에 대한 처리기능 : 입출력객체 사용시 무조건 IO예외 발생. 그래서 try catch문을 써줌
			try {
				Enumeration files = multi.getFileNames();	// 첨부파일 목록(파일이 하나 이상일 경우 한꺼번에 처리)(프로필사진에서는 파일이 하나라서 필요없을거같으나 할줄모르기에 똑같이씀)
				while(files.hasMoreElements()) {
					String file1 = (String) files.nextElement();	// 요소가 있으면 조회하고, 다음 요소로 이동
					
					filename = multi.getFilesystemName(file1);	// 요소의 파일이름을 추출
					
					File f1 = multi.getFile(file1);
					if(f1 != null)
						filesize = (int)f1.length();	// 파일 용량 추출
					System.out.println("filesize:"+filesize);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// 폼에서 넘어온 인자 처리 : 주의=> request객체 대신 MultipartRequest객체 사용!!!!
			String id = multi.getParameter("id");
			String pwd = multi.getParameter("pwd");
			String nick_name = multi.getParameter("nick_name");
			String email = multi.getParameter("email1")+"@"+multi.getParameter("email2");
			String address = multi.getParameter("address");
			String gender = multi.getParameter("gender");
			String birth = multi.getParameter("birth");
			String phone = multi.getParameter("phone1")+multi.getParameter("phone2")+multi.getParameter("phone3");
			
			dto.setId(id);
			dto.setPwd(pwd);
			dto.setNick_name(nick_name);
			dto.setEmail(email);
			dto.setAddress(address);
			dto.setGender(gender);
			dto.setBirth(birth);
			dto.setPhone(phone);
			
			if(filename==null || filename.trim().equals("")) {	// 첨부파일이 없을 경우 문자 "-"로 대체
				filename = "-";
			}
			dto.setPhoto(filename);
			System.out.println("dto:"+dto);
			
			dao.addMember(dto);	// DB에 입력 요청처리
			
			// view 페이지 이동
			String page = request.getContextPath() + "/board_servlet/addMember.do";
			response.sendRedirect(page);	// response.sendRedirect: forward와 달리 완전히 제어권을 page 쪽으로 넘겨줌.
		}
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}