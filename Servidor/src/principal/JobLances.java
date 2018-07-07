
package principal;

import java.util.TimerTask;

public class JobLances  extends TimerTask{

    private TelaLeilao tela;
    
    public JobLances(TelaLeilao p){
        this.tela = p;
    }
    
    @Override
    public void run() {
       this.tela.admLeilao();
    }
    
}
