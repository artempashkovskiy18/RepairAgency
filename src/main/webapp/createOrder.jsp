<%@ page import="java.time.LocalDate" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="CSS/createOrder.css" rel="stylesheet">
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<%
    pageContext.setAttribute("currentDate", LocalDate.now());
    pageContext.setAttribute("lastDate", LocalDate.now().plusDays(7));
%>

<div class="center-block create-order-container">
    <div class="row" style="margin-top: 5%">

        <form action="">
            <div class="form-group">
                <label>Description</label>
                <input name="description" class="form-control" placeholder="Description" type="text" required>
            </div>
            <div class="form-group">
                <label>Order date</label>
                <input name="date" class="form-control" placeholder="Date" type="date" required>
            </div>
            <div class="form-group">
                <label>Order date</label>
                <input name="time" class="form-control" placeholder="Time" type="time" required>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary btn-block" id="submit-sign-in">Sign in</button>
            </div> <!-- form-group// -->
        </form>

    </div>
</div>

</body>
</html>
