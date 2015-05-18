<%@ page import="java.util.UUID" %>
<%@ page import="helpers.VerifyHelper" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Cookie[] cookies = request.getCookies();
    boolean isLogined = false;
    boolean isInGame = false;
    boolean isAdmin = false;
    UUID gameId = null;
    UUID playerId = null;
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("id")) {
                isLogined = VerifyHelper.verifySession(UUID.fromString(cookie.getValue()));
                isAdmin = VerifyHelper.verifyAdmin(UUID.fromString(cookie.getValue()));
            }
            if (cookie.getName().equals("gameId")) {
                gameId = UUID.fromString(cookie.getValue());
            }
        }
    }
    if (gameId != null && playerId != null) {
        isInGame = VerifyHelper.verifyGame(gameId, playerId);
    }
%>
<div class="right">
    <% if(!isLogined) {%>
        <a class="loginButton" id="loginButton" href="#">login</a>
    <%}else{%>
        <a class="loginButton" id="loginButton" href="#">logout</a>
    <%}%>
</div>
<div class="menu">
    <% if(isLogined & !isInGame) {%>
        <a href="/creategame"><span class="menuButton" ><p>Create game</p></span></a>
        <a href="/connectgame"><span class="menuButton" ><p>Connect to game</p></span></a>
        <a href="/playerstat"><span class="menuButton" ><p>Statistic</p></span></a>

    <%}%>
    <% if(isLogined & isAdmin) {%>
    <a href="/adminpage"><span class="menuButton" ><p>Admin page</p></span></a>
    <%}%>
</div>