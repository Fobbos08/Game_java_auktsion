<%@ page import="game.Tovar" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="time" id="time">
    </div>
<%
    Tovar t = (Tovar) request.getAttribute("goods");
%>
<div class="title">
    <%=t.getGoods().getTitle()%>
</div>
<div class="description">
    <%=t.getGoods().getDescription()%>
</div>
<div class="image">

</div>

<div class="cost" id="cost">
    <%=t.getCurrentCost()%>
</div>


