 package negocio;

import java.net.Socket;
import java.util.ArrayList;
import negocio.Interfaces.*;

public class Participante implements IParticipante{
    private String nome;
    private double lance;
    private ArrayList<String> output = new ArrayList<String>();
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
        this.output.add(msg);
    }
    
    private void ciclo(){
         /*while(this.online){
                InputStream input = sc.getInputStream();
                OutputStream output = sc.getOutputStream();
                byte[] line = new byte[100];
                input.read(line);
                str = new String(line);
                this.fila.setInput(str);
                while(fila.hasOutput()){
                    str = this.fila.getOutput();
                    line = str.getBytes(Charset.forName("UTF-8"));
                    output.write(line);
                }
            }*/
    }
}