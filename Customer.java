import java.util.ArrayList;
import java.util.Random; 

/** Customer class */ 
public class Customer{

    //attributes
    public String name;
    public Food order; 
    public Boolean hasQuestion; 
    public Integer finalTip;
    public Table table;
    static ArrayList<String> posResponses = new ArrayList<String>();
    static ArrayList<String> negResponses = new ArrayList<String>();
    
    /** 
    *  Constructs a customer
    *  @param name name of customer
    *  @param order order of customer
    *  @param hasQuestion Boolean telling if the customer has a question
    *  @param finalTip amount of final tip
    *  @param table table that the customer is sitting at
    */
 
    public Customer(String name, Food order, Boolean hasQuestion, Integer finalTip, Table table) {
        this.name = name; 
        this.order = order;
        this.hasQuestion = hasQuestion;
        this.finalTip = 10;
        setAnswers();
    }

    public void setAnswers(){
        posResponses.add("Thank you! Looks delicious.");
        posResponses.add("Smells delicious.");
        posResponses.add("Thanks!");
        posResponses.add("Wow, it arrived so quickly!");
        posResponses.add("Thank you, it looks just like how my mom makes it!");
        negResponses.add("This isn't what I ordered! Unbelievable.");
        negResponses.add("Huh? I thought I ordered something else...");
        negResponses.add("Um, I think you got my order wrong.");
        negResponses.add("Get me your manager! This isn't what I ordered.");
        negResponses.add("Why are you giving me this? I ordered something else.");
    }

    /** 
    *  Returns the name of the customer 
    *  @return name : name of the customer
    */

    public String getName(){
        return this.name;
    }
    
    
    /** 
    *  Returns the final tip 
    *  @return finalTip : final tip amount for the waiter
    */

    public Integer finalTip(){
        //accessing the table's total tip 
        return this.finalTip;
    }

    
    /** 
    *  Returns the order with the customer's name 
    *  @return order : order that the customer chose 
    */

    public Food order(){
        //print customers order
        Game.printDialogue(this.name + ": I'll have the " + order.name + ".");
        return order;
    }


    /** 
    *  methods
    *  Customer sits at a table
    *  @param table table that the customer will sit 
    */

    public void sit(Table t){
        t.customers.add(this);
    }


    /** 
    *  Returns the question that the customer chose randomly 
    *  @return chosenQ : question that the customer is asking 
    */

    public String askQuestion(){
        //get random number
        Random rand2 = new Random();
        int randomQ = rand2.nextInt(Restaurant.questionKeys.size());
        
        //picks randomly from the list of questions 
        String chosenQ = Restaurant.questionKeys.get(randomQ);

        return chosenQ;
    }


    /** 
    *  Adds the tip amount to the current tip amount 
    *  @param amount amount of tip that is going to be add
    */

    public void tip(int amount){
        //updates the amount of final tip
        finalTip = finalTip + amount;
    }

    public Table getTable(){
        return this.table;
    }
    
}
