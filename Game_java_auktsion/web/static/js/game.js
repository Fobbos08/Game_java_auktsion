$(document).ready( function(){
    $("#buyButton").bind("click", function() {
        $.post('/game', {data : "buy"}, function(){});
    })
});