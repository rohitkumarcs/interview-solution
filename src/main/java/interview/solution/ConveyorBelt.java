package interview.solution;

import interview.solution.domain.RawMaterial;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ConveyorBelt {

    public static AtomicInteger noOfBolts;
    public static AtomicInteger noOfMachines;
    public static AtomicInteger machineCountToVerify;
    public static AtomicInteger totalProduct = new AtomicInteger(0);
    public static Map<Long, RawMaterial> countToAssemble = new HashMap<Long, RawMaterial>();

    public static boolean isBolts = true;

    private Integer noOfEmployees = 3;
    public static void main(String[] args) {
        ConveyorBelt belt = new ConveyorBelt();
        belt.totalProducts(3, 6);
    }

    /**
     *
     * @param noOfMachines
     * @param noOfBolts
     * @return
     */
    public int totalProducts(Integer noOfMachines, Integer noOfBolts) {
        this.noOfMachines = new AtomicInteger(noOfMachines);
        this.machineCountToVerify = new AtomicInteger(noOfMachines);
        this.noOfBolts = new AtomicInteger(noOfBolts);

        ExecutorService es = Executors.newCachedThreadPool();
        for(int i=0;i<3;i++)
            es.execute(new EmployeeThread());
        es.shutdown();

        while(!es.isTerminated()) {
        }
        return totalProduct.get();
    }


    public int timeConsumedToClearAllProductsInSec(Integer noOfProducts, Integer timeToAssembleAProduct) {
        return noOfProducts * timeToAssembleAProduct;
    }
}
