<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href='http://fonts.googleapis.com/css?family=PT+Sans:400,700' rel='stylesheet' type='text/css'>

    <link rel="stylesheet" href="/static/css/reset.css"> <!-- CSS reset -->
    <link rel="stylesheet" href="/static/css/homeStyle.css"> <!-- Gem style -->
    <link rel='stylesheet' href='/static/css/style.css' />
    <script src="/static/js/modernizr.js"></script> <!-- Modernizr -->

    <script src="/static/js/jquery-1.11.2.min.js"></script>
    <script src="/static/js/main.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="/static/js/home.js"></script> <!-- Gem jQuery -->

    <title>Log In &amp; Sign Up Form</title>

</head>
<body>

<header role="banner">
    <nav class="main-nav">
        <ul>
            <li><a class="cd-signin" id="loginButton" href="#">Sign in</a></li>
            <li><a class="cd-signup" id="registerButton" href="#">Sign up</a></li>
        </ul>
    </nav>
</header>

<div class="plate">
    <p class="script"><span><---></span></p>
    <p class="shadow text1">Welcome</p>
    <p class="shadow text1">to</p>
    <p class="shadow text3">the</p>
    <p class="shadow text3">game</p>
    <p class="script"><span>by AUCTION</span></p>
</div>

<div class="cd-user-modal"> <!-- this is the entire modal form, including the background -->
    <div class="cd-user-modal-container"> <!-- this is the container wrapper -->
        <div id="loginArea" style="display: none"></div>
    </div> <!-- cd-user-modal-container -->
</div> <!-- cd-user-modal -->

<div class="menu">
    <a href="/creategame">Create game</a>
    <a href="/">Start game</a>
</div>
<div id="body"><jsp:doBody/></div>
<div id="footer"></div>
</body>
</html>