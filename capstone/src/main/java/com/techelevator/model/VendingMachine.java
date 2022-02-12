package com.techelevator.model;

import com.techelevator.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class VendingMachine {
    private double balance; // TODO: NumberFormat currency instance for balance instance variable

    private final Map<String, List<VendingItem>> slotLocationToVendingItems; //TODO: make VendingItem abstract class and at least one child class

    // Format helper constants for toString() method
    private static final String SLOT_LOCATION = "Code: ";
    private static final String ITEM_NAME = "Item: ";
    private static final String PRICE = "Price: ";
    private static final String QUANTITY = "Quantity: ";

    public VendingMachine() {
        this.balance = 0.00;
        this.slotLocationToVendingItems = new TreeMap<>();
    }

    public void addToBalance(double moneyToAdd) {
        this.balance += moneyToAdd;
    }

    public void addVendingItem(String line) {
        String[] parts = line.split("\\|");
        String slotLocation = parts[0];
        String itemName = parts[1];
        String priceAsDouble = parts[2];
        double price = Double.parseDouble(priceAsDouble);
        String className = parts[3];

        List<VendingItem> list = makeFiveItems(itemName, price, className);
        slotLocationToVendingItems.put(slotLocation, list);
    }


    // Factory design pattern here could make things easier
    private List<VendingItem> makeFiveItems(String itemName, double price, String className) {
        List<VendingItem> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
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

    // Formatting toString() method to print slot location, item name, price, and quantity
    // Does not keep record of what is sold out, only indicates that the slot is SOLD OUT
    // if list is empty
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String slotLocation : slotLocationToVendingItems.keySet()) {
            if (slotLocationToVendingItems.get(slotLocation).isEmpty())
                sb.append("SOLD OUT").append(System.lineSeparator());
            else {
                int vendingItemQuantity = slotLocationToVendingItems.get(slotLocation).size();
                VendingItem vendingItem = slotLocationToVendingItems.get(slotLocation).get(0);
                String itemName = vendingItem.getItemName();
                double price = vendingItem.getPrice();

                sb.append(SLOT_LOCATION).append(slotLocation).append(" ")
                        .append(ITEM_NAME).append(itemName).append(" ")
                        .append(PRICE).append(price).append(" ")
                        .append(QUANTITY).append(vendingItemQuantity)
                        .append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
