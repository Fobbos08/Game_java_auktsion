<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<html>
<head>
    <link rel='stylesheet' href='/static/css/style.css' />
    <script src="/static/js/jquery-1.11.2.min.js"></script>
    <script src="/static/js/main.js"></script>
</head>
<body>
<div class="header" id="header">
    <jsp:include page="/shared/menu.jsp"/>
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