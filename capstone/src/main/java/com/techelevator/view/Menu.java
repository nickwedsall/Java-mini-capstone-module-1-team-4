package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Menu {

    private PrintWriter out;
    private Scanner in;

    public Menu(InputStream input, OutputStream output) {
        this.out = new PrintWriter(output);
        this.in = new Scanner(input);
    }

    public Object getChoiceFromOptions(Object[] options) {
        Object choice = null;
        while (choice == null) {
            displayMenuOptions(options);
            choice = getChoiceFromUserInput(options);
        }
        return choice;
    }

    public String getSlotLocationFromUser() {
        out.println();
        out.print("Enter slot location: ");
        out.flush();
        return in.nextLine();
    }

    private Object getChoiceFromUserInput(Object[] options) {
        Object choice = null;
        String userInput = in.nextLine();
        try {
            int selectedOption = Integer.valueOf(userInput);
            if (selectedOption > 0 && selectedOption <= options.length) {
                choice = options[selectedOption - 1];
            }
        } catch (NumberFormatException e) {
            // eat the exception, an error message will be displayed below since choice will be null
        }
        if (choice == null) {
            out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***" + System.lineSeparator());
        }
        return choice;
    }

    private void displayMenuOptions(Object[] options) {
        out.println();
        for (int i = 0; i < options.length; i++) {
            int optionNum = i + 1;
            out.println(optionNum + ") " + options[i]);
        }
        out.print(System.lineSeparator() + "Please choose an option >>> ");
        out.flush();
    }

    public void displayVendingMachineItems(String vendingMachineItems) {
        out.println();
        out.print(vendingMachineItems);
        out.flush();
    }

    public void displayCurrentMoneyProvided(String currentMoneyProvided) {
        out.println();
        out.println("Current Money Provided: " + currentMoneyProvided);
        out.flush();
    }

    public void displayMenuItemDoesNotExist() {
        out.println();
        out.println("Invalid vending code");
        out.flush();
    }

    public void displayMenuItemIsSoldOut() {
        out.println();
        out.println("Item is SOLD OUT");
        out.flush();
    }

    public void displayExitMessage() {
        out.println("");
        out.println("Thank you for your business!");
        out.flush();
    }

    public void displayErrorMessage() {
        out.println("");
        out.println("Failed to load file!  VendingMachine will self destruct in five seconds...");
        out.flush();
    }
}
