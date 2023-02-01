<%@ page import="constants.OrderStatus" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="navAndSignTags" prefix="nst" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Order</title>
    <link href="CSS/style.css" rel="stylesheet">

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>


    <script src="JS/main.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
</head>
<body>
<nst:navSignTag/>
<% pageContext.setAttribute("statuses", OrderStatus.values());%>

<%--do filtering statuses for different roles--%>
<div class="order-container">

    <form action="save">
        <div class="form-group">
            <label>Status</label>
            <select name="status">
                <c:forEach var="status" items="${pageScope.statuses}">
                    <c:choose>
                        <c:when test="${status == requestScope.order.status}">
                            <option value="${status}" selected><c:out value="${status}"/></option>
                        </c:when>
                        <c:otherwise>
                            <option value="${status}"><c:out value="${status}"/></option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label>Price</label>
            <input name="price" class="form-control" value="${requestScope.order.price}" type="number" step="0.01" required>
        </div>

        <div class="form-group">
            <input name="id" class="form-control" value="${requestScope.order.id}" type="hidden" required>
        </div>

        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block" id="create-order-submit">Save</button>
        </div>
    </form>
</div>

</body>
</html>
