/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mauriverti
 */
public class VM {
    
    private String      name;
    private Integer     id;
    private Integer     memory;
    private Integer     vcpu;
    private Character   state;
    private Double      time;
    
    public VM() {
        name = "";
        id = 0;
        memory = 0;
        vcpu = 0;
        state = '0';
        time = 0.0;
    }
    
    public VM(String name, Integer id, Integer memory, Integer vcpu, Character state, Double time) {
        this.name = name;
        this.id = id;
        this.memory = memory;
        this.vcpu = vcpu;
        this.state = state;
        this.time = time;
    }
    
    public VM(String info) {
        String[]infos = info.split(" ");
        List<String> propriedades = new ArrayList<String>();
        
        for (int i =0; i < infos.length; i++) {
            if (!infos[i].isEmpty()) {
                propriedades.add(infos[i]);
            }
        }
        
        name = propriedades.get(0);
        id = Integer.valueOf(propriedades.get(1));
        memory = Integer.valueOf(propriedades.get(2));
        vcpu = Integer.valueOf(propriedades.get(3));
        state = propriedades.get(4).replace("-", "").isEmpty() ? '-' : propriedades.get(4).replace("-", "").charAt(0);      // retorna o char do status, ou um - se nao tiver status
        time = Double.valueOf(propriedades.get(5));
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemory() {
        return memory;
    }

    public void setMemory(Integer memory) {
        this.memory = memory;
    }

    public Integer getVcpu() {
        return vcpu;
    }

    public void setVcpu(Integer vcpu) {
        this.vcpu = vcpu;
    }

    public Character getState() {
        return state;
    }

    public void setState(Character state) {
        this.state = state;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }
    
}

