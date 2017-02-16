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
//stupid change
/*
    TODO: remove from inventory
          add to inventory (admin menu)
          ______
          remove from cart (customer menu)
          ___________

          customer: arraylist customers, deal w previous purchases,
          _________
          validations (its own class) -- int validation w range
          ________
          add discount codes (counters w howevermany shirts, if theres 3 shirts, random deals)
          _______________
          clean up code w methods
 */

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
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }

        return productsList;
    }

    public static void listProducts(ArrayList<Product> productsList, boolean isCart) {
        System.out.printf("%-5s %-10s %-15s %-15s %-15s", "", "Name", "Description", "Category", "Price");
        System.out.println("");
        for (int i = 0; i < productsList.size(); ++i) {
            System.out.printf("%-5s %-10s %-15s %-15s %-15s", (i + 1) + ". ",
                    productsList.get(i).getName(), productsList.get(i).getDescription(),
                    productsList.get(i).getCategory(), NumberFormat.getCurrencyInstance().format(productsList.get(i).getPrice()));
            System.out.println("");
        }
        if (isCart == false) {
            System.out.printf("%-5s %-10s", (productsList.size() + 1) + ". ", "Back to Main Menu");
        }
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

    public static int choosePaymentoptions(Scanner sc) {
        return InputValidator.getInt(sc, "1. Cash \n 2. Credit card \n 3. Check", 1, 3);
    }

    public static void showReceipt(ArrayList<Product> cartList, int paymentOption) {
        System.out.println("Total Items : " + cartList.size());
        listProducts(cartList, true);
        System.out.println("========================================");
        Payments.total(cartList);
        if (paymentOption == 1) {
            System.out.println("Paid in CASH.");
            System.out.println("Your change is " + Payments.customerChange);
        } else if (paymentOption == 2) {
            System.out.println("Paid with CREDIT CARD");
        } else {
            System.out.println("Paid with CHECK");
        }


    }

    public static void main(String[] args) {
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

            //userchoice == 1 equals menu for owner
            //userchoice == 2 equals menu for customer
            //split up below if else statement
            if (userChoice == 1) {
                // TODO: Different menus, one for owner one for customer
                // TODO: in owner menu, add or remove inventory

                // print inventory menu
                listProducts(productsList, false);

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
                if (cartChoice == productsList.size() + 1) {
                    // should go back to main menu
                    System.out.println("Going back to main menu");
                }
                System.out.println("");
            } else if (userChoice == 2) {

                // TODO: deal with removing from cart and checking out
                // show cart list, fix formatting
                // Customer customer1
                // listProducts(customer1.getCartList());
                listProducts(cartList, true);


                System.out.println("Would you like to checkout? (y/n)");
                char userInput = sc.next().toLowerCase().charAt(0);
                int paymentOption = 0;
                if (userInput == 'y') {
                    System.out.println("Please slecet a pyment method");
                    paymentOption = choosePaymentoptions(sc);
                    if (paymentOption == 1) {
                        System.out.println("Cash");
                        Payments.total(cartList);
                        Payments.cash(cartList);

                    } else if (paymentOption == 2) {
                        System.out.println("Credit Card");
                        CreditCardValidator.isValidCardNumber(sc, "Please enter your credit card number");

                        // System.out.println("test " + CreditCardValidator.lastFourDigits);

                    } else {
                        System.out.println("Check");
                        Payments.check(cartList);
                    }
                    showReceipt(cartList, paymentOption);
                    cartList = new ArrayList<>();
                }
            } else {
                // validator will make sure number is 3
                System.out.println("Thanks for shopping!");
                System.out.println(cartList.size());
                break;
            }
        }
    }
}