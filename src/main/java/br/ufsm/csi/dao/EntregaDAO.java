package br.ufsm.csi.dao;

import br.ufsm.csi.connection.ConectaDB;
import br.ufsm.csi.model.*;
import java.sql.*;
import java.util.ArrayList;

public class EntregaDAO {

    private String sql;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String status;

    //get all delivery DONE
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

                Entregador entregador= new EntregadorDAO().getEntregador(this.resultSet.getInt("id_entregador"));
                entrega.setEntregador(entregador);

                Venda venda = new VendaDAO().getVenda(this.resultSet.getInt("id_venda"));
                entrega.setVenda(venda);

                entregas.add(entrega);
            }

        }catch (SQLException e){
            this.status = "Error";
            e.printStackTrace();
        }
        return entregas;
    }

    //retrieve delivery DONE
    public Entrega getEntrega(int id){

        Entrega entrega = new Entrega();

        try(Connection connection = new ConectaDB().getConexao()){

            this.sql = "SELECT * FROM entrega WHERE id_entrega = ?;";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id);
            this.resultSet = this.preparedStatement.executeQuery();

            while(this.resultSet.next()){
                entrega.setId_entrega(this.resultSet.getInt("id_entrega"));
                entrega.setEndereco_entrega(this.resultSet.getString("endereco_entrega"));

                Entregador entregador= new EntregadorDAO().getEntregador(this.resultSet.getInt("id_entregador"));
                entrega.setEntregador(entregador);

                Venda venda = new VendaDAO().getVenda(this.resultSet.getInt("id_venda"));
                entrega.setVenda(venda);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return entrega;
    }

    //update delivery DONE
    public String atualizar(Entrega entrega){
        try(Connection connection = new ConectaDB().getConexao() ){

            connection.setAutoCommit(false);

            this.sql = "UPDATE entrega SET endereco_entrega = ?, id_entregador = ?, id_venda = ? WHERE id_entrega = ?;";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, entrega.getEndereco_entrega());
            this.preparedStatement.setInt(2, entrega.getEntregador().getId_entregador());
            this.preparedStatement.setInt(3, entrega.getVenda().getId_venda());
            this.preparedStatement.setInt(4, entrega.getId_entrega());

            this.preparedStatement.executeUpdate();

            if(this.preparedStatement.getUpdateCount() > 0){
                this.status = "OK";
                connection.commit();
            }

        }catch (SQLException e){
            this.status = "Error";
            e.printStackTrace();
        }
        return this.status;
    }

    //create delivery DONE
    public String cadastrar(Entrega entrega){
        try(Connection connection = new ConectaDB().getConexao() ){

            connection.setAutoCommit(false);

            this.sql = "INSERT INTO entrega(endereco_entrega, id_entregador, id_venda) VALUES (?, ?, ?);";
            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, entrega.getEndereco_entrega());
            this.preparedStatement.setInt(2, entrega.getEntregador().getId_entregador());
            this.preparedStatement.setInt(3, entrega.getVenda().getId_venda());
            this.preparedStatement.execute();

            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();

            if(this.resultSet.getInt(1) > 0){
                this.status = "OK";
                connection.commit();
            }

        }catch (SQLException e){
            this.status = "Error";
            e.printStackTrace();
        }
        return this.status;
    }

    //delete delivery DONE
    public String deletar(Entrega entrega){

        try(Connection connection = new ConectaDB().getConexao() ){

            connection.setAutoCommit(false);

            this.sql = "DELETE FROM entrega WHERE id_entrega = ?;";
            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setInt(1, entrega.getId_entrega());
            this.preparedStatement.executeUpdate();

            if(this.preparedStatement.getUpdateCount()>0){
                this.status = "OK";
                connection.commit();
            }

        }catch (SQLException e){
            this.status = "Error";
            e.printStackTrace();
        }
        return this.status;
    }

}
