package utils;

import models.Categories;
import models.Orders;
import models.Product;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class SessionFactoryHibername {
    private static SessionFactory sessionFactory;

    private SessionFactoryHibername() {
    }

    ;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration()
                        .configure("hibernate.cfg.xml");
                configuration.addAnnotatedClass(Product.class);
                configuration.addAnnotatedClass(Categories.class);
                configuration.addAnnotatedClass(Orders.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Exception!" + e.getMessage());
            }
        }
        return sessionFactory;
    }
}
