package com.javaex.phone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhoneDao {

	//필드
	
	//생성자
	//디폴트 생성자 생략
	
	//메소드 g/s
	
	//메소드 일반
	
	//리스트  가져오기
	public List<PersonVo> getPhoneList(){
		
		List<PersonVo> phoneList = new ArrayList<PersonVo>();
		
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "phonedb", "phonedb");
			
		    // 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select person_id, ";
			query += "        name, ";
			query += "        hp, ";
			query += "        company, ";
			query += " from person";
			
			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			
		    // 4.결과처리
			while(rs.next()) {
				int personId = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");
				
				PersonVo vo = new PersonVo(personId, name, hp, company);
				phoneList.add(vo);
				
			}
	
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {
		        if (rs != null) {
		            rs.close();
		        }                
		        if (pstmt != null) {
		            pstmt.close();
		        }
		        if (conn != null) {
		            conn.close();
		        }
		    } catch (SQLException e) {
		        System.out.println("error:" + e);
		    }
	
		}
		return phoneList;
	}

	
	//전화번호부 등록
	public int personInsert(PersonVo personVo) {
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int count = 0;
		
		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "phonedb", "phonedb");
			
		    // 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += "insert into person ";
			query += "values(seq_person_id.nextval, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, personVo.getName());
			pstmt.setString(2, personVo.getHp());
			pstmt.setString(3, personVo.getCompany());
			
			count = pstmt.executeUpdate();
			
		    // 4.결과처리
			System.out.println(count + "건 등록되었습니다.");
	
			
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {             
		        if (pstmt != null) {
		            pstmt.close();
		        }
		        if (conn != null) {
		            conn.close();
		        }
		    } catch (SQLException e) {
		        System.out.println("error:" + e);
		    }
	
		}
		
		return 1;

	}
	
	//작가 삭제하기
	public int personDelete(int personId) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		//ResultSet rs = null;

		int count =0;
		
		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			
		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "phonedb", "phonedb");

			
		    // 3. SQL문 준비 / 바인딩 / 실행
		    String query = "";
		    query += " delete ";
		    query += " from person ";
		    query += " where person_id = ? ";
		    
		    System.out.println(query);
		    
		    pstmt = conn.prepareStatement(query); //쿼리로 만들기
		    pstmt.setInt(1, personId);
		    
		    count = pstmt.executeUpdate();
		    
		    // 4.결과처리
		    System.out.println("[dao]" + count + "건 삭제");

		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {               
		        if (pstmt != null) {
		            pstmt.close();
		        }
		        if (conn != null) {
		            conn.close();
		        }
		    } catch (SQLException e) {
		        System.out.println("error:" + e);
		    }

		}
		return count;

	}
	
}
