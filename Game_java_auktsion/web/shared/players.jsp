<%@ page import="game.Player" %>
<%@ page import="game.Tovar" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="users">
    <ul>
    <%
        ArrayList<Player> players = (ArrayList<Player>) request.getAttribute("users");
        for(Player player: players)
        {
    %>
         <li><%=player.getName()%></li>
    <%
        }
    %>
    </ul>
</div>
