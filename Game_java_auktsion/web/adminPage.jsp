<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags" %>

<layout:genericpage>
    <jsp:body>
        <div class="subMenu">
            <a href="#" id="addTovar"><span class="menuButton" ><p>Create game</p></span></a>
        </div>
        <div id="mainBody">
        </div>
        <script type="text/javascript">
            $(document).ready(function () {
                $("#addTovar").bind("click", function (e) {
                    e.preventDefault();
                    $.post("/adminpage", {data:"addtovar"}, function(data){
                        $("#mainBody").html(data);
                    });
                });
            });
        </script>
    </jsp:body>
</layout:genericpage>
