package negocio.Interfaces;

import java.util.ArrayList;

public interface ILeiloeiro {
  public void inicar();
  public double[] lerLances();
  public void finalizar();
  public void noticicar(String msg);
  public ArrayList<IParticipante> getParticipantes();
}
