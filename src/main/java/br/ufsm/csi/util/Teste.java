package br.ufsm.csi.util;

import br.ufsm.csi.connection.ConectaDB;

public class Teste {

    public static void main(String[] args) {
        //"testando" conexão com o banco de fato
        new ConectaDB().getConexao();
        System.out.println("Conexao OK");
    }
}
