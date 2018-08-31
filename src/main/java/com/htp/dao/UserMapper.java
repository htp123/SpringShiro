package com.htp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.htp.bean.User;

//@Repository ����ע��ʵ��bean
public interface UserMapper {

	public List<Map<String, String>> getUsers();
	
	
//	public List<Map<String,String>> getUserById(@Param("id")Integer id);
	
	public List<User> getUserById(@Param("id") Integer id);
}
