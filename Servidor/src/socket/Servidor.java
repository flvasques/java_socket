package socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.Interfaces.IParticipante;
import negocio.Participante;
import persistencia.Log;

/**
 *
 * @author Fernando Vasques
 */
public class Servidor {
    ServerSocket socket;
    private final int porta;
    private boolean online;
    ArrayList<IParticipante> participantes;
    
    private Servidor(){
        this.porta = 0;
    }
    
    public Servidor(ArrayList<IParticipante> p){
        this.porta = 10000;
        this.online = false;
        this.participantes = p;
    }
    
    public Servidor(int porta, ArrayList<IParticipante> p){
        this.porta = porta;
        this.online = false;
        this.participantes = p;        
    }
    
    public void rodar(){
        String str;
        this.online = true;
        try {
            this.socket = new ServerSocket(this.porta);
            while(this.online){
                Socket sc = this.socket.accept();
                this.participantes.add(new Participante(sc));
            }
        } catch (IOException ex) {
            Log.salvaLog("Falha em Iniciar o Servidor: " + ex.toString());
        }
    }
    
}
