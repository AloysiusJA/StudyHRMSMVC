package com.ja.learning.hrmsmvc.service;

import java.util.List;

import com.ja.learning.hrmsmvc.exception.HrmsException;
import com.ja.learning.hrmsmvc.model.Departments;
import com.ja.learning.hrmsmvc.model.User;
import com.ja.learning.hrmsmvc.repository.Dao;
import com.ja.learning.hrmsmvc.repository.DaoImpl;

public class LoginServiceImpl implements LoginService{
	
	Dao dao = new DaoImpl();

	public LoginServiceImpl() {
		
	}
	
	
	public User validateLogin (User user) throws HrmsException{
		return dao.verifyLoginCredentials(user);
	}
	
	@Override
	public List<Departments> getDepartments() throws HrmsException {
		return dao.getDepartments();
	}

}
