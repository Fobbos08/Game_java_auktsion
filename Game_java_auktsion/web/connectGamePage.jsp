<%@ page import="game.GameManipulator" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="game.ReturnedGame" %>
<%@ page import="business.ConstFields" %>
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
<%
    request.setAttribute("AddingUser", ConstFields.AddingUser);
    request.setAttribute("Buy", ConstFields.Buy);
    request.setAttribute("EndGame", ConstFields.EndGame);
    request.setAttribute("GetStat", ConstFields.GetStat);
    request.setAttribute("Tick", ConstFields.Tick);
%>
<layout:genericpage>
    <jsp:body>
        <jsp:include page="/shared/gamePage.jsp"/>
    </jsp:body>
</layout:genericpage>