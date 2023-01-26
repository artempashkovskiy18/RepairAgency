<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Orders</title>
    <link href="CSS/style.css" rel="stylesheet">
</head>
<body>
<div class="orders">
    <table border="1">
        <tr>
            <th>User name</th>
            <th>User email</th>
            <th>User phone</th>
            <th>Status</th>
            <th>Price</th>
            <th>Description</th>
        </tr>
        <c:forEach var="order" items="${requestScope.orders}">
            <tr>
                <td><c:out value="${order.user.name}"/></td>
                <td><c:out value="${order.user.email}"/></td>
                <td><c:out value="${order.user.phone}"/></td>
                <td><c:out value="${order.status}"/> </td>
                <td><c:out value="${order.price}"/></td>
                <td><c:out value="${order.description}"/></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
