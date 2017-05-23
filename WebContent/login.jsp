<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
 <div class="loginContainer">
    <div class="block">
        <h3 class="blockTitle">Login</h3>
        <div class="blockContent">
            <div class="formStyle">
                <script>
                $(document).on("click", "#login", function() {       
                            var username=$('#username').val();
                            var password=$('#password').val();
                            var transaction = "LOGIN"
                            $.ajax({
                                type: "POST",
                                url:"MainServletController",   // this is my servlet
                                data:JSON.stringify({"transaction":transaction,"username":username,"password":password}),
                                dataType: "json",
                                success: function (data) {
                                    if(data.status == 'SUCCESS'){
                                    	var url = "Welcome.jsp";    
                                   	 $(location).attr('href',url); 
                                    }else if(data.status == 'ERROR'){
                                        alert(data.message);
                                    }
                                	 
                                }
                            });                                
                        }); 
                </script>
                <label>Username</label>
                <input type="text" id="username"/> 

                <label>Password</label>
                <input type="password" id="password"/> 
                <input type="submit" value="Login" id="login"/>
            </div>
        </div>
    </div>
 </div>
</body>
</html>