package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import persistencia.Log;

public class Cliente extends Thread {

    private boolean online;
    private final int porta;
    private String ip;
    private ArrayList<String> inputMsg;
    private String outputMsg;
    Socket socket;

    public Cliente() {
        this.porta = 10000;
        //this.ip = "127.0.0.1";
        this.online = false;
        this.inputMsg = new ArrayList<String>();
    }

    public void rodar() {
        try {
            this.socket = new Socket(this.ip, this.porta);
            InputStream i = this.socket.getInputStream();
            OutputStream o = this.socket.getOutputStream();
            String str = "";
            o.write(this.outputMsg.getBytes(Charset.forName("UTF-8")));
            this.outputMsg = "0";
            do {
                byte[] line = new byte[100];
                line = this.outputMsg.getBytes(Charset.forName("UTF-8"));
                o.write(line);
                i.read(line);
                str = new String(line);
                while(!str.equals("fim")){
                    i.read(line);
                    str = new String(line);
                    this.inputMsg.add(str);
                }
            } while (!str.trim().equals("bye") && this.online);
            this.socket.close();

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex, "Leilão", ERROR_MESSAGE);
            Log.salvaLog("Falha na execução do socket: " + ex.toString());
            this.online = false;
        }
    }
    
    @Override
    public void run(){
        this.online = true;
        System.out.println("socket.Cliente.run()");
        System.out.println("Nome:" + this.outputMsg);
        System.out.println("IP:" + this.ip);
        this.rodar();
    }

    public ArrayList<String> getInputMsg() {
        return inputMsg;
    }

    public void setInputMsg(ArrayList<String> inputMsg) {
        this.inputMsg = inputMsg;
    }
    
    public String getOutputMsg() {
        return outputMsg;
    }

    public void setOutputMsg(String outputMsg) {
        this.outputMsg = outputMsg;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    
    
}
