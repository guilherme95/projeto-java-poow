package br.ufsm.csi.dao;

import br.ufsm.csi.connection.ConectaDB;
import br.ufsm.csi.model.Entrega;
import br.ufsm.csi.model.Entregador;
import br.ufsm.csi.model.Venda;

import java.sql.*;
import java.util.ArrayList;

public class EntregaDAO {
    private String sql;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String status;

    //get all
    public ArrayList<Entrega> getEntregas(){

        ArrayList<Entrega> entregas = new ArrayList<Entrega>();

        try(Connection connection = new ConectaDB().getConexao() ){

            this.sql = "SELECT * FROM entrega";
            this.statement = connection.createStatement();
            this.resultSet = this.statement.executeQuery(sql);

            while(this.resultSet.next()){
                Entrega entrega = new Entrega();
                entrega.setId_entrega(this.resultSet.getInt("id_entrega"));
                entrega.setEndereco_entrega(this.resultSet.getString("endereco_entrega"));
                entrega.setEntregador((Entregador) this.resultSet.getObject("id_entregador"));
                entrega.setVenda((Venda) this.resultSet.getObject("id_venda"));

                entregas.add(entrega);
            }

        }catch (SQLException e){
            this.status = "Error";
            e.printStackTrace();
        }
        return entregas;
    }

    //get one
    public Entrega getEntrega(Entrega entrega){
        try(Connection connection = new ConectaDB().getConexao() ){

            this.sql = "SELECT * FROM entrega WHERE id_entrega = ? OR endereco_entrega LIKE ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, entrega.getId_entrega());
            this.preparedStatement.setString(2, '%'+entrega.getEndereco_entrega()+'%');

            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getResultSet();
            this.resultSet.next();

            while(this.resultSet.next()){
                entrega.setId_entrega(this.resultSet.getInt("id_entrega"));
                entrega.setEndereco_entrega(this.resultSet.getString("endereco_entrega"));
                entrega.setEntregador((Entregador) this.resultSet.getObject("id_entregador"));
                entrega.setVenda((Venda) this.resultSet.getObject("id_venda"));
            }

        }catch (SQLException e){
            this.status = "Error";
            e.printStackTrace();
        }
        return entrega;
    }

    //update
    public String update(Entrega entrega){
        try(Connection connection = new ConectaDB().getConexao() ){

            connection.setAutoCommit(false);

            this.sql = "UPDATE entrega SET endereco_entrega = ? WHERE id_entrega = ?";
            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, entrega.getEndereco_entrega());

            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();

            if(this.resultSet.getInt(1) > 0){
                entrega.setId_entrega(this.resultSet.getInt(1));
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
    public String create(Entrega entrega){
        try(Connection connection = new ConectaDB().getConexao() ){

            connection.setAutoCommit(false);

            this.sql = "INSERT INTO entrega (endereco_entrega, id_entregador, id_venda) VALUES nome_entregador = ? , id_entregador = ? , id_venda = ?";
            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, entrega.getEndereco_entrega());
            this.preparedStatement.setObject(2, entrega.getEntregador());
            this.preparedStatement.setObject(3, entrega.getVenda());

            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();

            if(this.resultSet.getInt(1) > 0){
                entrega.setId_entrega(this.resultSet.getInt(1));
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
    public String delete(Entrega entrega){
        try(Connection connection = new ConectaDB().getConexao() ){

            connection.setAutoCommit(false);

            this.sql = "DELETE FROM entrega WHERE id_entrega = ?";
            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setInt(1, entrega.getId_entrega());

            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();

            if(this.resultSet.getInt(1) > 0){
                entrega.setId_entrega(this.resultSet.getInt(1));
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
