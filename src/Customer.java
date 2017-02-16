import java.util.ArrayList;

/*
 * Created by Jeff Choi on 2/15/17.
 */
public class Customer {
    private String name;
    private ArrayList<ArrayList<Product>> previousPurchases;

    // constructor

    public Customer(String customerName) {
        previousPurchases = new ArrayList<>();
        name = customerName;


    }

    public void addToPurchase (ArrayList<Product>historyList){

        previousPurchases.add(historyList); //adds cart list to list of purchases

    }

    public int getNumHistory (){
        return previousPurchases.size();
    }

    public String getName() {
        return this.name;
    }


}
