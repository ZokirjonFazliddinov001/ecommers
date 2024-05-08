<%@ page import="uz.pdp.ecommers.entity.Product" %>

<%@ page import="uz.pdp.ecommers.entity.NumberFormat" %>
<%@ page import="uz.pdp.ecommers.repo.ProductRepo" %>
<%@ page import="uz.pdp.ecommers.payload.Basket" %>
<%@ page import="java.util.Map" %>
<%@ page import="uz.pdp.ecommers.entity.User" %>
<%@ page import="uz.pdp.ecommers.repo.BasketRepo" %>


<html>
<head>
    <title>Basket</title>
    <link rel="stylesheet" href="static/bootstrap.min.css">
    <link rel="stylesheet" href="static/bootstrap-grid.css">
</head>
<body>
<%
    Object object = session.getAttribute("basket");
    Basket basket = (Basket) object;
    Object objectUser = session.getAttribute("currentUser");
    User currentUser = null;
    if (objectUser != null){
        currentUser = (User) objectUser;
    }else {
        response.sendRedirect("login.jsp");
        return;
    }


%>


<div class="row">
    <div class="col-4 offset-10 p-4">
        <a href="index.jsp"> <button class="btn btn-dark "> Back to home </button></a>
    </div>
</div>

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
        <tbody>
        </tbody>
        <% for (Map.Entry<Product, Integer> entry : basket.getBasketProducts().entrySet()) {  %>
        <tr>
            <td><%=entry.getKey().getId()%></td>
            <td><%=entry.getKey().getName()%></td>
            <td>
                <a href="http://localhost:8080/minusPlus?action=increment&product_id=<%=entry.getKey().getId()%>"><button>+</button></a>
                <%=entry.getValue()%>
                <a href="http://localhost:8080/minusPlus?action=decrement&product_id=<%=entry.getKey().getId()%>"><button>-</button></a>
            </td>
            <td><%=entry.getKey().getPrice()%></td>
            <td><%=entry.getKey().getCategoryById(entry.getKey().getCategoryId()) %></td>
            <td >
                <a href="http://localhost:8080/basketProduct/delete?id=<%=entry.getKey().getId()%>"><button class="btn btn-danger text-white">delete</button> </a>
            </td>
        </tr>
       <% } %>
    </table>
</div>
<div class="center">
        <div class="right">
            <h3>Total Sum: <%=NumberFormat.format(basket.getBasketProducts().entrySet().stream().mapToLong(item -> (long) (ProductRepo.findById(item.getKey().getId())).getPrice() * item.getValue()).sum())%></h3>
            <a href="http://localhost:8080/create_order?basket_id=<%=BasketRepo.findByUserId(currentUser.getUuid()).getId()%>&sum=<%=basket.getBasketProducts().entrySet().stream().mapToLong(item -> (long) (ProductRepo.findById(item.getKey().getId())).getPrice() * item.getValue()).sum()%>" class="btn"><button class="btn btn-light btn-outline-dark">Create Order</button></a>
        </div>
</div>

</body>
</html>
