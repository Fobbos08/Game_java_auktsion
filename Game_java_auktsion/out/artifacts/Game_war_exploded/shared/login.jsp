<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="/static/js/loginFormScript.js" ></script>
    <div id="login">
        <form id="loginForm" name="loginForm" action="#">
            <input name="loginForm" style="display: none;">
            <label class="element">Login:</label><input class="element" name="login" type="text"/>
            <label class="element">Password:</label><input class="element" name="password" type="password"/>
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
