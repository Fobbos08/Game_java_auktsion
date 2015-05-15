$("#loginForm").bind("submit", function(e){
    e.preventDefault();
    $.post('/loginblock', $('#loginForm').serialize(), function(data){
        if (data.length>20)
        {
            $("#loginErrors").html("");
            $('#loginForm').hide();
            $('#login').hide();
            $('.overlay').hide();
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

//hide or show password
$('.hide-password').on('click', function(){
    var $this= $(this),
        $password_field = $this.prev('input');

    ( 'password' == $password_field.attr('type') ) ? $password_field.attr('type', 'text') : $password_field.attr('type', 'password');
    ( 'Hide' == $this.text() ) ? $this.text('Show') : $this.text('Hide');
    //focus and move cursor to the end of input field
    $password_field.putCursorAtEnd();
});

$("#registerForm").bind("submit", function(e){
    e.preventDefault();
    $.post('/loginblock', $('#registerForm').serialize(), function(data){
        if (data.length<20)
        {
            $("#registerErrors").html("");
            $('#registerForm').hide();
            $('#loginForm').hide();
            $('.overlay').hide();
            alert("ползователь создан, залогиньтесь");
        }else {
            $("#registerErrors").html(data);
        }
    });
});