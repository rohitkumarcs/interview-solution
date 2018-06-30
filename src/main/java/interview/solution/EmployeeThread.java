package interview.solution;

import interview.solution.domain.RawMaterial;
import java.util.concurrent.atomic.AtomicInteger;
import static interview.solution.ConveyorBelt.*;

public class EmployeeThread extends Thread {

    public void run() {
        try {
            while ((noOfBolts.get() + noOfMachines.get() != 0)){
                pickMaterial();
                if((machineCountToVerify.get() == totalProduct.get())){
                    noOfBolts = new AtomicInteger(0);
                    noOfMachines = new AtomicInteger(0);
                }
            }

        } catch (Exception e) {
            // Throwing an exception
            System.out.println("Exception is caught");
        }
    }

    private synchronized void pickMaterial() {
        long threadCount = Thread.currentThread().getId();
        RawMaterial material = countToAssemble.get(threadCount);
        if(material == null){
            material = new RawMaterial();
            countToAssemble.put(threadCount, material);
        }
        if(isBolts || (noOfMachines.get() == 0 && noOfBolts.get() != 0)){
            if(material.modifyBolt()){
                noOfBolts.decrementAndGet();
            } else {
                noOfBolts.incrementAndGet();
            }

            isBolts = false;
        } else if (noOfMachines.get() != 0){
            isBolts = true;
            if(material.modifyMachine()){
                noOfMachines.decrementAndGet();
            } else {
                noOfMachines.incrementAndGet();
            }

        }
        if(material.isRawMaterialComplete()){
            ConveyorBelt.totalProduct.incrementAndGet();
        }

    }

}
