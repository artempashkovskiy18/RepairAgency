<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="navAndSignTags" prefix="nst" %>
<html>
<head>
    <title>Error</title>
    <link href="CSS/style.css" rel="stylesheet">
    <script src="JS/main.js"></script>

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
</head>
<body>
    <nst:navSignTag/>
    <h1>An error occurred: ${requestScope.errorMessage} </h1>
</body>
</html>
