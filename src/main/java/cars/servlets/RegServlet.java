package cars.servlets;

import cars.models.Driver;
import cars.store.HbmStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class RegServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String phoneNumber = req.getParameter("phoneNumber");
        if (HbmStore.instOf().findDriverByEmail(email) == null) {
            Driver driver = Driver.of(name);
            driver.setEmail(email);
            driver.setPassword(password);
            driver.setPhoneNumber(phoneNumber);
            HbmStore.instOf().addDriver(driver);
            HttpSession sc = req.getSession();
            sc.setAttribute("user", driver);
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            req.setAttribute("error", "User with this email already exists");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
