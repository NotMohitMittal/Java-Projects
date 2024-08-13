package bank;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Customer {

    public void welcomeInterface() {
        System.out.println("\n\t\t\t\t\t\tState Bank of India");
        System.out.println("\t\t\t\t\t\t-------------------");

    }

    public UserAccount interactives(String newUser, Scanner input){

        if(newUser.equalsIgnoreCase("yes")) {
            System.out.print("What's you Name: ");
            String userName = input.nextLine();
            
            System.out.print("Initial Balance: ");
            double inititalBalance = input.nextDouble();
            
            return new UserAccount(userName, inititalBalance);
        }
        return null;
    }

    public int attendingUser(Scanner input) {

        System.out.println("--------------");
        System.out.println("Choose Process: ");
        System.out.println("--------------\n");
        
        System.out.println("1) Check Balance");
        System.out.println("2) Deposit Balance");
        System.out.println("3) Withdraw Balance");
        System.out.println("4) Close Account");
        System.out.println("5) Exit the Menu");
        
        System.out.print("Enter: ");
        int option = input.nextInt();
        System.out.println("---------------------------------");
        
        return option;      // return input.nextInt(); --> since we have input.close()

    }
    

    public void providingFunctions(int userOption, BankAccount account, Scanner input) {

        switch (userOption) {
            case 1:
                System.out.println("Current Balance: " + account.getCurrentBalance());
                break;
            case 2: 
                System.out.print("Enter the Amount: ");
                double depositAmount = input.nextDouble();

                account.deposit(depositAmount);
                System.out.println("Amount addes successfully");
                System.out.println("Current Account Balance: " + account.getCurrentBalance());
                break;
            case 3:
                System.out.print("Enter the Amount: ");
                double withdrawAmount = input.nextDouble();
                
                account.withdraw(withdrawAmount);
                System.out.println("Current Account Balance: " + account.getCurrentBalance());
                break;
            case 4:
                if(account.getCurrentBalance() != 0) {
                    System.out.println("You have Available Balance: " + account.getCurrentBalance());
                    System.out.println("Withdraw the amount first");
                    break;
                }
                // file handling
                break;

            case 5:
                System.out.println("Have a good Day " + account.getAccountHolderName() + " sir");
                break;

            default:
                break;
        }
    }


    public static void main(String[] args) throws IOException {
        //-----------------------------------------------------------------------
        Customer customer = new Customer();
        BankAccount newAccount = new BankAccount();
        Scanner input = new Scanner(System.in);
        //-----------------------------------------------------------------------
        // File Handling
        FileWriter fileWriter = null;
        File file = new File("CustomerDetails.txt");
        
        try {
            boolean fileExist = file.exists();
            fileWriter = new FileWriter(file, true);   // here the true means the file will open in the append mode
            
            if(!fileExist) {    // implement this block only if the file doen't exits in the folder
                fileWriter.write("Custormer's Name\t\t");
                fileWriter.write("Account Number\t\t");
                fileWriter.write("Available Balance\n");
            }

            customer.welcomeInterface();
            
            System.out.print("New Here ? ");
            String newUser = input.nextLine();
            
            UserAccount user = customer.interactives(newUser, input);
            newAccount.createAccount(user.getUserName(), user.getInititalBalance());    // creating account

            // updating the deatils in the txt file
            fileWriter.append(newAccount.getAccountHolderName() + "\t\t\t\t");
            fileWriter.append(newAccount.getAccountNumber() + "\t\t\t\t");
            fileWriter.append(newAccount.getCurrentBalance() + "\n");

            System.out.println(newAccount); // printing account details
            
            do {
                int userChoice = customer.attendingUser(input);
                customer.providingFunctions(userChoice, newAccount, input);
                
                if(userChoice == 5) {
                    break;
                }
                
            } while(true);
        } catch(IOException exception) {
            System.out.println("Exception Caught");
        } finally {
            input.close();
            fileWriter.close();
        }

        newAccount.printBankDetails(file);
    }
}
