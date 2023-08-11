document.addEventListener("DOMContentLoaded", function() {
    let signInButtons = document.getElementsByName('sign-in-button');
    let signUpButtons = document.getElementsByName('sign-up-button');
    let closeButtons = document.getElementsByName('close');
    let logOutButton =  document.getElementsByName("log-out-button").item(0);
    for (let i = 0; i < signInButtons.length; i++) {
        signInButtons[i].addEventListener('click', function(){
            document.getElementById('sign-in').style.display = 'flex';
        });
    }
    for (let i = 0; i < signUpButtons.length; i++) {
        signUpButtons[i].addEventListener('click', function(){
            document.getElementById("sign-up").style.display = "flex";
        });
    }
    for (let i = 0; i < closeButtons.length; i++) {
        closeButtons[i].addEventListener('click', function(){
            document.getElementById("sign-up").style.display = "none";
            document.getElementById("sign-in").style.display = "none";
        });
    }

    logOutButton.addEventListener('click', function () {
        document.cookie = "email=; expires=Thu, 01 Jan 1970 00:00:00 UTC;";
        location.reload();
    })

    $('#submit-sign-in').click(function (){
        var signInEmail = document.getElementById('sign-in-email');
        validateEmail(signInEmail, event);
    });

    $('#submit-sign-up').click(function (){
        var signUpEmail = document.getElementById('sign-up-email');
        validateEmail(signUpEmail, event);
        validatePasswordsWhileRegistration(event);
    });

    if (getCookie("email") === ""){
        document.getElementById("get-orders-button").style.display = "none";
        logOutButton.style.display = "none";
    }
});

const validateEmail = (email, event) => {
    if(!email.value.match(
        /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/)){
        event.preventDefault();
        email.style.border = '2px solid red';
    }
};

const validatePasswordsWhileRegistration = (event) => {
    var password = document.getElementById('sign-up-password');
    var repeatPassword = document.getElementsByName('password-repeat')[0];
    if(password.value !== repeatPassword.value){
        event.preventDefault();
        password.style.border = '2px solid red';
        repeatPassword.style.border = '2px solid red';
    }
};

function getCookie(cname) {
    let name = cname + "=";
    let ca = document.cookie.split(';');
    for(let i = 0; i < ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}