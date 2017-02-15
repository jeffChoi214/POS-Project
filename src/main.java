/*
 * POS Project 2/15/2017
 * Jeff, Wei, Robert
*/

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class main {
    private static Path filePath = Paths.get("products.txt");
    private static File productsFile = filePath.toFile();

    public static ArrayList<Product> readFile() {
        ArrayList<Product> productsList = new ArrayList<>();
        try {
            FileReader r = new FileReader(productsFile);
            BufferedReader in = new BufferedReader(r);
            String line = in.readLine();
            while (line != null) {
                String[] splitList = line.split(" ");
                //System.out.println(splitList.length);
                Product theProduct = new Product(splitList[0], splitList[1], splitList[2], splitList[3]);
                productsList.add(theProduct);
                line = in.readLine();
            }
            in.close();
            r.close();
        }
        catch (FileNotFoundException e) {
            System.out.println(e);
        }
        catch (IOException e) {
            System.out.println(e);
        }

        return productsList;
    }

    public static void listProducts(ArrayList<Product> productsList) {
        for (int i = 0; i < productsList.size(); ++i) {
            // System.out.println(productsList.get(i).getName() + " " + productsList.get(i).getCategory());
        }
    }

    public static void showMainMenu() {
        System.out.println("Welcome to the clothing store");
        System.out.println("Choose an option: ");
        System.out.println("1. List Inventory");
        System.out.println("2. Show Cart");
        System.out.println("3. Exit\n");
    }

    public static void main (String[] args) {
        // creating arraylist from text file
        ArrayList<Product> productsList = readFile();
        showMainMenu();







    }


}