package member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import member.bean.MemberDTO2;

public class MemberDAO2 {
	private static MemberDAO2 instance = null;
	private Connection conn = null; 
	private PreparedStatement pstmt; 
	private ResultSet rs;
	
	private String driver ="oracle.jdbc.driver.OracleDriver";
	private String url ="jdbc:oracle:thin:@localhost:1521:xe";
	private String username="c##java";
	private String password="bit";
	
	//싱글톤 - 메모리에 딱 한번만 생성 
	public static MemberDAO2 getInstance() {//인스턴스 - 메모리 생성
		if(instance == null) {
			synchronized(MemberDAO2.class) {
				instance = new MemberDAO2();
			}
		}
		return instance;
	}
	
	//driver loading
	public MemberDAO2() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//Connection 
	public void getConnection() {
		try {
			conn=DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//PreparedStatement
	public void write(MemberDTO2 memberDTO2) {
		String sql="insert into member2 values(?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
		getConnection();
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, memberDTO2.getName());
			pstmt.setString(2, memberDTO2.getPwd());
			pstmt.setString(3, memberDTO2.getGender());
			pstmt.setString(4, memberDTO2.getId());
			pstmt.setString(5, memberDTO2.getEmail1());
			pstmt.setString(6, memberDTO2.getEmail2());
			pstmt.setString(7, memberDTO2.getTel1());
			pstmt.setString(8, memberDTO2.getTel2());
			pstmt.setString(9, memberDTO2.getTel3());
			pstmt.setString(10, memberDTO2.getZipcode());
			pstmt.setString(11, memberDTO2.getAddr1());
			pstmt.setString(12, memberDTO2.getAddr2());
			
			pstmt.executeUpdate();//실행
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				try {
					if(pstmt!=null) pstmt.close();
					if(conn!=null)conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
	}
	
	public String login(String id, String pwd) {
		String name=null; 
		
		String sql="select * from member2 where id=? and pwd=?";
		getConnection();
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			
			rs=pstmt.executeQuery(); //실행
			
			if(rs.next()) {
				name=rs.getString("name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
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
	

}
