package uz.pdp.ecommers.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.ecommers.entity.Product;
import uz.pdp.ecommers.repo.ProductRepo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static uz.pdp.ecommers.utils.Paths.rootDirectory;

@WebServlet(name = "downloadPhoto", urlPatterns = "/product/download-photo")
@MultipartConfig
public class DownloadPhotoServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productId = Integer.parseInt(req.getParameter("product_id"));
        Product product =  ProductRepo.findPhotoById(productId);
        resp.getOutputStream().write(product.getPhoto());

    }
}
