package program;

import app.CategoryTableInstance;
import models.Categories;
import org.hibernate.Session;
import utils.SessionFactoryHibername;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        CategoryTableInstance catInstance = new CategoryTableInstance();
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        Session context = SessionFactoryHibername.getSessionFactory().openSession();
        System.out.println("Connection success.");
        //-----Add new category-----
        //catInstance.addNewCategoryItem(context);

        //----Show all instance in database----
        //catInstance.fetchDataCategoryTable(context);

        //----Update Category name------
        //catInstance.updateCategoryItem(context);

        //----Delete Category----


        context.close();
    }
}
