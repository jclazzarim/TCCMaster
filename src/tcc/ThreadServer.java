/**
 * @author mauriverti
 */
package tcc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ThreadServer extends Thread {

    private final Socket client;

//    private final String vmName;
//    private final String vmIP;
//    private final String vmMaxMemory;
//    private final String vmMaxVCPU;
    private final Queue<VMData> datas = new LinkedList<>();
    private VMData dataActual;
    private String entrada = "";
    private static final int TAM_FILA = 5;
    public Integer memTotal;
    private boolean isVerticalEnable;
    private boolean isHorizontalEnable;
    private int memLimitMaxPercent;
    private int memQuantAloca;
    private int cpuLimitMaxPercent;
    private int cpuQuantAloca;
    private int memLimitMinPercent;
    private int memQuantDesaloca;
    private int cpuLimitMinPercent;
    private int cpuQuantDesaloca;
    
    private String vmFilhoName;
    private int vmFilhoIP;
    private int qtdVMFilhoAtual;
    private int qtdVMFilhoMax;

    public ThreadServer(Socket accept) {
        this.client = accept;
        qtdVMFilhoAtual = 0;
        qtdVMFilhoMax = 1;
    }

    @Override
    public void run() {
        try {
            String ipClient = client.getInetAddress().getHostAddress();
            this.vmFilhoIP = 201;
            System.out.println("Conexao com o cliente:" + ipClient);

            System.out.println(vmFilhoName + ": " + vmFilhoIP);
            ServerSocket nSocket = new ServerSocket();
            nSocket.bind(null);
            PrintStream sClient = new PrintStream(client.getOutputStream());
            sClient.println(nSocket.getLocalPort());
            final Socket accept = nSocket.accept();
            client.close();
            Scanner scanner = new Scanner(accept.getInputStream());
            if (scanner.hasNextLine()) {
                entrada = scanner.nextLine().split("->")[0];
                this.vmFilhoName = entrada + "-filho";
            }

//                if (!entrada.isEmpty() && !connections.contains(entrada)) {
//                    connections.add(entrada);
//                    Infos vms = new Infos();
//                    vms.printInfos(client);
//                }
            PrintStream saida = new PrintStream(accept.getOutputStream());
            while (scanner.hasNextLine()) {
                try {

                    String line = scanner.nextLine();
                    line = line.replace("|", " ");
                    final String[] fullSplit = line.split(" ");

                    List<String> cleanList = limpaArray(fullSplit);

                    VMData data = new VMData();
                    data.setDomain(getEntrada());
                    data.setCpuOcupada(100 - Integer.parseInt(cleanList.get(3)));

                    data.setMemLivre(cleanList.get(10));
                    data.setMemUsada(getPercentMemUsada(data.getMemLivre()));
 
                    this.dataActual = data;

                    getDatas().add(data);
                    if (getDatas().size() > TAM_FILA) {
                        getDatas().poll();
                    }

                    if (isVerticalEnable) {
                        elasticidadeVertical();
                    }else {
                        if (isHorizontalEnable) {
                            elasticidadeHorizontal();
                        }
                    }

                    saida.println(true);
                } catch (Exception e) {
                }

            }
            scanner.close();
        } catch (IOException ex) {
        }
    }
    
    public void elasticidadeHorizontal() {
        int sumCpu = 0;
        int sumMem = 0;
        for (VMData vmData : getDatas()) {
            sumCpu += vmData.getCpuOcupada();
            sumMem += vmData.getMemUsada();
        }
        List<String> xlList = executaXlList();

        if ((cpuLimitMaxPercent < (sumCpu / getDatas().size())
         || memLimitMaxPercent < (sumMem / getDatas().size()))
         && qtdVMFilhoAtual < qtdVMFilhoMax ) {
            
            Runnable avisaAlocacao = () -> {
                JOptionPane.showMessageDialog(null,"Alocando vm " + vmFilhoName);
            };
            
            Thread tAviso = new Thread (avisaAlocacao);
            tAviso.start();
            
            this.qtdVMFilhoAtual = 1;
            
            Runnable alocarNovaVM = () -> {
                alocarVM();
            };
            
            Thread tAloca = new Thread (alocarNovaVM);
            tAloca.start();
            
        } else if (qtdVMFilhoAtual > 0
             && cpuLimitMaxPercent > (sumCpu / getDatas().size())
             && memLimitMinPercent > (sumMem / getDatas().size())) {
            System.out.println("Desalocando " + vmFilhoName);
            desalocaVM(vmFilhoName);
        }   
    }
    
    private void desalocaVM(String vmName) {
        LocalTime inicio = LocalTime.now();
        
        if (vmName == null || vmName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma VM Selecionada");
            return;
        }

        if (vmName.equals("0")) {
            JOptionPane.showMessageDialog(null, "Não é possível remover Domain-0");
            return;
        }

        String command = "sudo xl shutdown " + vmName;

        try {
            Process proc = Runtime.getRuntime().exec(command);

        } catch (Exception e) {
            System.out.println("Erro em desligar VM");
            e.printStackTrace();
            return;
        }
        qtdVMFilhoAtual--;
        
        LocalTime fim = LocalTime.now();
        System.out.println("Tempo para desalocar vmFilho: " + (fim.toSecondOfDay()-inicio.toSecondOfDay()) + " segundos");
    }

    private void elasticidadeVertical() {
        LocalTime inicio = LocalTime.now();
        int sumCpu = 0;
        int sumMem = 0;
        for (VMData vmData : getDatas()) {
            sumCpu += vmData.getCpuOcupada();
            sumMem += vmData.getMemUsada();
        }
        List<String> xlList = executaXlList();

        int cpuAtual = Integer.parseInt(xlList.get(3));
        int memAtual = Integer.parseInt(xlList.get(2));

        if (cpuLimitMaxPercent < (sumCpu / getDatas().size())) {
            final String aloc = "sudo xl vcpu-set " + getEntrada() + " " + (cpuQuantAloca + cpuAtual);
            executaComando(aloc);
            System.out.println(aloc);
            
        } else if (cpuLimitMinPercent > (sumCpu / getDatas().size())) {
            final String desaloc = "sudo xl vcpu-set " + getEntrada() + " " + (cpuAtual - cpuQuantDesaloca);
            executaComando(desaloc);
            System.out.println(desaloc);
        }

        if (memLimitMaxPercent < (sumMem / getDatas().size())) {
            final String aloc = "sudo xl mem-set " + getEntrada() + " " + (memQuantAloca + memAtual);
            executaComando(aloc);
            System.out.println(aloc);
        } else if (memLimitMinPercent > (sumMem / getDatas().size())) {
            final String desaloc = "sudo xl mem-set " + getEntrada() + " " + (memAtual - memQuantDesaloca);
            executaComando(desaloc);
            System.out.println(desaloc);
        }
        LocalTime fim = LocalTime.now();
        System.out.println(" em " + (fim.toSecondOfDay()-inicio.toSecondOfDay()) + " segundos.");
    }

    private List<String> limpaArray(final String[] fullSplit) {
        List<String> cleanList = new ArrayList<>();
        for (String string : fullSplit) {
            if (!string.trim().isEmpty()) {
                cleanList.add(string);
            }
        }
        return cleanList;
    }

    public synchronized Queue<VMData> getDatas() {
        return datas;
    }

    public String getEntrada() {
        return entrada;
    }

    @Override
    public String toString() {
        return getEntrada();
    }

    synchronized Integer getActualCPU() {
        return this.dataActual.getCpuOcupada();
    }

    synchronized Integer getActualMemory() {
        return this.dataActual.getMemUsada();
    }

    public Integer getPercentMemUsada(String memLivreString) {
        List<String> listLimpa = executaXlList();
        Double memMax = Double.parseDouble(listLimpa.get(2));
        Double memLivre = Double.parseDouble(memLivreString.replaceAll("[a-zA-Z]", ""));
        Double d = ((memMax - memLivre) / memMax);
        Double x = (d * 100);
        return x.intValue();
    }

    private List<String> executaXlList() {
        try {
            String command = "sudo xl list";
            Process proc = executaComando(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            List<String> vmsInfo = new ArrayList<>();
            String line;

            reader.readLine();
            while ((line = reader.readLine()) != null) {
                vmsInfo.add(line);
            }

            proc.waitFor();
            for (String string : vmsInfo) {
                String[] split = string.split(" ");
                if (split[0].equals(getEntrada())) {
                    return limpaArray(split);
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    private void alocarVM() {
        
        LocalTime inicio = LocalTime.now();
        
        String config = " sudo  xen-create-image " +
        " --hostname " + this.vmFilhoName +
        " --ip 192.168.122." + this.vmFilhoIP +
        " --nopasswd" +
        " --dir /home/server/Documentos/magica/hds" +
        " --force " +
        " --install-method=copy " +
        " --install-source=/home/server/Documentos/magica/IMAGEM_OFICIAL/montada/ " +
        " --noswap " +
        " --genpass=0 " +
        " --memory=2048MB " +
        " --maxmem=4096MB " +
        " --vcpus=2 ";
          
        try {
            Process proc = Runtime.getRuntime().exec(config);

            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));

            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            proc.waitFor();
        } catch (IOException | InterruptedException e) {
            System.out.println("Erro em criar arquivo de configuracao da VM");
            e.printStackTrace();
            this.qtdVMFilhoAtual = 0;
            return;
        }
          
        
        String filePath = "/etc/xen/" + this.vmFilhoName + ".cfg";

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
            this.qtdVMFilhoAtual = 0;
            return;
        }
        
        fileText += "\nmaxvcpus = 4 ";
        
        try (PrintWriter out = new PrintWriter("/home/server/Documentos/magica/configs/" + this.vmFilhoName + ".cfg")) {
            
            out.print(fileText);
            out.flush();
            out.close();
            
        } catch (Exception e) {
            System.out.println("Impossivel criar arquivo de configuracoes");
            e.printStackTrace();
            this.qtdVMFilhoAtual = 0;
            return;
        }
          
        
        
        String command;
        command = "sudo xl create /home/server/Documentos/magica/configs/" + this.vmFilhoName + ".cfg";

        Process proc;
        try {
            proc = Runtime.getRuntime().exec(command);

            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));

            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            proc.waitFor();
        } catch (IOException | InterruptedException e) {
            System.out.println("Erro em create new VM");
            e.printStackTrace();
            this.qtdVMFilhoAtual = 0;
            return;
        }
        this.qtdVMFilhoAtual=1;
        
        LocalTime fim = LocalTime.now();
        System.out.println(vmFilhoName + " levou " + (fim.toSecondOfDay()-inicio.toSecondOfDay()) + " segundos para iniciar");
    }

    private Process executaComando(String command) {
        try {
            Process proc = Runtime.getRuntime().exec(command);
            return proc;
        } catch (IOException ex) {
            Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    void setVertical(boolean isVerticalEnable) {
        this.isVerticalEnable = isVerticalEnable;
    }
    
    
    void setHorizontal(boolean isHorizontalEnable) {
        this.isHorizontalEnable = isHorizontalEnable;
    }
    
    public Boolean getHorizontal() {
        return this.isHorizontalEnable;
    }
    
    void setAlocaVM(String memLimitMaxPercent, String vcpuLimitMaxPercent) {
        this.memLimitMaxPercent = Integer.parseInt(memLimitMaxPercent);
        this.cpuLimitMaxPercent = Integer.parseInt(vcpuLimitMaxPercent);
    }
    
    void setDesalocaVM(String memLimitMinPercent, String vcpuLimitMinPercent) {
        this.memLimitMinPercent = Integer.parseInt(memLimitMinPercent);
        this.cpuLimitMinPercent = Integer.parseInt(vcpuLimitMinPercent);
    }

    void setAlocarMemoria(String memLimitMaxPercent, String memQuantAloca) {
        this.memLimitMaxPercent = Integer.parseInt(memLimitMaxPercent);
        this.memQuantAloca = Integer.parseInt(memQuantAloca);
    }

    void setAlocarCPU(String cpuLimitMaxPercent, String cpuQuantAloca) {
        this.cpuLimitMaxPercent = Integer.parseInt(cpuLimitMaxPercent);
        this.cpuQuantAloca = Integer.parseInt(cpuQuantAloca);
    }

    void setDesalocarMemoria(String memLimitMinPercent, String memQuantDesaloca) {
        this.memLimitMinPercent = Integer.parseInt(memLimitMinPercent);
        this.memQuantDesaloca = Integer.parseInt(memQuantDesaloca);
    }

    void serDesalocarCPU(String cpuLimitMinPercent, String cpuQuantDesaloca) {
        this.cpuLimitMinPercent = Integer.parseInt(cpuLimitMinPercent);
        this.cpuQuantDesaloca = Integer.parseInt(cpuQuantDesaloca);
    }
    
//    void setMaxVms(Integer qtdMax) {
//        qtdVMFilhoMax = qtdMax;
//    }
}