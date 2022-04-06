package cars.store;

import cars.models.*;
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
    public <T> T add(T model) {
        this.tx(session -> session.save(model));
        return model;
    }

    @Override
    public Driver findDriverByEmail(String email) {
        return (Driver) this.tx(session -> session.createQuery("from Driver i where i.email = :key")
                .setParameter("key", email)
                .uniqueResult());
    }

    @Override
    public <T> List<T> findAll(Class<T> model) {
        return tx(session -> session.createQuery("from " + model.getName(), model).list());
    }

    @Override
    public <T> T findById(Class<T> model, Integer id) {
        return tx(session -> session.get(model, id));
    }

    @Override
    public Driver findDriverByName(String name) {
        return (Driver) this.tx(session -> session.createQuery("from Driver i where i.name = :key")
                .setParameter("key", name)
                .uniqueResult());
    }

    @Override
    public void changeStatus(int id) {
        this.tx(session -> {
            Advert advert = session.get(Advert.class, id);
            advert.setActive(advert.changeStatus());
            return null;
        });
    }

    @Override
    public void changePhotoStatus(int id) {
        this.tx(session -> {
            Advert advert = session.get(Advert.class, id);
            advert.setPhoto(advert.changePhotoStatus());
            return null;
        });
    }

    @Override
    public <T> void delete(T model) {
        tx(session -> {
            session.delete(model);
            return true;
        });
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