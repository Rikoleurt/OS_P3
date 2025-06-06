/*
 * Operating Systems - Universite de Fribourg
 * 
 * Practical #3: an introduction to threads and synchronization in Java
 * 
 * Do not forget to indicate with comments inside the code the 
 * modifications you have made and what problems they fix or 
 * prevent, with references to the questions of the subject (Q1, Q2, etc.)
 */
package Q5.src;
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
        notify(); // Notify the thread that is waiting
    }

    /**
     * Removes (takes) food
     */
    public synchronized void get() throws InterruptedException {
        // Avoid negative values in stock C
        while (nbFood ==  0) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted" + e.getMessage());
                e.printStackTrace();
            }
        }
        nbFood--;

    }

    /**
     * Display the stock status
     */
    public String display() {
        return name + " contains " + nbFood + " food";
    }

    public String getName() {
        return name;
    }
}
