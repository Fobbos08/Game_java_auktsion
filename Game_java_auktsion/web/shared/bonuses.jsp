<%@ page import="java.util.ArrayList" %>
<%@ page import="game.bonuses.Bonus" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
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
            <div class="bonus <%=name%>" id="<%=name%>" style="display:<%= name.equals(Bonus.Joker.name())?"none":""%>">
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