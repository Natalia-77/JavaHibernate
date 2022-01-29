package app;

import com.github.javafaker.Faker;
import models.Orders;
import models.Product;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class OrderTableInstance {
    Scanner in = new Scanner(System.in, "UTF-8");

    //список продуктів для фейкера,щоб вибирати продукт з навних і додавати в замовлення.
    public List<Product> list(Session session) {
        Query query = session.createQuery(" FROM Product ");
        List<Product> prodList = query.list();
        System.out.println("List of products: ");
        for (Product item : prodList) {
            System.out.println(item.getName());
        }
        return prodList;
    }

    public void addNewOrder(Session session) {

        session.beginTransaction();

        Orders order = new Orders();
        System.out.println("Enter customer name:");
        order.setName(in.nextLine());
        System.out.println("Enter customer number:");
        order.setNumber(in.nextInt());

        Faker faker = Faker.instance(new Locale("uk"));
        int index = 0;
        List<Product> listId = list(session);

        //зробила на 2 позиції товару в одне замовлення.
        for(int i=0;i<2;i++){
            index = faker.random().nextInt(listId.size());
            order.add(listId.get(index));
        }

        session.save(order);
        session.getTransaction().commit();
    }


}
