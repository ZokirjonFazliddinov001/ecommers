package uz.pdp.ecommers.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.ecommers.entity.BasketProduct;
import uz.pdp.ecommers.entity.Order;
import uz.pdp.ecommers.entity.User;
import uz.pdp.ecommers.payload.Basket;
import uz.pdp.ecommers.repo.BasketProductRepo;
import uz.pdp.ecommers.repo.OrderRepo;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "createOrder", value = "/create_order")
public class CreateOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int basketId = Integer.parseInt(req.getParameter("basket_id"));
        int sum = Integer.parseInt(req.getParameter("sum"));
        User user = (User) req.getSession().getAttribute("currentUser");
        Basket basket = (Basket) req.getSession().getAttribute("basket");
        BasketProductRepo.save(basket.getBasketProducts());

        Order order = OrderRepo.makeOrder(basketId, user.getUuid(), sum);
//        OrderRepo.save(order);

        req.getSession().removeAttribute("basket");

        resp.sendRedirect("/");
    }

}
