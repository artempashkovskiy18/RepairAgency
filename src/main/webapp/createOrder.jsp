<%@ page import="java.time.LocalDate" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="navAndSignTags" prefix="nst" %>
<html>
<head>
    <title>Create order</title>
    <link href="CSS/style.css" rel="stylesheet">

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>


    <script src="JS/main.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">

</head>
<body>
<%
    pageContext.setAttribute("currentDate", LocalDate.now());
    pageContext.setAttribute("lastDate", LocalDate.now().plusDays(7));
    pageContext.setAttribute("minTime", "09:00");
    pageContext.setAttribute("maxTime", "18:00");
%>

<nst:navSignTag/>

<div class="center-block create-order-container">
    <div style="margin-top: 5%">
        <form action="createOrder">
            <div class="form-group">
                <label>Description</label>
                <input name="description" class="form-control" placeholder="Description" type="text" required>
            </div>
            <div class="form-group">
                <label>Order date</label>
                <input name="date"
                       class="form-control"
                       id="date"
                       placeholder="Date"
                       type="date"
                       min=${pageScope.currentDate}
                       max=${pageScope.lastDate}
                       required>
            </div>
            <div class="form-group">
                <label>Order time</label>
                <input name="time"
                       class="form-control"
                       id="time"
                       placeholder="Time"
                       type="time"
                       min=${pageScope.minTime}
                       max=${pageScope.maxTime}
                       required>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary btn-block" id="create-order-submit">Create order</button>
            </div>
        </form>

    </div>
</div>

</body>
</html>
