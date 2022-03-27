package cars.store;

import cars.models.Driver;
import org.junit.Test;

import static org.junit.Assert.*;

public class HbmStoreTest {

    @Test
    public void whenAddDriver() {
        Store store = new HbmStore();
        Driver driver = Driver.of("Alexey");
        driver.setEmail("asd@asd.ru");
        store.addDriver(driver);
        assertEquals(store.findDriverByEmail(driver.getEmail()), driver);
    }

}