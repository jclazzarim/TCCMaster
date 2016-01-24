/**
 * @author mauriverti
 */
package tcc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;

public class CreateVM extends javax.swing.JFrame {

//    private final String path = "/home/mauriverti/Documentos/magica";
    private final String path = "/home/server/Documentos/magica/";
    private final Runnable atualizaTela;
    private final IPreencheValoreBase valores;

    public CreateVM(Runnable run, IPreencheValoreBase valores) {
        initComponents();

        SpinnerNumberModel smVCPUMax = new SpinnerNumberModel();
        smVCPUMax.setMinimum(1);
        smVCPUMax.setMaximum(32);
        smVCPUMax.setValue(2);
        vmVCPUMax.setModel(smVCPUMax);

        SpinnerNumberModel smVCPU = new SpinnerNumberModel();
        smVCPU.setMinimum(1);
        smVCPU.setMaximum(2);
        smVCPU.setValue(1);
        vmVCPU.setModel(smVCPU);

        SpinnerNumberModel smMemMax = new SpinnerNumberModel();
        smMemMax.setMinimum(512);
        smMemMax.setMaximum(32768);       // 32Gb
        smMemMax.setValue(2048);
        vmMemMax.setModel(smMemMax);

        SpinnerNumberModel smMem = new SpinnerNumberModel();
        smMem.setMinimum(512);
        smMem.setMaximum(2048);
        smMem.setValue(1024);
        vmMem.setModel(smMem);
        
        SpinnerNumberModel smIP = new SpinnerNumberModel();
        smIP.setMinimum(2);
        smIP.setMaximum(254);
        smIP.setValue(2);
        vmIP.setModel(smIP);
        
        
        this.atualizaTela = run;
        this.valores = valores;

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCreateVM = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        labelNome = new javax.swing.JLabel();
        labelVCPU = new javax.swing.JLabel();
        labelVCPUMax = new javax.swing.JLabel();
        labelMem = new javax.swing.JLabel();
        labelMemMax = new javax.swing.JLabel();
        vmName = new javax.swing.JTextField();
        vmVCPU = new javax.swing.JSpinner();
        vmVCPUMax = new javax.swing.JSpinner();
        vmMem = new javax.swing.JSpinner();
        vmMemMax = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        labelIP = new javax.swing.JLabel();
        vmIP = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(300, 225));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        btnCreateVM.setText("Criar VM");
        btnCreateVM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateVMActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancelar");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        labelNome.setText("Nome:");

        labelVCPU.setText("VCPU:");

        labelVCPUMax.setText("VCPU Máximo:");

        labelMem.setText("Memória:");

        labelMemMax.setText("Memória Máxima:");

        vmVCPU.setModel(new javax.swing.SpinnerNumberModel(1, 1, 32, 1));

        vmVCPUMax.setValue(vmVCPU.getNextValue());
        vmVCPUMax.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                vmVCPUMaxStateChanged(evt);
            }
        });

        vmMemMax.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                vmMemMaxStateChanged(evt);
            }
        });

        jLabel1.setText("Mb");

        jLabel2.setText("Mb");

        labelIP.setText("IP:            192.168.122.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelIP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vmIP))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnCreateVM)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                        .addComponent(btnCancel))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelNome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vmName))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelVCPU)
                        .addGap(18, 18, 18)
                        .addComponent(vmVCPU))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelVCPUMax)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vmVCPUMax))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelMemMax)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vmMemMax))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelMem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vmMem)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNome)
                    .addComponent(vmName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelVCPU)
                    .addComponent(vmVCPU))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelVCPUMax)
                    .addComponent(vmVCPUMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMem)
                    .addComponent(vmMem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMemMax)
                    .addComponent(vmMemMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelIP)
                    .addComponent(vmIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreateVM)
                    .addComponent(btnCancel))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void vmVCPUMaxStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_vmVCPUMaxStateChanged

        SpinnerNumberModel smVCPU = new SpinnerNumberModel();
        smVCPU.setMinimum(1);
        Integer maxAtual = new Integer(vmVCPUMax.getValue().toString());
        Integer cpuAtual = new Integer(vmVCPU.getValue().toString());
        smVCPU.setMaximum(maxAtual);
        if (maxAtual >= cpuAtual) {
            smVCPU.setValue(vmVCPU.getValue());         // usado pra manter o valor atual; eh gambiarra mas eh assim
        } else {
            smVCPU.setValue(maxAtual);
        }

        vmVCPU.setModel(smVCPU);


    }//GEN-LAST:event_vmVCPUMaxStateChanged

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        this.dispose();
    }//GEN-LAST:event_formWindowClosed

    private void vmMemMaxStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_vmMemMaxStateChanged

        SpinnerNumberModel smMem = new SpinnerNumberModel();
        smMem.setMinimum(512);
        Integer maxAtual = new Integer(vmMemMax.getValue().toString());
        Integer cpuAtual = new Integer(vmMem.getValue().toString());
        smMem.setMaximum(maxAtual);
        if (maxAtual >= cpuAtual) {
            smMem.setValue(vmMem.getValue());         // usado pra manter o valor atual; eh gambiarra mas eh assim
        } else {
            smMem.setValue(maxAtual);
        }

        vmMem.setModel(smMem);

    }//GEN-LAST:event_vmMemMaxStateChanged

    private void btnCreateVMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateVMActionPerformed

        if (!isConfigOk()) {
            return;
        }
        
        String diskPath = createImg(vmName.getText());

        String config = "";

        config += "name = \"" + vmName.getText() + "\" \n";
        config += "bootloader = \"pygrub\" \n";
        config += "vcpus = " + vmVCPU.getValue().toString() + " \n";
        config += "maxvcpus = " + vmVCPUMax.getValue().toString() + " \n";
        config += "memory = " + vmMem.getValue().toString() + " \n";
        config += "maxmem = " + vmMemMax.getValue().toString() + " \n";
        config += "disk = [ '" + diskPath + ",,xvda'] \n";
        config += "vif = [ 'bridge=virbr0' ]";

        valores.setParam(vmVCPUMax.getValue().toString(), vmMemMax.getValue().toString(), vmIP.getValue().toString());
        System.out.println(config);
//        SALVAR ARQUIVO
        salvaConfig(config);

//        ARRUAMR START
        startVM();
        
        this.dispose();

    }//GEN-LAST:event_btnCreateVMActionPerformed

    private Boolean isConfigOk() {
        if (vmName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "É necessário informar um nome para a VM");
            return false;
        }

        return true;
    }
    
     private String createImg(String name) {
        
        String vmName = "vm";
        String vmExtension = ".img";
        
        String vmPath = path + vmName + name + vmExtension;
        
        String command = "cp " + path + vmName + vmExtension + " " + vmPath;

        Process proc;
        try {
            proc = Runtime.getRuntime().exec(command);

            proc.waitFor();
        } catch (IOException | InterruptedException e) {
            System.out.println("Erro em create new VM");
            e.printStackTrace();
        }
        
        return vmPath;
    }


    private void salvaConfig(String config) {
        try (PrintWriter out = new PrintWriter(path + "/vmConfig.cfg")) {
            
            out.print(config);
            out.flush();
            
        } catch (Exception e) {
            System.out.println("Impossivel criar arquivo de configuracoes");
            e.printStackTrace();
        }
    }

    private void startVM() {

        String command;
        command = "sudo xl create " + path + "/vmConfig.cfg";

        Process proc;
        try {
            proc = Runtime.getRuntime().exec(command);

            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));

            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            proc.waitFor();
            atualizaTela.run();
        } catch (IOException | InterruptedException e) {
            System.out.println("Erro em create new VM");
            e.printStackTrace();
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCreateVM;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel labelIP;
    private javax.swing.JLabel labelMem;
    private javax.swing.JLabel labelMemMax;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelVCPU;
    private javax.swing.JLabel labelVCPUMax;
    private javax.swing.JSpinner vmIP;
    private javax.swing.JSpinner vmMem;
    private javax.swing.JSpinner vmMemMax;
    private javax.swing.JTextField vmName;
    private javax.swing.JSpinner vmVCPU;
    private javax.swing.JSpinner vmVCPUMax;
    // End of variables declaration//GEN-END:variables
}
