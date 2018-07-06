
package principal;

import negocio.Participante;
import socket.Cliente;

public class Loader {

    public static void main(String[] args) {
        Participante p = new Participante();
        new Inicio(p);
    }
    
}
