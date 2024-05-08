<%@ page import="uz.pdp.ecommers.repo.BasketProductRepo" %>
<%@ page import="uz.pdp.ecommers.entity.BasketProduct" %>
<%@ page import="uz.pdp.ecommers.entity.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="uz.pdp.ecommers.entity.OrderProduct" %>
<%@ page import="uz.pdp.ecommers.repo.OrderProductRepo" %><%--
  Created by IntelliJ IDEA.
  User: murod
  Date: 4/5/2024
  Time: 8:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Details</title>
    <link rel="stylesheet" href="../static/bootstrap.min.css">
    <link rel="stylesheet" href="../static/bootstrap-grid.css">
</head>
<body>
<%
    Integer id = Integer.parseInt(request.getParameter("id"));
    List<OrderProduct> orderProducts = OrderProductRepo.findByOrderId(id);
    %>
<div class="row">
    <div class="col-9">
        <div class="p-4 col-9">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Amount</th>
                    <th>price</th>
                    <th>categoryID</th>
                    <th>#</th>
                </tr>
                </thead>

                <%for (OrderProduct orderProduct : orderProducts) {
                    Product product = OrderProductRepo.getProductById(orderProduct.getProductId());
                %>
                <tbody>

                <tr>
                    <td><%= product.getId()%></td>
                    <td><%= product.getName()%></td>
                    <td>
                        <%=orderProduct.getAmount()%>
                    </td>
                    <td><%= product.getPrice()%></td>
                    <td><%= product.getCategoryById(product.getCategoryId())%></td>

                </tr>
                </tbody>
                <% } %>
            </table>
        </div>
    </div>

</div>
</body>
</html>
