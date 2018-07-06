
package negocio;

import java.util.ArrayList;
import negocio.Interfaces.*;
import socket.Servidor;


public class Leiloeiro implements ILeiloeiro {
    private ArrayList<IParticipante> participantes;
    private Servidor servidor;
    
    private Leiloeiro(){}
    
    public Leiloeiro(ArrayList<IParticipante> p, Servidor s){
        this.participantes = p;
        this.servidor = s;
    }

    @Override
    public void inicar() {
        
    }

    @Override
    public double[] lerLances() {
        double[] lances = new double[this.participantes.size()];
        for(int i = 0; i < this.participantes.size();i++){
            lances[i] = this.participantes.get(i).getLance();
        }
        return lances;
    }

    @Override
    public void finalizar() {
        
    }
    
    
}
