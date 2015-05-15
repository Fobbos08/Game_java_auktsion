<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="/static/css/homeStyle.css"> <!-- Gem style -->
<link rel="stylesheet" href="/static/css/style.css"> <!-- Gem style -->
<script type="text/javascript" src="/static/js/loginFormScript.js" ></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="/static/js/home.js"></script> <!-- Gem jQuery -->

<div id="login">
    <form class="cd-form" id="loginForm" name="loginForm" action="#">
        <input name="loginForm" style="display: none;">
        <p class="fieldset">
            <label class="image-replace cd-username" for="signup-username">Login</label>
            <input class="full-width has-padding has-border" name="login" id="signup-username" type="text" placeholder="Login"/>
            <span class="cd-error-message">Error message here!</span>
        </p>
        <p class="fieldset">
            <label class="image-replace cd-password" for="signin-password">Password</label>
            <input class="full-width has-padding has-border" id="signin-password" name="password" type="password" placeholder="Password"/>
            <a href="#0" class="hide-password">Hide</a>
            <span class="cd-error-message">Error message here!</span>
        </p>

        <p class="fieldset">
            <input type="checkbox" id="remember-me" checked>
            <label for="remember-me">Remember me</label>
        </p>

        <p class="fieldset">
        <div class=cd-error-message" class="errorList" id="loginErrors"></div>
        </p>

        <p class="fieldset">
            <input class="full-width" type="submit" value="Login">
        </p>
    </form>
    <a id="registerButton" href="#">Register</a>
    <p class="cd-form-bottom-message"><a  class="cd-signup" id="forgotButton" href="#">Forgot your password?</a></p>

</div>

<div id="register" style="display: none">
    <form  class="cd-form" id="registerForm" name="registerForm" action="#">
        <input style="display: none" name="register" value="1">
        <p class="fieldset">
            <label class="image-replace cd-username">Login</label>
            <input class="full-width has-padding has-border" id="loginField" name="login" type="text" placeholder="Login"/>
        </p>
        <p class="fieldset">
            <label class="image-replace cd-username">First name</label>
            <input class="full-width has-padding has-border" id="firstName" name="firstName" type="text" placeholder="First name"/>
        </p>
        <p class="fieldset">
            <label class="image-replace cd-username">Last name</label>
            <input class="full-width has-padding has-border" id="lastName" name="lastName" type="text" placeholder="Last name"/>
        </p>
        <p class="fieldset">
            <label class="image-replace cd-email" for="signin-email">E-mail</label>
            <input class="full-width has-padding has-border" id="signin-email" type="email" placeholder="E-mail">
            <span class="cd-error-message">Error message here!</span>
        </p>
        <p class="fieldset">
            <label class="image-replace cd-password">Password</label>
            <input class="full-width has-padding has-border" id="password" name="password" type="password" placeholder="Password" />
            <a href="#0" class="hide-password">Hide</a>
        </p>
        <p class="fieldset">
            <label class="image-replace cd-password">Repeat password</label>
            <input class="full-width has-padding has-border" id="rpassword" name="rpassword" type="password" placeholder="Repeat password"/>
            <a href="#0" class="hide-password">Hide</a>
        </p>
        <div class="errorList" id="registerErrors"></div>
        <p class="fieldset">
            <input class="full-width has-padding" type="submit" value="Create account">
        </p>
    </form>

</div>