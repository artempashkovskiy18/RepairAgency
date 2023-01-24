document.addEventListener("DOMContentLoaded", function() {
    let signInButtons = document.getElementsByName('sign-in-button');
    let signUpButtons = document.getElementsByName('sign-up-button');
    let closeButtons = document.getElementsByName('close');
    for (let i = 0; i < signInButtons.length; i++) {
        signInButtons[i].addEventListener('click', function(){
            document.getElementById("sign-in").style.display = "flex";
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


});