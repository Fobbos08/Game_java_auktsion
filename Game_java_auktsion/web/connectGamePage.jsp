<%@ page import="game.GameManipulator" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="game.ReturnedGame" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags" %>

<%

    String str = "";
    ArrayList<ReturnedGame> games;
    games = GameManipulator.getGames();
    for(int i=0; i<games.size(); i++)
    {
        str+=
                "<li id=\"" + games.get(i).getId() + "\">Game "+i+" max players: "+
                        games.get(i).getMaxPlayerCount()+" players in game: "+
                        games.get(i).getNowPlayerCount()+"</li>";
    }
    request.setAttribute("str", str);
%>
<layout:genericpage>
    <jsp:body>

        <h3>Games</h3>
        <ul>
            ${str}
        </ul>
    </jsp:body>
</layout:genericpage>