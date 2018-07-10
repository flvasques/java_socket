
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
        this.servidor.start();
    }

    @Override
    public double[] lerLances() {
        double[] lances = new double[this.participantes.size()];
        for(int i = 0; i < this.participantes.size(); i++){
            lances[i] = this.participantes.get(i).getLance();
        }
        double temp;
        for(int i = 0; i < lances.length; i++){
            for(int j = i + 1; j < lances.length; j++){
                if(lances[i] < lances[j]){
                    temp = lances[i];
                    lances[i] = lances[j];
                    lances[j] = temp;
                }
            }
        }
        return lances;
    }

    @Override
    public void finalizar() {
        while(!this.participantes.isEmpty()){
            ((Participante)this.participantes.get(0)).setOnline(false);
            this.participantes.remove(0);
        }
        this.servidor.setOnline(false);
    }

    @Override
    public ArrayList<IParticipante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(ArrayList<IParticipante> participantes) {
        this.participantes = participantes;
    }
    
    @Override
    public void noticicar(String msg){
        for(int i = 0; i < this.participantes.size(); i++){
            this.participantes.get(i).setOutPut(msg);
        }
    }
    
}
