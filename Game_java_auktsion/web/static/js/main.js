/**
 * Created by Эмиль on 25.04.2015.
 */
$(document).ready( function() {
    $('.overlay').bind("click", function () {
        $('.overlay').hide();
        $('#loginArea').hide();
    });
    $("#loginButton").click(function (e) {
        e.preventDefault();
        $.ajax({
            url: "/loginblock",
            type: "POST",
            success: function (data) {
                $('#loginArea').show();
                $('#loginArea').html(data);
                $('.overlay').show();
            },
            error: function () {
                alert('error load login block');
            }
        });
    });

    $("li").click(function (e) {
        document.cookie = "gameId="+this.id;
        $.get("/game", {data:"connection", gameId:this.id}, function(){})
    });
});