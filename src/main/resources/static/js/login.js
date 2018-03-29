$(document).ready(function () {
    var username = $('#signin-username');
    var password = $('#signin-password');

    alert('123');

    $('#signin-button').click(function () {
        $.ajax({
            url: 'http://localhost:8080/login',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({username: username.val(), password: password.val()}),
            success: function (JWT) {
                console.log('123');
                window.localStorage.setItem('JWT', JWT);
                window.location.href = 'http://localhost:8080/get/home';
            },
            error: function (xhr, status, error) {
                console.log(xhr);
                console.log(status);
                console.log(error);
            }
        });
    });
});