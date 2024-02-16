package ru.eltech;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import ru.eltech.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private static Session session = HibernateUtil.getSessionFactory().openSession();

    public static String[] getAll(){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        criteria.from(User.class);
        List<User> list = session.createQuery(criteria).getResultList();

        ArrayList <String> str = new ArrayList<>();

        for (User user_object: list)
        {
            str.add(user_object.getName());
        }
        String[] wordsArray = new String[str.size()];

        for (int i = 0; i < str.size(); i++) {
            wordsArray[i] = str.get(i);
        }
        return wordsArray;
    }
    public static String getJpgPath(Object selectedValue) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        criteria.from(User.class);
        List<User> list = session.createQuery(criteria).getResultList();
        String path = "C:\\Users\\petro\\OneDrive\\Pictures\\Walpaper\\10744917.jpg";
        for (User user_object: list) {
            if (user_object.getName().equals(selectedValue))
                path = user_object.getJpg_path();
        }
        return path;
    }

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
    public static String main(int change, String name, String jpg, String description) throws SQLException {
        // сразу получаем готовый SessionFactory и сразу создаем сессию
        session = HibernateUtil.getSessionFactory().openSession();

        // открыть транзакцию
        session.getTransaction().begin();

        String string = "";

        switch (change){
            case 1:
                User user = new User();
                user.setName(name);
                user.setJpg_path(jpg);
                user.setDescription(description);
                session.persist(user); // фиксирует изменения в объекте
                session.getTransaction().commit(); // сохранить изменения (транзакция завершается)
                break;
            case 2:
                string = getDescriptionData("ГУМ");
                break;
            case 3:
                break;
            case 4:

        }

//        session.close(); // закрыть сессию
//        HibernateUtil.close(); // закрыть SessionFactory
        return string;
    }
}
