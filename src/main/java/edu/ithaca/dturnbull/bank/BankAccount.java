package edu.ithaca.dturnbull.bank;


public class BankAccount {

    private String email;
    private double balance;


    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance) {
        if (isEmailValid(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
        if (isAmountValid(startingBalance)) {
            this.balance = startingBalance;
        } else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    public double getBalance() {
        return balance;
    }

    public String getEmail() {
        return email;
    }

    public static boolean isAmountValid(double amount) {
        // method takes in a double and checks amount of decimal points
        if (amount <= 0) {
            return false;
        }
        if ((amount * 100) % 1 != 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller
     *       than balance
     */
    public void withdraw(double amount) throws InsufficientFundsException {
        if (isAmountValid(amount)) {
            if (amount <= balance) {
                balance -= amount;
            } else {
                throw new InsufficientFundsException("Not enough money");
            }
        } else {
            throw new IllegalArgumentException("Invalid Amount");
        }
    }

    public static boolean isEmailValid(String email) {
        int atIndex = email.indexOf("@");
        int dotIndex = email.lastIndexOf(".");

        if (atIndex < 1 || dotIndex < atIndex + 2 || dotIndex + 2 >= email.length()) {
            return false;
        }

        for (int i = 0; i < atIndex; i++) {
            char c = email.charAt(i);
            if (i==0){
                if (c == '.'){return false;}
                else if( c == '_'){return false;}
                else if(c == '-'){return false;}       
            }
            if (c == '.' && email.charAt(i+1) == '.'){
                return false;
            }
            if (!Character.isLetterOrDigit(c) && c != '.' && c != '_' && c != '-') {
                return false;
            }
            if(i == atIndex-1 && email.charAt(i) == '-'){
                return false;
            }
        }

        for (int i = atIndex + 1; i < dotIndex; i++) {
            char c = email.charAt(i);
            if (!Character.isLetterOrDigit(c) && c != '.' && c != '-') {
                return false;
            }
            if (c == '.' && email.charAt(i+1) == '.'){
                return false;
            }
        }

        return true;

    }

    public void deposit(double amount){
        //This function deposits money into the account
        //throws illigal argument exception 
        if(isAmountValid(amount)){
            this.balance += amount;
        }
        else{
            throw new IllegalArgumentException("Invalid amount entered");
        }
    }
    public void transfer(double amount, BankAccount account) throws InsufficientFundsException{
        //This function transfers money into an account
        //throws illigal argument exception
        if(isAmountValid(amount)){
            if(this.balance < amount){
                throw new InsufficientFundsException("Not enough funds");
            }
            else{
                withdraw(amount);
                account.deposit(amount);
            }
        }
        else{
            throw new IllegalArgumentException("Illigal Argument");
        }
    }
}