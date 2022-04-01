package cars.store;

import cars.models.Advert;
import cars.models.Car;
import cars.models.Driver;
import org.junit.Test;

import static org.junit.Assert.*;

public class HbmStoreTest {

    @Test
    public void whenAddDriver() {
        Store store = new HbmStore();
        Driver driver = Driver.of("Alexey");
        driver.setEmail("asd@asd.ru");
        store.add(driver);
        assertEquals(store.findDriverByEmail(driver.getEmail()), driver);
    }

    @Test
    public void whenFindById() {
        Store store = new HbmStore();
        Driver firstDriver = Driver.of("Alex");
        Driver secondDriver = Driver.of("Alen");
        store.add(firstDriver);
        store.add(secondDriver);
        Car firstCar = Car.of("Toyota");
        Car secondCar = Car.of("BMW");
        store.add(firstCar);
        store.add(secondCar);
        Advert firstAdvert = Advert.of(firstCar, "Description");
        Advert secondAdvert = Advert.of(secondCar, "Description");
        store.add(firstAdvert);
        store.add(secondAdvert);
        assertEquals(store.findById(Driver.class, 2), secondDriver);
        assertEquals(store.findById(Car.class, 1), firstCar);
        assertEquals(store.findById(Advert.class, 2), secondAdvert);
    }

    @Test
    public void whenAddPhoto() {
        Store store = new HbmStore();
        Advert advert = new Advert();
        advert.setPhoto(true);
        store.add(advert);
        assertTrue(store.findById(Advert.class, 1).isPhoto());
    }

    @Test
    public void whenFindDriverByName() {
        Store store = new HbmStore();
        Driver firstDriver = Driver.of("Petr");
        Driver secondDriver = Driver.of("Alexey");
        Driver thirdDriver = Driver.of("Ivan");
        store.add(firstDriver);
        store.add(secondDriver);
        store.add(thirdDriver);
        assertEquals(store.findDriverByName("Ivan"), thirdDriver);
    }

    @Test
    public void whenDeleteAdvert() {
        Store store = new HbmStore();
        Advert advert = new Advert();
        store.add(advert);
        assertEquals(store.findAll(Advert.class).size(), 1);
        store.delete(advert);
        assertEquals(store.findAll(Advert.class).size(), 0);
    }
}