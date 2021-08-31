package br.ufsm.csi.model;

public class Entrega {

    private int id_entrega;
    private String endereco_entrega;
    private Entregador entregador;
    private Venda venda;

    public Entrega() {
    }

    public Entrega(int id_entrega, String endereco_entrega, Entregador entregador, Venda venda) {
        this.id_entrega = id_entrega;
        this.endereco_entrega = endereco_entrega;
        this.entregador = entregador;
        this.venda = venda;
    }

    public Entrega(String endereco_entrega, Entregador entregador, Venda venda) {
        this.endereco_entrega = endereco_entrega;
        this.entregador = entregador;
        this.venda = venda;
    }

    public int getId_entrega() {
        return id_entrega;
    }

    public void setId_entrega(int id_entrega) {
        this.id_entrega = id_entrega;
    }

    public String getEndereco_entrega() {
        return endereco_entrega;
    }

    public void setEndereco_entrega(String endereco_entrega) {
        this.endereco_entrega = endereco_entrega;
    }

    public Entregador getEntregador() {
        return entregador;
    }

    public void setEntregador(Entregador entregador) {
        this.entregador = entregador;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }
}
