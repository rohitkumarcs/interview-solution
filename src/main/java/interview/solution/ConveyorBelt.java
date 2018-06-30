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
        for (int i = 0; i < noOfEmployees; i++) {
            es.execute(new EmployeeThread());
        }
        es.shutdown();

        while(!es.isTerminated()) {
        }
        return totalProduct.get();
    }

    /**
     * Prefered not to have Thread.sleep instead we can calculate total time.
     * @param noOfProducts
     * @param timeToAssembleAProduct
     * @return
     */
    public int timeConsumedToClearAllProductsInSec(Integer noOfProducts, Integer timeToAssembleAProduct) {
        return noOfProducts * timeToAssembleAProduct;
    }
}
