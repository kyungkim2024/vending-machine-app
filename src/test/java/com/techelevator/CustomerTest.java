package com.techelevator;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class CustomerTest {

    private BigDecimal currentBalance = new BigDecimal("0.00");
    private BigDecimal feedMoneyValue = new BigDecimal("5.00");
    private BigDecimal itemPrice = new BigDecimal("4.15");
    private Items itemsList = new Items();
    private ShoppingCart shoppingCart = new ShoppingCart(itemsList.getItemDetails());

    @Before
    public void makeCurrentBalanceToZero() {
        Customer.zeroBalance();
    }

    @Test
    public void checkDeposit() {
        System.out.println("Money is added to the current balance");
        Customer.deposit(feedMoneyValue.intValue()); // 0.00 + 5.00 = 5.00
        System.out.println("Expected: " + currentBalance.add(feedMoneyValue));
        System.out.println("Actual: " + Customer.getCurrentBalance());
        Assert.assertEquals(currentBalance.add(feedMoneyValue), Customer.getCurrentBalance());
        System.out.println();
    }

    @Test
    public void checkPurchase() {
        System.out.println("Item price is subracted from current balance");
        Customer.purchase(itemPrice); // 0 - 4.15 = -4.15
        System.out.println("Expected: " + currentBalance.subtract(itemPrice));
        System.out.println("Actual: " + Customer.getCurrentBalance());
        Assert.assertEquals(currentBalance.subtract(itemPrice), Customer.getCurrentBalance());
        System.out.println();
    }

    @Test
    public void checkReturnChange() {
        System.out.println("Print return of change for each type of coin");
        Customer.deposit(feedMoneyValue.intValue());
        Customer.purchase(itemPrice); // current balance = 5.00 - 4.15 = 0.85 -> 3 quarters and 1 dime
        System.out.println("Expected: Returned customer 3 quarter(s), 1 dime(s), 0 nickel(s) and 0 penny(ies)");
        System.out.print("Actual: ");
        Customer.returnChange();
        System.out.println();
    }

    @Test
    public void zerorizeBalance() {
        System.out.println("Make balance to zero");
        Customer.deposit(feedMoneyValue.intValue()); // 5.00
        Customer.zeroBalance(); // 5.00 - 5.00
        System.out.println("Expected: 0.00");
        System.out.println("Actual: " + Customer.getCurrentBalance());
        Assert.assertEquals(new BigDecimal("0.00"), Customer.getCurrentBalance());
        System.out.println();
    }

    @Test
    public void addTotalCost() {
        System.out.println("Add purchased item price to the total cost");
        shoppingCart.addToTotalCost(itemPrice);
        shoppingCart.addToTotalCost(itemPrice);
        shoppingCart.addToTotalCost(itemPrice);
        System.out.println("Expected: 12.45"); // 4.15 * 3 = 12.45
        System.out.print("Actual: ");
        System.out.println(shoppingCart.getTotalCost());
        Assert.assertEquals(new BigDecimal("12.45"), shoppingCart.getTotalCost());
        System.out.println();
    }
}

