package uz.pdp.ecommers.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.ecommers.entity.Order;
import uz.pdp.ecommers.enums.Status;
import uz.pdp.ecommers.repo.OrderRepo;

import java.io.IOException;

@WebServlet(name = "chooseStatus", value = "/product/chooseStatus")
public class ChooseStatusServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("id");
        Order order = OrderRepo.findById(Integer.parseInt(orderId));
        assert order != null;
        if (order.getStatus().equals(Status.OPEN)){
            order.setStatus(Status.IN_PROGRESS);
        } else if (order.getStatus().equals(Status.IN_PROGRESS)) {
            order.setStatus(Status.COMPLETED);
        } else if (order.getStatus().equals(Status.COMPLETED)) {
            order.setStatus(Status.ARCHIVE);
        }

        OrderRepo.edit(order);
        resp.sendRedirect("/admin/orderStatus.jsp");
    }

}
