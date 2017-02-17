import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Scanner;

public class Payments {
    static Scanner sc = new Scanner(System.in);
    static BigDecimal customerChange = new BigDecimal(0);

    public static void cash(ArrayList<Product> cartList, boolean getPayment) {
        BigDecimal subTotal = new BigDecimal(0);
        for (int i = 0; i < cartList.size(); ++i) {
            subTotal = subTotal.add(cartList.get(i).getPrice().multiply(new BigDecimal(cartList.get(i).getQuantity())));
            subTotal = subTotal.setScale(2, BigDecimal.ROUND_HALF_UP);

        }
        BigDecimal tax = new BigDecimal(1.06);
        tax = tax.setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal total = subTotal.multiply(tax);
        total = total.setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println("Subtotal: $" + String.format("%,.2f", subTotal));
        System.out.println("Total: $" + String.format("%,.2f", total));
        if (getPayment) {
            BigDecimal customerAmount = new BigDecimal(InputValidator.getDouble(sc, "Enter payment amount: "));
            customerAmount = customerAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
            int checker = total.compareTo(customerAmount);

            while (checker == 1) {
                System.out.println("That's not enough money!!!");
                customerAmount = new BigDecimal(InputValidator.getDouble(sc, "Enter payment amount: "));
                customerAmount = customerAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
                checker = total.compareTo(customerAmount);
            }
            customerChange = customerAmount.subtract(total).setScale(2, BigDecimal.ROUND_HALF_UP);
        }

    }


    public static void creditCard(ArrayList<Product> cartList) {

        BigDecimal total = new BigDecimal(0);
        for (int i = 0; i < cartList.size(); ++i) {
            total = total.add(cartList.get(i).getPrice());
            total = total.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
//        System.out.println(total);
        CreditCardValidator.isValidCardNumber(sc, "Please enter your credit card number");
        CreditCardValidator.isValidCardExpiration(sc,"Please enter the card's Expiration date (MMyy): ");
        CreditCardValidator.isValidCVV(sc, "Please enter the card's CVV: ");

    }

    public static void check(ArrayList<Product> cartList) {
        BigDecimal total = new BigDecimal(0);
        for (int i = 0; i < cartList.size(); ++i) {
            total = total.add(cartList.get(i).getPrice());
        }
        InputValidator.isValidCheckNumber(sc, "Please enter check number");

    }


}
