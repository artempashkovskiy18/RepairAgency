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
<nav>
    <ul class="topMenu">
        <li><a href="index.jsp" class="down">Main</a>
            <ul class="submenu">
                <li><a href="">Prices</a></li>
                <li><a href="">Create order</a></li>
            </ul>
        </li>
        <li><a href="">Contacts</a></li>
        <li><a href="">About us</a></li>
        <li><a href="orders">Get orders</a></li>
    </ul>
    <div>
        <a class="float-right btn btn-outline-primary" name="sign-in-button">Sign in</a>
        <a class="float-right btn btn-outline-primary" name="sign-up-button" >Sign up</a>
    </div>
</nav>


<div class = "sign-container" id="sign-in">
    <div class="card">
        <article class="card-body">
            <a href="" class="float-right btn btn-outline-primary" name="close">X</a>
            <h4 class="card-title mb-4 mt-1">Sign in</h4>

            <form action="login" method="post" id="sign-in-form">
                <div class="form-group">
                    <label>Your email</label>
                    <input name="email" class="form-control" id="sign-in-email" placeholder="Email" type="email" required>
                </div> <!-- form-group// -->
                <div class="form-group">
                    <a class="float-right" href="#">Forgot your password?</a>
                    <label>Your password</label>
                    <input name="password" class="form-control" placeholder="******" type="password" required>
                </div> <!-- form-group// -->
                <div class="form-group">
                    <div class="checkbox">
                        <label> <input name="remember-me" type="checkbox" checked /> Remember me </label>
                    </div> <!-- checkbox .// -->
                </div> <!-- form-group// -->
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block" id="submit-sign-in">Sign in</button>
                </div> <!-- form-group// -->
            </form>

        </article>
    </div>
</div>

<div class = "sign-container" id ="sign-up">
    <div class="card">
        <article class="card-body">
            <a href="" class="float-right btn btn-outline-primary" name="close">X</a>
            <h4 class="card-title mb-4 mt-1">Sign up</h4>

            <form action="registration" method="post" id="sign-up-form">
                <div class="form-group">
                    <label>Your name</label>
                    <input name="name" class="form-control" placeholder="Name" required>
                </div>
                <div class="form-group">
                    <label>Your phone</label>
                    <input name="phone" class="form-control" placeholder="Phone" type = "tel">
                </div>
                <div class="form-group">
                    <label>Your email</label>
                    <input name="email" class="form-control" id="sign-up-email" placeholder="Email" type="email" required>
                </div> <!-- form-group// -->
                <div class="form-group">
                    <label>Your password</label>
                    <input name="password" class="form-control" id="sign-up-password" placeholder="******" type="password" required>
                </div>
                <div class="form-group">
                    <label>Repeat your password</label>
                    <input name="password-repeat" class="form-control" placeholder="******" type="password" required>
                </div><!-- form-group// -->
                <div class="form-group">
                    <div class="checkbox">
                        <label> <input name="remember-me" type="checkbox" checked /> Remember me </label>
                    </div> <!-- checkbox .// -->
                </div> <!-- form-group// -->
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block" id="submit-sign-up">Sign up</button>
                </div> <!-- form-group// -->
            </form>

        </article>
    </div>
</div>



</body>
</html>
