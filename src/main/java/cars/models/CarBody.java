package cars.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "bodies")
public class CarBody {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;

    public CarBody() {

    }

    public static CarBody of(String name) {
        CarBody carBody = new CarBody();
        carBody.name = name;
        return carBody;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CarBody carBody = (CarBody) o;
        return id == carBody.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}