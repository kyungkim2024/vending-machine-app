package com.techelevator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {
    // Purchased items are
    // String: purchased item name, Integer: how many each item was purchased
    private static Map<String, Integer> salesReportList = new HashMap<>();
    private static BigDecimal totalCost = new BigDecimal("0.00");
    private final int INITIAL_QUANTITY_OF_PURCHASED_ITEMS = 0;

    // Return the current items list
    public Map<String, Integer> getSalesReportList() {
        return salesReportList;
    }
    // Return the current total cost
    public BigDecimal getTotalCost() {
        return totalCost;
    }

    // Initialize sales report of every item with zero number of purchase
    public ShoppingCart(List<Items> itemsList) {
        for(Items item : itemsList) {
            salesReportList.put(item.getItemName(), INITIAL_QUANTITY_OF_PURCHASED_ITEMS);
        }
    }

    // Add purchased item object into sales report list as Map<itemName, numberOfPurchase>
    public void addToCart(Items chosenItem) {
        int currentNumberOfThePurchasedItem = salesReportList.get(chosenItem.getItemName());
        salesReportList.put(chosenItem.getItemName(), currentNumberOfThePurchasedItem + 1);
    }

    // Add the item price to the current total cost by the customer
    public void addToTotalCost(BigDecimal itemPrice) {
        totalCost = totalCost.add(itemPrice);
    }

    // Print sales report on console output as well as in a sales report text file
    public String printSalesReport() {
        StringBuilder salesReport = new StringBuilder();
        salesReport.append("**SALES REPORT**\n\n");
        System.out.println("**SALES REPORT**");
        System.out.println();
        for(Map.Entry<String, Integer> item : salesReportList.entrySet()) {
            salesReport.append(item.getKey()).append(" | ").append(item.getValue()).append("\n");
            System.out.printf("%s | %s\n", item.getKey(), item.getValue());
        }
        System.out.println();
        salesReport.append("\n").append("TOTAL SALES $").append(totalCost);
        System.out.println("TOTAL SALES $" + totalCost);
        System.out.println();

        return salesReport.toString();
    }
}
