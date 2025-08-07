import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/** Waiter Class */
public class Waiter {
    Scanner scanner = new Scanner(System.in);
    /**
     * Player name
     */
    String name;

    /**
     * Amount of tips the player currently has
     */
    public int tips;

    /**
     * Current no. of times the player has fucked up
     */
    public int currentFuckUps;

    /**
     * No. of fuck ups left
     */
    public int fuckUpsLeft;

    /**
     * Sets maximum no. of times player can fuck up to 3
     */
    public int maxFuckUps;

    /**
     * Whether or not the player is fired
     */
    public boolean isFired = false;

    /**
     * No. of times the player has asked the kitchen for help
     */
    private int askKitchenCount = 0;

    /**
     * Constructor
     * @param name
     * @param dateName
     */

    public Waiter(String name){
        this.name = name;
        this.tips = 0;
        this.currentFuckUps = 0;
        this.fuckUpsLeft = 0;
        this.maxFuckUps = 0;
    }

    /** 
     * Returns the waiter's name
     * @return name
     */
    public String getName(){
        return this.name;
    }

     /**
     * Returns the current number of times the player has fucked up
     * @return currentFuckUps
     */

    public int getCurrentFuckUps(){
        return this.currentFuckUps;
    }
     /**
     * Returns the number of fucks up left
     * @return fuckUpsLeft
     */

    public int getFuckUpsLeft(){
        return this.fuckUpsLeft;
    }
    /**
     * Returns the maximum number of times the player can fuck up
     * @return maxFuckUps
     */
    public int getMaxFuckUps() {
        return maxFuckUps;
    }

    /**
     * Returns the current amount of tips the player has
     * @return tips
     */
    public double getTips() {
        return this.tips;
    }

    /**
     * Allows the player to answer the customer's question, and adjusts tips, no. of times they have fucked up accordingly. Allows user to ask the kitchen, if they have chances available.
     * @param question The customer's question
     * @param amount Amount the tip must be decremented by
     */
    public void answerQuestion(String question, int amount, Customer customer){
        System.out.println("Game: Choose your answer: \n" + "A. Yes\n" + "B. No\n" + "C. I'm not sure, let me double check that.");
        String answer = scanner.nextLine();

        while (!answer.equalsIgnoreCase("A") && !answer.equalsIgnoreCase("B") && !answer.equalsIgnoreCase("C")){
            if (answer.equalsIgnoreCase("help")){
                Help.help();
            }
            else{
                System.out.println("Game: Choose one of the given options.");
            }
            answer = scanner.nextLine();
        }

        // if the player is unsure
        if (answer.equalsIgnoreCase("C")){
            Game.printDialogue(this.name + ": I'm not sure, let me double check that.");
            System.out.println("Game: Would you like to ask the kitchen?\n" + "A. Yes\n" + "B. No");
            String askKitchenChoice = scanner.nextLine();
            if(askKitchenChoice.equalsIgnoreCase("A")){
                askKitchen(question);
                 Game.printDialogue(customer.name + ": So?");
                 answerQuestion(question, amount, customer);
            }
            
            else if(askKitchenChoice.equalsIgnoreCase("B")){
                answerQuestion(question, amount, customer);
            }
            
            else {
                System.out.println("");
            }
        }

        // if the player chooses to answer
        else if (answer.equalsIgnoreCase("A") || answer.equalsIgnoreCase("B")){
            if (answer.equalsIgnoreCase("A")){
                Game.printDialogue(this.name + ": Yes");
            }
            else {
                Game.printDialogue(this.name + ": No");
            }

            if ((Restaurant.questions.get(question) && answer.equalsIgnoreCase("B")) || 
                (!Restaurant.questions.get(question) && answer.equalsIgnoreCase("A"))) {
                Game.printDialogue("Game: You were wrong. The customer reported you to the manager.");
                this.currentFuckUps += 1;
                this.fuckUpsLeft = this.maxFuckUps - this.currentFuckUps;
                if (this.fuckUpsLeft==0){
                    Game.fired();
                }
                Game.printDialogue("Manager: How did you not know that? I'd better not get any more complaints. You only have " + fuckUpsLeft + " fuck ups left.");
                customer.tip(amount);
            }
            else{
                Game.printDialogue(customer.getName() + ": Okay. Thank you.");
            }
        }
    }

    /**
     * Prompts user to pick the correct order from a list of menu items
     * @param order String
     * @param customerName String
     * @return boolean if waiter picked correctly
     */
    public Boolean enterOrder(String order, String customerName){ 
        // how do i randomly assign A,B,C,D to the menu items, so the correct option is not always D
        // fixed
        // List of answer indeces
        ArrayList<String> index = new ArrayList<String>();  
        index.add("A. ");
        index.add("B. ");
        index.add("C. ");
        index.add("D. ");

        // Copy of menu items to prevent repeats
        ArrayList<String> menuCopy = new ArrayList<String>();  
        for (int i=0; i<Restaurant.menu.size(); i++){
            menuCopy.add(Restaurant.menu.get(i).name);
        }
        menuCopy.remove(order);

        System.out.println("What did " + customerName + " order?");
        Random random = new Random();
        int correct = random.nextInt(4);    // Which answer will be correct
        int randomIndex;

        for (int i=0; i<4; i++){
            if (i==correct){
                System.out.println(index.get(i) + order);
            }
            else {
                randomIndex = random.nextInt(menuCopy.size());
                System.out.println(index.get(i) + menuCopy.get(randomIndex));
                menuCopy.remove(menuCopy.get(randomIndex));
            }
        }
        menuCopy.clear();

        String answer = scanner.nextLine().toUpperCase();

        while (!(answer.equals("A") || answer.equals("B") || answer.equals("C") || answer.equals("D"))){
            System.out.println("Game: Choose one of the given options.");
            answer = scanner.nextLine().toUpperCase();
        }

        Boolean answerWasCorrect = false;
        if(index.get(correct).contains(answer)){
            answerWasCorrect = true;
        }
        return answerWasCorrect;
    }

    /**
     * Checks the questions hashtable and returns the correct answer, if the player still has a chance left
     * @param question question being asked
     */
    private void askKitchen(String question){
        if (askKitchenCount < 1) {
            Game.printDialogue("Game: Entering Kitchen......");
            Game.printDialogue("Chef: What do you want " + this.name + "?");
            Game.printDialogue(this.name + ": " + question);
            if (Restaurant.questions.get(question)) {
                Game.printDialogue("Chef: Yeah");
            }
            else {
                Game.printDialogue("Chef: No");
            }
        }

        else {
            Game.printDialogue("Chef: Get out. You've already asked me a question, let me do my job. ");
        }
    }
    
    /**
     * Allows the player to view the menu
     */
    public void viewMenu(){
        //Restaurant.printMenu();
    }

    /**
     * Serves food to the customer
     * @param correctOrders boolean list if customers order is correct
     * @param t current table
     * @param tip how much tip will be reduced for mistakes
     */
    public void serveFood(ArrayList<Boolean> correctOrders, Table t, int tip){
        Random random = new Random();
        int randomInt;
        for (int i = 0; i<t.customers.size(); i++){
            if (correctOrders.get(i)){
                randomInt = random.nextInt(Customer.posResponses.size());
                Game.printDialogue(t.customers.get(i).name + ": " + Customer.posResponses.get(randomInt));
            }
            else {
                randomInt = random.nextInt(Customer.negResponses.size());
                Game.printDialogue(t.customers.get(i).name + ": " + Customer.negResponses.get(randomInt));
                this.currentFuckUps += 1;
                this.fuckUpsLeft = this.maxFuckUps - this.currentFuckUps;
                if (this.fuckUpsLeft==0){
                    Game.fired();
                }
                Game.printDialogue("Manager: How did you get someone's order wrong? You'd better start paying more attention. You only have " + fuckUpsLeft + " fuck ups left.");
                t.customers.get(i).tip(tip);
            }
        }
        Game.printDialogue("Game: All of the customers at this table have been served.");
    }

    public void help(){
        Help.help();
        callCommand(scanner.nextLine());
    }

    public void callCommand(String command){
        if (command.equals("Get Current Fuck Ups")){
            this.getCurrentFuckUps();
        }
        if (command.equals("Get Fuck Ups Left")){
            this.getFuckUpsLeft();
        }
        if (command.equals("Get Max Fuck Ups")){
            this.getMaxFuckUps();
        }
        if (command.equals("Get Tips")){
            this.getTips();
        }
        if (command.equals("View Menu")){
            this.viewMenu();
        }
    }

    public static void main(String[] args) {
        
    }
}
