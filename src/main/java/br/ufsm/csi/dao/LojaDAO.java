package br.ufsm.csi.dao;

import br.ufsm.csi.connection.ConectaDB;
import br.ufsm.csi.model.Entregador;
import br.ufsm.csi.model.Loja;
import br.ufsm.csi.model.Usuario;

import java.sql.*;
import java.util.ArrayList;

public class LojaDAO {

    private String sql;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String status;

    //get all stores DONE
    public ArrayList<Loja> getLojas(){

        ArrayList<Loja> lojas = new ArrayList<Loja>();

        try(Connection connection = new ConectaDB().getConexao() ){

            this.sql = "SELECT * FROM loja;";
            this.statement = connection.createStatement();
            this.resultSet = this.statement.executeQuery(sql);

            while(this.resultSet.next()){
                Loja loja = new Loja();
                loja.setId_loja(this.resultSet.getInt("id_loja"));
                loja.setNome_loja(this.resultSet.getString("nome_loja"));

                lojas.add(loja);
            }

        }catch (SQLException e){
            this.status = "Error";
            e.printStackTrace();
        }
        return lojas;
    }

    //retrieve store DONE
    public Loja getLoja(int id){
        Loja loja = new Loja();

        try(Connection connection = new ConectaDB().getConexao()){

            this.sql = "select * from loja where id_loja = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id);

            this.resultSet = this.preparedStatement.executeQuery();

            while(this.resultSet.next()){
                loja.setId_loja(this.resultSet.getInt("id_loja"));
                loja.setNome_loja(this.resultSet.getString("nome_loja"));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return loja;
    }

    //update store DONE
    public String atualizar(Loja loja){
        try(Connection connection = new ConectaDB().getConexao() ){

            connection.setAutoCommit(false);

            this.sql = "UPDATE loja SET nome_loja = ? WHERE id_loja = ?;";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, loja.getNome_loja());
            this.preparedStatement.setInt(2, loja.getId_loja());
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

    //create store DONE
    public String cadastrar(Loja loja){
        try(Connection connection = new ConectaDB().getConexao() ){

            connection.setAutoCommit(false);


            this.sql = "INSERT INTO loja(nome_loja) VALUES (?);";
            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, loja.getNome_loja());
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

    //delete store DONE
    public String deletar(Loja loja){

        try(Connection connection = new ConectaDB().getConexao() ){

            connection.setAutoCommit(false);

            this.sql = "DELETE FROM loja WHERE id_loja = ?;";
            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setInt(1, loja.getId_loja());
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
