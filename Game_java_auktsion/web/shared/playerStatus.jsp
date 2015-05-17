<%@ page import="game.Player" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Player p = (Player)request.getAttribute("player");
%>
<label>You have money: </label>
<span class="money">
    <%=p.getCash()%>
</span>
