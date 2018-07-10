/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

/**
 *
 * @author Fernando Vasques
 */
public class Lote {
    private String item;
    private double valor;
    private double valorVendido;
    private EnumStatusLote status;

    public Lote(String item, double valor) {
        this.item = item;
        this.valor = valor;
        this.status = EnumStatusLote.Aguardando;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status.toString();
    }

    public void setStatus(EnumStatusLote status) {
        this.status = status;
    }
    

    @Override
    public String toString() {
        return this.item + "-" + this.valor + "-" + this.status;
    }

    public double getValorVendido() {
        return valorVendido;
    }

    public void setValorVendido(double valorVendido) {
        this.valorVendido = valorVendido;
    }
    
    
    
}
