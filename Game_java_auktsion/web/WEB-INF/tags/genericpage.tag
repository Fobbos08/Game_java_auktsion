<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<html>
<head>
    <link rel='stylesheet' href='/static/css/style.css' />
    <script src="/static/js/jquery-1.11.2.min.js"></script>
    <script src="/static/js/main.js"></script>
</head>
<body>
<div id="header">
    <a id="loginButton" href="#">Login</a>
    <div id="loginArea">
    </div>
    <div class="overlay" style="display: none"></div>
</div>
<div id="body">
    <jsp:doBody/>
</div>
<div id="footer">

</div>
</body>
</html>