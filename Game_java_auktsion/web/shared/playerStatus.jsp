<%@ page import="game.Player" %>
<%@ page import="game.Tovar" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Player p = (Player)request.getAttribute("player");
%>
<div class="userInfo">
    <label>You have money: </label>
    <span class="money">
        <%=p.getCash()%>
    </span>
    <label>You have goods: </label>
    <%
        for (Tovar t: p.getTovars())
        {
    %>
        <span class="tovar">
            <%=t.getGoods().getTitle()%>
        </span>
    <%
        }
    %>
</div>


