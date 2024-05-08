<%@ page import="java.util.List" %>
<%@ page import="uz.pdp.ecommers.entity.Category" %>
<%@ page import="uz.pdp.ecommers.repo.CategoryRepo" %><%--
  Created by IntelliJ IDEA.
  User: murod
  Date: 3/31/2024
  Time: 10:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../static/bootstrap.min.css">

</head>
<body>
<%
    List<Category> categories = CategoryRepo.findAll();
%>

<div class="row">
    <div class="col-3 border-rigth p-4">
        <ul class="list-group">
            <div  class="my-1">
                <a href="category.jsp"> <li class="list-group-item bg-dark text-white">Category</li> </a>
                <a href="product.jsp"> <li class="list-group-item">Product</li> </a>
            </div>

        </ul>
    </div>
    <div class="col-9">
        <div class="row">
            <div class="col-4 offset-9 p-4">
                <a href="../index.jsp"> <button class="btn btn-dark "> Back to home </button></a>
                <a href="addCategory.jsp"><button class="btn btn-dark text-white"> Add category </button> </a>
            </div>
        </div>
        <div class="p-4">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>#</th>
                </tr>
                </thead>
                <tbody>
                <%for (Category category : categories) {%>
                <tr>
                    <td><%= category.getId()%></td>
                    <td><%= category.getName()%></td>
                    <td>
                        <a href="editCategory.jsp?id=<%=category.getId()%>"><button class="btn btn-info">edit</button></a>
                        <a href="http://localhost:8080/category/delete?id=<%=category.getId()%>"><button class="btn btn-dark text-white">delete</button> </a>
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
