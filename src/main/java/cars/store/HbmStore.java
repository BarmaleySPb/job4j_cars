package cars.store;

import cars.models.Advert;
import cars.models.Car;
import cars.models.Driver;
import cars.models.Engine;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Function;

public class HbmStore implements Store {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();
    private static final Logger LOG = LoggerFactory.getLogger(HbmStore.class.getName());

    private static final class Lazy {
        private static final Store INST = new HbmStore();
    }

    public static Store instOf() {
        return Lazy.INST;
    }

    @Override
    public Driver addDriver(Driver driver) {
        this.tx(session -> session.save(driver));
        return driver;
    }

    @Override
    public Advert addAdvert(Advert advert) {
        this.tx(session -> session.save(advert));
        return advert;
    }

    @Override
    public Engine addEngine(Engine engine) {
        this.tx(session -> session.save(engine));
        return engine;
    }

    @Override
    public Car addCar(Car car) {
        this.tx(session -> session.save(car));
        return car;
    }

    @Override
    public Driver findDriverByEmail(String email) {
        return (Driver) this.tx(session -> session.createQuery("from Driver i where i.email = :key")
                .setParameter("key", email)
                .uniqueResult());
    }

    @Override
    public List<Advert> findAllAdvert() {
        return this.tx(
                session -> session.createQuery("from Advert").list()
        );
    }

    @Override
    public List<Engine> findAllEngine() {
        return this.tx(
                session -> session.createQuery("from Engine").list()
        );
    }

    @Override
    public Driver findDriverByName(String name) {
        return (Driver) this.tx(session -> session.createQuery("from Driver i where i.name = :key")
                .setParameter("key", name)
                .uniqueResult());
    }

    @Override
    public Engine findEngineById(int id) {
        return this.tx(session -> session.get(Engine.class, id));
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            LOG.error("Error: ", e);
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}