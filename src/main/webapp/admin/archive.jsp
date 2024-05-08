<%@ page import="uz.pdp.ecommers.entity.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="uz.pdp.ecommers.repo.OrderRepo" %>
<%@ page import="uz.pdp.ecommers.entity.OrderProduct" %>
<%@ page import="uz.pdp.ecommers.repo.OrderProductRepo" %>
<%@ page import="uz.pdp.ecommers.enums.Status" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Archive</title>
    <link rel="stylesheet" href="../static/bootstrap.min.css">
</head>
<body>
<%
    List<Order> orders = OrderRepo.findAll();
    List<OrderProduct> orderProducts = OrderProductRepo.findAll();

%>


<table class="table">
    <thead>
    <tr>
        <th>Id</th>
        <th>DateTime</th>
        <th>Status</th>
        <th>Price</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <%for (Order order : orders) { %>
        <%
            int sum = 0;
            for (OrderProduct orderProduct : orderProducts) {
                if (orderProduct.getOrderId()==order.getId()) {
                    sum += 1;
                }
            }
        %>
        <% if (order.getStatus().equals(Status.ARCHIVE)){ %>
        <td><%=order.getId()%></td>
        <td><%=order.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))%></td>
        <td><%=order.getStatus()%></td>
        <td><%=order.getPrice()%></td>

        <% } %>

    </tr>
    </tbody>
    <% }  %>
</table>

</body>
</html>
