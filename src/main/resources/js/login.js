/**
 * 
 */


$(document).ready(function(){
  $("#loginbtn").click(function(){
	 alert("loginbtn test.....");
	 var param = {
		        username : $("#username").val(),
		        password : $("#password").val()
		    };
	 $.ajax({ 
	        type: "post", 
	        url: "/checkLogin.json",
	        data: param, 
	        dataType: "json", 
	        success: function(data) { 
	        	debugger;
	            if(data.success == "false"){
	                alert(data.errorMsg);
	                window.location.href = "/error.html";
	            }else if(data.success == "true"){
	                //登录成功
	                window.location.href = "/successLogin.html";
	            }
	            else{
	            	alert("true/false");
	            }
	        },
	        error: function(data) { 
	            alert("调用失败...."); 
	        }
	    });
  });
  
  $('#logoutbtn').click(function(){
	  var param = {
		        username : $("#username").val(),
		        password : $("#password").val()
		    };
	  $.ajax({
		  
		    type: "post", 
	        url: "/logout.json",
	        data: param,
	        success: function(data) { 
	           alert("退出系统")
	        },
	        error: function(data) { 
	            alert("调用失败...."); 
	        }
		  
	  });
	  
	  
  });
/*  
  $('#roleAuthBtn').click(function(){
	  var param = {
		        username : $("#username").val(),
		        password : $("#password").val()
		    };
	  alert(username);
	  $.ajax({
		  
		    type: "post", 
	        url: "/DemoShiro/test.json",
	        data: param,
	        success: function(data) { 
	           alert("认证成功")
	        },
	        error: function(data) { 
	            alert("调用失败...."); 
	        }
		  
	  });
	  
	  
  });
  */
});
