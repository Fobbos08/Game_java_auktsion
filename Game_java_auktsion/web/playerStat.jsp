<%@ page import="java.util.UUID" %>
<%@ page import="helpers.VerifyHelper" %>
<%@ page import="business.DBConnector" %>
<%@ page import="business.SessionManipulator" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags" %>
<%Cookie[] cookies = request.getCookies();
    boolean isLogined = false;
    boolean isInGame = false;
    boolean isAdmin = false;
    UUID gameId = null;
    UUID playerId = null;
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("id")) {
                playerId = UUID.fromString(cookie.getValue());
                isLogined = VerifyHelper.verifySession(UUID.fromString(cookie.getValue()));
                isAdmin = VerifyHelper.verifyAdmin(UUID.fromString(cookie.getValue()));
            }
            if (cookie.getName().equals("gameId")) {
                gameId = UUID.fromString(cookie.getValue());
                //isInGame = true;
            }
        }
    }
    request.setAttribute("totalScore", DBConnector.getUserTotalScore(SessionManipulator.getUser(playerId).getId()));
    request.setAttribute("countGame",DBConnector.getUserGameCount(SessionManipulator.getUser(playerId).getId()));
%>

<layout:genericpage>
    <jsp:body>
        <div class="stat">
            <label>Total Score:</label>${totalScore}
            <label>Game count:</label>${countGame}
        </div>
    </jsp:body>
</layout:genericpage>

