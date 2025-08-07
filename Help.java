import java.util.Scanner;
import java.util.ArrayList;

public class Help{
    static Scanner scanner = new Scanner(System.in);
    /**
     * Stores a list of all the available commands the player can use
     */
    public static ArrayList<String> availableCommands;

    public Help(){
        availableCommands = new ArrayList<>();
        availableCommands.add("Get Current Fuck Ups");
        availableCommands.add("Get Fuck Ups Left");
        availableCommands.add("Get Max Fuck Ups");
        availableCommands.add("Get Tips");
        availableCommands.add("View Menu");
    }

    /**
     * Allows the player to choose to get a list of available commands they can use or access the game rules again
     */
    public static void help(){
        Game.printBorder(25);
        System.out.println("What do you need help with?\n" + "A. Available Commands\n" + "B. Game Rules");
        String scan1 = scanner.nextLine();
        while (!scan1.equalsIgnoreCase("A") && !scan1.equalsIgnoreCase("B")) {
            System.out.println("Choose one of the given options.");
            scan1 = scanner.nextLine();
        }
        if (scan1.equalsIgnoreCase("A")){
            System.out.println("This is a list of all the commands you can use.\n-----");
            System.out.println("welcome\ngreet\nenter\nserve\nhelp");
            System.out.println("Press enter to move to the next line of dialogue.");
            System.out.println("-----");
            System.out.println("Try using one of the commands now!");
        }

        else if (scan1.equalsIgnoreCase("B")){
            /*prints all game rules */
            System.out.println("---RULES---");
            System.out.println("- *welcome* guests when they arrive.");
            System.out.println("- *greet* your table once they've all sat down and take their order.");
            System.out.println("- *enter* their order so it gets sent to the kitchen.");
            System.out.println("- *serve* their food.\n");
            System.out.println("- Press enter to move to the next line of dialogue.");
            System.out.println("- If customers have any questions, be sure to answer them by picking the correct answer that you will be given beforehand.");
            System.out.println("- Don't be afraid to ask for help, but make sure you answer customers quickly. We wouldn't want to keep anyone waiting!\n----------");
            System.out.println("Try using one of the commands now!");
        }

        Game.printBorder(30);
    }

    public static void main(String[] args) {
        Help.help();
    }
}
