$(document).ready(function () {
    var JWT = window.localStorage.getItem('JWT');
    $.ajax({
        url: 'http://localhost:8080/auth',
        method: 'GET',
        headers: {
            'Authorization': JWT
        },
        success: function (JWT) {

        },
        error: function (xhr, status, error) {
            window.location.href = 'http://localhost:8080/login';
        }
    });
});