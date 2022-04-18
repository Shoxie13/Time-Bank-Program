
/**********************************************
Workshop 10 
Course: JAC444 - Semester 4
Last Name: Abdi
First Name: Tareq
ID: 123809196
This assignment represents my own work in accordance with Seneca Academic Policy.
Signature - TA
Date: 15/04/2022
**********************************************/

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankApp {
    List<Integer> unsafeBanks = new ArrayList<Integer>();
    Scanner myObj = new Scanner(System.in);
    int n = 0;
    int limit = 0;
    final int ID = 0;
    final int LOAN = 1;
    double[][][] mBanks;

    // scans the banks if they are unsafe or save depending on what the user set up
    // for the banks
    boolean[] scanBanks(double[][][] m, int limit) {
        boolean[] indexUnsafeBanks = new boolean[m.length];
        double total;
        boolean isSafe = false;

        while (!isSafe) {
            isSafe = true;

            for (int banks = 0; banks < m.length; banks++) {
                total = m[banks][0][0];

                for (int LoanedBanks = 1; LoanedBanks < m[banks].length; LoanedBanks++) {
                    int index = (int) m[banks][LoanedBanks][ID];

                    if (!indexUnsafeBanks[index])
                        total += m[banks][LoanedBanks][LOAN];
                }

                if (total < limit && !indexUnsafeBanks[banks]) {
                    indexUnsafeBanks[banks] = true;
                    isSafe = false;
                }
            }
        }
        return indexUnsafeBanks;
    }

    // checks if input is only double numbers with a dot (1.3)
    double doubleCheck(String input) {
        while (!input.matches("^-?\\d+(?:\\.\\d+)?$")) {
            System.out.print("Invalid input! Enter only numbers: ");
            input = myObj.nextLine();
        }
        return Double.parseDouble(input);
    }

    // checks if input is only integer(whole) numbers
    int integerCheck(String input) {
        while (!input.matches("^-?\\d+(?:\\.\\d+)?$")) {
            System.out.print("Invalid input! Enter only numbers: ");
            input = myObj.nextLine();
        }
        return Integer.parseInt(input);
    }

    // function that is handling all user input for the N number of banks
    void userInput() {
        System.out.print("Enter the number of banks: ");
        n = integerCheck(myObj.nextLine()); // indicates the number of banks
        System.out.print("Enter minimum limit: ");
        limit = integerCheck(myObj.nextLine()); // indicates the limit of the banks

        mBanks = new double[n][][];

        for (int i = 0; i < mBanks.length; i++) {
            System.out.println("For bank #" + i); // bank's counter

            System.out.print("  Balance: ");
            double balance = doubleCheck(myObj.nextLine()); // bank's balance
            System.out.print("  Number of banks Loaned: ");
            int numBanks = integerCheck(myObj.nextLine()); // num of bank's loaned
            mBanks[i] = new double[++numBanks][2];
            mBanks[i][0][0] = balance;

            for (int bank = 1; bank < mBanks[i].length; bank++) {
                System.out.print("      Bank ID who gets the loan: "); // num of bank's id that loan is made to
                mBanks[i][bank][ID] = doubleCheck(myObj.nextLine());
                System.out.print("      Loaned Amount: "); // amount of the loan that is made to that bank
                mBanks[i][bank][LOAN] = doubleCheck(myObj.nextLine());
            }
        }
    }

    // prints info at the end of program
    void printInfo() {
        userInput();

        System.out.println("");
        boolean[] unsafeIndex = scanBanks(mBanks, limit);

        for (int j = 0; j < unsafeIndex.length; j++) {
            if (unsafeIndex[j] == true) {
                unsafeBanks.add(j);
            }
        }

        System.out.println("Unsafe banks are Bank " + unsafeBanks.get(1) + " and Bank " + unsafeBanks.get(0));
        System.out.println(
                "Bank " + unsafeBanks.get(1) + " got bankrupted and it got the loan from Bank " + unsafeBanks.get(0)
                        + ", because Bank " + unsafeBanks.get(1) + " is unsafe due to under the limit Bank "
                        + unsafeBanks.get(0) + " is also unsafe due to lower limit.");
    }

    public static void main(String[] args) {
        BankApp bk = new BankApp();
        bk.printInfo();
    }
}
