package ru.eltech;

import ru.eltech.entity.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class Main {
    private static Session session;


    public static String getDescriptionData(Object selectedValue) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        criteria.from(User.class);
        List<User> list = session.createQuery(criteria).getResultList();
        String path = "Даннфх нет";
        for (User user_object: list) {
            if (user_object.getName().equals(selectedValue))
                path = user_object.getDescription();
        }
        return path;
    }
    public static void main(int change, String name) throws SQLException {
        // сразу получаем готовый SessionFactory и сразу создаем сессию
        session = HibernateUtil.getSessionFactory().openSession();

        // открыть транзакцию
        session.getTransaction().begin();

        change = 2;
        switch (change){
            case 1:
                User user = new User();
                user.setName(name);
                user.setJpg_path("");
                user.setDescription("");
                session.persist(user); // фиксирует изменения в объекте
                break;
            case 2:
                System.out.println(getDescriptionData("ГУМ"));
                break;
            case 3:
                break;
            case 4:

        }

        session.close(); // закрыть сессию
        HibernateUtil.close(); // закрыть SessionFactory
    }
}
