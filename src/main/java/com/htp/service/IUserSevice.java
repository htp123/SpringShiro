package com.htp.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.htp.bean.User;

@Repository
public interface IUserSevice {
	public List<Map<String, String>> getUsers();
	
//	public List<Map<String,String>> getUserById(Integer  id);
	
	public List<User> getUserById(Integer id);
}
