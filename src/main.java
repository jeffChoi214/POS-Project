/*
 * POS Project 2/15/2017
 * Jeff, Wei, Robert
*/

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

//TODO bug when adding duplicate items, it replaces original entry with new quantity and adds the quanity to cart as new entry

public class main {
    private static Path filePath = Paths.get("products.txt");
    private static File productsFile = filePath.toFile();
    private static ArrayList<Product> productsList = Driver.readFile();
    private static ArrayList<Product> cartList = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
    private static Customer customer = new Customer(Driver.showMainMenu(sc, true));

    public static void main(String[] args) {
        Driver.runProgram(productsList, cartList, sc, customer);
    }
}