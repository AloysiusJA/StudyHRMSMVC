package com.ja.learning.hrmsmvc.constants;

public class Constants {

	public Constants() {
		
	}

	
	/*****************************DB connectiion****************************/
	public static String DB_URL="jdbc:mysql://localhost:3306/empdb";
	public static String DB_USERNAME="root";
	public static String DB_PASSWORD="welcome";
	public static String DB_DRIVER="com.mysql.cj.jdbc.Driver";
	
	public static String SQL_SELECT_LOGINCREDENTIALS ="select * from empdb.user where username=? and password=?";
	
	public static String SQL_ADD_EMPLOYEE = "insert into employee(fname,lname,email,designation,department,salary,joindate,phone) values (?,?,?,?,?,?,?,?)";
	public static String SQL_ADD_ADDRESS = "insert into address(homeaddress1,homeaddress2,homecity,homepincode,homephone,officeaddress1,officeaddress2,officecity,officepincode,officephone,id) values (?,?,?,?,?,?,?,?,?,?,LAST_INSERT_ID())";
	public static String SQL_ADD_PAYROLL ="insert into payroll(net,basic,hra,da,ta,id) values (?,?,?,?,?,?)";
	
	public static String SQL_SELECT_LASTID = "select last_insert_id() from employee ";
	
	
	public static String DELETE_RECORD ="DELETE e,a,s FROM empdb.employee AS e LEFT JOIN  empdb.address AS a ON e.id = a.id LEFT JOIN payroll AS s ON e.id = s.id WHERE e.id = ?";
	
	public static String SQL_SELECT_EMPLOYEES ="SELECT e.id,e.fname, e.lname,e.email,e.designation,e.department ,e.joindate,a.homeaddress1,a.homeaddress2,a.homecity,a.homepincode,a.homephone,a.officeaddress1,a.officeaddress2,a.officecity,a.officepincode,a.officephone,s.net,s.basic,s.hra,s.ta,s.da FROM employee AS e LEFT OUTER JOIN address AS a ON e.id = a.id LEFT JOIN payroll AS s ON e.id = s.id";
	
	
	public static String SQL_SELECT_WITHEMPID ="SELECT e.id,e.fname, e.lname,e.email,e.designation,e.department ,e.joindate,a.homeaddress1,a.homeaddress2,a.homecity,a.homepincode,a.homephone,a.officeaddress1,a.officeaddress2,a.officecity,a.officepincode,a.officephone,s.net,s.basic,s.hra,s.ta,s.da FROM employee AS e LEFT OUTER JOIN address AS a ON e.id = a.id LEFT JOIN payroll AS s ON e.id = s.id WHERE e.id=?";
	
	public static String SQL_SELECT_DEPARTMENTS ="select distinct deptid,departmentname from departments";
	
	public static String SQL_SELECT_WITHDEPT ="SELECT e.id,e.fname, e.lname,e.email,e.designation,e.department ,e.joindate,a.homeaddress1,a.homeaddress2,a.homecity,a.homepincode,a.homephone,a.officeaddress1,a.officeaddress2,a.officecity,a.officepincode,a.officephone,s.net,s.basic,s.hra,s.ta,s.da FROM employee AS e LEFT OUTER JOIN address AS a ON e.id = a.id LEFT JOIN payroll AS s ON e.id = s.id WHERE e.department=?";
	
	
	
	
	
}
