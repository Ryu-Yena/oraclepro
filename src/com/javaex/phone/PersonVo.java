package com.javaex.phone;

public class PersonVo {
	
	//필드
	public int personId;
	public String name;
	public String hp;
	public String company;
	
	
	//생성자
	public PersonVo() {}
	
	public PersonVo(String name, String hp, String company) {
		this.name = name;
		this.hp = hp;
		this.company = company;
	}
	
	public PersonVo(int personId, String name, String hp, String company) {
		this.personId = personId;
		this.name = name;
		this.hp = hp;
		this.company = company;
	}
	
	
	//메소드 g/s
	public int getPersonid() {
		return personId;
	}


	public void setPersonid(int personid) {
		this.personId = personid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getHp() {
		return hp;
	}


	public void setHp(String hp) {
		this.hp = hp;
	}


	public String getCompany() {
		return company;
	}


	public void setCompany(String company) {
		this.company = company;
	}


	//메소드 일반
	@Override
	public String toString() {
		return "PersonVo [personid=" + personId + ", name=" + name + ", hp=" + hp + ", company=" + company + "]";
	}
	
	

}
