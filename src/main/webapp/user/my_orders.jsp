<%@ page import="uz.pdp.ecommers.entity.Order" %>
<%@ page import="uz.pdp.ecommers.repo.OrderRepo" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<html>
<head>
    <title>My orders</title>
    <link rel="stylesheet" href="../static/bootstrap.min.css">
    <link rel="stylesheet" href="../static/bootstrap-grid.css">
</head>
<body>
<%
    List<Order> orders = OrderRepo.findAll();
%>

<div class="row">
        <div class="col-3 offset-10 p-4">
            <a href="../index.jsp"> <button class="btn btn-dark "> Back to home </button></a>
        </div>
    <div class="col-8 offset-2">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>DateTime</th>
                    <th>Status</th>
                    <th>#</th>
                </tr>
                </thead>
                <tbody>
                <%for (Order order :orders ) {%>
                <tr>
                    <td><%= order.getId()%></td>
                    <td><%= order.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))%></td>
                    <td><%= order.getStatus()%></td>
                    <td>
                        <a href="/user/details.jsp?id=<%=order.getId()%>"><button class="btn btn-outline-dark">details</button></a>
                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>
    </div>
</div>
</body>
</html>
