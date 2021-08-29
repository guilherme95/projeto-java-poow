package br.ufsm.csi.model;

public class Produto {
    private int id_produto;
    private String nome_produto;
    private String valor_produto;
    private Loja loja;

    public Produto() {
    }

    public Produto(int id_produto, String nome_produto, String valor_produto, Loja loja) {
        this.id_produto = id_produto;
        this.nome_produto = nome_produto;
        this.valor_produto = valor_produto;
        this.loja = loja;
    }

    public Produto(String nome_produto, String valor_produto, Loja loja) {
        this.nome_produto = nome_produto;
        this.valor_produto = valor_produto;
        this.loja = loja;
    }

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public String getNome_produto() {
        return nome_produto;
    }

    public void setNome_produto(String nome_produto) {
        this.nome_produto = nome_produto;
    }

    public String getValor_produto() {
        return valor_produto;
    }

    public void setValor_produto(String valor_produto) {
        this.valor_produto = valor_produto;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }
}
