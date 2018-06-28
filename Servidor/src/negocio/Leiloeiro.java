
package negocio;

import java.util.ArrayList;
import negocio.Interfaces.*;


public class Leiloeiro implements ILeiloeiro {
    private Lance vencedor;
    private ArrayList<IParticipante> participantes;
    private int cont = 0;
    
    public Leiloeiro(ArrayList<IParticipante> p){
        this.participantes = p;
    }
    
    @Override
    public void entar(IParticipante p){
        ;
    }
    @Override
    public void desistir(IParticipante p){
        ;
    }
    @Override
    public void receberLance(Lance l){
        
    }
    @Override
    public void iniciar(double v){
       
    }
    public void finalizar(){
       
    }
    private void notificar(boolean fim){
        
    }
    private void novo(){
        
    }
}
