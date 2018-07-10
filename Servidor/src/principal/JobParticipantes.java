/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;


import java.util.TimerTask;

/**
 *
 * @author aluno
 */
public class JobParticipantes extends TimerTask{
    private TelaLeilao tela;
    
    public JobParticipantes(TelaLeilao p){
        this.tela = p;
    }
    
    @Override
    public void run() {
    this.tela.listarParticipantes();
    }
    
}
