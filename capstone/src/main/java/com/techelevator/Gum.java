package com.techelevator;

public class Gum extends VendingItem {
    private static final String DISPENSE_MESSAGE_PREFIX = "Chew Chew";

    public Gum(String itemName,double price) {
        super(itemName, price);
    }

    @Override
    public String getDispenseMessage() {
        return dispenseVendingItemMessage(DISPENSE_MESSAGE_PREFIX);
    }
}
