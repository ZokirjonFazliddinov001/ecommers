<%@ page import="uz.pdp.ecommers.entity.Product" %>
<%@ page import="uz.pdp.ecommers.repo.ProductRepo" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../static/bootstrap.min.css">

</head>
<body>

<%
    List<Product> products = ProductRepo.findAll();
%>
<div class="row">
    <div class="col-3 border-rigth p-4">
        <ul class="list-group">
            <a href="category.jsp"><li class="list-group-item">Category</li></a>
            <a href="product.jsp"><li class="list-group-item bg-dark text-white">Product</li></a>
        </ul>
    </div>

    <div class="col-9">
        <div class="row">
            <div class="col-4 offset-9 p-4">
                <a href="../index.jsp"> <button class="btn btn-dark "> Back to home </button></a>
                <a href="addProduct.jsp"> <button class="btn btn-dark "> Add product </button></a>
            </div>
        </div>

        <div class="p-4">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>price</th>
                    <th>categoryID</th>
                    <th>#</th>
                </tr>
                </thead>
                <tbody>
                <%for (Product product :products ) {%>
                <tr>
                    <td><%= product.getId()%></td>
                    <td><%= product.getName()%></td>
                    <td><%= product.getPrice()%></td>
                    <td><%= product.getCategoryById(product.getCategoryId())%></td>
                    <td>
                        <a href="editProduct.jsp?id=<%=product.getId()%>"><button class="btn btn-info">edit</button></a>
                        <a href="http://localhost:8080/product/delete?id=<%=product.getId()%>" ><button class="btn btn-danger">Delete</button></a>
                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
