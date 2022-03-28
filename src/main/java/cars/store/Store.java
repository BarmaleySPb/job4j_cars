package cars.store;

import cars.models.*;

import java.util.List;

public interface Store extends AutoCloseable {
    Driver addDriver(Driver driver);
    Advert addAdvert(Advert advert);
    Engine addEngine(Engine engine);
    Car addCar(Car car);
    Driver findDriverByEmail(String email);
    Engine findEngineById(int id);
    CarBody findCarBodyById(int id);
    List<Advert> findAllAdvert();
    List<Engine> findAllEngine();
    List<CarBody> findAllCarBody();
    Driver findDriverByName(String name);
    void changeStatus(int id);
}
