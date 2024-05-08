package uz.pdp.ecommers.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import uz.pdp.ecommers.entity.Product;
import uz.pdp.ecommers.payload.Basket;
import uz.pdp.ecommers.repo.BasketProductRepo;
import uz.pdp.ecommers.repo.CategoryRepo;

import java.io.IOException;
import java.util.Map;

@WebServlet(name = "deleteBasketProduct", urlPatterns = "/basketProduct/delete")
public class DeleteBasketProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        HttpSession session = req.getSession();

        Object basketProduct = session.getAttribute("basket");
        Basket basket = (Basket) basketProduct;
        basket.getBasketProducts().entrySet().removeIf(entry -> entry.getKey().getId() == id);

        resp.sendRedirect("basket.jsp");
    }
}
