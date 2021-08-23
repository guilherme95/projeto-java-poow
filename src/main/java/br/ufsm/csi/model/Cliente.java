package br.ufsm.csi.model;

public class Cliente {

    private int id_cliente;
    private Usuario usuario;

    public Cliente (){}

    public Cliente(Usuario usuario) {
        this.usuario = usuario;
    }

    public Cliente(int id_cliente, Usuario usuario) {
        this.id_cliente = id_cliente;
        this.usuario = usuario;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
