package configuration;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConfig {
    private static final Configuration configuration = new Configuration().configure();
    private static SessionFactory factory;

    public static Session openSession() {
        try {
            return factory.openSession();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void buildSessionFactory() {
        factory = configuration.buildSessionFactory();
    }

    public static void closeSessionFactory() {
        factory.close();
    }
}
