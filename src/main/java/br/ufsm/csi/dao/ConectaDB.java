package br.ufsm.csi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaDB {

    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://127.0.0.1:5432/Projeto";
    private static final String USER = "postgres";
    private static final String SENHA = "postgres";

    public Connection getConexao(){

        Connection conn = null;

        try {
            Class.forName(this.DRIVER);
            conn = DriverManager.getConnection(this.URL, this.USER, this.SENHA);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException sqlE){
            sqlE.printStackTrace();
        }

        return conn;
    }

}
