/*
 * POS Project 2/15/2017
 * Jeff, Wei, Robert
*/

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

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
        System.out.printf("%-10s %-15s %-15s %-15s", "Name", "Description", "Category", "Price");
        System.out.println("");
        for (int i = 0; i < productsList.size(); ++i) {
            System.out.printf("%-5s %-10s %-15s %-15s %-15s", (i + 1) + ". ",
                    productsList.get(i).getName(), productsList.get(i).getDescription(),
                    productsList.get(i).getCategory(), NumberFormat.getCurrencyInstance().format(productsList.get(i).getPrice()));
            System.out.println("");
        }
        System.out.printf("%-5s %-10s",(productsList.size() + 1) + ". ", "Back to Main Menu");
        System.out.println("");
        System.out.println("");
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
        ArrayList<Product> cartList = new ArrayList<>();
        final String admin = "Kamel";
        Scanner sc = new Scanner(System.in);
        int userChoice;

        // TODO: get user name at beginning of loop

        while (true) {
            showMainMenu();
            userChoice = sc.nextInt();
            sc.nextLine();
            if (userChoice == 1) {
                // TODO: Different menus, one for owner one for customer
                // TODO: in owner menu, add or remove inventory

                // print inventory menu
                listProducts(productsList);

                // ask user choice to add to cart
                System.out.println("Choose an item to add to cart: ");
                int cartChoice = sc.nextInt();
                sc.nextLine();

                // for loop goes through all items of inventory and adds based off index
                for (int i = 1; i <= productsList.size(); ++i) {
                    if (cartChoice == i) {
                        cartList.add(productsList.get(cartChoice - 1));
                        break;
                    }
                }
                // exits if user chooses last index(size of inventory plus 1)
                if (cartChoice == productsList.size()+1) {
                    // should go back to main menu
                    System.out.println("going back to main menu");
                }
                System.out.println("");
            } else if (userChoice == 2) {
                // TODO: deal with removing from cart and checking out
                // show cart list, fix formatting
                listProducts(cartList);
            } else {
                // validator will make sure number is 3
                System.out.println("Thanks for shopping!");
                System.out.println(cartList.size());
                break;
            }
        }
    }
}