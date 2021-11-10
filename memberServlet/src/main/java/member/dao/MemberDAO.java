	package member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import member.bean.MemberDTO;

public class MemberDAO {
	
	private Connection conn = null; 
	private PreparedStatement pstmt; 
	private ResultSet rs;
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "c##java";
	private String password = "bit"; 
	
	private static MemberDAO instance = null; //초기화되는 시점 1번 

	//싱글톤 
	public static MemberDAO getInstance() { //인스턴스 - 메모리생성 
		if(instance == null) {//한번도 생성된 적 없느냐. 맨 처음이냐
			synchronized(MemberDAO.class) {//동기화처리 - 스레드중에 한개만 처리 
				instance = new MemberDAO();//생성 - 1번
			}
		}
		return instance;
	}

	//driver loading
	public MemberDAO() {
		try {
			Class.forName(driver); 
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
}
	
	//Connection
	public void getConnection() {
	try { 
		conn = DriverManager.getConnection(url, username, password); 
		System.out.println("접속 성공");
	}catch(SQLException e) {
		e.printStackTrace(); 
	}
}

	public void write(MemberDTO memberDTO) {
		String sql = "insert into member values(?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
		getConnection();
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, memberDTO.getName());
			pstmt.setString(2, memberDTO.getId());
			pstmt.setString(3, memberDTO.getPwd());
			pstmt.setString(4, memberDTO.getGender());
			pstmt.setString(5, memberDTO.getEmail1());
			pstmt.setString(6, memberDTO.getEmail2());
			pstmt.setString(7, memberDTO.getTel1());
			pstmt.setString(8, memberDTO.getTel2());
			pstmt.setString(9, memberDTO.getTel3());
			pstmt.setString(10, memberDTO.getZipcode());
			pstmt.setString(11, memberDTO.getAddr1());
			pstmt.setString(12, memberDTO.getAddr2());
			
			pstmt.executeUpdate();//실행
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				try {
					if(pstmt!=null) pstmt.close();
					if(conn!=null) conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}	
		}
	}

	public String login(String id, String pwd) {
		String name=null;
		String sql ="select * from member where id=? and pwd=?";
		getConnection();
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			
			rs= pstmt.executeQuery();//실행
			
			if(rs.next()) {
				name = rs.getString("name");//아무것도 없으면 null값 쥐고 간다. 
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
	}
		return name;
	}

	public boolean isCheckId(String id) {
		boolean exist = false; //안써도 기본 false
		
		String sql ="select * from member where id=?";
		getConnection();
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs= pstmt.executeQuery();//실행
			
			if(rs.next()){//rs.next() -> 데이터가 있으면 T (아이디 사용 불가능), 데이터 없으면 F (아이디 사용 가능)
				exist = true; 
			};
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return exist;
	}
	
	
}
