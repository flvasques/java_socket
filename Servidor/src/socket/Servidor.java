package socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import negocio.Interfaces.IParticipante;
import negocio.Participante;
import persistencia.Log;

public class Servidor extends Thread {
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
        try {
            this.socket = new ServerSocket(this.porta);
            while(this.online){
                Socket sc = this.socket.accept();
                IParticipante p = new Participante(sc);
                ((Participante)p).start();
                this.participantes.add(p);
            }
        } catch (IOException ex) {
            Log.salvaLog("Falha no Servidor: " + ex.toString());
            JOptionPane.showMessageDialog(null, ex, "Leilão", ERROR_MESSAGE);
        }
    }
    @Override
    public void run(){
        this.online = true;
        this.rodar();
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
}
