<%@ page import="java.util.ArrayList" %>
<%@ page import="game.bonuses.Bonus" %>
<%@ page import="helpers.VerifyHelper" %>
<%@ page import="java.util.UUID" %>
<%@ page import="game.GameManipulator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Cookie[] cookies = request.getCookies();
    boolean isLogined = false;
    boolean isInGame = false;
    boolean isAdmin = false;
    UUID gameId = null;
    UUID playerId = null;
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("id")) {
                isLogined = VerifyHelper.verifySession(UUID.fromString(cookie.getValue()));
                isAdmin = VerifyHelper.verifyAdmin(UUID.fromString(cookie.getValue()));
            }
            if (cookie.getName().equals("gameId")) {
                gameId = UUID.fromString(cookie.getValue());
                //isInGame = true;
            }
        }
    }

    ArrayList<String> names = new ArrayList<String>();
    for(Bonus bonus: Bonus.values())
    {
        names.add(bonus.name());
    }
%>
<div class="bonuses">
    <%
        for(String name:names)
        {
    %>
            <div class="bonus <%=name%>" id="<%=name%>" style="display:<%= (name.equals(Bonus.Joker.name())&isLogined& GameManipulator.isJoker(gameId))||!name.equals(Bonus.Joker.name())?"":"none"%>">
            </div>
    <%
        }
    %>
</div>

<script type="text/javascript">
    $(document).ready(function(){
        $(".bonuses").find("div").bind("click", function(e) {
            $.post('/game', {data : "buyBonus", bonusName: this.id}, function(){});
        });
    });
</script>