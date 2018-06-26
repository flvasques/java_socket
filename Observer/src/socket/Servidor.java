package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando Vasques
 */
public class Servidor {
    ServerSocket socket;
    private final int porta;
    private boolean online;
    private FilaIO fila;
    
    public Servidor(int porta, FilaIO fila){
        this.porta = porta;
        this.online = false;
        this.fila = fila;
        //socket = new ServerSocket(porta);
    }
    
    public void rodar(){
        String str;
        this.online = true;
        try {
            this.socket = new ServerSocket(this.porta);
            Socket sc = this.socket.accept();
            while(this.online){
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
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
