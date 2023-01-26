package edu.ithaca.dturnbull.bank;

public class InvalidWithdrawalAmountException extends Exception{
    private static final long serialVersionUID = 1L;

    public InvalidWithdrawalAmountException(String s) {
        super(s);
    }

}