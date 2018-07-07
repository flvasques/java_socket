
package principal;

import java.util.TimerTask;

public class Job extends TimerTask{
    private Principal tela;
    public Job(Principal t){
        this.tela = t;
    }
    @Override
    public void run() {
        this.tela.lerLances();
    }
    
}
