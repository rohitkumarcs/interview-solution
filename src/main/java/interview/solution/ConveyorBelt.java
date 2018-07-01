package interview.solution;

import interview.solution.domain.RawMaterial;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ConveyorBelt {

    private AtomicInteger noOfBolts;
    private AtomicInteger noOfMachines;
    private AtomicInteger machineCountToVerify;
    private AtomicInteger totalProduct = new AtomicInteger(0);
    private Map<Long, RawMaterial> countToAssemble = new HashMap<Long, RawMaterial>();
    private Integer totalTimeElapsed;
    private boolean isBolts = true;
    private Integer noOfEmployees = 3;

    /**
     *
     * @param noOfMachines
     * @param noOfBolts
     * @return
     */
    public int totalProducts(Integer noOfMachines, Integer noOfBolts, Integer timeToFormProduct) {
        this.noOfMachines = new AtomicInteger(noOfMachines);
        this.machineCountToVerify = new AtomicInteger(noOfMachines);
        this.noOfBolts = new AtomicInteger(noOfBolts);

        long startTime = System.currentTimeMillis();
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < noOfEmployees; i++) {
            es.execute(new EmployeeThread(this, timeToFormProduct));
        }
        es.shutdown();

        while(!es.isTerminated()) {
            //Wait till all threads are terminated
        }
        long endTime = System.currentTimeMillis();
        totalTimeElapsed =  (int)(endTime-startTime)/1000;
        return totalProduct.get();
    }

    /**
     * actual total time in secs
     * @return
     */
    public int getTotalTimeElapsed() {
        return totalTimeElapsed;
    }

    public AtomicInteger getNoOfBolts() {
        return noOfBolts;
    }

    public AtomicInteger getNoOfMachines() {
        return noOfMachines;
    }

    public AtomicInteger getMachineCountToVerify() {
        return machineCountToVerify;
    }

    public AtomicInteger getTotalProduct() {
        return totalProduct;
    }

    public Map<Long, RawMaterial> getCountToAssemble() {
        return countToAssemble;
    }

    public boolean isBolts() {
        return isBolts;
    }

    public void setBolts(boolean bolts) {
        isBolts = bolts;
    }

    public Integer getNoOfEmployees() {
        return noOfEmployees;
    }

    public void setNoOfBolts(AtomicInteger noOfBolts) {
        this.noOfBolts = noOfBolts;
    }

    public void setNoOfMachines(AtomicInteger noOfMachines) {
        this.noOfMachines = noOfMachines;
    }
}
