package edu.ithaca.dturnbull.bank;


public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email)){
            this.email = email;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
        if(isAmountValid(startingBalance)){
            this.balance = startingBalance;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    public static boolean isAmountValid(double amount) {
         // method takes in a double and checks amount of decimal points
         if (amount < 0) {
            return false;
        }
        if ((amount * 100) % 1 != 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     */
    public void withdraw (double amount) throws InsufficientFundsException{
        if(isAmountValid(amount)){
            if (amount <= balance) {
                balance -= amount;
            } else {
                throw new InsufficientFundsException("Not enough money");
            }
        }
        else {
            throw new IllegalArgumentException("Invalid Amount");
        }
    }


    public static boolean isEmailValid(String email){
        int atsign = email.indexOf('@');
        if (atsign == -1){
            return false;
        }
     
        for(int i=0; i<atsign; i++ ){
            if(email.charAt(i) == '#'){
                return false;
            }
            if (email.charAt(i) == '-' || email.charAt(i) == '_' || email.charAt(i) == '.'){
                
                if (i == atsign-1){
                    return false;
                }
                if (i == 0){
                    return false;
                }
                if (email.charAt(i+1) == '-' || email.charAt(i+1) == '_' || email.charAt(i+1) == '.'){
                    return false;
                }
                if (email.charAt(i-1) == '-' || email.charAt(i-1) == '_' || email.charAt(i-1) == '.'){
                    return false;
                }
                
            }
        }
        for (int i=atsign; i<email.length(); i++){
            if(email.charAt(i) == '#'){
                return false;
            }
            if (email.charAt(i) == '.'){
                
            }
        }
    
        return true;
       
    }
}