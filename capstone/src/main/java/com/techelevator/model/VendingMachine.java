package com.techelevator.model;

import com.techelevator.domain.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.*;

public class VendingMachine {
    private double balance;
    private final Map<String, List<VendingItem>> slotLocationToVendingItems;

    // CURRENCY is used to format the double when printing the balance
    private static final NumberFormat CURRENCY = NumberFormat.getCurrencyInstance();

    // Format helper constants for toString() method
    // Replaces 'magic strings' in toString method
    // Unsure if this save memory, but it does make code more readable
    private static final String SLOT_LOCATION = "Slot Location";
    private static final String ITEM_NAME = "Item";
    private static final String PRICE = "Price";
    private static final String QUANTITY = "Quantity";
    private static final String DELIMITER = ": ";
    private static final String SPACE = " ";
    private static final String SOLD_OUT = "SOLD OUT";

    private static final int CENTS_PER_DOLLAR = 100;
    private static final int CENTS_PER_QUARTER = 25;
    private static final int CENTS_PER_DIME = 10;
    private static final int CENTS_PER_NICKEL = 5;

    public VendingMachine() {
        this.balance = 0.00;
        // TreeMap used here to assist in ordered printing by slot location key
        // Ex: A1 is printed first, D4 is printed last in toString() method
        this.slotLocationToVendingItems = new TreeMap<>();
    }

    public void addToBalance(double moneyToAdd) {
        this.balance += moneyToAdd;
    }

    // Method assumes checking for valid VendingItem has already happened
    // In this implementation that is done in VendingMachineCLI
    public VendingItem dispenseVendingItem(String slotLocation) {
        VendingItem vendingItem = this.slotLocationToVendingItems.get(slotLocation).remove(0);
        double price = vendingItem.getPrice();
        this.balance -= price;
        return vendingItem;
    }

    public boolean loadVendingItemLine(String filePath) {
        boolean loadSuccess = false;
        File file = new File(filePath);
        try (Scanner fileReader = new Scanner(file)) {
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] parts = line.split("\\|");
                String slotLocation = parts[0];
                String itemName = parts[1];
                double price = Double.parseDouble(parts[2]);
                String className = parts[3];
                List<VendingItem> list = makeVendingItems(itemName, price, className, 5);
                slotLocationToVendingItems.put(slotLocation, list);
            }
            loadSuccess = true;
        } catch (FileNotFoundException e) {
            // eat exception
        }
        return loadSuccess;
    }

    public boolean isVendingItemInStock(String slotLocation) {
        return slotLocationToVendingItems.get(slotLocation).size() != 0;
    }

    public boolean isValidSlotLocation(String slotLocation) {
        return slotLocationToVendingItems.containsKey(slotLocation);
    }

    private List<VendingItem> makeVendingItems(String itemName, double price, String className, int quantity) {
        List<VendingItem> list = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            VendingItem vendingItem = null;
            switch (className) {
                case "Chip":
                    vendingItem = new Chip(itemName, price);
                    break;
                case "Candy":
                    vendingItem = new Candy(itemName, price);
                    break;
                case "Drink":
                    vendingItem = new Drink(itemName, price);
                    break;
                case "Gum":
                    vendingItem = new Gum(itemName, price);
                    break;
            }
            list.add(vendingItem);
        }
        return list;
    }

    public Map<String, List<VendingItem>> getSlotLocationToVendingItems() {
        return slotLocationToVendingItems;
    }

    public double getBalance() {
        return balance;
    }

    public String getBalanceAsFormattedCurrency() {
        return CURRENCY.format(getBalance());
    }

    public String giveChange() {
        int balanceAsInt = (int) (balance * CENTS_PER_DOLLAR);
        resetBalance();

        int quarters = balanceAsInt / CENTS_PER_QUARTER;
        balanceAsInt -= quarters * CENTS_PER_QUARTER;

        int dimes = balanceAsInt / CENTS_PER_DIME;
        balanceAsInt -= dimes * CENTS_PER_DIME;

        // balanceAsInt should always be a multiple of CENTS_PER_NICKEL
        int nickels = balanceAsInt / CENTS_PER_NICKEL;

        // ternary operator (condition ? true : false)
        // inline ternary operator
        return String.format("Change due: %d " + (quarters == 1 ? "quarter" : "quarters") +
                        " %d " + (dimes == 1 ? "dime" : "dimes") +
                        " %d " + (nickels == 1 ? "nickel" : "nickels"),
                quarters, dimes, nickels);
    }

    private void resetBalance() {
        this.balance = 0;
    }

    // Formatting toString() method to print slot location, item name, price, and quantity
    // Does not keep record of what is sold out, only indicates that the slot is SOLD OUT
    // when list is empty
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String slotLocation : slotLocationToVendingItems.keySet()) {
            if (slotLocationToVendingItems.get(slotLocation).isEmpty())
                sb.append(SOLD_OUT).append(System.lineSeparator());
            else {
                int vendingItemQuantity = slotLocationToVendingItems.get(slotLocation).size();
                VendingItem vendingItem = slotLocationToVendingItems.get(slotLocation).get(0);
                String itemName = vendingItem.getItemName();
                double price = vendingItem.getPrice();

                sb.append(SLOT_LOCATION).append(DELIMITER).append(slotLocation).append(SPACE)
                        .append(ITEM_NAME).append(DELIMITER).append(itemName).append(SPACE)
                        .append(PRICE).append(DELIMITER).append(CURRENCY.format(price)).append(SPACE)
                        .append(QUANTITY).append(DELIMITER).append(vendingItemQuantity)
                        .append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
