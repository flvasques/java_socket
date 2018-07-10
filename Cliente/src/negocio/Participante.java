
package negocio;

import socket.Cliente;

public class Participante {
    private String Nome;
    private final Cliente socket;

    public  Participante(){
        this.socket = new Cliente();
    }
   
    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public Cliente getSocket() {
        return socket;
    }
    public String getMgs(){
        String str = null;
        if(!this.socket.getInputMsg().isEmpty()){
            str = this.socket.getInputMsg().remove(0);
        }
        return str;
    }
    
}
