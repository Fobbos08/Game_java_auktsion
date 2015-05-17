$(document).ready( function(){
    $.get("/game", {data: "getMyMoney"}, function (data){
        $("#myMoney").html(data);
    });
    $("#buyButton").bind("click", function() {
        $.post('/game', {data : "buy"}, function(){});
    })
});
