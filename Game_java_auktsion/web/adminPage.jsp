<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags" %>

<layout:genericpage>
    <jsp:body>
        <div class="subMenu">
            <a href="#" id="settings"><span class="menuButton" ><p>Settings</p></span></a>
        </div>
        <div id="mainBody">
        </div>
        <script type="text/javascript">
            $(document).ready(function () {
                $("#settings").bind("click", function (e) {
                    e.preventDefault();
                    $.post("/adminpage", {data:"settings"}, function(data){
                        $("#mainBody").html(data);
                    });
                });
            });
        </script>
    </jsp:body>
</layout:genericpage>
