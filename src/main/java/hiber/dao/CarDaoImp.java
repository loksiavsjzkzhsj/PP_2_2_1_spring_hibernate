package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CarDaoImp implements CarDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    public List<Car> listCars() {
        TypedQuery<Car> query=sessionFactory.getCurrentSession().createQuery("from Car");
        return query.getResultList();
    }

    @Override
    public User getUserByCar(Car car) {
        TypedQuery<User> query = sessionFactory
                .getCurrentSession()
                .createQuery("from User user where user.car.model = :model and user.car.series = :series");
        query.setParameter("model", car.getModel())
                .setParameter("series", car.getSeries());
        return query.getSingleResult();
    }
}
