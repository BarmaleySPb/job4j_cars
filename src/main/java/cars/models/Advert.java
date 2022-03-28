package cars.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "adverts")
public class Advert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private Car car;
    private boolean active;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    private String creator;
    private String description;
    private String ownerPhoneNumber;

    public Advert() {

    }

    public static Advert of(Car car, String description) {
        Advert advert = new Advert();
        advert.active = true;
        advert.car = car;
        advert.created = new Date(System.currentTimeMillis());
        advert.description = description;
        return advert;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean changeStatus() {
        return !active;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getOwnerPhoneNumber() {
        return ownerPhoneNumber;
    }

    public void setOwnerPhoneNumber(String ownerPhoneNumber) {
        this.ownerPhoneNumber = ownerPhoneNumber;
    }
}
