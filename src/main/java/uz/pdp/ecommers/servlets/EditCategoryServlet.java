package uz.pdp.ecommers.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.ecommers.entity.Category;
import uz.pdp.ecommers.repo.CategoryRepo;

import java.io.IOException;

@WebServlet(name = "editCategory", urlPatterns = "/category/edit")
public class EditCategoryServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        int id = Integer.parseInt(req.getParameter("id"));
        Category category = new Category(
                id, name
        );
        CategoryRepo.edit(category);
        resp.sendRedirect("/admin/category.jsp");
    }
}
