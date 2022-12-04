package com.ja.learning.hrmsmvc.model;

public class Departments {

	public Departments() {
		
	}
	
	private long dept_id;
	private String dept_description;
	public long getDept_id() {
		return dept_id;
	}
	public void setDept_id(long dept_id) {
		this.dept_id = dept_id;
	}
	public String getDept_description() {
		return dept_description;
	}
	public void setDept_description(String dept_description) {
		this.dept_description = dept_description;
	}
	@Override
	public String toString() {
		return "Departments [dept_id=" + dept_id + ", dept_description=" + dept_description + "]";
	}

}
