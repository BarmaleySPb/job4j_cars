package cars.servlets;

import cars.models.Advert;
import cars.store.HbmStore;
import cars.utils.GetProperties;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class DeleteAdvertServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Advert advertForDelete = new Advert();
        advertForDelete.setId(id);
        HbmStore.instOf().delete(advertForDelete);
        File folder = new File(
                String.valueOf(GetProperties.config("photostore.properties").getProperty("photostore"))
        );
        File file = new File(folder + File.separator + id);
        file.delete();
        resp.sendRedirect(req.getContextPath() + "/adverts");
    }
}