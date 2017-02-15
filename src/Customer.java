import java.util.ArrayList;

/*
 * Created by Jeff Choi on 2/15/17.
 */
public class Customer {
    private String name;
    private ArrayList<Product> cartList;
    private ArrayList<ArrayList<Product>> previousPurchases;

    // constructor
    public Customer() {

    }


    public String getName() {
        return this.name;
    }

    public ArrayList<Product> getCartList() {
        return cartList;
    }
}
