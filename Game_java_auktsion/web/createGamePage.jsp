<%@ page import="business.ConstFields" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:body>
        <a id="startGameButton" class="startGameButton" href="#">Start game</a>
        <jsp:include page="/shared/gamePage.jsp"/>
    </jsp:body>
</t:genericpage>