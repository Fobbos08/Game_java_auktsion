<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<html>
<head>
    <script src="/js/jquery-1.11.2.min.js"></script>
    <script src="/js/main.js"></script>
</head>
<body>
<div id="header">
    <a id="loginButton" href="#">Login</a>
    <div id="loginArea">
        </div>
</div>
<div id="body">
    <jsp:doBody/>
</div>
<div id="footer">

</div>
</body>
</html>