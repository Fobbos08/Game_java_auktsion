<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:body>
        <div class="usersList" id="#users"></div>

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
        </script>
    </jsp:body>
</t:genericpage>