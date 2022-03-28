package cars.servlets;

import cars.models.*;
import cars.store.HbmStore;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddAdvertServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String carDescription = req.getParameter("carDescription");
        Engine engine = HbmStore.instOf().findEngineById(Integer.parseInt(req.getParameter("carEngine")));
        CarBody carBody = HbmStore.instOf().findCarBodyById(Integer.parseInt(req.getParameter("carBody")));
        Car car = Car.of(req.getParameter("carModel"));
        Driver driver = (Driver) req.getSession().getAttribute("user");
        car.addDriver(driver);
        car.setEngine(engine);
        car.setCarBody(carBody);
        HbmStore.instOf().addCar(car);
        Advert advert = Advert.of(car, carDescription);
        advert.setOwnerPhoneNumber(HbmStore.instOf().findDriverByName(driver.getName()).getPhoneNumber());
        HbmStore.instOf().addAdvert(advert);
        resp.sendRedirect(req.getContextPath() + "/adverts");
    }
}
