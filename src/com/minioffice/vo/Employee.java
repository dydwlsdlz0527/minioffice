package com.minioffice.vo;

import java.util.Date;

public class Employee {
	
	private String emp_no;
	private String emp_name;
	private String emp_id;
	private String emp_pw;
	private Date emp_hiredate;
	private String emp_phone;
	private String emp_addr;
	private byte[] emp_sign;
	private byte[] emp_img;
	private String emp_signpw;
	private char emp_admin;
	private Rank rank;
	private Department dept;

	public Employee() {
		
	}

	public Employee(String emp_no, String emp_name, String emp_id, String emp_pw, Date emp_hiredate, String emp_phone,
			String emp_addr, byte[] emp_sign, byte[] emp_img, String emp_signpw, char emp_admin, Rank rank,
			Department dept) {
		super();
		this.emp_no = emp_no;
		this.emp_name = emp_name;
		this.emp_id = emp_id;
		this.emp_pw = emp_pw;
		this.emp_hiredate = emp_hiredate;
		this.emp_phone = emp_phone;
		this.emp_addr = emp_addr;
		this.emp_sign = emp_sign;
		this.emp_img = emp_img;
		this.emp_signpw = emp_signpw;
		this.emp_admin = emp_admin;
		this.rank = rank;
		this.dept = dept;
	}

	public String getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public String getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}

	public String getEmp_pw() {
		return emp_pw;
	}

	public void setEmp_pw(String emp_pw) {
		this.emp_pw = emp_pw;
	}

	public Date getEmp_hiredate() {
		return emp_hiredate;
	}

	public void setEmp_hiredate(Date emp_hiredate) {
		this.emp_hiredate = emp_hiredate;
	}

	public String getEmp_phone() {
		return emp_phone;
	}

	public void setEmp_phone(String emp_phone) {
		this.emp_phone = emp_phone;
	}

	public String getEmp_addr() {
		return emp_addr;
	}

	public void setEmp_addr(String emp_addr) {
		this.emp_addr = emp_addr;
	}

	public byte[] getEmp_sign() {
		return emp_sign;
	}

	public void setEmp_sign(byte[] emp_sign) {
		this.emp_sign = emp_sign;
	}

	public byte[] getEmp_img() {
		return emp_img;
	}

	public void setEmp_img(byte[] emp_img) {
		this.emp_img = emp_img;
	}

	public String getEmp_signpw() {
		return emp_signpw;
	}

	public void setEmp_signpw(String emp_signpw) {
		this.emp_signpw = emp_signpw;
	}

	public char getEmp_admin() {
		return emp_admin;
	}

	public void setEmp_admin(char emp_admin) {
		this.emp_admin = emp_admin;
	}

	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}
	
	
}
