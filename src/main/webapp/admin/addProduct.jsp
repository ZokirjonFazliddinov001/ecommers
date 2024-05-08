<%@ page import="uz.pdp.ecommers.repo.CategoryRepo" %>
<%@ page import="uz.pdp.ecommers.entity.Category" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: murod
  Date: 3/31/2024
  Time: 5:41 PM
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
<div class="row mt-4">
    <div class="col-4 offset-4">
        <div class="card p-2">
            <form enctype="multipart/form-data" action="http://localhost:8080/product/add" method="post" >
                <h1>Add Product</h1>
                <input name="name" autofocus class=" form-control my-3" type="text" placeholder="Name" >
                <input name="price" autofocus class=" form-control my-3" type="number" placeholder="price" >
                <select name="categoryId" class="form-control my-3">
                    <option value="" selected disabled>Select category</option>
                    <%for (Category category : categories) { %>
                    <option value="<%=category.getId()%>"><%= category.getName()%></option>
                    <% } %>
                </select>
                <input name="photo" type="file">
                <div class="text-center form-control">
                    <button class="btn btn-dark w-100 ">save</button>
                </div>
            </form>

        </div>
    </div>

</div>

</body>
</html>
