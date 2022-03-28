package cars.servlets;

import cars.models.Driver;
import cars.store.HbmStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class AuthServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Driver driverInDbase = HbmStore.instOf().findDriverByEmail(email);
        if (driverInDbase != null && driverInDbase.getPassword().equals(password)) {
            HttpSession sc = req.getSession();
            sc.setAttribute("user", driverInDbase);
            resp.sendRedirect(req.getContextPath() + "/adverts");
        } else {
            req.setAttribute("error", "Неверный email или пароль");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}