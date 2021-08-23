package br.ufsm.csi.dao;

import br.ufsm.csi.connection.ConectaDB;
import br.ufsm.csi.model.Entregador;

import java.sql.*;
import java.util.ArrayList;

public class EntregadorDAO {

    private String sql;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String status;

    //get all
    public ArrayList<Entregador> getEntregadores(){

        ArrayList<Entregador> entregadores = new ArrayList<Entregador>();

        try(Connection connection = new ConectaDB().getConexao() ){

            this.sql = "SELECT * FROM entregador";
            this.statement = connection.createStatement();
            this.resultSet = this.statement.executeQuery(sql);

            while(this.resultSet.next()){
                Entregador entregador = new Entregador();
                entregador.setId_entregador(this.resultSet.getInt("id_entregador"));
                entregador.setNome_entregador(this.resultSet.getString("nome_entregador"));
                entregador.setRg_entregador(this.resultSet.getString("rg_entregador"));
                entregador.setCpf_entregador(this.resultSet.getString("cpf_entregador"));

                entregadores.add(entregador);
            }

        }catch (SQLException e){
            this.status = "Error";
            e.printStackTrace();
        }
        return entregadores;
    }

    //get one
    public Entregador getEntregador(Entregador entregador){
        try(Connection connection = new ConectaDB().getConexao() ){

            this.sql = "SELECT * FROM entregador WHERE id_entregador = ? OR nome_entregador LIKE ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, entregador.getId_entregador());
            this.preparedStatement.setString(2, '%'+entregador.getNome_entregador()+'%');

            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getResultSet();
            this.resultSet.next();

            while(this.resultSet.next()){
                entregador.setId_entregador(this.resultSet.getInt("id_entregador"));
                entregador.setNome_entregador(this.resultSet.getString("nome_entregador"));
                entregador.setRg_entregador(this.resultSet.getString("rg_entregador"));
                entregador.setCpf_entregador(this.resultSet.getString("cpf_entregador"));
            }

        }catch (SQLException e){
            this.status = "Error";
            e.printStackTrace();
        }
        return entregador;
    }

    //update
    public String update(Entregador entregador){
        try(Connection connection = new ConectaDB().getConexao() ){

            connection.setAutoCommit(false);

            this.sql = "UPDATE entregador SET nome_entregador = ?, rg_entregador = ?, cpf_entregador = ? WHERE id_entregador = ?";
            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, entregador.getNome_entregador());
            this.preparedStatement.setString(2, entregador.getRg_entregador());
            this.preparedStatement.setString(3, entregador.getCpf_entregador());

            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();

            if(this.resultSet.getInt(1) > 0){
                entregador.setId_entregador(this.resultSet.getInt(1));
                this.status = "OK";
            }

            if(this.status.equals("OK")){
                connection.commit();
            }

        }catch (SQLException e){
            this.status = "Error";
            e.printStackTrace();
        }
        return null;
    }

    //create
    public String create(Entregador entregador){
        try(Connection connection = new ConectaDB().getConexao() ){

            connection.setAutoCommit(false);

            this.sql = "INSERT INTO entregador (nome_entregador, rg_entregador, cpf_entregador) VALUES nome_entregador = ? , rg_entregador = ? , cpf_entregador = ?";
            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, entregador.getNome_entregador());
            this.preparedStatement.setString(2, entregador.getRg_entregador());
            this.preparedStatement.setString(3, entregador.getCpf_entregador());

            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();

            if(this.resultSet.getInt(1) > 0){
                entregador.setId_entregador(this.resultSet.getInt(1));
                this.status = "OK";
            }

            if(this.status.equals("OK")){
                connection.commit();
            }

        }catch (SQLException e){
            this.status = "Error";
            e.printStackTrace();
        }
        return null;
    }

    //delete
    public String delete(Entregador entregador){
        try(Connection connection = new ConectaDB().getConexao() ){

            connection.setAutoCommit(false);

            this.sql = "DELETE FROM entregador WHERE id_entregador = ?";
            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setInt(1, entregador.getId_entregador());

            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();

            if(this.resultSet.getInt(1) > 0){
                entregador.setId_entregador(this.resultSet.getInt(1));
                this.status = "OK";
            }

            if(this.status.equals("OK")){
                connection.commit();
            }

        }catch (SQLException e){
            this.status = "Error";
            e.printStackTrace();
        }
        return null;
    }

}
