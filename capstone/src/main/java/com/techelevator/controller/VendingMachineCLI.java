package com.techelevator.controller;

import com.techelevator.model.VendingMachine;
import com.techelevator.view.Menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class VendingMachineCLI {
    private static final String FILE_PATH = "vendingmachine.csv";

    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit";
    private static final String[] MAIN_MENU_OPTIONS = {
            MAIN_MENU_OPTION_DISPLAY_ITEMS,
            MAIN_MENU_OPTION_PURCHASE,
            MAIN_MENU_OPTION_EXIT,
    };

    private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
    private static final String PURCHASE_MENU_OPTION_SELECT = "Select Product";
    private static final String PURCHASE_MENU_OPTION_FINISH = "Finish Transaction";
    private static final String[] PURCHASE_MENU_OPTIONS = {
            PURCHASE_MENU_OPTION_FEED_MONEY,
            PURCHASE_MENU_OPTION_SELECT,
            PURCHASE_MENU_OPTION_FINISH,
    };

    private static final String FEED_MONEY_MENU_OPTION_ONE_DOLLAR = "$1.00";
    private static final String FEED_MONEY_MENU_OPTION_TWO_DOLLARS = "$2.00";
    private static final String FEED_MONEY_MENU_OPTION_FIVE_DOLLARS = "$5.00";
    private static final String FEED_MONEY_MENU_OPTION_TEN_DOLLARS = "10.00";
    private static final String[] FEED_MONEY_MENU_OPTIONS = {
            FEED_MONEY_MENU_OPTION_ONE_DOLLAR,
            FEED_MONEY_MENU_OPTION_TWO_DOLLARS,
            FEED_MONEY_MENU_OPTION_FIVE_DOLLARS,
            FEED_MONEY_MENU_OPTION_TEN_DOLLARS,
    };

    private Menu menu;
    private VendingMachine vendingMachine;

    public VendingMachineCLI(Menu menu, VendingMachine vendingMachine) {
        this.menu = menu;
        this.vendingMachine = vendingMachine;
    }

    public void run() {
        // Where we should read the file?
        if (loadVendingMachine()) {
            mainMenu();
        } else {
            menu.displayErrorMessage();
        }
    }

    private boolean loadVendingMachine() {
        boolean loadSuccess = true;

        // eat the error and return loadSuccess
        // populate our map
        // this is where we will read from a file
        // try-with-resources bullshit

        File filePath = new File(FILE_PATH);
        try (java.util.Scanner fileReader = new Scanner(filePath)) {
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                vendingMachine.addVendingItem(line);
            }
            loadSuccess = true;
        } catch (FileNotFoundException e) {
            // eat exception???
        }

        return loadSuccess;
    }

    private void mainMenu() {
        while (true) {
            String choice = getMainMenuChoice();
            // Switch case situation called for here?
            if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
                // display vending machine items
                displayVendingMachineItem();
            } else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
                // do purchase
                purchaseMenu();
            } else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
                // display exit message
                menu.displayExitMessage();
                // exit
                break;
            }
        }
    }

    private void purchaseMenu() {
        while (true) { // TODO: Refactor to switch-case statement
            String choice = getPurchaseMenuChoice();
            if (choice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
                // goto feedMoneyMenu();
            } else if (choice.equals(PURCHASE_MENU_OPTION_SELECT)) {
                // goto selectProductMenu();
            }
            else if (choice.equals(PURCHASE_MENU_OPTION_FINISH)) {
                break;
            }
            menu.displayCurrentMoneyProvided(vendingMachine.getBalance());
        }
    }

    private String getMainMenuChoice() {
        return (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
    }

    private String getPurchaseMenuChoice() {
        return (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
    }

    private void displayVendingMachineItem() {
        menu.displayVendingMachineItems(vendingMachine.getVendingCodeToVendingItemList());
    }
}