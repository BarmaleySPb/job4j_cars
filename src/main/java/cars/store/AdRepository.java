package cars.store;

import cars.models.Advert;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZoneId;
import java.util.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;


public class AdRepository implements AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();
    private static final Logger LOG = LoggerFactory.getLogger(HbmStore.class.getName());

    public <T> T add(T model) {
        this.tx(session -> session.save(model));
        return model;
    }

    public List<Advert> findTodayAdverts() {
        return this.tx(session -> {
            Date currentDate = Date.from(LocalDate.now().atStartOfDay()
                    .atZone(ZoneId.systemDefault())
                    .toInstant());
            return (List<Advert>) session.createQuery("from Advert a where a.created >= :key")
                    .setParameter("key", currentDate).list();
                }
        );
    }

    public List<Advert> findAdvertWithPhoto() {
        return this.tx(session -> session.createQuery("from Advert adv where adv.photo = :key")
                .setParameter("key", true)
                .list());
    }

    public List<Advert> findAdvertOneCarModel(String carModel) {
        return this.tx(session -> session.createQuery("from Advert adv where adv.car.name = :key ")
                .setParameter("key", carModel)
                .list());
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
