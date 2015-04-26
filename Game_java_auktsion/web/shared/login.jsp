<%--
  Created by IntelliJ IDEA.
  User: Эмиль
  Date: 25.04.2015
  Time: 21:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="loginArea">
    <div id="login">
        <form name="loginForm" action="#">
            <label>Login:</label><input name="login" type="text"/>
            <label>Password:</label><input name="password" type="text"/>
            <div id="loginErrors"></div>
            <input type="submit">
        </form>
        <a id="registerButton" href="#">Register</a><a id="forgotButton" href="#">Forgot password</a>
    </div>
    <div id="register" style="display: none">
        <form id="registerForm" name="registerForm" action="#">
            <input style="display: none" name="register" value="1">
            <label>Login:</label><input id="loginField" name="login" type="text"/>
            <label>First name:</label><input id="firstName" name="firstName" type="text"/>
            <label>Last name:</label><input id="lastName" name="lastName" type="text"/>
            <label>Email:</label><input id="email" name="email" type="text"/>
            <label>Password:</label><input id="password" name="password" type="password"/>
            <label>Repeat password:</label><input id="rpassword" name="rpassword" type="password"/>
            <div id="registerErrors"></div>
            <input type="submit">
        </form>

    </div>
    <script type="text/javascript">
        $("#registerButton").bind("click", function(e){
            e.preventDefault();
            $("#login").hide();
            $("#register").show();
        });
        $("#registerForm").bind("submit", function(e){
            e.preventDefault();
            $.post('/loginblock', $('#registerForm').serialize(), function(data){
                if (data.length<20)
                {
                    $("#registerErrors").html("");
                }else {
                    $("#registerErrors").html(data);
                }
            });
            /*$.ajax({
                url: "/loginblock",
                type: "POST",
                data: this,
                success: function(data){
                    if (data.length>10)
                    {
                        $("#registerErrors").html(data);
                    }
                },
                error: function(){ alert('error'); }
            });*/
        });
    </script>
</div>
