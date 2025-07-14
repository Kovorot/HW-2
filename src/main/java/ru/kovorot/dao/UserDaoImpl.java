package ru.kovorot.dao;

import ru.kovorot.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.kovorot.util.HibernateUtils;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    @Override
    public Long create(User user) {
        Transaction transaction = null;

        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Long id = (Long) session.save(user);

            transaction.commit();
            return id;

        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            throw new RuntimeException("Ошибка создания объекта", e);
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(User.class, id));
        } catch (Exception e) {
            throw new RuntimeException("Ошибка поиска объекта по id", e);
        }
    }

    @Override
    public List<User> findAll() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createQuery("FROM User", User.class).list();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка поиска объектов", e);
        }
    }

    @Override
    public void update(User user) {
        Transaction transaction = null;

        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.merge(user);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            throw new RuntimeException("Ошибка обновления данных", e);
        }
    }

    @Override
    public void delete(Long id) {
        Transaction transaction = null;

        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            User user = session.get(User.class, id);
            if (user != null)
                session.remove(user);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            throw new RuntimeException("Ошибка удаления объекта", e);
        }
    }
}
