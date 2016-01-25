/**
 * @author mauriverti
 */
package tcc;

public class VMSettings {
    
    String vmName = "";
    String vmIP = "";
    String vmVCPU = "";
    String vmMaxVCPU = "";
    String vmMemory = "";
    String vmMaxMemory = "";
    
    public VMSettings(
            String vmName,
            String vmIP,
            String vmVCPU,
            String vmMaxVCPU,
            String vmMemory,
            String vmMaxMemory) {
        
        this.vmName = vmName;
        this.vmIP = "192.168.122." + vmIP;
        this.vmVCPU = vmVCPU;
        this.vmMaxVCPU = vmMaxVCPU;
        this.vmMemory = vmMemory;
        this.vmMaxMemory = vmMaxMemory;
    }

    public String getVmName() {
        return vmName;
    }

    public void setVmName(String vmName) {
        this.vmName = vmName;
    }

    public String getVmIP() {
        return vmIP;
    }

    public void setVmIP(String vmIP) {
        this.vmIP = vmIP;
    }

    public String getVmVCPU() {
        return vmVCPU;
    }

    public void setVmVCPU(String vmVCPU) {
        this.vmVCPU = vmVCPU;
    }

    public String getVmMaxVCPU() {
        return vmMaxVCPU;
    }

    public void setVmMaxVCPU(String vmMaxVCPU) {
        this.vmMaxVCPU = vmMaxVCPU;
    }

    public String getVmMemory() {
        return vmMemory;
    }

    public void setVmMemory(String vmMemory) {
        this.vmMemory = vmMemory;
    }

    public String getVmMaxMemory() {
        return vmMaxMemory;
    }

    public void setVmMaxMemory(String vmMaxMemory) {
        this.vmMaxMemory = vmMaxMemory;
    }
    
    
    
    
    
}