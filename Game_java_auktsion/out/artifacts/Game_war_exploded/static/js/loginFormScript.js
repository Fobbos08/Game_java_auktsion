$("#loginForm").bind("submit", function(e){
    e.preventDefault();
    $.post('/loginblock', $('#loginForm').serialize(), function(data){
        if (data.length>20)
        {
            $("#loginErrors").html("");
            $('#loginForm').hide();
            document.cookie = "id = " + data;
            alert("найден");
        }else {
            alert(data);
            $("#loginErrors").html(data);
        }
    });
});

$("#registerButton").bind("click", function(e){
    e.preventDefault();
    $("#login").hide();
    $("#register").show();
});

$("#registerForm").bind("submit", function(e){
    e.preventDefault();
    $.post('/loginblock', $('#registerForm').serialize(), function(data){
        if (data.length<20)
        {
            $("#registerErrors").html("");
            $('#registerForm').hide();
            alert("ползователь создан, залогиньтесь");
        }else {
            $("#registerErrors").html(data);
        }
    });
});