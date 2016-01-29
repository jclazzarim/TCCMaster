/**
 * @author mauriverti
 */
package tcc;

import java.io.BufferedReader;
import java.io.FileReader;
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
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(300, 240));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        btnCreateVM.setText("Criar VM");
        btnCreateVM.setPreferredSize(new java.awt.Dimension(100, 25));
        btnCreateVM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateVMActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancelar");
        btnCancel.setPreferredSize(new java.awt.Dimension(100, 25));
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
                        .addComponent(btnCreateVM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelIP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vmIP, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE))
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
            .addGroup(layout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreateVM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
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
        
//        String diskPath = createImg(vmName.getText());

        String config = " sudo  xen-create-image " +
        " --hostname " + vmName.getText() +
        " --ip 192.168.122." + vmIP.getValue().toString() +
        " --nopasswd" +
        " --dir /home/server/Documentos/magica/hds" +
        " --force " +
        " --install-method=copy " +
        " --install-source=/home/server/Documentos/magica/IMAGEM_OFICIAL/montada/ " +
        " --noswap " +
        " --genpass=0 " +
        " --memory=" + vmMem.getValue().toString() + "MB" +
        " --maxmem=" + vmMemMax.getValue().toString() + "MB" +
        " --vcpus=" + vmVCPU.getValue().toString() + " ";

        valores.setParam(
                vmName.getText(),
                vmIP.getValue().toString(),
                vmVCPU.getValue().toString(),
                vmVCPUMax.getValue().toString(),
                vmMem.getValue().toString(),
                vmMemMax.getValue().toString()
        );
        
//        CRIAR ARQUIVO DE CONFIGURACAO DA VM
        createConfig(config);
        
//        ADICIONA AO ARQUIVO O NUMERO DE VCPU MAXIMO
        alterarConfig(vmName.getText());

//        INICIA A VM
        startVM(vmName.getText());
        
        this.dispose();

    }//GEN-LAST:event_btnCreateVMActionPerformed

    private Boolean isConfigOk() {
        if (vmName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "É necessário informar um nome para a VM");
            return false;
        }

        return true;
    }

    private void startVM(String hostname) {

        String command;
        command = "sudo xl create " + path + "configs/" + hostname + ".cfg";

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
    private javax.swing.Box.Filler filler1;
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

    private void createConfig(String config) {
        
        try {
            Process proc = Runtime.getRuntime().exec(config);

            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));

            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            proc.waitFor();
            atualizaTela.run();
        } catch (IOException | InterruptedException e) {
            System.out.println("Erro em criar arquivo de configuracao da VM");
            e.printStackTrace();
        }
    }

    private void alterarConfig(String vmName) {
        
        String filePath = "/etc/xen/" + vmName + ".cfg";

        String fileText = "";
        BufferedReader br;
        
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader(filePath));
            while ((sCurrentLine = br.readLine()) != null) {
                fileText += sCurrentLine + "\n";
            }
            
            
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        fileText += "\nmaxvcpus = " + vmVCPUMax.getValue().toString();
        
//        File f = new File(filePath);
        try (PrintWriter out = new PrintWriter(path + "configs/" + vmName + ".cfg")) {
            
            out.print(fileText);
            out.flush();
            out.close();
            
        } catch (Exception e) {
            System.out.println("Impossivel criar arquivo de configuracoes");
            e.printStackTrace();
        }
    }
}