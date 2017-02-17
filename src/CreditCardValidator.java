import java.util.Scanner;

public class CreditCardValidator {
    static String lastFourDigits = "";
    static String cardType = "";

    //use to take cc# as long and check as valid numbers input
    public static long getLong(Scanner sc, String prompt) {
        long l = 0;
        boolean isValid = false;
        while (isValid == false) {
            System.out.print(prompt);
            if (sc.hasNextLong()) {
                l = sc.nextLong();
                isValid = true;
            } else {
                System.out.println("Error! Invalid long value. Try again.");
            }
            sc.nextLine(); //clear buffer
        }
        return l;
    }

    //checks digits length and prefixes for types of card
    public static String isValidCardNumber(Scanner sc, String prompt) {

        boolean condition = true;
        while (condition) {

            //TODO: clean up credit card

            System.out.println(prompt);
            long cardNumber = getLong(sc, "");
            int length = String.valueOf(cardNumber).length();

            String cardDigits = String.valueOf(cardNumber);
            String digit1 = cardDigits.substring(0, 1);
            String digit2 = cardDigits.substring(0, 2);

            // MasterCard have 16 digits length and prefix 51 to 55
            // Visa have 13 or 16 digits length and prefix 41
            // Discover have 16 digits length prefix of 6011
            // Amex have 15 digits length and prefix of 34 or 37

            if (digit1.equals("4") && (length == 16)) {
                lastFourDigits = "" + cardDigits.charAt(12) + cardDigits.charAt(13) + cardDigits.charAt(14) + cardDigits.charAt(15);
                condition = false;
                cardType = "Visa";
            } else if ((digit2.compareTo("51") >= 0 && digit2.compareTo("55") <= 0) && (length == 16)) {
                lastFourDigits = "" + cardDigits.charAt(12) + cardDigits.charAt(13) + cardDigits.charAt(14) + cardDigits.charAt(15);
                condition = false;
                cardType = "Master Card";
            } else if (digit2.equals("60") && (length == 16)) {
                lastFourDigits = "" + cardDigits.charAt(12) + cardDigits.charAt(13) + cardDigits.charAt(14) + cardDigits.charAt(15);
                condition = false;
                cardType = "Discover";
            } else if ((digit2.equals("34") || digit2.equals("37")) && (length == 15)) {
                lastFourDigits = "" + cardDigits.charAt(11) + cardDigits.charAt(12) + cardDigits.charAt(13) + cardDigits.charAt(14);
                condition = false;
                cardType = "American Express";
            } else
                System.out.println("That is not a valid card number. Try again.");
        }
        return lastFourDigits;  //returns the last 4 digits for confirmation
    }

    //checks the date enter by format of MMyy and checks it against last date of previous month
    public static boolean isValidCardExpiration(Scanner sc, String prompt){

        System.out.println(prompt);
        String s = sc.nextLine();

        java.text.DateFormat sdf = new java.text.SimpleDateFormat("MMyy");


        java.util.Calendar now = java.util.Calendar.getInstance();
        now.set(now.get(java.util.Calendar.YEAR), now.get(java.util.Calendar.MONTH), 0, 23, 59, 59);

        try {
            java.util.Date exp = sdf.parse(s);

            while (exp.before(now.getTime())){
                System.out.println("Your card has expired! Please enter a valid expiration date: ");
                s = sc.nextLine();
                exp = sdf.parse(s);

            }

        } catch (java.text.ParseException e) {
            System.out.println(e);
        }
        return true;
    }

//    //simple validation that checks by length of string. any 4 character will pass the validation.
//    public static void isValidCardDate(Scanner sc, String prompt) {
//        boolean condition = true;
//        while (condition) {
//            System.out.println(prompt);
//            long cardNumber = getLong(sc, "");
//            int length = String.valueOf(cardNumber).length();
//            if (length == 4) {
//                condition = false;
//            } else
//                System.out.println("That is not a valid expiration date. Try again.");
//        }
//    }

////Checking CVV by String length. any characters including letters will pass validation
//    public static void isValidCardCVV(Scanner scan, String prompt) {
//        boolean condition = true;
//        while (condition) {
//            System.out.println(prompt);
//            long cardNumber = getLong(scan, "");
//            int length = String.valueOf(cardNumber).length();
//            if (length == 3) {
//                condition = false;
//            } else
//                System.out.println("That is not a valid CVV number. Try again.");
//        }
//    }

    //Currently unable to differentiate 3 digits vs 4 digits CVV
    public static int isValidCVV(Scanner sc, String prompt) {
        int i = 0;
        int counter = 0;
        String userInput = "";
        boolean invalidCVV = false;

        System.out.println(prompt);
        //i = sc.nextInt();
        userInput = sc.nextLine();

        for (int j = 0; j < userInput.length(); ++j) {
            if (!Character.isDigit(userInput.charAt(j))) {
                invalidCVV = true;
            }
        }

        while (invalidCVV) {
            System.out.println("Please enter a valid CVV");
            userInput = sc.nextLine();
            invalidCVV = false;
            for (int j = 0; j < userInput.length(); ++j) {
                if (!Character.isDigit(userInput.charAt(j))) {
                    invalidCVV = true;
                }
            }
        }
        i = Integer.parseInt(userInput);
        return i;
    }


} //end of class
