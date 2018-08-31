package com.htp.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.htp.serviceImpl.UserServiceImpl;

public class UserRealm  extends AuthorizingRealm {

	//这里因为没有调用后台，直接默认只有一个用户
    private static final String USER_NAME = "hutp";  
    private static final String PASSWORD = "123456"; 
    
    //需要添加注解才可使用，否则报空指针异常
	@Resource
	private UserServiceImpl userService;
	
    //权限认证
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		// TODO Auto-generated method stub
	
	String username = (String) arg0.getPrimaryPrincipal();	
	System.out.println("principal username["+username+"]");
	
		System.out.println("enter doGetAuthorizationInfo ");
		Set<String> roleNames = new HashSet<String>();  
        Set<String> permissions = new HashSet<String>();  
        roleNames.add("admin");//添加角色
        
        permissions.add("secret.html");  //添加权限
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);  
        info.setStringPermissions(permissions);  
        return info;  
	}

	//doGetAuthenticationInfo  身份认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken arg0) throws AuthenticationException {
		// TODO Auto-generated method stub
		System.out.println("enter doGetAuthenticationInfo 1");
		UsernamePasswordToken token = (UsernamePasswordToken) arg0;
		String username = token.getUsername();
		List<Map<String,String>> rst = userService.getUsers();
		if(rst == null)
			return null;
		System.out.println("rst.size:"+rst.size());
		for(int i=0; i<rst.size();i++){
			if(rst.get(i).get("name").equals(username))
			{
				System.out.println("succcess...getname["+getName()+"]");
				return new SimpleAuthenticationInfo(username, token.getPassword(), getName());
			}
		}
	   System.out.println("user not found");
	   throw new AuthenticationException();  

	}

}
