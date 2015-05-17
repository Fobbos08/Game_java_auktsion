<%@ page import="game.Player" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="score">
        <%
            ArrayList<Player> players = (ArrayList<Player>) request.getAttribute("players");
            for(Player player: players)
            {
        %>
        <h3><%=player.getName()%></h3>
        <label>Score:</label><p><%=player.getScore()%></p>
        <%
            }
        %>
</div>
