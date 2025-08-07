public class Food{
    /** Food name */
    public String name;
    /** Food category from MAIN, DESSERT, DRINK */
    public FoodCategory category;
    /** boolean isVegan */
    private Boolean isVegan;
    /** boolean isVegetarian */
    private Boolean isVegetarian;
    /** boolean isSpicy */
    private Boolean isSpicy;
    /** boolean hasGluten */
    private Boolean hasGluten;
    /** boolean hasLacose */
    private Boolean hasLactose;
    /** boolean int price */
    public int price;

    // ADD INGREDIENT
    /**
     * constructor
     * @param string name
     * @param food category from FoodCategory
     * @param boolean isVegan, isVegetarian, isSpicy, hasGluten, hasLactose
     * @param int price
     */
    public Food(String name, FoodCategory category, boolean isVegan, boolean isVegetarian,
    boolean isSpicy, boolean hasGluten, boolean hasLactose, int price){
        this.name = name;
        this.category = category;
        this.isVegan = isVegan;
        this.isVegetarian = isVegetarian;
        this.isSpicy = isSpicy;
        this.hasGluten = hasGluten;
        this.hasLactose = hasLactose;
        this.price = price;
    }
     /**
      * accessor for food name
      * @return String name
      */
    public String getName(){
        return this.name;
    }
     /** accessor for category */
    public FoodCategory getCategory(){
        return this.category;
    }
     /**
     * accessor for whether it is vegan
     * @return boolean isVegan
     */
    public Boolean isVegan(){
        return this.isVegan;
    }
     /**
     * accessor for whether is is vegetarian
     * @return boolean isVegetarian
     */
    public Boolean isVegetarian(){
        return this.isVegetarian;
    }
     /**
     * accessor for whether is is spciy
     * @return boolean isSpicy
     */
    public Boolean isSpicy(){
        return this.isSpicy;
    }
      /**
     * accessor for whether it has gluten
     * @return boolean hasGluten
     */
    public Boolean hasGluten(){
        return this.hasGluten;
    }
     /**
     * accessor for whether it has lactose
     * @return boolean hasGluten
     */
    public Boolean hasLactose(){
        return this.hasLactose;
    }
     /**
     * accessor for price
     * @return int price
     */
    public int getPrice(){
        return this.price;
    }
     /**
      * print the price of food
      */
    public void print(){
        String output = this.name + "(";
        if (this.isVegan == true){
            output+="vegan, ";}
        if (this.isVegetarian == true){
            output+="vegetarian, ";}
        if (this.isSpicy == true){
            output+="spicy, ";} //FINISH
        System.out.println(output);
    }

    public static void main(String[] args) {
        Food ab = new Food ("ab", FoodCategory.DRINK, false, false, false, false, false, 2);
        ab.print();
    }
}
