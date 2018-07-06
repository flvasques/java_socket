
package principal;

import java.util.ArrayList;
import negocio.*;
import negocio.Interfaces.*;
import socket.Servidor;

public class Loader {
    public static void main(String[] args) {
        
        ArrayList<IParticipante> p = new ArrayList<>();

        Servidor serverSocket = new Servidor(p);
        ILeiloeiro ll = new Leiloeiro(p, serverSocket);
    
    }
    
}
