package cars.servlets;

import cars.models.Advert;
import cars.models.Car;
import cars.models.Driver;
import cars.models.Engine;
import cars.store.HbmStore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class AddAdvertServlet extends HttpServlet {

    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String carDescription = req.getParameter("carDescription");
        Engine engine = HbmStore.instOf().findEngineById(Integer.parseInt(req.getParameter("carEngine")));
        Car car = Car.of(req.getParameter("carModel"));
        Driver driver = (Driver) req.getSession().getAttribute("user");
        car.addDriver(driver);
        car.setEngine(engine);
        HbmStore.instOf().addCar(car);
        Advert advert = Advert.of(car, carDescription);
        HbmStore.instOf().addAdvert(advert);
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
