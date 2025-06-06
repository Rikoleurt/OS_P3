/*
 * Operating Systems - Universite de Fribourg
 * 
 * Practical #3: an introduction to threads and synchronization in Java
 * 
 * Do not forget to indicate with comments inside the code the 
 * modifications you have made and what problems they fix or 
 * prevent, with references to the questions of the subject (Q1, Q2, etc.)
 */
package Q2.src;
/**
 * Objects of class Stock represent a set of food. Food is not effectively stored,
 * only a counter is used to represent how much food is available.
 * 
 * It could be possible to use a more realistic queue (FIFO) for the Stock representation.
 * This is left as an exercise for home work. *
 */
class Stock {
	/**
	 * Amount of food
	 */
    private int nbFood;
    /**
     * Name of the stock
     */
    private String name;

    /**
     * Creates a new Stock object
     * @param name its name
     * @param nbFood initial number of food
     */
    public Stock(String name, int nbFood) {
        this.nbFood = nbFood;
        this.name = name;		
    }

    /**
     * Adds food
     */
    public synchronized void put() {
        nbFood++;
    } // synchronized keyword to correct concurrency issues

    /**
     * Removes (takes) food
     */
    public synchronized void get() {
        nbFood--;
    } // synchronized keyword to correct concurrency issues

    /**
     * Display the stock status
     */
    public String display() {
        return "The stock " + name + " contains " + nbFood + " food.";
    }

    /** 
     * "Unit test" for class Stock
     * @param args not used
     */
    static public void main(String[] args) {
        Stock stock = new Stock("test", 5);
        stock.put();
        stock.display();
        stock.get();
        stock.display();
    }
}
