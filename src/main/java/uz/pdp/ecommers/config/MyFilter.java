package uz.pdp.ecommers.config;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import uz.pdp.ecommers.entity.User;
import uz.pdp.ecommers.enums.Role;
import uz.pdp.ecommers.repo.UserRepo;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@WebFilter(urlPatterns = "/admin/*")
public class MyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req =  (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        Object object = session.getAttribute("currentUser");
        if (object==null){
            Optional<Cookie> cookieOptional = Arrays.stream(req.getCookies()).filter(cookie -> cookie.getName().equals("userId")).findFirst();
            if (cookieOptional.isPresent()){
                Cookie cookie = cookieOptional.get();
                UUID userId = UUID.fromString(cookie.getValue());
                Optional<User> userOptional = UserRepo.findById(userId);
                if (userOptional.isPresent()){
                    User user = userOptional.get();
                    session.setAttribute("currentUser", user);
                    resp.sendRedirect("/admin/admin.jsp");
                }
            }else {
                session.setAttribute("currentUser", null);
            }
            resp.sendRedirect("/404");
        }else {
            User user = (User) object;
            if (user.getRole().equals(Role.ADMIN)){
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }

        }
        resp.sendRedirect("/404.jsp");

    }
}
