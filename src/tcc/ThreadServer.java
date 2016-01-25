/**
 * @author mauriverti
 */
package tcc;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class ThreadServer extends Thread {

    private final Socket client;

//    private final String vmName;
//    private final String vmIP;
//    private final String vmMaxMemory;
//    private final String vmMaxVCPU;
    private final Queue<VMData> datas = new LinkedList<>();
    private VMData dataActual;
    private String entrada = "";
    private static final int TAM_FILA = 60;

    public ThreadServer(Socket accept/*, String vmName, String vmIP, String vmMaxMemory, String vmMaxVCPU*/) {
        this.client = accept;
        /*this.vmName = vmName;
        this.vmIP = vmIP;
        this.vmMaxMemory = vmMaxMemory;
        this.vmMaxVCPU = vmMaxVCPU;*/
    }

    @Override
    public void run() {
        try {
            System.out.println("Conexao com o cliente:" + client.getInetAddress().getHostAddress());

            ServerSocket nSocket = new ServerSocket();
            nSocket.bind(null);
            PrintStream sClient = new PrintStream(client.getOutputStream());
            sClient.println(nSocket.getLocalPort());
            final Socket accept = nSocket.accept();
            client.close();
            Scanner scanner = new Scanner(accept.getInputStream());
            if (scanner.hasNextLine()) {
                entrada = scanner.nextLine().split("->")[0];
            }

//                if (!entrada.isEmpty() && !connections.contains(entrada)) {
//                    connections.add(entrada);
//                    Infos vms = new Infos();
//                    vms.printInfos(client);
//                }
            PrintStream saida = new PrintStream(accept.getOutputStream());
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                line = line.replace("|", " ");
                final String[] fullSplit = line.split(" ");

                List<String> cleanList = new ArrayList<>();
                for (String string : fullSplit) {
                    if (!string.trim().isEmpty()) {
                        cleanList.add(string);
                    }
                }

                VMData data = new VMData();
                data.setDomain(getEntrada());
                data.setCpuLivre(cleanList.get(3));

                data.setMemUsada(cleanList.get(7));
                data.setMemLivre(cleanList.get(10));

                this.dataActual = data;
                getDatas().add(data);
                if (getDatas().size() > TAM_FILA) {
                    getDatas().poll();
                }
                saida.println(true);

            }
            scanner.close();
        } catch (IOException ex) {
        }
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
        return Integer.parseInt(this.dataActual.getCpuLivre());
    }
}
