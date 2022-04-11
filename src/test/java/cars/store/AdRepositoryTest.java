package cars.store;

import cars.models.Advert;
import cars.models.Car;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;

public class AdRepositoryTest {

    @Test
    public void whenFindAdvertOneCarModel() {
        AdRepository repository = new AdRepository();
        Advert firstAdvert = Advert.of(Car.of("BMW"), "description");
        Advert secondAdvert = Advert.of(Car.of("Toyota"), "description");
        Advert thirdAdvert = Advert.of(Car.of("LADA"), "description");
        Advert fourthAdvert = Advert.of(Car.of("Toyota"), "description");
        repository.add(firstAdvert);
        repository.add(secondAdvert);
        repository.add(thirdAdvert);
        repository.add(fourthAdvert);
        assertEquals(repository.findAdvertOneCarModel("Toyota"), List.of(secondAdvert, fourthAdvert));
    }

    @Test
    public void whenFindAdvertWithPhoto() {
        AdRepository repository = new AdRepository();
        Advert firstAdvert = Advert.of(Car.of("BMW"), "description");
        Advert secondAdvert = Advert.of(Car.of("Toyota"), "description");
        Advert thirdAdvert = Advert.of(Car.of("LADA"), "description");
        thirdAdvert.setPhoto(true);
        repository.add(firstAdvert);
        repository.add(secondAdvert);
        repository.add(thirdAdvert);
        assertEquals(repository.findAdvertWithPhoto(), List.of(thirdAdvert));
    }

    @Test
    public void whenFindTodayAdvert() {
        AdRepository repository = new AdRepository();
        Advert firstAdvert = Advert.of(Car.of("BMW"), "description");
        Advert secondAdvert = Advert.of(Car.of("Toyota"), "description");
        Advert thirdAdvert = Advert.of(Car.of("LADA"), "description");
        Advert fourthAdvert = Advert.of(Car.of("LADA"), "description");
        Date date = new GregorianCalendar(2022, Calendar.JANUARY,12).getTime();
        Date date2 = new GregorianCalendar(2022, Calendar.JANUARY,1).getTime();
        firstAdvert.setCreated(date);
        fourthAdvert.setCreated(date2);
        repository.add(firstAdvert);
        repository.add(secondAdvert);
        repository.add(thirdAdvert);
        repository.add(fourthAdvert);
        assertEquals(repository.findTodayAdverts(), List.of(secondAdvert, thirdAdvert));
    }
}