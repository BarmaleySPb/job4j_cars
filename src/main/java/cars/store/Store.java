package cars.store;

import cars.models.Advert;
import cars.models.Car;
import cars.models.Driver;
import cars.models.Engine;

import java.util.List;

public interface Store extends AutoCloseable {
    Driver addDriver(Driver driver);
    Advert addAdvert(Advert advert);
    Engine addEngine(Engine engine);
    Car addCar(Car car);
    Driver findDriverByEmail(String email);
    Engine findEngineById(int id);
    List<Advert> findAllAdvert();
    List<Engine> findAllEngine();
    Driver findDriverByName(String name);
}
