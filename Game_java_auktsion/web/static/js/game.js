$(document).ready( function(){
    $("#buyButton").bind("click", function() {
        $.post('/game', {tag : "buy"}, function(){});
    })
});