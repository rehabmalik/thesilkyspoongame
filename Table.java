import java.util.ArrayList;

public class Table{
    /** arraylist of customers on table */
    ArrayList <Customer> customers;
    /** capacity of table customers */
    final int nCustomers = 4;
    int totalTip;
    
    public Table(){
        this.customers = new ArrayList<Customer>();
        this.totalTip = 0;
    }
    
     /**
      * adds customer to the arraylist of customers of table
      * @param customer
      */
    public void sitCustomer(Customer customer){
        if (this.customers.size() == 4){
            throw new RuntimeException("This table is full!");
        }

        if (this.customers.contains(customer)){
            throw new RuntimeException(customer.getName() + "is already seated here!");
        }

        this.customers.add(customer);
    }
    /**
     * removes customer from the arraylist of customers on table
     * @param customer
     */
    public void removeCustomer(Customer customer){
        if (this.customers == null){
            throw new RuntimeException("This table is empty!");
        }

        if (!this.customers.contains(customer)){
            throw new RuntimeException(customer.getName() + "is not at this table!");
        }

        this.customers.remove(customer);
    }
    /**
     * adds and returns the tips of the customers on the table
     * @return int total tip
     */
    public int totalTip(){
        if (this.customers.isEmpty()){
            throw new RuntimeException("This table is empty!");
        }
        for (Customer customer: this.customers){
            this.totalTip += customer.finalTip;
        }
        return this.totalTip;
    }
}
