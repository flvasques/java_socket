
package principal;

import java.util.ArrayList;
import java.util.Timer;

import negocio.Leiloeiro;
import negocio.Lote;

public class TelaLeilao extends javax.swing.JFrame {
    
    private final ConfigTela config = new ConfigTela();
    private ArrayList<Lote> lotes;
    private Leiloeiro leiloeiro;
    private Timer timer;
    private boolean iniciado = false;
    private int atual = 0;
    
    public TelaLeilao() {
        initComponents();
    }
    public TelaLeilao(Leiloeiro l, ArrayList<Lote> ll){
        this.timer = null;
        this.leiloeiro = l;
        this.lotes = ll;
        this.setTitle(config.getTiutlo());
        this.setSize(config.getSizeY(),config.getSizeX());
        this.setResizable(config.isResizeble());
        initComponents();
        this.setVisible(true);
        initComponents();
        ListarLotes();
        this.listaparticipantes.removeAll();
        this.ListaLances.removeAll();
        this.timer.schedule(new JobParticipantes(this), 3000);
    }
    
    void ListarLotes(){
        this.listaLotes.removeAll();
        for(int i = 0;  i < this.lotes.size(); i++){
            this.listaLotes.add(this.lotes.get(i).toString());
        }
    }
    
    void ListarParticipantes(){
        this.listaparticipantes.removeAll();
        for(int i = 0 ; i < this.leiloeiro.getParticipantes().size(); i++){
            this.listaparticipantes.add(this.leiloeiro.getParticipantes().get(i).getNome());
        }
        if(this.leiloeiro.getParticipantes().size() >= 2){
            this.ListaLances.add("Leilão iniciado");
            this.lotes.get(0).setStatus("Em andamento");
            ListarLotes();
            this.timer.cancel();
            this.leiloeiro.noticicar(this.lotes.get(0).toString());
        }
        this.timer.schedule(new JobLances(this), 3000);
    }
    private void andamento(){
        for(int i = 0 ; i < this.leiloeiro.getParticipantes().size(); i++){
            this.listaparticipantes.add(this.leiloeiro.getParticipantes().get(i).getNome()
            + " - " + this.leiloeiro.getParticipantes().get(i).getLance()
            );
        }
    }
    void admLeilao(){
        this.andamento();
        double temp = 0;
        double valoes[] = this.leiloeiro.lerLances();
        for(int i = 0; i < valoes.length; i++){
            for(int j = i;j < valoes.length;i++ ){
                if(valoes[i] < valoes[j]){
                    temp = i;
                }
            }
        }
        
        if(this.lotes.get(this.atual).getValor() >= valoes[temp]){
           this.lotes.get(this.atual).setStatus("Finalizado");
           this.leiloeiro.noticicar(this.leiloeiro.getParticipantes().get(temp).getNome());
           this.atual++;
           this.leiloeiro.noticicar(this.lotes.get(0).toString());
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        listaLotes = new java.awt.List();
        lblLotes = new javax.swing.JLabel();
        listaparticipantes = new java.awt.List();
        lblParticipantes = new javax.swing.JLabel();
        ListaLances = new java.awt.List();
        lblLances = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblLotes.setText("Lotes");

        lblParticipantes.setText("Participantes");

        lblLances.setText("Lances");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(listaLotes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblLotes, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(listaparticipantes, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLances, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblParticipantes, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ListaLances, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLotes, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(lblParticipantes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblLances, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(listaLotes, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                        .addComponent(listaparticipantes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(ListaLances, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**3
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLeilao().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.List ListaLances;
    private javax.swing.JLabel lblLances;
    private javax.swing.JLabel lblLotes;
    private javax.swing.JLabel lblParticipantes;
    private java.awt.List listaLotes;
    private java.awt.List listaparticipantes;
    // End of variables declaration//GEN-END:variables
}
