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
    public void prepare() throws InterruptedException {
        A.get();
        System.out.println(Thread.currentThread().getName() + " : " + A.display() + ", " + B.display());
        try { Thread.sleep(64); } catch(InterruptedException e) {}
        B.put();
        System.out.println(Thread.currentThread().getName() + " : " + A.display() + ", " + B.display());
    }


    /**
     * Proceeds to nbPrepare preparations
     */
    public void work() throws InterruptedException {
        /* The name of this method should really be changed to override a
         * central method of the Thread class, but which one...? 
         */
        for(; nbPrepare > 0; nbPrepare--)
            prepare();
    }

    @Override
    public void run() {
        try {
            work();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
