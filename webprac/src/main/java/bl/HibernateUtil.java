package bl;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
           //return new Configuration().configure().buildSessionFactory();

        	Configuration configuration = new Configuration();
        	configuration.configure("hibernate.cfg.xml");
            configuration.addAnnotatedClass(entity.Education.class);
            return configuration.buildSessionFactory();


        }
        catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }

}