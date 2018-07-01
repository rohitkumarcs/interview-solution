package interview.solution;

import interview.solution.domain.RawMaterial;
import java.util.concurrent.atomic.AtomicInteger;

public class EmployeeThread extends Thread {

    private ConveyorBelt belt;
    private long timeToFormProduct;

    public EmployeeThread(ConveyorBelt belt, long timeToFormProduct) {
        this.belt = belt;
        this.timeToFormProduct = timeToFormProduct;
    }

    @Override
    public void run() {
        try {
            while ((belt.getNoOfBolts().get() + belt.getNoOfMachines().get() != 0)){
                pickMaterial();
                if((belt.getMachineCountToVerify().get() == belt.getTotalProduct().get())){
                    belt.setNoOfBolts(new AtomicInteger(0));
                    belt.setNoOfMachines(new AtomicInteger(0));
                }
            }

        } catch (Exception e) {
            // Throwing an exception
            System.out.println("Exception is caught");
        }
    }

    private synchronized void pickMaterial() throws InterruptedException {
        long threadCount = Thread.currentThread().getId();
        RawMaterial material = belt.getCountToAssemble().get(threadCount);
        if(material == null){
            material = new RawMaterial();
            belt.getCountToAssemble().put(threadCount, material);
        }
        if(belt.isBolts() || (belt.getNoOfMachines().get() == 0 && belt.getNoOfBolts().get() != 0)){
            if(material.modifyBolt()){
                belt.getNoOfBolts().decrementAndGet();
            } else {
                belt.getNoOfBolts().incrementAndGet();
            }

            belt.setBolts(false);
        } else if (belt.getNoOfMachines().get() != 0){
            belt.setBolts(true);
            if(material.modifyMachine()){
                belt.getNoOfMachines().decrementAndGet();
            } else {
                belt.getNoOfMachines().incrementAndGet();
            }

        }
        if(material.isRawMaterialComplete()){
            belt.getTotalProduct().incrementAndGet();
            Thread.sleep(timeToFormProduct * 1000);
        }
    }

}
