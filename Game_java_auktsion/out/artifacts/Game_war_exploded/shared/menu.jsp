<%@ page import="java.util.UUID" %>
<%@ page import="business.SessionManipulator" %>
<%@ page import="game.GameManipulator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Cookie[] cookies = request.getCookies();
    boolean isLogined = false;
    boolean isInGame = false;
    UUID gameId = null;
    UUID playerId = null;
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("id")) {
                isLogined = SessionManipulator.getUser(UUID.fromString(cookie.getValue())) == null ? false : true;
            }
            if (cookie.getName().equals("gameId")) {
                gameId = UUID.fromString(cookie.getValue());
                //isInGame = true;
            }
        }
    }
    if (gameId != null && playerId != null) {
        isInGame = GameManipulator.getPlayer(gameId, playerId) == null ? false : true;
    }
%>
<div class="right">
    <% if(!isLogined) {%>
        <a id="loginButton" href="#">login</a>
    <%}else{%>
        <a id="logoutButton" href="#">logout</a>
    <%}%>
</div>
<div class="menu">
    <% if(isLogined) {%>
        <a href="/creategame"><span class="menuButton" ><p>Create game</p></span></a>
        <a href="/"><span class="menuButton" ><p>Connect to game</p></span></a>
    <%}%>
</div>