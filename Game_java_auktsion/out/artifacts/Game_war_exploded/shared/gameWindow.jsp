<%@ page import="game.Tovar" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Tovar t = (Tovar) request.getAttribute("goods");
%>
<div class="windowGame">
    <div class="startCost" id="startCost">
    Start cost: <%=t.getCost()%>
    </div>
    <div class="title">
        <%=t.getGoods().getTitle()%>
    </div>
    <div class="description">
        <%=t.getGoods().getDescription()%>
    </div>
    <div class="image">

    </div>

    <div class="cost" id="currentCost">
        <%=t.getCurrentCost()%>
    </div>

    <div class="byButton">
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

