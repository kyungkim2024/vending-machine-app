package com.techelevator;

import com.techelevator.log.Log;

import java.math.BigDecimal;
import java.util.*;

public class Application {

    private static final Scanner inputScanner = new Scanner(System.in);
    private static Items items = new Items();
    private static List<Items> itemsList = new ArrayList<>();
    private static ShoppingCart shoppingCart;

    public static void main(String[] args) {
        itemsList = items.getItemDetails();
        shoppingCart = new ShoppingCart(itemsList);
        mainMenu();
        inputScanner.close();
    }

    private static void mainMenu() {
        boolean keepRunning = true;
        while (keepRunning) {
            System.out.println("(1) Display Vending Machine Items");
            System.out.println("(2) Purchase");
            System.out.println("(3) Exit");

            try {
                int input = inputScanner.nextInt();
                switch (input) {
                    case 1:
                        System.out.println("-----------------------------------");
                        System.out.println("Code - Name - Price - Type - Stock");
                        System.out.println("-----------------------------------");
                        for (Items itemDetail : itemsList) {
                            System.out.println(itemDetail);
                        }
                        System.out.println();
                        mainMenu();
                        return;
                    case 2:
                        purchaseMenu();
                        return;
                    case 3:
                        System.out.println("Thank you! Come again!");
                        keepRunning = false;
                        break;
                    case 4:
                        hiddenMenu();
                        return;
                    default:
                        return;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input: " + e.getMessage() + ", Exiting Program.");
                break;
            }
        }
    }

    private static void purchaseMenu() {
        System.out.println("Current Balance: $" + Customer.getCurrentBalance());
        System.out.println();
        System.out.println("(1) Feed Money");
        System.out.println("(2) Select Product");
        System.out.println("(3) Finish Transaction");
        System.out.println("(4) Main Menu");

        try {
            int input = inputScanner.nextInt();
            switch (input) {
                case 1:
                    System.out.println("Insert cash (whole dollar amounts):");
                    int amountToDeposit = inputScanner.nextInt();
                    Customer.deposit(amountToDeposit);
                    purchaseMenu();
                    return;
                case 2:
                    // Display items
                    System.out.println("-----------------------------------");
                    System.out.println("Code - Name - Price - Type - Stock");
                    System.out.println("-----------------------------------");
                    for (Items itemDetail : itemsList) {
                        System.out.println(itemDetail);
                    }
                    // Input
                    System.out.println("Input your snack code:");
                    String slotInput = inputScanner.next();
                    /*
                     * The input will now have to pass three tests: if the slot exists, if the Customer has
                     * money, and if the Item is in stock. Only if all three tests pass then will the
                     * operations be run to purchase the Item. A successful purchase includes deducting
                     * from the Customers current balance, reducing the chosen Item's stock by 1, and
                     * adding the transaction to the Log. Lastly, after a purchase we return to the
                     * purchaseMenu so that the Customer can buy more snacks or perform other options.
                     */
                    if(hasSlot(items, slotInput)){
                        Items chosenItem = items.getItemBySlotLocation(slotInput);

                        if(hasMoney(chosenItem, Customer.getCurrentBalance())){

                            if(hasStock(chosenItem)){
                                System.out.println("Dispensing " + chosenItem.getItemName() + ", " + snackMessage(chosenItem));
                                System.out.println("Charging $" + chosenItem.getItemPrice());
                                Customer.purchase(chosenItem.getItemPrice());
                                shoppingCart.addToCart(chosenItem);
                                shoppingCart.addToTotalCost(chosenItem.getItemPrice());
                                chosenItem.setCurrentItemStock(chosenItem.getCurrentItemStock() - 1);
                                Log.log(String.format("%s %s $%s $%s",
                                        chosenItem.getItemName(), slotInput, chosenItem.getItemPrice(), Customer.getCurrentBalance()));
                                System.out.println(chosenItem.getItemName() + "'s stock is now " + chosenItem.getCurrentItemStock());
                                System.out.println("-----------------------------------");
                                purchaseMenu();
                                return;
                            } else {
                                System.out.println("Item not in stock... Returning to Purchase Menu.");
                                System.out.println("-----------------------------------");
                                purchaseMenu();
                                return;
                            }
                        } else {
                            System.out.println("Not enough money... Returning to Purchase Menu.");
                            System.out.println("-----------------------------------");
                            purchaseMenu();
                            return;
                        }
                    } else {
                        System.out.println("Item slot doesn't exist... Returning to Purchase Menu.");
                        System.out.println("-----------------------------------");
                        purchaseMenu();
                        return;
                    }
                case 3:
                    Customer.returnChange();
                    Customer.zeroBalance();
                    purchaseMenu();
                    return;
                case 4:
                    mainMenu();
                default:
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input: " + e.getMessage() + ", Exiting Program.");
        }
    }

    private static void hiddenMenu() {
        String salesReport = shoppingCart.printSalesReport();
        Log.salesReportLog(String.format(salesReport));
        mainMenu();
    }

    private static boolean hasSlot(Items items, String slotInput){
        return items.getItemBySlotLocation(slotInput) != null;
    }

    private static boolean hasMoney(Items items, BigDecimal balance){
        return balance.compareTo(items.getItemPrice()) >= 0;
    }

    private static boolean hasStock(Items items){
        return items.getCurrentItemStock() > 0;
    }

    private static String snackMessage(Items items){
        String snackMessage = null;
        switch (items.getItemCategory()) {
            case "Chip":
                snackMessage = "'Crunch Crunch, Yum!'";
                break;
            case "Candy":
                snackMessage = "'Munch Munch, Yum!'";
                break;
            case "Drink":
                snackMessage = "'Glug Glug, Yum!'";
                break;
            case "Gum":
                snackMessage = "'Chew Chew, Yum!'";
                break;
            default:
                snackMessage = "snackMessage was not found.";
                break;
        }
        return snackMessage;
    }
}

