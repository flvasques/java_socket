
package observer;

import java.util.ArrayList;
import negocio.*;
import negocio.Interfaces.*;

public class Loader {
    public static void main(String[] args) {
        
        ArrayList<IParticipante> p = new ArrayList<IParticipante>();
        
        ILeiloeiro ll = new Leiloeiro(p);
    
        new Inicio(ll);
    }
    
}
