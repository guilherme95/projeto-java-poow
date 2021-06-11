package br.ufsm.csi.model;

public class Entregador {

    private int id_entregador;
    private String nome_entregador;
    private String rg_entregador;
    private String cpf_entregador;

    public Entregador() {
    }

    public int getId_entregador() {
        return id_entregador;
    }

    public void setId_entregador(int id_entregador) {
        this.id_entregador = id_entregador;
    }

    public String getNome_entregador() {
        return nome_entregador;
    }

    public void setNome_entregador(String nome_entregador) {
        this.nome_entregador = nome_entregador;
    }

    public String getRg_entregador() {
        return rg_entregador;
    }

    public void setRg_entregador(String rg_entregador) {
        this.rg_entregador = rg_entregador;
    }

    public String getCpf_entregador() {
        return cpf_entregador;
    }

    public void setCpf_entregador(String cpf_entregador) {
        this.cpf_entregador = cpf_entregador;
    }
}
