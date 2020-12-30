package com.javaex.phone;

import java.util.List;
import java.util.Scanner;

public class PhoneApp {

	public static void main(String[] args) {
		PhoneDao phoneDao = new PhoneDao();
		List<PersonVo> phoneList;
		
		
		//기존데이터 등록
		PersonVo personVo1 = new PersonVo("이효리", "010-1111-1111", "02-1111-1111");
		phoneDao.personInsert(personVo1);
		
		PersonVo personVo2 = new PersonVo("정우성", "010-2222-2222", "02-2222-2222");
		phoneDao.personInsert(personVo2);
		
		PersonVo personVo3 = new PersonVo("유재석", "010-3333-3333", "02-3333-3333");
		phoneDao.personInsert(personVo3);
		
		PersonVo personVo4 = new PersonVo("이정재", "010-4444-4444", "02-4444-4444");
		phoneDao.personInsert(personVo4);
		
		PersonVo personVo5 = new PersonVo("서장훈", "010-5555-5555", "02-5555-5555");
		phoneDao.personInsert(personVo5);
		
		//시작화면
		
		//스캐너
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			
			System.out.println("********************************");
			System.out.println("*        전화번호 관리 프로그램                 *");
			System.out.println("********************************");
			System.out.println();
			System.out.println(" 1.리스트   2.등록   3.삭제   4.검색   5.종료");
			System.out.println("---------------------------------");
			System.out.print(">메뉴번호: ");
			
			int num = sc.nextInt();
			
			switch(num) {
			case 1:
				//리스트
				phoneList = phoneDao.getPhoneList();
				//리스트 전체 출력
				System.out.println("<1.리스트>");
				for(int i=0;i<phoneList.size(); i++) {
					PersonVo vo = phoneList.get(i);
					System.out.println(vo.getPersonid() + ", " + vo.getName() + ", " + vo.getHp() + ", " + vo.getCompany());
				}
				
			case 2:
				//등록
				
			case 3:
				//수정
				
			case 4:
				//삭제
				phoneDao.personDelete(2);
			case 5:
				//검색
				
			case 6:
				//종료
				System.out.println("********************************");
				System.out.println("*             감사합니다                      *");
				System.out.println("********************************");
				break;
				
			default:
				System.out.println("[다시 입력해주세요.]");
			}
		
			sc.close();
		}
		
		
		
				
		//리스트 전체 출력
		
	

	}

}
