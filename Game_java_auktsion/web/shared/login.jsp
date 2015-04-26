<%--
  Created by IntelliJ IDEA.
  User: Эмиль
  Date: 25.04.2015
  Time: 21:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <div id="login">
        <form name="loginForm" action="#">
            <label class="element">Login:</label><input class="element" name="login" type="text"/>
            <label class="element">Password:</label><input class="element" name="password" type="text"/>
            <div class="element" class="errorList" id="loginErrors"></div>
            <input class="element" type="submit">
        </form>
        <a id="registerButton" href="#">Register</a><a id="forgotButton" href="#">Forgot password</a>
    </div>
    <div id="register" style="display: none">
        <form id="registerForm" name="registerForm" action="#">
            <input style="display: none" name="register" value="1">
            <label class="element">Login:</label><input class="element" id="loginField" name="login" type="text"/>
            <label class="element">First name:</label><input class="element" id="firstName" name="firstName" type="text"/>
            <label class="element">Last name:</label><input class="element" id="lastName" name="lastName" type="text"/>
            <label class="element">Email:</label><input class="element" id="email" name="email" type="text"/>
            <label class="element">Password:</label><input class="element" id="password" name="password" type="password"/>
            <label class="element">Repeat password:</label><input class="element" id="rpassword" name="rpassword" type="password"/>
            <div class="errorList" id="registerErrors"></div>
            <input class="element" type="submit">
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
