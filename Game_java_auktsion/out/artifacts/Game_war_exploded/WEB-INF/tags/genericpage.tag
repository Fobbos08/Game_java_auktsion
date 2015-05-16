<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<html>
<head>
    <link rel='stylesheet' href='/static/css/style.css' />
    <script src="/static/js/jquery-1.11.2.min.js"></script>
    <script src="/static/js/main.js"></script>
</head>
<body>
<div class="header" id="header">
    <div class="right">
        <a id="loginButton" href="#">Login</a>
    </div>
    <div class="menu">
        <a href="/creategame"><span class="menuButton" ><p>Create game</p></span></a>
        <a href="/"><span class="menuButton" ><p>Start game</p></span></a>
    </div>
</div>
<div class="login loginArea" id="loginArea" style="display: none">
</div>
<div class="overlay" style="display: none"></div>
<div class="main">
<div id="body">
    <jsp:doBody/>
</div>
    </div>
<div id="footer">

</div>
</body>
</html>