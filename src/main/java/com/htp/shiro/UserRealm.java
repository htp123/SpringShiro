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

	//������Ϊû�е��ú�̨��ֱ��Ĭ��ֻ��һ���û�
    private static final String USER_NAME = "hutp";  
    private static final String PASSWORD = "123456"; 
    
    //��Ҫ���ע��ſ�ʹ�ã����򱨿�ָ���쳣
	@Resource
	private UserServiceImpl userService;
	
    //Ȩ����֤
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		// TODO Auto-generated method stub
	
	String username = (String) arg0.getPrimaryPrincipal();	
	System.out.println("principal username["+username+"]");
	
		System.out.println("enter doGetAuthorizationInfo ");
		Set<String> roleNames = new HashSet<String>();  
        Set<String> permissions = new HashSet<String>();  
        roleNames.add("admin");//��ӽ�ɫ
        
        permissions.add("secret.html");  //���Ȩ��
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);  
        info.setStringPermissions(permissions);  
        return info;  
	}

	//doGetAuthenticationInfo  �����֤
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
