<%@ page import="uz.pdp.ecommers.repo.OrderProductRepo" %>
<%@ page import="uz.pdp.ecommers.entity.OrderProduct" %>
<%@ page import="java.util.List" %>
<%@ page import="uz.pdp.ecommers.repo.OrderRepo" %>
<%@ page import="uz.pdp.ecommers.entity.Order" %>
<%@ page import="uz.pdp.ecommers.enums.Status" %>
<html>
<head>
    <title>OrderStatus</title>
    <link rel="stylesheet" href="../static/bootstrap.min.css">
    <link rel="stylesheet" href="../static/bootstrap-grid.css">
</head>
<body>
<%
    List<Order> orders = OrderRepo.findAll();
    List<OrderProduct> orderProducts = OrderProductRepo.findAll();

%>

<%--<div class="col-3 p-2">--%>
<div class="row offset-7">
    <a class="btn btn-outline-dark" href="archive.jsp">Go to archive</a>
</div>
<table class="table">
    <thead>
    <tr>
        <th>Open</th>
        <th>In-Progress</th>
        <th>Completed</th>
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
        <td>
            <% if (order.getStatus().equals(Status.OPEN)){ %>
            <div class="card">
                <%=order.getStatus() %>
                <div class="card-body">
                    <h2 class="card-title text-center"><%= order.getId()%></h2>
                    <p class="card-text text-center">products: <%=sum%></p>
                    <p class="card-text text-center">price: <%=order.getPrice()%></p>
                    <form action="product/addToBasket" method="post">
                        <a href="http://localhost:8080/product/chooseStatus?id=<%=order.getId()%>" class="btn btn-primary"  data-mdb-ripple-init> >>> </a>
                    </form>
                </div>
            </div>
            <% } %>
        </td>
        <td>
            <% if (order.getStatus().equals(Status.IN_PROGRESS)){ %>
            <div class="card">
                <%=order.getStatus() %>
                <div class="card-body">
                    <h2 class="card-title text-center"><%= order.getId()%></h2>
                    <p class="card-text text-center">products: <%=sum%></p>
                    <p class="card-text text-center">price: <%=order.getPrice()%></p>
                    <form action="product/addToBasket" method="post">
                        <a href="http://localhost:8080/product/chooseStatus?id=<%=order.getId()%>" class="btn btn-primary"  data-mdb-ripple-init> >>> </a>
                    </form>
                </div>
            </div>
            <% } %>
        </td>
        <td>
            <% if (order.getStatus().equals(Status.COMPLETED)){ %>
            <div class="card">
                <%=order.getStatus() %>
                <div class="card-body">
                    <h2 class="card-title text-center"><%= order.getId()%></h2>
                    <p class="card-text text-center">products: <%=sum%></p>
                    <p class="card-text text-center">price: <%=order.getPrice()%></p>
                    <form action="product/addToBasket" method="post">
                        <a href="http://localhost:8080/product/chooseStatus?id=<%=order.getId()%>" class="btn btn-primary"  data-mdb-ripple-init> >>> </a>
                    </form>
                </div>
            </div>
            <% } %>
        </td>
    </tr>
    </tbody>
    <% }  %>
</table>
<%--</div>--%>



<%--<div class="row">--%>
<%--    <div class="col-3">--%>
<%--        <% if (order.getStatus().equals(Status.OPEN)){ %>--%>
<%--        <div class="card">--%>
<%--            <%=order.getStatus() %>--%>
<%--            <div class="card-body">--%>
<%--                <h5 class="card-title text-center"><%= order.getId()%></h5>--%>
<%--                <p class="card-text text-center">products: <%=sum%></p>--%>
<%--                <form action="product/addToBasket" method="post">--%>
<%--                    <a href="http://localhost:8080/product/chooseStatus?id=<%=order.getId()%>" class="btn btn-primary"  data-mdb-ripple-init> >>> </a>--%>
<%--                </form>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <% } %>--%>
<%--    </div>--%>
<%--    <div class="col-3">--%>
<%--        <% if (order.getStatus().equals(Status.IN_PROGRESS)){ %>--%>
<%--        <div class="card">--%>
<%--            <%=order.getStatus() %>--%>
<%--            <div class="card-body">--%>
<%--                <h5 class="card-title text-center"><%= order.getId()%></h5>--%>
<%--                <p class="card-text text-center">products: <%=sum%></p>--%>
<%--                <form action="product/addToBasket" method="post">--%>
<%--                    <a href="http://localhost:8080/product/chooseStatus?id=<%=order.getId()%>" class="btn btn-primary"  data-mdb-ripple-init> >>> </a>--%>
<%--                </form>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <% } %>--%>
<%--    </div>--%>
<%--    <div class="col-3">--%>
<%--        <% if (order.getStatus().equals(Status.COMPLETED)){ %>--%>
<%--        <div class="card">--%>
<%--            <%=order.getStatus() %>--%>
<%--            <div class="card-body">--%>
<%--                <h5 class="card-title text-center"><%= order.getId()%></h5>--%>
<%--                <p class="card-text text-center">products: <%=sum%></p>--%>
<%--                <form action="product/addToBasket" method="post">--%>
<%--                    <a href="http://localhost:8080/product/chooseStatus?id=<%=order.getId()%>" class="btn btn-primary"  data-mdb-ripple-init> >>> </a>--%>
<%--                </form>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <% } %>--%>
<%--    </div>--%>
<%--</div>--%>
<%--<% } %>--%>
</body>
</html>
