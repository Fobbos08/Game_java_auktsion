<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="tovarForm">
    <form name="settings" id="settings" action="#">
        <label>Money count:</label><input type="number" id="playerStartMoney" name="playerStartMoney" min="50"/>
        <input type="submit"/>
    </form>
</div>
<script type="text/javascript">
    $(document).ready(function(){
       $("#playerStartMoney").bind("change", function(e)
       {
           if(this.value<50) {this.value=50;}
       });
        $("#settings").bind("submit", function(e){
            e.preventDefault();
            alert($('#settings'));
            $.post('/settings', $('#settings').serialize(), function(data){
            });
        });
    });

</script>
