package com.ja.learning.hrmsmvc.service;

import java.util.List;

import com.ja.learning.hrmsmvc.exception.HrmsException;
import com.ja.learning.hrmsmvc.model.Departments;
import com.ja.learning.hrmsmvc.model.User;

public interface LoginService {

	public User validateLogin (User user) throws HrmsException;
	
	public List<Departments> getDepartments() throws HrmsException;
	
}
