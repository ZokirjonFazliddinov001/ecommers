package uz.pdp.ecommers.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import uz.pdp.ecommers.entity.User;
import uz.pdp.ecommers.enums.Role;
import uz.pdp.ecommers.repo.UserRepo;

import java.io.IOException;

import java.util.Objects;
import java.util.Optional;

@WebServlet(name = "auth", value ="/auth/login" )
public class AuthServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Optional<User> optionalUser = UserRepo.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getPassword().equals(password)){
                if (user.getRole().equals(Role.ADMIN)){
                    HttpSession session = req.getSession();
                    session.setAttribute("currentUser", user);
                    addCookie(req, resp, user);
                    resp.sendRedirect("/admin/admin.jsp");
                    return;
                }else {
                    addCookie(req, resp, user);
                    HttpSession session = req.getSession();
                    session.setAttribute("currentUser", user);
                    resp.sendRedirect("/");
                    return;
                }

            }
        }
        resp.sendRedirect("/login.jsp");
    }


    private static void addCookie(HttpServletRequest req, HttpServletResponse resp, User user) {
        String rememberMe = req.getParameter("rememberMe");
        if (Objects.equals("on", rememberMe)){
            Cookie cookie = new Cookie("userId", user.getUuid().toString());
            cookie.setSecure(false);
            cookie.setPath("/");
            cookie.setMaxAge(3600);
            resp.addCookie(cookie);
        }
    }
}
