package member.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.bean.MemberDTO;
import member.dao.MemberDAO;


@WebServlet("/CheckIdServlet")
public class CheckIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//요청 - 한글처리 
		request.setCharacterEncoding("UTF-8");
		//데이터
		String id = request.getParameter("id");
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId(id);
		
		//DB
		MemberDAO memberDAO = MemberDAO.getInstance();//싱글톤
		//boolean idcheck=memberDAO.checkId(id);
		boolean exist = memberDAO.isCheckId(id);//boolean일때 is를 써준다
		//응답
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");		
		out.println("<head>");	
		out.println("<body>");
		if(exist) {//false
			out.println("아이디"+id+"은 사용하실 수 없습니다.");
		}else {//true
			out.println("아이디"+id+"은 사용하실 수 있습니다.");
		}
		out.println("</body>");
		out.println("</html>");
		
	}

}
