<%--<html>--%>
<%--<body>--%>
<%--<h2>Hello World!</h2>--%>
<%--</body>--%>
<%--</html>--%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Repair agency</title>
    <link href="CSS/style.css" rel="stylesheet">
    <script src = "JS/main.js"></script>

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">


</head>
<body>

<!--<nav>-->
<!--    <div class="upperRadius"><a href="main.html" class="navbarTextLink firstLevelLink"> Головна </a>-->

<!--        <div class="containerForInternalLinks">-->
<!--            <div class="internalLink"><a href="" class="navbarTextLink"> Ціни </a></div>-->
<!--            <div class="internalLink"><a href="" class="navbarTextLink"> Створити замовлення </a></div>-->
<!--        </div>-->

<!--    </div>-->
<!--    <div class="upperRadius downRadius"><a href="" class="navbarTextLink firstLevelLink"> Новини </a></div>-->
<!--    <div class="upperRadius downRadius"><a href="" class="navbarTextLink firstLevelLink"> Контакти </a></div>-->
<!--    <div class="upperRadius downRadius"><a href="" class="navbarTextLink firstLevelLink"> Про нас </a></div>-->
<!--</nav>-->


<nav>
    <ul class="topMenu">
        <li><a href="index.jsp" class="down">Main</a>
            <ul class="submenu">
                <li><a href="">Prices</a></li>
                <li><a href="">Create order</a></li>
            </ul>
        </li>
        <li><a href="">News</a></li>
        <li><a href="">Contacts</a></li>
        <li><a href="">About us</a></li>
    </ul>
    <div>
        <a class="float-right btn btn-outline-primary" id = "sign-in">Sign in</a>
        <a class="float-right btn btn-outline-primary" id = "sign-up">Sign up</a>
    </div>
</nav>


<div class = "registration-container" id = "registration">
    <div class="card">
        <article class="card-body">
            <a href="" class="float-right btn btn-outline-primary">Sign up</a>
            <h4 class="card-title mb-4 mt-1">Sign in</h4>

            <form action="reg" method="post" id="sign-in-form">
                <div class="form-group">
                    <label>Your email</label>
                    <input name="email" class="form-control" placeholder="Email" type="email">
                </div> <!-- form-group// -->
                <div class="form-group">
                    <a class="float-right" href="#">Forgot your password?</a>
                    <label>Your password</label>
                    <input name="password" class="form-control" placeholder="******" type="password">
                </div> <!-- form-group// -->
                <div class="form-group">
                    <div class="checkbox">
                        <label> <input name="save-password" type="checkbox" checked /> Save password </label>
                    </div> <!-- checkbox .// -->
                </div> <!-- form-group// -->
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block" id="submit-sign-in">Sign in</button>
                </div> <!-- form-group// -->
            </form>

        </article>
    </div>
</div>

</body>
</html>
