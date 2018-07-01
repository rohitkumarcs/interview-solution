package interview.solution;

import interview.solution.domain.RawMaterial;
import java.util.concurrent.atomic.AtomicInteger;

public class EmployeeThread extends Thread {

    private ConveyorBelt belt;
    private int timeToFormProduct;

    public EmployeeThread(ConveyorBelt belt, int timeToFormProduct) {
        this.belt = belt;
        this.timeToFormProduct = timeToFormProduct;
    }

    public void run() {
        try {
            while ((belt.noOfBolts.get() + belt.noOfMachines.get() != 0)){
                pickMaterial();
                if((belt.machineCountToVerify.get() == belt.totalProduct.get())){
                    belt.noOfBolts = new AtomicInteger(0);
                    belt.noOfMachines = new AtomicInteger(0);
                }
            }

        } catch (Exception e) {
            // Throwing an exception
            System.out.println("Exception is caught");
        }
    }

    private synchronized void pickMaterial() throws InterruptedException {
        long threadCount = Thread.currentThread().getId();
        RawMaterial material = belt.countToAssemble.get(threadCount);
        if(material == null){
            material = new RawMaterial();
            belt.countToAssemble.put(threadCount, material);
        }
        if(belt.isBolts || (belt.noOfMachines.get() == 0 && belt.noOfBolts.get() != 0)){
            if(material.modifyBolt()){
                belt.noOfBolts.decrementAndGet();
            } else {
                belt.noOfBolts.incrementAndGet();
            }

            belt.isBolts = false;
        } else if (belt.noOfMachines.get() != 0){
            belt.isBolts = true;
            if(material.modifyMachine()){
                belt.noOfMachines.decrementAndGet();
            } else {
                belt.noOfMachines.incrementAndGet();
            }

        }
        if(material.isRawMaterialComplete()){
            belt.totalProduct.incrementAndGet();
            Thread.sleep(timeToFormProduct * 1000);
        }
    }

}
