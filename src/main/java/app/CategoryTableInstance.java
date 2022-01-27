package app;

import exceptions.ExceptionEntityData;
import models.Categories;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;
import java.util.Scanner;


import static exceptions.ExceptionEntityData.entityExceptionNull;

public class CategoryTableInstance {

    Scanner in = new Scanner(System.in, "UTF-8");

    public void fetchDataCategoryTable(Session session) {
        Query query = session.createQuery("FROM Categories");
        List<Categories> categoriesList = query.list();
        System.out.println("List of categories: ");
        for (Categories cat : categoriesList) {
            System.out.println(cat.getName());
        }
    }

    public void addNewCategoryItem(Session session) {
        session.beginTransaction();
        System.out.println("Enter category name:");
        Categories cat = new Categories();
        cat.setName(in.nextLine());
        session.save(cat);
        session.getTransaction().commit();
    }

    public void updateCategoryItem(Session session) {
        session.beginTransaction();
        System.out.println("Enter id Category for update : ");
        int idResult = in.nextInt();
        try {
            Categories categoriesEdit = (Categories) session.find(Categories.class, idResult);
            entityExceptionNull(categoriesEdit);
            System.out.println("Enter new name: ");
            String newName = in.next();
            categoriesEdit.setName(newName);
            session.saveOrUpdate(categoriesEdit);
            session.getTransaction().commit();

        } catch (ExceptionEntityData e) {
            System.out.println("Exception message: "+e.getMessage());
        }
    }

    public void deleteCategoryItem(Session session){
       session.beginTransaction();
        System.out.println("Enter id Category for delete : ");
        int idDel = in.nextInt();
        try {
            Categories categoriesDel = session.find(Categories.class, idDel);
            entityExceptionNull(categoriesDel);
            session.delete(categoriesDel);
            System.out.println("Deleted successfully!");
            session.getTransaction().commit();

        } catch (ExceptionEntityData e) {
            System.out.println("Exception message: "+e.getMessage());
        }
    }
}
