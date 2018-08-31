package com.htp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


//import net.sf.ezmorph.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import net.sf.json.util.JSONUtils;

@Controller
public class UserController {

    /** 
     * 验证用户名和密码 
     * @param  username,String password
     * @return 
     */  
    @RequestMapping(value="/checkLogin.json",method=RequestMethod.POST)

    @ResponseBody 
    
/*    @Responsebody 注解表示该方法的返回的结果直接写入 HTTP 响应正文（ResponseBody）中，
    一般在异步获取数据时使用，通常是在使用 @RequestMapping 后，返回值通常解析为跳转路径，
    加上 @Responsebody 后返回结果不会被解析为跳转路径，而是直接写入HTTP 响应正文中*/
    
    
    public String checkLogin(String username,String password) {  
        Map<String, Object> result = new HashMap<String, Object>();
        System.out.println("enter checkLogin...username【"+username+"】");
       
        try{
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);  
            Subject currentUser = SecurityUtils.getSubject();  
            if (!currentUser.isAuthenticated()){
                //使用shiro来验证  
                token.setRememberMe(true);  
                currentUser.login(token);//验证角色和权限  
                System.out.println("principals["+currentUser.getPrincipals()+"]");
                System.out.println("admin["+currentUser.hasRole("admin")+"]");
                System.out.println("administrator["+currentUser.hasRole("administrator")+"]");
                System.out.println("secret["+currentUser.isPermitted("secret.html")+"]");
                System.out.println("newpage["+currentUser.isPermitted("newPage.html")+"]");
    
            }
        }catch(Exception ex){
        	System.out.println("fail login exception");
        	result.put("success", false);
        	result.put("errorMsg", "用户身份认证失败");
        	System.out.println("result"+result);
        	return JSONUtils.valueToString(result);
        }
     
        
        
        
//        System.out.println("end...");
        result.put("success", true);
        System.out.println("result"+result);
        return JSONUtils.valueToString(result);
        //return JSONUtils.toJSONString(result);  
    }  

    /** 
     * 退出登录
     */  
    @RequestMapping(value="/logout.json",method=RequestMethod.POST)
    @ResponseBody    
    public String logout() {  
    	System.out.println("enter logout");
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", true);
        Subject currentUser = SecurityUtils.getSubject();       
        currentUser.logout();  
        System.out.println("logout...");
//        return JSONUtils.toJSONString(result);
        return JSONUtils.valueToString(result);
    }  
}