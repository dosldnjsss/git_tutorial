package member.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.bean.MemberDTO;
import member.dao.MemberDAO;


@WebServlet("/WriteServlet") //url mapping 
public class WriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
        									//요청						//응답
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//post방식은 자동 한글처리가 안된다. (get방식은 자동 한글처리가 된다)
		request.setCharacterEncoding("UTF-8");//요청에 대한 한글처리 - post방식 
		//응답이랑 요청에서 따로 한글처리해야한다
		
		//데이터 (12개)  - name 속성의 이름을 가져옴 
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String gender = request.getParameter("gender");
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		String tel1 = request.getParameter("te11");
		String tel2 = request.getParameter("te12");
		String te13 = request.getParameter("te13");
		String zipcode = request.getParameter("zipcode");
		String addr1 = request.getParameter("addr1");
		String addr2 = request.getParameter("addr2");
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setName(name);
		memberDTO.setId(id);
		memberDTO.setPwd(pwd);
		memberDTO.setGender(gender);
		memberDTO.setEmail1(email1);
		memberDTO.setEmail2(email2);
		memberDTO.setTel1(tel1);
		memberDTO.setTel2(tel2);
		memberDTO.setTel3(te13);
		memberDTO.setZipcode(zipcode);
		memberDTO.setAddr1(addr1);
		memberDTO.setAddr2(addr2);
		
		//DB - 내용이 너무 많아서 MemberDAO.java 
		//MemberDAO memberDAO = new MemberDAO();//new는 계속 생성 되서 new 하면 안된다. 
			     	//객체명 - 클래스명과 같이(앞만 소문자)  
		MemberDAO memberDAO = MemberDAO.getInstance();//싱글톤 - 메모리에 1번 생성해서 계속 사용한다.
		
		memberDAO.write(memberDTO);//호출
		
		
		//응답
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("</head>");
		out.println("<body>");
		out.println("회원가입을 축하합니다.<br><br>");
		out.println("<input type=button value=로그인 onclick=location.href='/memberServlet/member/loginForm.html'>");
		//회원가입 창 : http://localhost:8080/memberServlet/member/writeForm.html도 가능 (위에 축약)
		//주소는 반드시 ''(싱글따옴표) 써야 한다- 나머지는 생략 가능
		out.println("</body>");
		out.println("</html>");
		
		
	}

}
