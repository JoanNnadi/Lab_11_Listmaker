import java.util.ArrayList;
import java.util.Scanner;

public class menuChoiceItems {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        String choice;
        boolean done = false;

        do {
            displayList(list);
            displayMenu();

            //  Using SafeInput to ensure valid menu choices are picked by the Users (A, D, I, P, Q)
            choice = SafeInput.getRegExString(in, "Please enter your menu choice: ", "[AaDdIiPpQq]").toUpperCase();

            switch (choice) {
                case "A":
                    addItem(in, list);
                    break;
                case "D":
                    deleteItem(in, list);
                    break;
                case "I":
                    insertItem(in, list);
                    break;
                case "P":
                    displayList(list);
                    break;
                case "Q":
                    //  Confirming quit using SafeInput
                    if (SafeInput.getYNConfirm(in, "Are you sure you want to quit picking your menu choices?")) {
                        done = true;
                        System.out.println("\n Thanks for checking in. The menu choice program has ended. Goodbye!");
                    }
                    break;
            }

        } while (!done);

        in.close();
    }

    // ------------------- MENU CHOICE OPTION METHODS -------------------

    // Add Menu Method
    private static void addItem(Scanner in, ArrayList<String> list) {
        System.out.print("Enter the menu choice item to add: ");
        String item = in.nextLine();
        list.add(item);
        System.out.println("'" + item + "' added to the end of the list.");
    }

    // Delete Menu Method
    private static void deleteItem(Scanner in, ArrayList<String> list) {
        if (list.isEmpty()) {
            System.out.println("The list is empty. Nothing to delete.");
            return;
        }

        displayList(list);
        // Using SafeInput to safely get the item number
        int itemNum = SafeInput.getRangedInt(in, "Enter the menu item number to delete", 1, list.size());
        String removed = list.remove(itemNum - 1);
        System.out.println("'" + removed + "'Menu item deleted from the list.");
    }

    // Insert a Menu Choice Method
    private static void insertItem(Scanner in, ArrayList<String> list) {
        System.out.print("Enter the menu item you want to insert: ");
        String item = in.nextLine();

        //  Using SafeInput to get valid insert position
        int position = SafeInput.getRangedInt(in, "Enter the menu position number", 1, list.size() + 1);
        list.add(position - 1, item);
        System.out.println("'" + item + "' menu position inserted at position " + position + ".");
    }

    // ------------------- DISPLAY MENU CHOICE METHODS -------------------

    // Display menu choice method
    private static void displayList(ArrayList<String> list) {
        System.out.println("\n==== CURRENT LIST ====");
        if (list.isEmpty()) {
            System.out.println("[List is empty]");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + ". " + list.get(i));
            }
        }
        System.out.println("======================\n");
    }

    private static void displayMenu() {
        System.out.println("==== MENU CHOICE OPTIONS ====");
        System.out.println("A – Add an item to the list");
        System.out.println("D – Delete an item from the list");
        System.out.println("I – Insert an item into the list");
        System.out.println("P – Print the list");
        System.out.println("Q – Quit the program");
        System.out.println("======================");
    }
}

