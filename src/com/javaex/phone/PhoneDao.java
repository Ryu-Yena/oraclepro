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
	
	//DB
	// 0. import java.sql.*;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "phonedb";
	private String pw = "phonedb";

	
	
	public void getconnection() {
			try {
				// 1. JDBC 드라이버 (Oracle) 로딩
				Class.forName(driver);
	
				// 2. Connection 얻어오기
				conn = DriverManager.getConnection(url, id, pw);
	
			} catch (ClassNotFoundException e) {
				System.out.println("error: 드라이버 로딩 실패 - " + e);
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
	
		// 자원 정리
		public void close() {
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
	}

	
	//리스트  가져오기
	public List<PersonVo> getPhoneList(){
		
		List<PersonVo> phoneList = new ArrayList<PersonVo>();
		
		
		getconnection();
		
		try {

		    // 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select person_id, ";
			query += "        name, ";
			query += "        hp, ";
			query += "        company, ";
			query += " from person";			
			query += " order by person_id";
			
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

		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} 
		
		close();
		
		return phoneList;
	}

	
	//전화번호부 등록
	public int personInsert(PersonVo personVo) {
		
		int count = 0;
		
		getconnection();
		
		try {
		   
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
			System.out.println("");
	
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		}
		
		close();
		
		return count;
	}
	
	//전화번호부 수정
	public int personUpdate (PersonVo personVo) {
		
		int count = 0;
		
		getconnection();
		
		try {
			   
		    // 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " update person ";
			query += " set name = ?, ";
			query += "    hp = ?, ";
			query += "	   company = ? ";
			query += " where person person_id = ?";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, personVo.getName());
			pstmt.setString(2, personVo.getHp());
			pstmt.setString(3, personVo.getCompany());
			pstmt.setInt(4, personVo.getPersonid());
			
			
			count = pstmt.executeUpdate();
			
		    // 4.결과처리
			System.out.println(count + "건 수정되었습니다.");
			System.out.println("");
	
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		}
		
		close();
		
		return count;
		
	}
	
	//작가 삭제하기
	public int personDelete(int personId) {
		
		int count =0;
		
		getconnection();
		
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
		    System.out.println(count + "건 삭제되었습니다.");

		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} 
		
		close();
		return count;

	}
	
	//검색
	public List<PersonVo> getSearchList(String str){
		
		List<PersonVo> phoneList = new ArrayList<PersonVo>();
		
		
		getconnection();
		
		try {

		    // 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select person_id, ";
			query += "        name, ";
			query += "        hp, ";
			query += "        company, ";
			query += " from person ";			
			query += " where neme like ? ";
			query += " or hp like ? ";
			query += " or company like ? ";
			query += " order by person_id";
			
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + str + "%");
			pstmt.setString(2, "%" + str + "%");
			pstmt.setString(3, "%" + str + "%");
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

		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} 
		
		close();
		
		return phoneList;
	}

	
	
}
