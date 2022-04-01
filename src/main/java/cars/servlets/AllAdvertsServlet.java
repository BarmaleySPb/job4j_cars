package cars.servlets;

import cars.models.Advert;
import cars.store.HbmStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AllAdvertsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("adverts", HbmStore.instOf().findAll(Advert.class));
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}