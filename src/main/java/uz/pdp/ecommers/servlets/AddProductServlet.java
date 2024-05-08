package uz.pdp.ecommers.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import lombok.SneakyThrows;
import uz.pdp.ecommers.entity.Category;
import uz.pdp.ecommers.entity.Product;
import uz.pdp.ecommers.repo.CategoryRepo;
import uz.pdp.ecommers.repo.ProductRepo;

import java.io.*;
import java.util.UUID;

@WebServlet(name = "addProduct", urlPatterns = "/product/add")
@MultipartConfig
public class AddProductServlet extends HttpServlet {
    private final String rootDirectory = System.getProperty("user.home");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        int price = Integer.parseInt(req.getParameter("price"));
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        Part photo = req.getPart("photo");
        byte[] photoBytes = photo.getInputStream().readAllBytes();

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setCategoryId(categoryId);
        product.setPhoto(photoBytes);
        ProductRepo.save(product);
        resp.sendRedirect("/admin/product.jsp");
    }
}
