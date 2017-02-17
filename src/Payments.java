import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Scanner;

public class Payments {
    static Scanner sc = new Scanner(System.in);
    static BigDecimal customerChange = new BigDecimal(0);

    public static void total(ArrayList<Product> cartList) {

        BigDecimal subTotal = new BigDecimal(0);
        for (int i = 0; i < cartList.size(); ++i) {

            subTotal = subTotal.add(cartList.get(i).getPrice().multiply(new BigDecimal(cartList.get(i).getQuantity())));

        }

        BigDecimal tax = new BigDecimal(1.06);
        BigDecimal total = subTotal.multiply(tax, new MathContext(4));
        System.out.println("Subtotal: " + subTotal);
        System.out.println("Total: " + total);

    }


    public static void creditCard(ArrayList<Product> cartList) {

        BigDecimal total = new BigDecimal(0);
        for (int i = 0; i < cartList.size(); ++i) {
            total = total.add(cartList.get(i).getPrice());
        }
//        System.out.println(total);
        CreditCardValidator.isValidCardNumber(sc, "Please enter your credit card number");
        CreditCardValidator.isValidCardExpiration(sc,"Please enter the card's Expiration date (MMyy): ");
        CreditCardValidator.isValidCVV(sc, "Please enter the card's CVV: ");

    }

    public static void cash(ArrayList<Product> cartList) {
        BigDecimal subTotal = new BigDecimal(0);
        for (int i = 0; i < cartList.size(); ++i) {
            subTotal = subTotal.add(cartList.get(i).getPrice().multiply(new BigDecimal(cartList.get(i).getQuantity())));

        }
        BigDecimal customerAmount = new BigDecimal(InputValidator.getDouble(sc, "Enter payment amount: "), new MathContext(4));
        BigDecimal tax = new BigDecimal(1.06, new MathContext(4));
        BigDecimal total = subTotal.multiply(tax, new MathContext(4));
        int checker = total.compareTo(customerAmount);

//        System.out.println(checker);
//        System.out.println(customerAmount);
//        System.out.println(tax);
//        System.out.println(total);

        while (checker == 1) {
            System.out.println("That's not enough money!!!");
            customerAmount = new BigDecimal(InputValidator.getDouble(sc, "Enter payment amount: "));
            checker = total.compareTo(customerAmount);
        }
        customerChange = customerAmount.subtract(total, new MathContext(4));
    }

    public static void check(ArrayList<Product> cartList) {
        BigDecimal total = new BigDecimal(0);
        for (int i = 0; i < cartList.size(); ++i) {
            total = total.add(cartList.get(i).getPrice());
        }
        InputValidator.isValidCheckNumber(sc, "Please enter check number");

    }


}
