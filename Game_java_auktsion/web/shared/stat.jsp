<%@ page import="game.Player" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Collections" %>
<%@ page import="helpers.PlayersComparer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="score">
        <%
            ArrayList<Player> players = (ArrayList<Player>) request.getAttribute("players");
            Collections.sort(players, new PlayersComparer());
            int maxScore = players.get(0).getScore();
            for(Player player: players)
            {
        %>
        <div class="user <%= player.getScore()==maxScore ? "winner" : "looser" %>">
            <h2><%=player.getName()%></h2>
            <label>Score:</label><span><%=player.getScore()%></span>
        </div>
        <%
            }
        %>
</div>
