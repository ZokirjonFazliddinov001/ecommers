package uz.pdp.ecommers.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import uz.pdp.ecommers.entity.BasketProduct;
import uz.pdp.ecommers.entity.Product;
import uz.pdp.ecommers.payload.Basket;
import uz.pdp.ecommers.repo.BasketRepo;
import uz.pdp.ecommers.repo.ProductRepo;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "addToBasket", value = "/product/addToBasket")
public class AddToBasketServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = ProductRepo.findById(id);
        HttpSession session = req.getSession();
        Object object = session.getAttribute("basket");
        if (object==null){
            Basket basket = new Basket();
            basket.getBasketProducts().put(product, 1);
            session.setAttribute("basket", basket);
        }else {
            Basket basket = (Basket) object;
            basket.getBasketProducts().put(product, 1);
            session.setAttribute("basket", basket);
        }
        resp.sendRedirect("/?categoryId="+product.getCategoryId());
    }
}
