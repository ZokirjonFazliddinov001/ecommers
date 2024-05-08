package uz.pdp.ecommers.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import uz.pdp.ecommers.entity.Product;
import uz.pdp.ecommers.payload.Basket;

import java.io.IOException;
import java.util.Map;

@WebServlet(name = "editMinusPlus", urlPatterns = "/minusPlus")
public class EditMinusPlusServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        int productId = Integer.parseInt(req.getParameter("product_id"));
        HttpSession session = req.getSession();
        Object object = session.getAttribute("basket");
        Basket basket = (Basket) object;
        for (Map.Entry<Product, Integer> entry : basket.getBasketProducts().entrySet()) {
            int amount = entry.getValue();
            if (entry.getKey().getId().equals(productId)) {
                Product product = entry.getKey();
                if ("increment".equals(action)) {
                    basket.getBasketProducts().replace(product, amount + 1);
                } else if ("decrement".equals(action)) {
                    basket.getBasketProducts().replace(product, amount - 1);
                } else {
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
                    return;
                }
            }
        }
        resp.sendRedirect("basket.jsp");
    }
}

