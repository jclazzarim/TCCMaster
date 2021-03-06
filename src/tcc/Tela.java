/**
 * @author mauriverti
 */
package tcc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYSeries;

public class Tela extends javax.swing.JFrame {

    public static Integer portEntrada = 8910;
    public Map<String, ThreadServer> vms = new HashMap<>();
    public Map<String, VMSettings> vmsSettings = new HashMap<>();
    final static TimeSeries tsCPU = new TimeSeries("CPU", Second.class);
    final static TimeSeries tsMem = new TimeSeries("Memória", Second.class);
    private Thread timer;
    private XYPlot plot;

    public Tela() {
        initComponents();
        plotGraph();
        startHandShake();
        atualizar();
    }

    private void plotGraph() {
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(tsMem);
        dataset.addSeries(tsCPU);

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "", "Tempo", "% uso", dataset, true, true, false
        );

        plot = chart.getXYPlot();
        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setRange(0.0, 100.0);
        final ValueAxis domainAxis = plot.getDomainAxis();
        domainAxis.setAutoRange(true);
        domainAxis.setFixedAutoRange(60000.0);
        ChartPanel label = new ChartPanel(chart);
        this.pnlDesempenho.setLayout(new BoxLayout(pnlDesempenho,
                BoxLayout.LINE_AXIS));
        pnlDesempenho.removeAll();
        this.pnlDesempenho.add(label);
        this.pnlDesempenho.revalidate();
        this.pnlDesempenho.repaint();
    }

    private void startHandShake() {
        new Thread(() -> {
            int x = 0;
            try (ServerSocket server = new ServerSocket(portEntrada)) {
                while (true) {
                    System.out.println("Entrou handShake");
                    Socket client = server.accept();
                    System.out.println("Aceitou client");

                    ThreadServer vm = new ThreadServer(client);
                    vm.start();
                    Thread.sleep(1000);
                    vms.put(vm.getEntrada(), vm);
                    System.out.println(vm.getEntrada());
                }
            } catch (IOException ioE) {
                System.out.println("Problemas em criar socket Server, porta ocupada?");
            } catch (Exception e) {
                System.out.println("Problemas em criar socket Server");
            }
        }).start();
    }

    Controller controller = new Controller();
    JButton btn = new JButton();
    final Integer amostras = 50;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnNovo = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnAtualizar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        vmList = new javax.swing.JList();
        painel = new javax.swing.JTabbedPane();
        pnlDesempenho = new javax.swing.JPanel();
        pnlPropriedades = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfVmId = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        vcpuSpinner = new javax.swing.JSlider();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        memorySpinner = new javax.swing.JSlider();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        manualApplyChanges = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        hMemMaxLimit = new javax.swing.JTextField();
        hVcpuMaxLimit = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        horizontalCheckBox = new javax.swing.JCheckBox();
        jLabel17 = new javax.swing.JLabel();
        hMemMinLimit = new javax.swing.JTextField();
        hVcpuMinLimit = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        verticalCheckBox = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        vMemMaxLimit = new javax.swing.JTextField();
        vVcpuMaxLimit = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        memQtdAlocacao = new javax.swing.JTextField();
        vcpuQtdAlocacao = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        vMemMinLimit = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        memQtdDesalocacao = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        vVcpuMinLimit = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        vcpuQtdDesalocacao = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        automaticApplyChanges = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnAtualizar.setText("Atualizar");
        btnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarActionPerformed(evt);
            }
        });

        vmList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        vmList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                vmListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(vmList);

        javax.swing.GroupLayout pnlDesempenhoLayout = new javax.swing.GroupLayout(pnlDesempenho);
        pnlDesempenho.setLayout(pnlDesempenhoLayout);
        pnlDesempenhoLayout.setHorizontalGroup(
            pnlDesempenhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 628, Short.MAX_VALUE)
        );
        pnlDesempenhoLayout.setVerticalGroup(
            pnlDesempenhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 451, Short.MAX_VALUE)
        );

        painel.addTab("Desempenho", null, pnlDesempenho, "Exibe o desempenho da VM selecionada");

        jLabel1.setText("ID:");

        tfVmId.setEditable(false);
        tfVmId.setToolTipText("");
        tfVmId.setEnabled(false);
        tfVmId.setPreferredSize(new java.awt.Dimension(70, 27));

        jLabel4.setText("Min");

        vcpuSpinner.setMaximum(4);
        vcpuSpinner.setMinimum(1);
        vcpuSpinner.setValue(2);

        jLabel2.setText("VCPU:");

        jLabel3.setText("Max");

        jLabel5.setText("Max");

        memorySpinner.setMaximum(2048);
        memorySpinner.setMinimum(512);
        memorySpinner.setValue(2048);

        jLabel6.setText("Memória");

        jLabel7.setText("Min");

        manualApplyChanges.setText("Aplicar Alterações");
        manualApplyChanges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manualApplyChangesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlPropriedadesLayout = new javax.swing.GroupLayout(pnlPropriedades);
        pnlPropriedades.setLayout(pnlPropriedadesLayout);
        pnlPropriedadesLayout.setHorizontalGroup(
            pnlPropriedadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPropriedadesLayout.createSequentialGroup()
                .addGroup(pnlPropriedadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPropriedadesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)
                        .addGap(1, 1, 1)
                        .addComponent(vcpuSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addGroup(pnlPropriedadesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7)
                        .addGap(1, 1, 1)
                        .addComponent(memorySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5))
                    .addGroup(pnlPropriedadesLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(pnlPropriedadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlPropriedadesLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfVmId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnlPropriedadesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlPropriedadesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(manualApplyChanges, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(375, Short.MAX_VALUE))
        );
        pnlPropriedadesLayout.setVerticalGroup(
            pnlPropriedadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPropriedadesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPropriedadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfVmId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnlPropriedadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPropriedadesLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jLabel3))
                    .addGroup(pnlPropriedadesLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlPropriedadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(vcpuSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlPropriedadesLayout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel4)))))
                .addGap(49, 49, 49)
                .addGroup(pnlPropriedadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPropriedadesLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel5))
                    .addGroup(pnlPropriedadesLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlPropriedadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(memorySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlPropriedadesLayout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel7)))))
                .addGap(18, 18, 18)
                .addComponent(manualApplyChanges)
                .addContainerGap(187, Short.MAX_VALUE))
        );

        painel.addTab("Propriedades Manuais", pnlPropriedades);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Horizontal"));

        jLabel8.setText("Alocar uma nova máquina virtual quando limites atingirem:");

        hMemMaxLimit.setText("70");
        hMemMaxLimit.setEnabled(false);

        hVcpuMaxLimit.setText("60");
        hVcpuMaxLimit.setEnabled(false);
        hVcpuMaxLimit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hVcpuMaxLimitActionPerformed(evt);
            }
        });

        jLabel9.setText("% de memória");

        jLabel10.setText("% de CPU");

        horizontalCheckBox.setText("Ativo");
        horizontalCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                horizontalCheckBoxActionPerformed(evt);
            }
        });

        jLabel17.setText("Desalocar uma nova máquina virtual quando limites atingirem:");

        hMemMinLimit.setText("50");
        hMemMinLimit.setEnabled(false);

        hVcpuMinLimit.setText("30");
        hVcpuMinLimit.setEnabled(false);
        hVcpuMinLimit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hVcpuMinLimitActionPerformed(evt);
            }
        });

        jLabel18.setText("% de CPU");

        jLabel19.setText("% de memória");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(hVcpuMaxLimit, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                            .addComponent(hMemMaxLimit))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel10))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel9))))
                    .addComponent(horizontalCheckBox)
                    .addComponent(jLabel17)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(hVcpuMinLimit)
                            .addComponent(hMemMinLimit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel18))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel19)))))
                .addContainerGap(117, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(horizontalCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hMemMaxLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hVcpuMaxLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(8, 8, 8)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hMemMinLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hVcpuMinLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Vertical"));

        verticalCheckBox.setText("Ativo");
        verticalCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verticalCheckBoxActionPerformed(evt);
            }
        });

        jLabel11.setText("Quando a carga na memória for maior que");

        jLabel12.setText("Quando a carga no CPU for maior que");

        vMemMaxLimit.setText("70");
        vMemMaxLimit.setEnabled(false);

        vVcpuMaxLimit.setText("60");
        vVcpuMaxLimit.setEnabled(false);

        jLabel13.setText("%, alocar");

        jLabel14.setText("%, alocar");

        memQtdAlocacao.setText("50");
        memQtdAlocacao.setEnabled(false);

        vcpuQtdAlocacao.setText("1");
        vcpuQtdAlocacao.setEnabled(false);

        jLabel15.setText("MB");

        jLabel16.setText("Core");

        jLabel20.setText("Quando a carga na memória for menor que");

        vMemMinLimit.setText("50");
        vMemMinLimit.setEnabled(false);
        vMemMinLimit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vMemMinLimitActionPerformed(evt);
            }
        });

        jLabel21.setText("%, desalocar");

        memQtdDesalocacao.setText("50");
        memQtdDesalocacao.setEnabled(false);
        memQtdDesalocacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memQtdDesalocacaoActionPerformed(evt);
            }
        });

        jLabel22.setText("MB");

        jLabel23.setText("Quando a carga no CPU for menor que");

        vVcpuMinLimit.setText("30");
        vVcpuMinLimit.setEnabled(false);

        jLabel24.setText("%, desalocar");

        vcpuQtdDesalocacao.setText("1");
        vcpuQtdDesalocacao.setEnabled(false);

        jLabel25.setText("Core");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(verticalCheckBox, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel23)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(vVcpuMinLimit, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(vMemMinLimit, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(jLabel11)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(vVcpuMaxLimit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(vMemMaxLimit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel20))
                                .addGap(141, 141, 141)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(memQtdAlocacao, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(vcpuQtdAlocacao, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(memQtdDesalocacao, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(vcpuQtdDesalocacao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel22)
                    .addComponent(jLabel25))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(verticalCheckBox)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(vMemMaxLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13)
                        .addComponent(memQtdAlocacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(vVcpuMaxLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel16)
                    .addComponent(vcpuQtdAlocacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(vMemMinLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(memQtdDesalocacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(vVcpuMinLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(vcpuQtdDesalocacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        automaticApplyChanges.setText("Aplicar Alterações");
        automaticApplyChanges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                automaticApplyChangesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(automaticApplyChanges))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 582, Short.MAX_VALUE)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(automaticApplyChanges)
                .addContainerGap())
        );

        painel.addTab("Propriedades Automáticas", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(painel)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnNovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAtualizar)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovo)
                    .addComponent(btnExcluir)
                    .addComponent(btnAtualizar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painel)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
        atualizar();

    }//GEN-LAST:event_btnAtualizarActionPerformed

    private void atualizar() {
        controller.atualizaLista(this.vmList);
//        vmList.validate();
//        vmList.repaint();
//        jScrollPane1.repaint();
//        jScrollPane1.revalidate();
    }

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        
        LocalTime inicio = LocalTime.now();
        
        Integer vmID = this.getSelectedVMId();

        if (vmID == null || vmID < 0) {
            JOptionPane.showMessageDialog(null, "Nenhuma VM Selecionada");
            return;
        }

        if (vmID == 0) {
            JOptionPane.showMessageDialog(null, "Não é possível remover Domain-0");
            return;
        }

        String command = "sudo xl shutdown " + vmID;

        try {
            Process proc = Runtime.getRuntime().exec(command);

        } catch (Exception e) {
            System.out.println("Erro em desligar VM");
            e.printStackTrace();
        }

        atualizar();
        controller.atualizarDstat();
        
        LocalTime fim = LocalTime.now();
        System.out.println("Tempo para desalocar VM manualmente: " + (fim.toSecondOfDay()-inicio.toSecondOfDay()) + " segundos.");

    }//GEN-LAST:event_btnExcluirActionPerformed

    private void hVcpuMaxLimitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hVcpuMaxLimitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hVcpuMaxLimitActionPerformed

    private void horizontalCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_horizontalCheckBoxActionPerformed
        
        if (verticalCheckBox.isSelected()) {
            JOptionPane.showMessageDialog(null,"Apenas um tipo de elasticidade pode estar ativo por vez, desmarque a opcao Vertical para continuar!");
            horizontalCheckBox.setSelected(false);
            return;
        }
        
        hMemMaxLimit.setEnabled(horizontalCheckBox.isSelected());
        hVcpuMaxLimit.setEnabled(horizontalCheckBox.isSelected());
        hMemMinLimit.setEnabled(horizontalCheckBox.isSelected());
        hVcpuMinLimit.setEnabled(horizontalCheckBox.isSelected());
    }//GEN-LAST:event_horizontalCheckBoxActionPerformed

    private void verticalCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verticalCheckBoxActionPerformed

        if (horizontalCheckBox.isSelected()) {
            JOptionPane.showMessageDialog(null,"Apenas um tipo de elasticidade pode estar ativo por vez, desmarque a opcao Horizontal para continuar!");
            verticalCheckBox.setSelected(false);
            return;
        }
        
        vMemMaxLimit.setEnabled(verticalCheckBox.isSelected());
        vVcpuMaxLimit.setEnabled(verticalCheckBox.isSelected());
        memQtdAlocacao.setEnabled(verticalCheckBox.isSelected());
        vcpuQtdAlocacao.setEnabled(verticalCheckBox.isSelected());
        vMemMinLimit.setEnabled(verticalCheckBox.isSelected());
        memQtdDesalocacao.setEnabled(verticalCheckBox.isSelected());
        vVcpuMinLimit.setEnabled(verticalCheckBox.isSelected());
        vcpuQtdDesalocacao.setEnabled(verticalCheckBox.isSelected());

    }//GEN-LAST:event_verticalCheckBoxActionPerformed

    private void hVcpuMinLimitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hVcpuMinLimitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hVcpuMinLimitActionPerformed

    private void vMemMinLimitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vMemMinLimitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vMemMinLimitActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed

        /*VirtualMachineStarter vm = new VirtualMachineStarter();
        mapThreads.put(name + id++, vm);
        vm.start();

        atualizar(); */
        CreateVM createVM = new CreateVM(() -> {
            atualizar();
        }, (name, ip, vcpu, maxvcpu, memory, maxMemory) -> {

            VMSettings vmSettings = new VMSettings(name, ip, vcpu, maxvcpu, memory, maxMemory);
            vmsSettings.put(name, vmSettings);

        });
        createVM.setLocationRelativeTo(null);
        createVM.setVisible(true);

        atualizar();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void vmListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_vmListValueChanged

//        plotGraph();
        Integer vmId = getSelectedVMId();

        if (vmId == null) {
            tfVmId.setText("");
            return;
        } else {
            tfVmId.setText(vmId.toString());
        }

        final ThreadServer thread = getSelectedThread();

        if (thread == null) return; // evita null pointer caso cliente nao esteja rodando na vm
        
        if (timer != null) {
            timer.interrupt();
            tsCPU.clear();
            tsMem.clear();
        }

        timer = new Thread(() -> {
            while (!Thread.interrupted()) {
                final Integer actualCPU = thread.getActualCPU();
                final Integer actualMem = thread.getActualMemory();
                synchronized (tsCPU) {
                    tsCPU.addOrUpdate(new Second(), actualCPU);
                }
                synchronized (tsMem) {
                    tsMem.addOrUpdate(new Second(), actualMem);
                }
            }
        });

        timer.start();
        setDadosTelaPropriedadeManual();
    }//GEN-LAST:event_vmListValueChanged

    private void automaticApplyChangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_automaticApplyChangesActionPerformed
        ThreadServer thread = getSelectedThread();
        if (thread == null) {
            return;
        }
        
        if (verticalCheckBox.isSelected()) { 
            thread.setVertical(true);
            thread.setHorizontal(false);
            thread.setAlocarMemoria(vMemMaxLimit.getText(), memQtdAlocacao.getText());
            thread.setAlocarCPU(vVcpuMaxLimit.getText(), vcpuQtdAlocacao.getText());
            thread.setDesalocarMemoria(vMemMinLimit.getText(), memQtdDesalocacao.getText());
            thread.serDesalocarCPU(vVcpuMinLimit.getText(), vcpuQtdDesalocacao.getText());
        } else {
            if (horizontalCheckBox.isSelected()) {
                thread.setVertical(false);
                thread.setHorizontal(true);
                thread.setAlocaVM(hMemMaxLimit.getText(), hVcpuMaxLimit.getText());
                thread.setDesalocaVM(hMemMinLimit.getText(), hVcpuMinLimit.getText());
            }
        }
    }//GEN-LAST:event_automaticApplyChangesActionPerformed

    private void memQtdDesalocacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memQtdDesalocacaoActionPerformed
    }//GEN-LAST:event_memQtdDesalocacaoActionPerformed

    private void manualApplyChangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manualApplyChangesActionPerformed

        Integer vmSelected = getSelectedVMId();
        if (vmSelected != null) {
            changeMemValue(vmSelected, memorySpinner.getValue());
        
            changeVCPUValue(vmSelected, vcpuSpinner.getValue());
        }

    }//GEN-LAST:event_manualApplyChangesActionPerformed

    private void setDadosTelaPropriedadeManual() throws NumberFormatException {
        VMSettings sett = vmsSettings.get(getSelectedVMName());
        if (sett == null) {
            return;
        }

        vcpuSpinner.setValue(Integer.parseInt(sett.getVmVCPU()));
        vcpuSpinner.setMaximum(Integer.parseInt(sett.getVmMaxVCPU()));
        memorySpinner.setValue(Integer.parseInt(sett.getVmMemory()));
        memorySpinner.setMaximum(Integer.parseInt(sett.getVmMaxMemory()));
    }

    private ThreadServer getSelectedThread() {
        return vms.get(getSelectedVMName());
    }

    private String getSelectedVMName() {
        Object selectedItem = this.vmList.getSelectedValue();
        final String[] id = selectedItem.toString().split(" ");
        return id[2];
    }

    private Integer getSelectedVMId() {

        Object selectedItem = this.vmList.getSelectedValue();
        if (selectedItem == null) {
            return null;
        }

        final String[] id = selectedItem.toString().split(" ");
        Integer vmID = selectedItem == null ? null : new Integer(id[0]);

        ThreadServer thread = vms.get(id[2]);
        if (thread != null) {
            System.out.println(thread.toString());
        }
        return vmID;
    }

    public void setVMManualSettings(Integer memoAtual, Integer memoMax, Integer vcpuAtual, Integer vcpuMax) {
        this.memorySpinner.setValue(memoAtual);
        this.memorySpinner.setMaximum(memoMax);
        this.vcpuSpinner.setValue(vcpuAtual);
        this.vcpuSpinner.setMaximum(vcpuMax);
    }

    public void changeMemValue(Integer vmID, Integer qtdMem) {
        String command = "sudo xl mem-set " + vmID + " " + qtdMem;

        try {
            Process proc = Runtime.getRuntime().exec(command);

        } catch (Exception e) {
            System.out.println("Erro em alocar memória: mem-set " + vmID + " " + qtdMem);
            e.printStackTrace();
        }
    }

    public void changeVCPUValue(Integer vmID, Integer qtdVCPU) {
        String command = "sudo xl vcpu-set " + vmID + " " + qtdVCPU;

        try {
            Process proc = Runtime.getRuntime().exec(command);

        } catch (Exception e) {
            System.out.println("Erro em alocar vcpu: vcpu-set " + vmID + " " + qtdVCPU);
            e.printStackTrace();
        }
    }

    public XYSeries setaLimites(XYSeries serie, String nome, Integer valor) {
        serie = new XYSeries(nome);
        for (int i = 1; i <= this.amostras; i++) {
            serie.add(i, valor);
        }

        return serie;
    }

    public XYSeries setaSerie(XYSeries serie, String nome, Integer valInicial, Integer alteracao) {
        serie = new XYSeries(nome);

        int aux = valInicial;
        for (int i = 1; i <= this.amostras; i++) {
            Random r = new Random();
            int j = alteracao - r.nextInt(alteracao * 2);
//            System.out.println(j);
            aux += j;
            serie.add(i, aux);
        }

        return serie;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }

            //UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the fonullrm */
        try {
//                UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
//                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//                UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            
        } catch (Exception e) {
            System.out.println("Problema em aplicar look and feel");
        }
        java.awt.EventQueue.invokeLater(() -> {
            Tela t = new Tela();

            t.setLocationRelativeTo(null);
            t.setVisible(true);
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton automaticApplyChanges;
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JTextField hMemMaxLimit;
    private javax.swing.JTextField hMemMinLimit;
    private javax.swing.JTextField hVcpuMaxLimit;
    private javax.swing.JTextField hVcpuMinLimit;
    private javax.swing.JCheckBox horizontalCheckBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton manualApplyChanges;
    private javax.swing.JTextField memQtdAlocacao;
    private javax.swing.JTextField memQtdDesalocacao;
    private javax.swing.JSlider memorySpinner;
    private javax.swing.JTabbedPane painel;
    private javax.swing.JPanel pnlDesempenho;
    private javax.swing.JPanel pnlPropriedades;
    private javax.swing.JTextField tfVmId;
    private javax.swing.JTextField vMemMaxLimit;
    private javax.swing.JTextField vMemMinLimit;
    private javax.swing.JTextField vVcpuMaxLimit;
    private javax.swing.JTextField vVcpuMinLimit;
    private javax.swing.JTextField vcpuQtdAlocacao;
    private javax.swing.JTextField vcpuQtdDesalocacao;
    private javax.swing.JSlider vcpuSpinner;
    private javax.swing.JCheckBox verticalCheckBox;
    private javax.swing.JList vmList;
    // End of variables declaration//GEN-END:variables

}
