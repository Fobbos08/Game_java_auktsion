<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<html>
<head>
    <link rel='stylesheet' href='/static/css/style.css' />
    <script src="/static/js/jquery-1.11.2.min.js"></script>
    <script src="/static/js/main.js"></script>
</head>
<body>
<div class="top" id="header">
    <div class="right">
        <a id="loginButton" href="#">Login</a>
    </div>
    <div class="menu">
        <a href="/creategame">Create game</a>
        <a href="/">Start game</a>
    </div>
</div>
<div class="login loginArea" id="loginArea" style="display: none">
</div>
<div class="overlay" style="display: none"></div>
<div id="body">
    <jsp:doBody/>
</div>
<div id="footer">

</div>
</body>
</html>