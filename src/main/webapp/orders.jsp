<%@ page import="constants.OtherConstants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="navAndSignTags" prefix="nst" %>
<html>
<head>
    <title>Orders</title>
    <link href="CSS/style.css" rel="stylesheet">
    <script src="JS/main.js"></script>

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
</head>
<body>
<%
    pageContext.setAttribute("cost", OtherConstants.SORT_OPTION_COST);
    pageContext.setAttribute("status", OtherConstants.SORT_OPTION_STATUS);
    pageContext.setAttribute("date", OtherConstants.SORT_OPTION_DATE);
    pageContext.setAttribute("craftsman", OtherConstants.SORT_OPTION_CRAFTSMAN);
%>

<nst:navSignTag/>
<div>
    <form action="sort">
        <label>Sort by</label>
        <select name="sort">
            <option value="${date}">Date</option>
            <option value="${status}">Status</option>
            <option value="${cost}">Cost</option>
            <option value="${craftsman}">Craftsman</option>
        </select>
        <button type="submit" class="btn btn-outline-primary">Sort</button>
    </form>
</div>
<div class="orders">
    <table border="1">
        <tr>
            <th>Customer name</th>
            <th>Customer email</th>
            <th>Customer phone</th>
            <th>Status</th>
            <th>Price</th>
            <th>Description</th>
            <th>Date</th>
            <th>Time</th>
            <th>Craftsman</th>
            <th>Change</th>
        </tr>
        <c:forEach var="order" items="${requestScope.orders}">
            <tr>
                <td><c:out value="${order.user.name}"/></td>
                <td><c:out value="${order.user.email}"/></td>
                <td><c:out value="${order.user.phone}"/></td>
                <td><c:out value="${order.status}"/></td>
                <td><c:out value="${order.price}"/></td>
                <td><c:out value="${order.description}"/></td>
                <td><c:out value="${order.date}"/></td>
                <td><c:out value="${order.time}"/></td>
                <td>
                    <c:out value="${order.craftsman.name}"/>
                    <c:out value="${order.craftsman.email}"/>
                </td>
                <td>
                    <form action="change">
                        <input type="hidden" name="id" value="${order.id}">
                        <button type="submit" class="btn btn-outline-primary">Change</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
