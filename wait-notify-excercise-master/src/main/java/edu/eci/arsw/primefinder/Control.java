
package edu.eci.arsw.primefinder;

/**
 *
 */
public class Control extends Thread {
    
    private final static int NTHREADS = 3;
    private final static int MAXVALUE = 30000000;
    private final static int TMILISECONDS = 5000;

    private final int NDATA = MAXVALUE / NTHREADS;

    private PrimeFinderThread pft[];
    
    private Control() {
        super();
        this.pft = new  PrimeFinderThread[NTHREADS];

        int i;
        for(i = 0;i < NTHREADS - 1; i++) {
            PrimeFinderThread elem = new PrimeFinderThread(i*NDATA, (i+1)*NDATA);
            pft[i] = elem;
        }
        pft[i] = new PrimeFinderThread(i*NDATA, MAXVALUE + 1);
    }
    
    public static Control newControl() {
        return new Control();
    }

    @Override
    public void run() {
        for(int i = 0;i < NTHREADS;i++ ) {
            pft[i].start();
        }
    }
    public void hold(){
        for(PrimeFinderThread  thread: pft){
            thread.hold();
        }
    }
    public void restart(){
        for(PrimeFinderThread  thread: pft){
            thread.restart();
        }
    }


    public boolean threadsRunning() {
        int cont = 0;
        for(PrimeFinderThread  thread: pft){
            if(thread.isRunning()){
                cont++;
            }
        }
        return cont>0;
    }

    public void counter() {
        int counter_ = 0;
        for(int i = 0;i < NTHREADS;i++ ) {
            counter_ += pft[i].getPrimes().size();
        }
        System.out.println("Number of primes found: " + counter_);
    }
}
