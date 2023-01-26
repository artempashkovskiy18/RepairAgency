<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Orders</title>
</head>
<body>
<div class="orders">
    <table>
        <c:forEach var="order" items="${requestScope.orders}">
            <li><c:out value="${order}" /></li>
            <tr>
                <td><c:out value="${order.status}"/> </td>
                <td><c:out value="${order.price}"/></td>
                <td><c:out value="${order.user.email}"/></td>
                <td><c:out value="${order.user.phone}"/></td>
                <td><c:out value="${order.user.name}"/></td>
                <td><c:out value="${order.user.description}"/></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
