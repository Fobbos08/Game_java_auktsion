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
        <div class="usersList" id="users"></div>

        <h3>Games</h3>
        <ul>
            ${str}
        </ul>

        <script type="text/javascript">
            var ws = new WebSocket("ws://localhost:8081/socket");

            ws.onmessage = function(event) {
                var mySpan = document.getElementById("cost");
                if (event.data == "addingUser")
                {
                    $.get("/users", null, function (data){
                        $("#body").html(data);
                    });
                }
                if (event.data == "tick")
                {
                    $.get("/gamewindow", null, function (data){
                        $("#body").html(data);
                    });
                }
            };
        </script>

    </jsp:body>
</layout:genericpage>