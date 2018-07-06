 package negocio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.ArrayList;
import negocio.Interfaces.*;
import org.omg.CORBA.portable.InputStream;
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

    @Override
    public void setOutPut(String msg) {
        this.fila.add(msg);
    }
    
    private void ciclo(){
        while(true){
            try {
                String str = null;
                InputStream input = (InputStream) this.cliente.getInputStream();
                OutputStream output = this.cliente.getOutputStream();
                 byte[] line = new byte[100];
                input.read(line);
                str = new String(line);
                this.lance = Double.parseDouble(str);
                while(fila.size() > 0){
                    str = this.fila.remove(0);
                    line = str.getBytes(Charset.forName("UTF-8"));
                    output.write(line);
                }
            } catch (IOException ex) {
                 Log.salvaLog("Falha no ciclo do sokect: " + ex.toString());
            }          
        }
    }
}