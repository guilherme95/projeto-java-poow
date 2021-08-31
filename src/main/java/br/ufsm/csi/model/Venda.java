package br.ufsm.csi.model;

import java.util.Date;

public class Venda {

    private int id_venda;
    private Date data_venda;
    private double quantidade;
    private String forma_pagamento;
    private Produto produto;
    private Cliente cliente;

    public Venda() {
    }

    public Venda(int id_venda, double quantidade, String forma_pagamento, Produto produto, Cliente cliente) {
        this.id_venda = id_venda;
        this.data_venda = data_venda;
        this.quantidade = quantidade;
        this.forma_pagamento = forma_pagamento;
        this.produto = produto;
        this.cliente = cliente;
    }

    public Venda(double quantidade, String forma_pagamento, Produto produto, Cliente cliente) {
        this.quantidade = quantidade;
        this.forma_pagamento = forma_pagamento;
        this.produto = produto;
        this.cliente = cliente;
    }

    public int getId_venda() {
        return id_venda;
    }

    public void setId_venda(int id_venda) {
        this.id_venda = id_venda;
    }

    public Date getData_venda() {
        return data_venda;
    }

    public void setData_venda(Date data_venda) {
        this.data_venda = data_venda;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public String getForma_pagamento() {
        return forma_pagamento;
    }

    public void setForma_pagamento(String forma_pagamento) {
        this.forma_pagamento = forma_pagamento;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
