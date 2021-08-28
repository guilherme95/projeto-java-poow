package br.ufsm.csi.model;

public class Entregador {

    private int id_entregador;
    private String cnh_entregador;
    private Usuario usuario;

    public Entregador() {
    }

    public Entregador(String cnh_entregador, Usuario usuario) {
        this.cnh_entregador = cnh_entregador;
        this.usuario = usuario;
    }

    public Entregador(int id_entregador, String cnh_entregador, Usuario usuario) {
        this.id_entregador = id_entregador;
        this.cnh_entregador = cnh_entregador;
        this.usuario = usuario;
    }

    public int getId_entregador() {
        return id_entregador;
    }

    public void setId_entregador(int id_entregador) {
        this.id_entregador = id_entregador;
    }

    public String getCnh_entregador() {
        return cnh_entregador;
    }

    public void setCnh_entregador(String cnh_entregador) {
        this.cnh_entregador = cnh_entregador;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
