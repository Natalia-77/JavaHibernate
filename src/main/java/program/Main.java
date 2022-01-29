package program;

import app.CategoryTableInstance;
import app.ProductTableInstance;
import org.hibernate.Session;
import utils.SessionFactoryHibername;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        CategoryTableInstance catInstance = new CategoryTableInstance();
        ProductTableInstance productTableInstance = new ProductTableInstance();
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        Session context = SessionFactoryHibername.getSessionFactory().openSession();
        System.out.println("Connection success.");
        //-----Add new category-----
        catInstance.addNewCategoryItem(context);

        //----Show all instance in database----
        catInstance.fetchDataCategoryTable(context);

        //----Update Category name----
        catInstance.updateCategoryItem(context);

        //----Delete Category----
        catInstance.deleteCategoryItem(context);

        //----Add new Product----
        productTableInstance.addNewProductItem(context);

        //----Show all products----
        productTableInstance.fetchDataProductTable(context);

        //---Update Product name---
        productTableInstance.updateProductItem(context);

        //---Delete Product---
        productTableInstance.deleteProductItem(context);

        context.close();
    }
}
