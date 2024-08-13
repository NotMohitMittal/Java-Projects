package bank;

import java.util.Random;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BankAccount {
    private int accountNumber;
    private double currentBalance;
    private String accountHolderName;

    

    public void createAccount(String accountHolderName, double inititalBalance) {
        Random getNum = new Random();
        /**
        The accountNumber will the random number between the smallest seven digit number and
        the greatest seven digit number
        */
        this.accountNumber = 1000000 + getNum.nextInt(9000000);
        // 1000000  --> smallest seven digit number
        // 9000000  --> greatest seven digit number
        this.accountHolderName = accountHolderName;
        this.currentBalance = inititalBalance;
        System.out.println("Account Created Successfully");
    }

    public void deposit(double amountToDeposite) {
        currentBalance += amountToDeposite;
    }

    public void withdraw(double amountToWithdraw) {
        if(currentBalance > amountToWithdraw) {
            currentBalance -= amountToWithdraw;
        } else {
            System.out.println("Balance not Available");
        }
        
    }

    public void closeAccount() {
        currentBalance = 0;
    }


    // getters for the class fields
    public double getCurrentBalance() {
        return currentBalance;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void printBankDetails(File file) {
        try(FileReader readFile = new FileReader(file)) {

            int readChar = 0;
            do {
                readChar = readFile.read();
                System.out.print((char)readChar);
            } while(readChar != -1);
        } catch(IOException exception) {
            System.out.println("Exception caught " + exception.getMessage());
        } 
    }

    // defining the toString to get the account deatails all at once
    @Override
    public String toString() {
        return new StringBuilder("\nAccount Holder's Name: ").append(this.accountHolderName)
            .append("\nAccount Number: ").append(this.accountNumber)
            .append("\nCurrect Balance: ").append(this.currentBalance)
            .toString();
    }

}
