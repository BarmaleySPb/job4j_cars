package cars.store;

import cars.models.*;

import java.util.List;

public interface Store extends AutoCloseable {
    <T> T add(T model);
    <T> List<T> findAll(Class<T> model);
    <T> T findById(Class<T> model, Integer id);
    <T> void delete(T model);
    Driver findDriverByEmail(String email);
    Driver findDriverByName(String name);
    void changeStatus(int id);
}
