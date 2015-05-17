<%@ page import="business.ConstFields" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%
    request.setAttribute("AddingUser", ConstFields.AddingUser);
    request.setAttribute("Buy", ConstFields.Buy);
    request.setAttribute("EndGame", ConstFields.EndGame);
    request.setAttribute("GetStat", ConstFields.GetStat);
    request.setAttribute("Tick", ConstFields.Tick);
%>
<t:genericpage>
    <jsp:body>
        <div class="usersList" id="users"></div>

        <a id="startGameButton" class="startGameButton" href="#"> Начать игру</a>

        <script type="text/javascript">
            $("#startGameButton").bind("click", function(e){
                e.preventDefault();
                $.post("/creategame", null, function(data){
                    if(data.length<10) {//ok
                        $.get("/gamewindow", null, function (data){
                            $("#body").html(data);
                        });
                    }
                });
            });

            var ws = new WebSocket("ws://localhost:8081/socket");

            ws.onmessage = function(event) {
                var mySpan = document.getElementById("cost");
                if (event.data == "${AddingUser}")
                {
                    $.get("/users", null, function (data){
                        $("#users").html(data);
                    });
                }
                if (event.data == "${Tick}")
                {
                    $.get("/gamewindow", null, function (data){
                        $("#body").html(data);
                    });
                }
                if (event.data == "${EndGame}")
                {
                    $.get("/game", {data : "${GetStat}"}, function (data){
                        $("#body").html(data);
                    });
                }
            };

            ws.onerror = function(event){
                console.log("Error ", event)
            }
        </script>

    </jsp:body>
</t:genericpage>