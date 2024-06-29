package com.techelevator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.*;

public class Items {
    private static final int DEFAULT_STOCK_QUANTITY = 5;
    private static List<Items> itemDetails = new ArrayList<>();
    private String itemSlot;
    private String itemName;
    private BigDecimal itemPrice;
    private String itemCategory;
    private int itemStock;

    public Items() {
        itemDetails = initializieItemsDetails();
    }

    public Items(String itemSlot, String itemName, BigDecimal itemPrice, String itemCategory, int itemStock) {
        this.itemSlot = itemSlot;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCategory = itemCategory;
        this.itemStock = itemStock;
    }

    // getItemDetails method return the list of items
    // and its details like item slot, item name, item price, item category, item stock
    // that is read from the given csv file
    public static List<Items> initializieItemsDetails() {
        String filePath = "vendingmachine.csv";
        try (Scanner scanner = new Scanner(new FileReader(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] lineArray = line.split("\\|");
                String itemSlot = lineArray[0];
                String itemName = lineArray[1];
                BigDecimal itemPrice = new BigDecimal(lineArray[2]);
                String itemCategory = lineArray[3];
                Items items = new Items(itemSlot, itemName, itemPrice, itemCategory, DEFAULT_STOCK_QUANTITY);
                itemDetails.add(items);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error! File not found : " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error! Invalid format for Price " + e.getMessage());
        }
        return itemDetails;
    }

    // adding a method that returns the item according to the slot location.
    public static Items getItemBySlotLocation(String slotLocation) {
        for (Items item : itemDetails) {
            if (item.getItemSlot().equalsIgnoreCase(slotLocation)) {
                return item;
            }
        }
        return null;
    }

    public String getItemSlot() {
        return itemSlot;
    }

    public String getItemName() {
        return itemName;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public int getCurrentItemStock() {
        return itemStock;
    }

    public void setCurrentItemStock(int currentItemStock) {
        this.itemStock = currentItemStock;
    }

    public List<Items> getItemDetails() {
        return itemDetails;
    }

    // overrides the default toSring() method
    // to return the items from the array in a String format
    @Override
    public String toString() {
        return String.format("%s - %s - $%.2f - %s - %s",
                itemSlot, itemName, itemPrice, itemCategory, itemStock);
    }
}
