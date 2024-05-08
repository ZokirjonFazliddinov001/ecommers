<%@ page import="uz.pdp.ecommers.repo.CategoryRepo" %>
<%@ page import="uz.pdp.ecommers.entity.Category" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../static/bootstrap.min.css">
</head>
<body>
<%
  int id = Integer.parseInt(request.getParameter("id"));
  Category category = CategoryRepo.findById(id);
%>

<div class="row mt-4">
  <div class="col-4 offset-4">
    <div class="card p-2">
      <form action="http://localhost:8080/category/edit" method="post" >
        <h1>Edit Category</h1>
        <input name="id" type="text" value="<%= category.getId()%>" hidden="hidden">
        <input value="<%=category.getName()%>" name="name" autofocus class=" form-control my-3" type="text" placeholder="Name" >
        <div class="text-center">
          <button class="btn btn-dark w-100">edit</button>
        </div>
      </form>

    </div>
  </div>

</div>

</body>
</html>
