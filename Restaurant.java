import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class Restaurant {
    /** arraylist of tables in the restaurant */
    ArrayList<Table> tables;
    /** arraylist of foods */
    public static ArrayList <Food> menu = new ArrayList<Food>();
    /** arraylist of customers */
    ArrayList<Customer> customers;
    /** string of restaurant name */
    public static String name;
    /** hashtable of customer questions and keys */
    static Hashtable <String, Boolean> questions;
    private Random random;
    /** arraylist of customer questions */
    static ArrayList <String> questionKeys;


    /** Defualt constructor for restaurant */
    public Restaurant (String name){
        Restaurant.name = name;
        this.tables = new ArrayList<Table>();
        
        //customer
        this.customers = new ArrayList<Customer>();

        //questions hashtable
        questions = new Hashtable<String, Boolean>();
        this.random = new Random();
        questions.put("Do you take debit cards?", random.nextBoolean());
        questions.put("Do you have little spoons for babies?", random.nextBoolean());
        questions.put("Does this restaurant have gender-neutral bathrooms?", random.nextBoolean());
        questions.put("Do you have craft beer?", random.nextBoolean());
        questions.put("Does this restaurant do take out?", random.nextBoolean());
        questions.put("Is smoking inside allowed?", random.nextBoolean());
        questions.put("Are pets allowed in?", random.nextBoolean());
        questions.put("Does Matcha contain caffeine?", true);
        questions.put("Do you have vegetarian dishes?", true);
        questions.put("Do you have vegan dishes?", true);
        questions.put("Are there any drinks without alcohol?", true);

        //questions keys
        questionKeys = new ArrayList<>();
        questionKeys.add("Do you take debit cards?");
        questionKeys.add("Do you have little spoons for babies?");
        questionKeys.add("Does this restaurant have gender-neutral bathrooms?");
        questionKeys.add("Do you have craft beer?");
        questionKeys.add("Does this restaurant do take out?");
        questionKeys.add("Is smoking inside allowed?");
        questionKeys.add("Are pets allowed in?");
        questionKeys.add("Does Matcha contain caffeine?");
        questionKeys.add("Do you have vegetarian dishes?");
        questionKeys.add("Do you have vegan dishes?");
        questionKeys.add("Are there any drinks without alcohol?");
        
        // menu items
        menu.add(new Food("California Roll", FoodCategory.MAIN, false, false, false, false, false, 15));
        menu.add(new Food("Japanese Curry", FoodCategory.MAIN, true, true, false, false, false, 15));
        menu.add(new Food("Moussaka", FoodCategory.MAIN, false, false, false, true, true, 15));
        menu.add(new Food("Biryani", FoodCategory.MAIN, false, false, true, false, false, 15));
        menu.add(new Food("Tahchin", FoodCategory.MAIN, false, true, false, false, true, 15));
        menu.add(new Food("Chana Chaat", FoodCategory.MAIN, true, true, false, false, false, 15));
        menu.add(new Food("Katsudon", FoodCategory.MAIN, false, false, false, true, false, 15));
        menu.add(new Food("Tempura Udon", FoodCategory.MAIN, false, true, false, true, false, 15));
        menu.add(new Food("Matcha Sundae", FoodCategory.DESSERT, false, true, false, true, true, 10));
        menu.add(new Food("Trigona", FoodCategory.DESSERT, false, true, false, true, true, 10));
        menu.add(new Food("Kulfa Falooda", FoodCategory.DESSERT, false, true, false, false, true, 10));
        menu.add(new Food("Baklava", FoodCategory.DESSERT, false, true, false, true, true, 10));
        menu.add(new Food("Fereni", FoodCategory.DESSERT, false, true, false, false, true, 10));
        menu.add(new Food("Water", FoodCategory.DRINK, true, true, false, false, false, 0));
        menu.add(new Food("Lassi", FoodCategory.DRINK, false, true, false, false, true, 5));
        menu.add(new Food("Tea", FoodCategory.DRINK, true, true, false, false, false, 5));
        menu.add(new Food("Beer", FoodCategory.DRINK, true, true, false, true, false, 7));
        menu.add(new Food("Sake", FoodCategory.DRINK, true, true, false, false, false, 7));
    }

     /**
      * Overloaded constructor with the number of tables
      */
    public Restaurant(String name, int n){
        this(name);
        this.tables = new ArrayList<Table>(n);
    }

    public void addTable(){
        Table table = new Table();
        this.tables.add(table);
    }

    /**
     * Gives name of Restaurant
     * @return name
     */
    public String getName(){
        return Restaurant.name;
    }
     
     /**
      * adds food to menu
      * @param food
      */
    public void addToMenu(Food food){
        menu.add(food);
    }

     /**
      * prints menu in the format of "-- costs $--"
      */
    public void printMenu(){
        System.out.println("** " + this.getName() + " Menu! **");
        for (Food f: Restaurant.menu){
            f.print();
        }
    }

    /** Prints questions in a readable format */
    public void printQuestions(){
        for (int i = 0; i<Restaurant.questionKeys.size(); i++){
            if (questions.get(Restaurant.questionKeys.get(i)) == true) {
                System.out.println(Restaurant.questionKeys.get(i) + " Yes.");
            }
            else {
                System.out.println(Restaurant.questionKeys.get(i) + " No.");
            }
        }
    }

     /**
      * adds customer to the first vacant spot on a table
      * @param customer
      */
    public void enter(Customer customer, Table t){
       try {
            t.sitCustomer(customer);
            System.out.println("      " + customer.getName() + " has been seated.");
            }
        catch (RuntimeException e) {
                // Table is full or customer is already seated, try the next table
            }
        }
    

    public void exit(Customer customer){
        this.customers.remove(customer);
    }


    public static void main(String[] args) {
        Food ab = new Food ("ab", FoodCategory.DRINK, false, false, false, false, false, 2);
        Restaurant myRestaurant = new Restaurant("Pashm");
        myRestaurant.addToMenu(ab);
        ab.toString();
       // myRestaurant.printMenu();
    }
}
