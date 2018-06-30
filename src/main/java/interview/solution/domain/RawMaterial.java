package interview.solution.domain;

public class RawMaterial {
    private Integer boltCount = 0;
    private Integer machineCount = 0;

    public boolean modifyBolt(){
        if(boltCount == 2){
            return  false;
        }
        boltCount++;
        return true;
    }

    public boolean modifyMachine(){
        if(machineCount == 1){
            return  false;
        }
        machineCount++;
        return true;
    }

    public boolean isRawMaterialComplete(){
        if(boltCount == 2 && machineCount == 1){
            boltCount = 0;
            machineCount = 0;
            return true;
        }
        return false;
    }
}
