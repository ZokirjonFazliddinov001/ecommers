<%@ page import="uz.pdp.ecommers.repo.ProductRepo" %>
<%@ page import="uz.pdp.ecommers.entity.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="uz.pdp.ecommers.repo.CategoryRepo" %>
<%@ page import="uz.pdp.ecommers.entity.Category" %>
<%@ page import="uz.pdp.ecommers.entity.User" %>
<%@ page import="uz.pdp.ecommers.payload.Basket" %>
<%@ page import="uz.pdp.ecommers.repo.UserRepo" %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Online Market</title>
    <link rel="stylesheet" href="static/bootstrap.min.css">
</head>
<body>
<%
    User currentUser = UserRepo.getUser(session);
    Basket basket = new Basket();
    Object object = session.getAttribute("basket");
    if (object != null){
        basket = (Basket) object;
    }
    List<Category> categories = CategoryRepo.findAll();

    List<Product> products;
    String id = request.getParameter("category_id");

    if (id != null){
        int categoryId = Integer.parseInt(id);
        products = ProductRepo.findByCategoryId(categoryId);
    }else {
        products = ProductRepo.findAll();
    }

%>
<nav class="navbar bg-body-tertiary bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand col-1">OnlineShop</a>
        <a href="./user/my_orders.jsp" class="offset-7 col-1"><button class="btn btn-outline-danger " type="submit">My orders</button> </a>
        <a href="/basket.jsp" class="col-1"><button class="btn btn-outline-danger " type="submit">Basket <%=basket.getBasketProducts().size()%></button> </a>
        <% if(currentUser==null ){ %>
        <a href="login.jsp" class="col-1"><button class="btn btn-outline-success " type="submit">Login</button> </a>
        <% } else { %>
        <a href="auth/logout" class="col-1"><button class="btn btn-outline-success " type="submit">Logout</button> </a>
        <% } %>
    </div>
</nav>

<div class="row">
    <div class="col-3 border-right p-3 btn-success">
        <ul class="list-group">
            <a href="?" class="btn"> <li class="list-group-item btn btn-light, btn-outline-dark">All</li> </a>
            <% for (Category category : categories) { %>
                <a href="?category_id=<%=category.getId()%>" class="btn">
                    <li class="list-group-item btn btn-light, btn-outline-dark"><%=category.getName()%></li>
                </a>
            <%}%>
        </ul>
    </div>
    <div class="col-9 bg-secondary">
        <div class="row">
        <%for (Product product : products) { %>
        <div class="col-3 my-2">
            <div class="card">
                <img width="260px" height="170px" src="product/download-photo?product_id=<%=product.getId()%>" alt="Card image cap">
                <div class="card-body">
                    <h5 class="card-title text-center"><%= product.getName()%></h5>
                    <p class="card-text text-center"><%=product.getPrice()%></p>
                    <% if(basket.getBasketProducts().containsKey(product)) { %>
                    <button class="btn btn-secondary w-100"> Added </button>
                    <% } else { %>
                    <form action="product/addToBasket" method="post">
                        <a href="http://localhost:8080/product/addToBasket?id=<%=product.getId()%>" class="btn btn-primary w-100"  data-mdb-ripple-init>Add to basket </a>
                    </form>
                    <% } %>
                </div>
            </div>
        </div>
        <% } %>
        </div>
    </div>

</div>

</body>
</html>