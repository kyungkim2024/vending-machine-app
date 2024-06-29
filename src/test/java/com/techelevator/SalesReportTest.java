package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class SalesReportTest {
    private Items items = new Items();
    private ShoppingCart shoppingCart = new ShoppingCart(items.getItemDetails());;
    private String expectedSalesReportOutput;
    private Map<String, Integer> salesReportList = shoppingCart.getSalesReportList();

    @Before
    public void initializeItemsAndShoppingCart() {
        expectedSalesReportOutput = "**SALES REPORT**\n" +
                "\n" +
                "Stackers | 1\n" +
                "Cowtales | 0\n" +
                "Cola | 0\n" +
                "Heavy | 0\n" +
                "Cloud Popcorn | 0\n" +
                "Dr. Salt | 0\n" +
                "Little League Chew | 0\n" +
                "Wonka Bar | 0\n" +
                "Crunchie | 0\n" +
                "Moonpie | 0\n" +
                "Potato Crisps | 1\n" +
                "Triplemint | 0\n" +
                "Mountain Melter | 0\n" +
                "U-Chews | 0\n" +
                "Grain Waves | 0\n" +
                "Chiclets | 0\n" +
                "\n" +
                "TOTAL SALES $4.50";
        Items stackers = items.getItemBySlotLocation("a1");
        Items potatoCrips = items.getItemBySlotLocation("a2");
        salesReportList.put(stackers.getItemName(), 1);
        salesReportList.put(potatoCrips.getItemName(), 1);
        shoppingCart.addToTotalCost(stackers.getItemPrice());
        shoppingCart.addToTotalCost(potatoCrips.getItemPrice());

    }

    @Test
    public void salesReportTesting() {
        String actualReport = shoppingCart.printSalesReport();
        Assert.assertEquals(expectedSalesReportOutput, actualReport);
    }
}
