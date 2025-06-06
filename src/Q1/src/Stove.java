package Q1.src;/*
 * Operating Systems - Universite de Fribourg
 * 
 * Practical #3: an introduction to threads and synchronization in Java
 * 
 * Do not forget to indicate with comments inside the code the 
 * modifications you have made and what problems they fix or 
 * prevent, with references to the questions of the subject (Q1, Q2, etc.)
 */

/**
 * Objects that are instances of the Stove class represent stoves to prepare
 * food. The principle is as follows: the call to prepare() picks an element
 * from Stock A, waits for 64 ms, and puts an element to stock B. The work() method
 * runs nbPrepare times the prepare() method, nbPrepare being set by the constructor.
 */
class Stove extends Thread {

	/**
	 * The initial stock
	 */
    private Stock A;
    /**
     * The stock where to put prepared food
     */
    private Stock B;
    /** 
     * Number of preparations when calling work().
     */
    private int nbPrepare;

    /**
     * Constructs an instance of Stove
     * @param A Initial stock
     * @param B Destination stock
     * @param nbPrepare How many preparations should be made
     */
    public Stove(Stock A, Stock B, int nbPrepare) {
        this.A = A;
        this.B = B;
        this.nbPrepare = nbPrepare;
    }

    /**
     * Proceeds to a single preparation from food of Stock A to food of Stock B
     */
    public void prepare() {
        A.get();
        System.out.println(A.display()); // Display ongoing operations
        try { Thread.sleep(64); } catch(InterruptedException e) {}
        System.out.println(B.display()); // Display ongoing operations
        B.put();
    }

    /**
     * Proceeds to nbPrepare preparations
     */
    public void work() {
        /* The name of this method should really be changed to override a
         * central method of the Thread class, but which one...? 
         */
        for(; nbPrepare > 0; nbPrepare--)
            prepare();
    }

    @Override
    public void run() {
        work();
    }

    /** 
     * "Unit test" for class Stove (has no use for the rest of the lab!)
     * @param args not used
     */
    static public void main(String[] args) {
        Stock stockInput = new Stock("input", 4);
        Stock stockOutput = new Stock("output", 1);
        new Stove(stockInput, stockOutput, 2).work();
        stockInput.display();
        stockOutput.display();
    }
}
