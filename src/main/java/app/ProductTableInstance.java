package app;

import com.github.javafaker.Faker;
import exceptions.ExceptionEntityData;
import models.Categories;
import models.Product;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import static exceptions.ExceptionEntityData.entityExceptionNull;


public class ProductTableInstance {
    Scanner in = new Scanner(System.in, "UTF-8");

    public List<Integer> list(Session session) {
        Query query = session.createQuery("SELECT id FROM Categories ");
        List<Integer> categories = query.list();
        System.out.println("List of categories: ");
        for (Integer cat : categories) {
            System.out.println(cat);
        }
        return categories;
    }

    public void addNewProductItem(Session session) {
        Faker faker = Faker.instance(new Locale("uk"));
        int index = 0;
        List<Integer> listId = list(session);
        session.beginTransaction();
        Product product = new Product();
        System.out.println("Enter product name:");
        product.setName(in.nextLine());
        System.out.println("Enter product price:");
        product.setPrice(in.nextDouble());
        index = faker.random().nextInt(listId.size());
        Categories cat = new Categories();
        cat.setId(listId.get(index));
        product.setCategories(cat);
        session.save(product);
        session.getTransaction().commit();
    }

    public void fetchDataProductTable(Session session) {
        Query query = session.createQuery("FROM Product ");
        List<Product> productList = query.list();
        System.out.println("List of products: ");
        for (Product product : productList) {
            System.out.println(product.getName());
        }
    }

    public void updateProductItem(Session session) {
        session.beginTransaction();
        System.out.println("Enter id Product for update : ");
        int resultId = in.nextInt();
        try {
            Product productEdit = (Product) session.find(Product.class, resultId);
            entityExceptionNull(productEdit);
            System.out.println("Enter new name product: ");
            String newName = in.next();
            productEdit.setName(newName);
            System.out.println("Do you want to change price?");
            System.out.println("Yes,I do - 1");
            System.out.println("No,I don't - 2");
            int choose=in.nextInt();
            switch (choose) {
                case 1:
                    System.out.println("Enter new price: ");
                    double price = in.nextDouble();
                    productEdit.setPrice(price);
                    break;
                case 2:

                    break;
                default:
                    System.out.println("Incorrect choice");
            }

            session.saveOrUpdate(productEdit);
            session.getTransaction().commit();

        } catch (ExceptionEntityData e) {
            System.out.println("Exception message: " + e.getMessage());
        }
    }

    public void deleteProductItem(Session session){
        session.beginTransaction();
        System.out.println("Enter id Product for delete : ");
        int idDelete = in.nextInt();
        try {
           Product productDel = session.find(Product.class, idDelete);
            entityExceptionNull(productDel);
            session.delete(productDel);
            System.out.println("Deleted successfully!");
            session.getTransaction().commit();

        } catch (ExceptionEntityData e) {
            System.out.println("Exception message: "+e.getMessage());
        }
    }
}
