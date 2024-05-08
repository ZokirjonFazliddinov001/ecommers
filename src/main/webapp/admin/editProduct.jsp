<%@ page import="uz.pdp.ecommers.entity.Category" %>
<%@ page import="uz.pdp.ecommers.repo.CategoryRepo" %>
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
    int id = Integer.parseInt(request.getParameter("id"));
    Product product = ProductRepo.findById(id);
    List<Category> categories = CategoryRepo.findAll();
%>

<div class="row mt-4">
    <div class="col-4 offset-4">
        <div class="card p-2">
            <form action="http://localhost:8080/product/edit" method="post" >
                <h1>Edit product</h1>
                <input name="id" type="text" value="<%= product.getId()%>" hidden="hidden">
                <input value="<%=product.getName()%>" name="name" autofocus class=" form-control my-3" type="text" placeholder="Name" >
                <input value="<%=product.getPrice()%>" name="price" autofocus class=" form-control my-3" type="number" placeholder="Price" >
                <select name="categoryId" class="form-control my-3">
                    <option value="" selected disabled>Select category</option>
                    <%for (Category category : categories) { %>
                    <option value="<%=category.getId()%>"><%= category.getName()%></option>
                    <%} %>
                </select>
                <div class="text-center">
                    <button class="btn btn-dark w-100">edit</button>
                </div>
            </form>

        </div>
    </div>

</div>
</body>
</html>
