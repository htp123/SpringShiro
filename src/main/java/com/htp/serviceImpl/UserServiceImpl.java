package com.htp.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htp.bean.User;
import com.htp.dao.UserMapper;
import com.htp.service.IUserSevice;

//@Service
public class UserServiceImpl implements IUserSevice {

//	@Autowired
	UserMapper userMapper;



	public List<Map<String, String>> getUsers() {
		// TODO Auto-generated method stub
		return userMapper.getUsers();
	}


	public List<User> getUserById(Integer id) {
		// TODO Auto-generated method stub
		return userMapper.getUserById(id);
	}

/*	@Override
	public List<Map<String, String>> getUserById(Integer id) {
		
		// TODO Auto-generated method stub
		return userMapper.getUserById(id);
	}*/

}
