package member.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.bean.MemberDTO2;
import member.dao.MemberDAO2;


@WebServlet("/WriterServlet2")
public class WriterServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//한글처리
		request.setCharacterEncoding("UTF-8");
		
		//데이터
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String gender = request.getParameter("gender");
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		String te11 = request.getParameter("te1");
		String te12 = request.getParameter("te12");
		String tel3 = request.getParameter("te13");
		String zipcode = request.getParameter("zipcode");
		String addr1 = request.getParameter("addr1");
		String addr2 = request.getParameter("addr2");
		
		MemberDTO2 memberDTO2 = new MemberDTO2();
		memberDTO2.setName(name);
		memberDTO2.setId(id);
		memberDTO2.setPwd(pwd);
		memberDTO2.setGender(gender);
		memberDTO2.setEmail1(email1);
		memberDTO2.setEmail2(email2);
		memberDTO2.setTel1(te11);
		memberDTO2.setTel2(te12);
		memberDTO2.setTel3(tel3);
		memberDTO2.setZipcode(zipcode);
		memberDTO2.setAddr1(addr1);
		memberDTO2.setAddr2(addr2);

		//DB
		MemberDAO2 memberDAO2 = MemberDAO2.getInstance();
		
		memberDAO2.write(memberDTO2);
		
		//응답
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("</head>");
		out.println("<body>");
		out.println("회원가입을 축하합니다.<br><br>");
		out.println("<input type=button value=로그인 onclick=location.href='/memberServlet/member/loginFormAlone.html'>");
		out.println("</body>");
		out.println("</html>");
	}

}
