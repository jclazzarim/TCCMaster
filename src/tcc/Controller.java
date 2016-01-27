/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author mauriverti
 */
public class Controller {

    private VM server;
    List<VM> vms;

//    public void atualizar() {
//        String command;
//        command = "sudo xl list";
//
//        Process proc;
//        try {
//            proc = Runtime.getRuntime().exec(command);
//
//            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
//
//            String line;
//            List<String> vmsInfo = new ArrayList<>();
//
//            while ((line = reader.readLine()) != null) {
//                vmsInfo.add(line);
//            }
//
//            proc.waitFor();
//
//            List<String[]> infoVM = new ArrayList<>();
//            for (int i =1 ; i < vmsInfo.size(); i++) {
//                String s = vmsInfo.get(i).replace("|", " ");
//                String[] str = s.split(" ");
//                
//                if (str[0].equals())
//                
//                String[] strClear = new String[6];
//                int pos = 0;
//                for (String str1 : str) {
//                    if (!str1.isEmpty()) {
//                        strClear[pos] = str1;
//                        pos++;
//                    }
//                }
//                    
//                infoVM.add(strClear);
//                
//            }
//                int a =0;
//            
//            
//            
//            server = new VM(vmsInfo.get(1));
//
//            vms = new ArrayList<>();
//            for (int i = 2; i < vmsInfo.size(); i++) {
//                VM vm = new VM(vmsInfo.get(i));
//                vms.add(vm);
//            }
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//            System.out.println("Erro ao tentar Atualizar");
//        }
//    }
    
    
    
    public void atualizar() {
        String command;
        command = "sudo xl list";

        Process proc;
        try {
            proc = Runtime.getRuntime().exec(command);

            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));

            String line;
            List<String> vmsInfo = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                vmsInfo.add(line);
            }

            proc.waitFor();

            server = new VM(vmsInfo.get(1));

            vms = new ArrayList<>();
            for (int i = 2; i < vmsInfo.size(); i++) {
                VM vm = new VM(vmsInfo.get(i));
                vms.add(vm);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("Erro ao tentar Atualizar");
        }
    }
    
    

    public void atualizaLista(JList l) {
        atualizar();

        DefaultListModel model = new DefaultListModel();
        l.setModel(model);      // limpa os campos, se ja possuir algum

        model.addElement(server.getId().toString() + " - " + server.getName());
        l.setModel(model);
        if (vms != null) {
            for (int i = 0; i < vms.size(); i++) {
                model.addElement(vms.get(i).getId().toString() + " - " + vms.get(i).getName());
                l.setModel(model);
            }
        }

    }

    public void atualizarDstat() {
        String command;
        command = "sudo dstat -c -m -t 1 2";

        Process proc;
        try {
            proc = Runtime.getRuntime().exec(command);

            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));

            String line = "";
            List<String> vmsInfo = new ArrayList<String>();

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                vmsInfo.add(line);
            }

            proc.waitFor();

            server = new VM(vmsInfo.get(1));

            vms = new ArrayList<VM>();
            for (int i = 2; i < vmsInfo.size(); i++) {
                VM vm = new VM(vmsInfo.get(i));
                vms.add(vm);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao tentar Atualizar");
        }
    }

    void atualizaListaFake(JList vmList, HashMap<String, Runnable> mapThreads) {
        DefaultListModel model = new DefaultListModel();

        mapThreads.entrySet().stream().forEach((entry) -> {
            model.addElement(entry.getValue());
        });

        vmList.setModel(model);
    }
}
