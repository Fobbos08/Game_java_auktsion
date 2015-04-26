/**
 * Created by Эмиль on 25.04.2015.
 */
$(document).ready( function(){
    $("#loginButton").click(function(e){
        e.preventDefault();
        $.ajax({
            url: "/loginblock",
            type: "POST",
            success: function (data) {
                $('#loginArea').html(data);
            },
            error: function ()
            {
                alert('error load login block');
            }
        });
    });
});
