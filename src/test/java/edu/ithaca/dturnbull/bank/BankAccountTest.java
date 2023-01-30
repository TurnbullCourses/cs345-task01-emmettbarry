package edu.ithaca.dturnbull.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class BankAccountTest {

    @Test
    void getBalanceTest() throws InsufficientFundsException {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance(), 0.001);

        // Yash's tests

        // balance after initialization
        assertEquals(200, bankAccount.getBalance(), 0.001);

        // balance after withdrawal
        bankAccount.withdraw(100);
        assertEquals(100, bankAccount.getBalance(), 0.001);

    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance(), 0.001);
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));

        // Yash's tests
        
        // withdraw amount <= 0
        assertThrows(InvalidWithdrawalAmountException.class, () -> bankAccount.withdraw(0));
        assertThrows(InvalidWithdrawalAmountException.class, () -> bankAccount.withdraw(-10));
        // withdraw amount > bank balance
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(101));
        // withdraw amount < bank balance
        assertDoesNotThrow( () -> bankAccount.withdraw(30));
        assertDoesNotThrow( () -> bankAccount.withdraw(1));
    }


    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid( "a@b.com"));   // valid email address
        assertFalse( BankAccount.isEmailValid(""));         // empty string

        // Yash's tests

        // Valid email prefixes checks
        assertTrue(BankAccount.isEmailValid( "abc-d@mail.com")); 
        assertTrue(BankAccount.isEmailValid( "abc.def@mail.com")); 
        assertTrue(BankAccount.isEmailValid( "abc@mail.com")); 
        assertTrue(BankAccount.isEmailValid( "abc_def@mail.com")); 

        // Invalid email prefixes checks
        assertFalse( BankAccount.isEmailValid("abc-@mail.com"));
        assertFalse( BankAccount.isEmailValid("abc..def@mail.com"));
        assertFalse( BankAccount.isEmailValid(".abc@mail.com"));
        assertFalse( BankAccount.isEmailValid("abc#def@mail.com"));

        // Valid email domains checks
        assertTrue(BankAccount.isEmailValid( "abc.def@mail.cc")); 
        assertTrue(BankAccount.isEmailValid( "abc.def@mail-archive.com")); 
        assertTrue(BankAccount.isEmailValid( "abc.def@mail.org")); 
        assertTrue(BankAccount.isEmailValid( "abc.def@mail.com")); 

        // Invalid email domains checks
        assertFalse( BankAccount.isEmailValid("abc.def@mail.c"));
        assertFalse( BankAccount.isEmailValid("abc.def@mail#archive.com"));
        assertFalse( BankAccount.isEmailValid("abc.def@mail"));
        assertFalse( BankAccount.isEmailValid("abc.def@mail..com"));

        
    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance(), 0.001);
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

    @Test
    void isAmountValidTest(){
        //Testing more than 2 decimal places
        assertFalse(BankAccount.isAmountValid(100.3555));
        //Testing no decimal places
        assertTrue(BankAccount.isAmountValid(100));
        //Testing a negative number
        assertFalse(BankAccount.isAmountValid(-30));
        //Testing 2 decimal places
        assertTrue(BankAccount.isAmountValid(50.50));

    }

}