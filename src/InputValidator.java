import java.util.Scanner;

public class InputValidator {

    public static String getString(Scanner sc, String prompt)
    {
        System.out.print(prompt);
        String s = sc.nextLine();

        return s;
    }


    public static int getInt(Scanner sc, String prompt)
    {
        int i = 0;
        boolean isValid = false;
        while (isValid == false)
        {
            System.out.print(prompt);
            if (sc.hasNextInt())
            {
                i = sc.nextInt();
                isValid = true;
            }
            else
            {
                System.out.println("Error! Invalid integer value. Try again.");
            }
            sc.nextLine();  //clear buffer
        }
        return i;
    }


    public static int getInt(Scanner sc, String prompt, int min, int max)
    {
        int i = 0;
        boolean isValid = false;
        while (isValid == false)
        {
            i = getInt(sc, prompt);
            if (i < min)
                System.out.println(
                        "Error! Number must be " + min + " or greater.");
            else if (i > max)
                System.out.println(
                        "Error! Number must be " + max + " or less.");
            else
                isValid = true;
        }
        return i;
    }

    public static String isValidRoutingNumber (Scanner sc, String prompt) {
        System.out.println(prompt);
        String routingNumber = sc.nextLine();
        if(routingNumber.length()<9||routingNumber.length()>9)
            System.out.println("Invalid routing number.");
        return routingNumber;
    }

    public static String isValidCheckNumber(Scanner scan, String prompt) {
        System.out.println(prompt);
        String checkNumber = scan.nextLine();
        while (checkNumber.length() < 4 || checkNumber.length() > 4){
            System.out.println("Invalid check number.");
        checkNumber = scan.nextLine();
    }
        return checkNumber;
    }

    public static String isValidAccountNumber(Scanner scan, String prompt) {
        System.out.println(prompt);
        String accountNumber = scan.nextLine();
        if (accountNumber.length() < 14 || accountNumber.length() > 14)
            System.out.println("Invalid account number.");
        return accountNumber;
    }









}
