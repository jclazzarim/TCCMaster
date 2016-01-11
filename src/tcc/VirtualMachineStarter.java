/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mauriverti
 */
public class VirtualMachineStarter extends Thread {

    private Integer cpuAtual;
    private Integer memoAtual;

    private Integer cpuMin;
    private Integer memoMin;

    private Integer cpuMax;
    private Integer memoMax;
    private Consumer<Integer> cpuListener;
    private Consumer<Integer> memoListener;

    @Override
    public void run() {
        final ThreadLocalRandom random = ThreadLocalRandom.current();

        cpuMin = random.nextInt(1, 8);
        cpuMax = random.nextInt(cpuMin + 1, 9);

        memoMin = random.nextInt(512, 1024);
        memoMax = random.nextInt(memoMin + 1, 2048);

        while (true) {
            try {
                cpuAtual = random.nextInt(cpuMin, cpuMax);
                memoAtual = random.nextInt(memoMin, memoMax);
                if (cpuListener != null) {
                    cpuListener.accept(cpuAtual);
                }

                if (memoListener != null) {
                    memoListener.accept(memoAtual);
                }
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(VirtualMachineStarter.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        }

    }

    public void setCpuListener(Consumer<Integer> c) {
        this.cpuListener = c;
    }

    public void setMemoListener(Consumer<Integer> c) {
        this.memoListener = c;
    }

    public Integer getCpuAtual() {
        return cpuAtual;
    }

    public void setCpuAtual(Integer cpuAtual) {
        this.cpuAtual = cpuAtual;
    }

    public Integer getMemoAtual() {
        return memoAtual;
    }

    public void setMemoAtual(Integer memoAtual) {
        this.memoAtual = memoAtual;
    }

    public Integer getCpuMin() {
        return cpuMin;
    }

    public void setCpuMin(Integer cpuMin) {
        this.cpuMin = cpuMin;
    }

    public Integer getMemoMin() {
        return memoMin;
    }

    public void setMemoMin(Integer memoMin) {
        this.memoMin = memoMin;
    }

    public Integer getCpuMax() {
        return cpuMax;
    }

    public void setCpuMax(Integer cpuMax) {
        this.cpuMax = cpuMax;
    }

    public Integer getMemoMax() {
        return memoMax;
    }

    public void setMemoMax(Integer memoMax) {
        this.memoMax = memoMax;
    }

}
