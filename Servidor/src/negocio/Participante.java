 package negocio;

import java.io.IOException;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.ArrayList;
import negocio.Interfaces.*;
import persistencia.Log;

public class Participante extends Thread implements IParticipante{
    private String nome;
    private double lance;
    private ArrayList<String> fila = new ArrayList<String>();
    private Socket cliente;
    
    private Participante(){}
    
    public Participante(Socket sc){
        this.cliente = sc;
    }
    
    @Override
    public String getNome(){
        return this.nome;
    }

    @Override
    public double getLance() {
        return this.lance;
    }

    public void setLance(double lance) {
        this.lance = lance;
    }

    @Override
    public void setOutPut(String msg) {
        this.fila.add(msg);
    }
    
    private void ciclo(){
        String str;
        while(true){
            InputStream input;
            try {
                input = this.cliente.getInputStream();
                 OutputStream output = this.cliente.getOutputStream();
                byte[] line = new byte[100];
                input.read(line);
                str = new String(line);
                this.lance = Double.parseDouble(str);
                while(this.fila.size() > 0){
                    str = this.fila.remove(0);
                    line = str.getBytes(Charset.forName("UTF-8"));
                    output.write(line);
                }
            } catch (IOException ex) {
                Log.salvaLog("Falha na de execução de Participante: " + ex.toString());
            }
           
        }
    }
    @Override
    public void run(){
        this.ciclo();
    }
}
