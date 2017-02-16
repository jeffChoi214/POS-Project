import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * TODO: clean up, create total method, move everything into main
 */
public class Payments {
    static Scanner sc = new Scanner(System.in);
    static BigDecimal customerChange = new BigDecimal(0);

    public static void total(ArrayList<Product> cartList) {

        BigDecimal subTotal = new BigDecimal(0);
        for (int i = 0; i < cartList.size(); ++i) {
            subTotal = subTotal.add(cartList.get(i).getPrice());
        }


        BigDecimal tax = new BigDecimal(1.06);
        BigDecimal total = subTotal.multiply(tax, new MathContext(4));
        System.out.println("Subtotal : " + subTotal);
        System.out.println(" Total: " + total);


    }


    public static void creditCard(ArrayList<Product> cartList) {

        BigDecimal total = new BigDecimal(0);
        for (int i = 0; i < cartList.size(); ++i) {
            total = total.add(cartList.get(i).getPrice());
        }
        System.out.println(total);
        CreditCardValidator.isValidCardNumber(sc, "Please enter your credit card number");


    }

    public static void cash(ArrayList<Product> cartList) {
        BigDecimal subTotal = new BigDecimal(0);
        for (int i = 0; i < cartList.size(); ++i) {
            subTotal = subTotal.add(cartList.get(i).getPrice());
        }
        //int customerAmount = InputValidator.getInt(sc, "Enter payment amount: " );
        BigDecimal customerAmount = new BigDecimal(InputValidator.getInt(sc, "Enter payment amount: "));
        BigDecimal tax = new BigDecimal(1.06);
        BigDecimal total = subTotal.multiply(tax, new MathContext(4));
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
