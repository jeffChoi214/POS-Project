import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Frett on 2/16/2017.
 */
public class Driver {
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
        if (!isCart) {
            System.out.printf("%-5s %-10s %-15s %-15s %-15s", "", "Name", "Description", "Category", "Price");
        } else {
            System.out.printf("%-5s %-10s %-15s %-15s %-15s %-15s %-15s", "", "Name", "Description", "Category", "Price", "Quantity", "Item Total");
        }

        System.out.println("");
        for (int i = 0; i < productsList.size(); ++i) {
            System.out.printf("%-5s %-10s %-15s %-15s %-15s", (i + 1) + ". ",
                    productsList.get(i).getName(), productsList.get(i).getDescription(),
                    productsList.get(i).getCategory(), NumberFormat.getCurrencyInstance().format(productsList.get(i).getPrice()));
            if (isCart == true) {
                System.out.printf(" %-15d $%-15s", productsList.get(i).getQuantity(), productsList.get(i).getPrice().multiply(new BigDecimal(productsList.get(i).getQuantity())));
            }
            System.out.println("");
        }
        if (isCart == false) {
            System.out.printf("%-5s %-10s", (productsList.size() + 1) + ". ", "Back to Main Menu");
        }
        System.out.println("");
        System.out.println("");
    }

    public static String showMainMenu(Scanner sc, boolean isFirst) {
        String output;
        if (isFirst) {
            System.out.println("Welcome to the clothing store");
            System.out.println("Please enter your name: ");
            output = sc.nextLine();
        } else {
            output = "Choose an option: \n" +
                    "1. List Inventory\n" +
                    "2. Show Cart\n" +
                    "3. Remove Item from Cart\n" +
                    "4. Exit\n";
        }


        return output;
    }

    public static int choosePaymentoptions(Scanner sc) {
        return InputValidator.getInt(sc, "1. Cash \n2. Credit card \n3. Check", 1, 3);
    }

    public static void showReceipt(ArrayList<Product> cartList, int paymentOption, Customer customer) {
        System.out.println(customer.getName());
        System.out.println("Total Items : " + cartList.size());
        Driver.listProducts(cartList, true);
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

        System.out.println("You have made a total of " + customer.getNumHistory() + " purchases at our store! Thank you for shopping with us! ");
    }

    public static void runProgram(ArrayList<Product> productsList, ArrayList<Product> cartList, Scanner sc, Customer customer) {
        while (true) {
            System.out.println(Driver.showMainMenu(sc, false));
            int userChoice = InputValidator.getInt(sc, "", 1, 4);
            if (userChoice == 1) {
                // print inventory menu
                Driver.listProducts(productsList, false);

                // ask user choice to add to cart
                int userQuantity = 0;

                System.out.println("Choose an item to add to cart: ");
                int cartChoice = sc.nextInt();
                sc.nextLine();
                while (cartChoice > productsList.size() + 1 || cartChoice < 1) {
                    System.out.println("Please choose a valid number!");
                    cartChoice = sc.nextInt();
                    sc.nextLine();
                }
                //send user back to main menu if choice = last choice in array
                if (cartChoice != productsList.size() + 1) {
                    System.out.println("Please enter quantity: ");
                    userQuantity = sc.nextInt();
                    sc.nextLine();
                }


                // for loop goes through all items of inventory and adds based off index
                for (int i = 1; i <= productsList.size(); ++i) {
                    if (cartChoice == i) {
                        productsList.get(cartChoice - 1).setQuantity(userQuantity);
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

                Driver.listProducts(cartList, true);


                System.out.println("Would you like to checkout? (y/n)");
                char userInput = sc.next().toLowerCase().charAt(0);
                int paymentOption = 0;
                if (userInput == 'y' && (cartList.size() > 0)) {
                    System.out.println("Please select a payment method");
                    paymentOption = Driver.choosePaymentoptions(sc);
                    if (paymentOption == 1) {
                        System.out.println("Cash");
                        Payments.total(cartList);
                        Payments.cash(cartList);

                    } else if (paymentOption == 2) {
                        System.out.println("Credit Card");
                        CreditCardValidator.isValidCardNumber(sc, "Please enter your credit card number");
                        CreditCardValidator.isValidCardExpiration(sc, "Please enter the card's expiration date: ");
                        CreditCardValidator.isValidCVV(sc, "Please enter the card's CVV: ");

                        // System.out.println("test " + CreditCardValidator.lastFourDigits);

                    } else {
                        System.out.println("Check");
                        Payments.check(cartList);
                    }
                    customer.addToPurchase(cartList);
                    Driver.showReceipt(cartList, paymentOption, customer);

                    cartList = new ArrayList<>();
                } else if ((userInput == 'y' && (cartList.size() <= 0))) {
                    System.out.println("Your Shopping Cart is empty. Please add items to Cart!\n");
                }
            } else if (userChoice == 3) {
                if (cartList.size() == 0) {
                    System.out.println("Your Shopping Cart is empty. Please add items to Cart!\n");
                    continue;
                }
                Driver.listProducts(cartList, true);
                System.out.println("Which item would you like to remove from the Cart? ");
                int userRemove = sc.nextInt();
                sc.nextLine();

                while (userRemove > cartList.size() || userRemove < 1) {
                    System.out.println("That item does not exist in your cart!");
                    userRemove = sc.nextInt();
                    sc.nextLine();
                }

                System.out.println("How many of item " + userRemove + " would you like to remove? ");
                int quantityRemove = sc.nextInt();
                sc.nextLine();

                while (quantityRemove > cartList.get(userRemove - 1).getQuantity()) {
                    System.out.println("You do not have that many items in your cart!");
                    quantityRemove = sc.nextInt();
                }
                if (quantityRemove == cartList.get(userRemove - 1).getQuantity()) {
                    cartList.remove(userRemove - 1);
                } else {
                    cartList.get(userRemove - 1).setQuantity(cartList.get(userRemove - 1).getQuantity() - quantityRemove);
                }


                System.out.println("Updated Cart: ");
                Driver.listProducts(cartList, true);

            } else {
                // validator will make sure number is 3
                System.out.println("Thanks for shopping!");
                break;
            }
        }
    }

}
