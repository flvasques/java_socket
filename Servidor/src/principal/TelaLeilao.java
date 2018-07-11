
package principal;

import java.util.ArrayList;
import java.util.Timer;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import negocio.EnumStatusLote;
import negocio.Interfaces.ILeiloeiro;
import negocio.Lote;

public class TelaLeilao extends javax.swing.JFrame {
    private final ConfigTela config = new ConfigTela();
    private ILeiloeiro usuario;
    private ArrayList<Lote> lotes;
    private Timer timer;
    private int loteAtual;
    
    public TelaLeilao() {
        initComponents();
    }

    public TelaLeilao(ILeiloeiro l, ArrayList<Lote> ll) {
        this.usuario = l;
        this.lotes = ll;
        this.setTitle(config.getTiutlo());
        this.setSize(config.getSizeY(),config.getSizeX());
        this.setResizable(config.isResizeble());
        this.setLocationRelativeTo(null);
        initComponents();
        this.setVisible(true);
        listarLotes();
        listarParticipantes();
        this.listaLances.removeAll();
    }
    
    void listarLotes(){
        this.listaLotesVenda.removeAll();
        for(int i = 0; i < this.lotes.size(); i++){
            this.listaLotesVenda.add(this.lotes.get(i).toString());
        }
    }
    
    void listarParticipantes(){
        this.listaParticipantes.removeAll();
        for(int i = 0; i < this.usuario.getParticipantes().size(); i++){
            this.listaParticipantes.add(this.usuario.getParticipantes().get(i).getNome());
        }
        if(this.usuario.getParticipantes().isEmpty()){
            this.listaParticipantes.add("Sem Participantes");
            this.listaParticipantes.add("Aguradando Participantes");
        }else if(this.usuario.getParticipantes().size() < 2){
            this.listaParticipantes.add("Aguradando Completar Corum");
        }else if(this.usuario.getParticipantes().size()>= 2){
            this.timer.cancel();
            this.lotes.get(0).setStatus(EnumStatusLote.Atual);
            this.listarLotes();
            this.usuario.noticicar("Leilão Iniciado");
            this.usuario.noticicar(this.lotes.get(0).toString());
            this.listaLances.add("Leilão Iniciado");
            this.listaLances.add(this.lotes.get(0).toString());
            JobLances job = new JobLances(this);
            this.timer.schedule(job, 5000, 5000);
            this.loteAtual = 0;
        } 
    }
    
    void setTimer(JobParticipantes job){
        this.timer = new Timer();
        this.timer.schedule(job, 60000, 60000);
    }
    
    void admLeilao(){
        double lances[] = this.usuario.lerLances();
        for(int i = 0; i < this.usuario.getParticipantes().size(); i++){
            String nome = this.usuario.getParticipantes().get(i).getNome();
            String lance = this.usuario.getParticipantes().get(i).getLance() + "";
            this.listaLances.add(nome + "\tR$" + lance);
            this.usuario.noticicar(nome + "\tR$" + lance);
        }
        if(lances[0] >= this.lotes.get(this.loteAtual).getValor()){
            this.lotes.get(this.loteAtual).setValorVendido(lances[0]);
            this.lotes.get(this.loteAtual).setStatus(EnumStatusLote.Vendido);
            this.listaLances.add("Item vendido por:" + lances[0]);
            this.usuario.noticicar("Item vendido por:" + lances[0]);
        }else{
            this.lotes.get(this.loteAtual).setValorVendido(0);
            this.lotes.get(this.loteAtual).setStatus(EnumStatusLote.Nao_Vendido);
            this.listaLances.add("Item Regeitado");
            this.usuario.noticicar("Item Regeitado");
        }
        this.listarLotes();
        if(this.loteAtual < this.lotes.size()){
            this.loteAtual++;
            this.usuario.noticicar("Próximo Lote");
            this.usuario.noticicar(this.lotes.get(this.loteAtual).toString());
            this.listaLances.add("Próximo Lote");
            this.listaLances.add(this.lotes.get(this.loteAtual).toString());
        }else{
            this.timer.cancel();
            this.usuario.noticicar("Leilão Finalizado");
            this.usuario.noticicar("Até a proxima");
            this.usuario.noticicar("bye");
            this.usuario.noticicar("Leilão Finalizado");
            double total = 0;
            for(int i  = 0; i < this.lotes.size(); i++){
                total += this.lotes.get(i).getValorVendido();
            }
            JOptionPane.showMessageDialog(null, "Leilão Finalizado\n Valor arracadado de: R$" + total, "Leilão", ERROR_MESSAGE);
            new NovoLeilao(this.usuario);
            this.dispose();
            this.setVisible(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblLote = new javax.swing.JLabel();
        listaLotesVenda = new java.awt.List();
        lblTitulo = new javax.swing.JLabel();
        listaParticipantes = new java.awt.List();
        lblParticipantes = new javax.swing.JLabel();
        listaLances = new java.awt.List();
        lblLances = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblLote.setText("Lotes");

        lblTitulo.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lblTitulo.setText("Leilão");

        lblParticipantes.setText("Participantes");

        lblLances.setText("Lances");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(listaLotesVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLote))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(listaParticipantes, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblParticipantes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLances)
                    .addComponent(listaLances, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTitulo)
                .addGap(349, 349, 349))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLote)
                    .addComponent(lblParticipantes)
                    .addComponent(lblLances))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(listaLances, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(listaParticipantes, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                        .addComponent(listaLotesVenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(104, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaLeilao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLeilao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLeilao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLeilao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLeilao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblLances;
    private javax.swing.JLabel lblLote;
    private javax.swing.JLabel lblParticipantes;
    private javax.swing.JLabel lblTitulo;
    private java.awt.List listaLances;
    private java.awt.List listaLotesVenda;
    private java.awt.List listaParticipantes;
    // End of variables declaration//GEN-END:variables
}
