package app;

import models.Categories;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

public class CategoryTableInstance {
    public void fetchDataCategotyTable(Session session) {
        Query query = session.createQuery("FROM Categories");
        List<Categories> categoriesList = query.list();
        System.out.println("List of categories: ");
        for (Categories cat : categoriesList) {
            System.out.println(cat.getName());
        }
    }
}
