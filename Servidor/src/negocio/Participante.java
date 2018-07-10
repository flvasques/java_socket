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
    public String nome;
    private double lance;
    private ArrayList<String> fila = new ArrayList<String>();
    private Socket cliente;
    private boolean online = true;
    
    private Participante(){}
    
    public Participante(Socket sc){
        this.cliente = sc;
        this.fila.add("Seja bem Vindo!");
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
        while(this.online){
            InputStream input;
            try {
                input = this.cliente.getInputStream();
                OutputStream output = this.cliente.getOutputStream();
                byte[] line = new byte[100];
                input.read(line);
                str = new String(line);
                tryParseDouble(str);
                while(this.fila.size() > 0){
                    str = this.fila.remove(0);
                    str += "'";
                    line = str.getBytes(Charset.forName("UTF-8"));
                    output.write(line);
                }
                line = "*".getBytes(Charset.forName("UTF-8"));
                output.write(line);
            } catch (IOException ex) {
                Log.salvaLog("Falha na de execução de Participante: " + ex.toString());                
            }
           
        }
    }
    @Override
    public void run(){
        this.ciclo();
    }
    private void tryParseDouble(String str){
        try {
            this.lance = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            this.nome = str;
        }

    }

    public void setOnline(boolean b) {
        this.online = b;
    }
    
}
