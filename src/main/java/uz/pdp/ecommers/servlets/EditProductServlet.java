package uz.pdp.ecommers.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.ecommers.entity.Product;
import uz.pdp.ecommers.repo.ProductRepo;

import java.io.IOException;

@WebServlet(name = "editProduct", urlPatterns = "/product/edit")
public class EditProductServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = new Product(
                Integer.parseInt(req.getParameter("id")), req.getParameter("name"),
                Integer.parseInt(req.getParameter("price")),
                Integer.parseInt(req.getParameter("categoryId")),
                req.getParameter("photo").getBytes()
        );
        ProductRepo.edit(product);
        resp.sendRedirect("/admin/product.jsp");
    }
}
