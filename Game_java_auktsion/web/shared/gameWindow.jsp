<%@ page import="game.Tovar" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Tovar tovar = (Tovar) request.getAttribute("goods");
%>
<script type="text/javascript" src="/static/js/game.js" ></script>
<div class="windowGame">
    <div class="startCost" id="startCost">
    Start cost: <%=tovar.getCost()%>
    </div>
    <div class="title">
        <%=tovar.getGoods().getTitle()%>
    </div>
    <div class="description">
        <%=tovar.getGoods().getDescription()%>
    </div>
    <div class="image">

    </div>

    <div class="cost" id="currentCost">
        <%=tovar.getCurrentCost()%>
    </div>

    <div id="buyButton" class="buyButton">
        Buy
    </div>
</div>
<script type="text/javascript">
    var ws = new WebSocket("ws://localhost:8081/socket");

    ws.onmessage = function(event) {
        var mySpan = document.getElementById("cost");
        if (event.data == "tick")
        {
            $.get("/gamewindow", null, function (data){
                $("#body").html(data);
            });
        }
    };

    ws.onerror = function(event){
        console.log("Error ", event)
    }
</script>

