package com.techelevator.model;

import com.techelevator.domain.*;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class VendingMachine {
    private double balance; // TODO: NumberFormat currency instance for balance instance variable

    private final Map<String, List<VendingItem>> vendingCodeToVendingItemList; //TODO: make VendingItem abstract class and at least one child class

    public VendingMachine() {
        this.balance = 0.00;
        this.vendingCodeToVendingItemList = new TreeMap<>();
    }

    public void addToBalance(double moneyToAdd) {
        this.balance += moneyToAdd;
    }

    public void addVendingItem(String line) {
        String[] parts = line.split("\\|");
        String vendingCode = parts[0];
        String itemName = parts[1];
        String priceAsDouble = parts[2];
        double price = Double.parseDouble(priceAsDouble);
        String className = parts[3];

        List<VendingItem> list = makeFiveItems(itemName, price, className);
        vendingCodeToVendingItemList.put(vendingCode, list);
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

    public Map<String, List<VendingItem>> getVendingCodeToVendingItemList() {
        return vendingCodeToVendingItemList;
    }

    public double getBalance() {
        return balance;
    }
}
