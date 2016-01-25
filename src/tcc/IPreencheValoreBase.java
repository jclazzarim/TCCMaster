/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc;

/**
 *
 * @author server
 */
@FunctionalInterface
public interface IPreencheValoreBase {
    
    public void setParam(
            String name,
            String ip,
            String vcpu,
            String maxVCPU,
            String memory,
            String maxMemory
    );
    
}
