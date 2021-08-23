package br.ufsm.csi.dao;

import br.ufsm.csi.connection.ConectaDB;
import br.ufsm.csi.model.Loja;

import java.sql.*;
import java.util.ArrayList;

public class LojaDAO {

    private String sql;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String status;

    //get all
    public ArrayList<Loja> getLojas(){

        ArrayList<Loja> lojas = new ArrayList<Loja>();

        try(Connection connection = new ConectaDB().getConexao() ){

            this.sql = "SELECT * FROM loja";
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

    //get one
    public Loja getLoja(Loja loja){
        try(Connection connection = new ConectaDB().getConexao() ){

            this.sql = "SELECT * FROM loja WHERE id_loja = ? OR nome_loja LIKE ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, loja.getId_loja());
            this.preparedStatement.setString(2, '%'+loja.getNome_loja()+'%');

            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getResultSet();
            this.resultSet.next();

            while(this.resultSet.next()){
                loja.setId_loja(this.resultSet.getInt("id_loja"));
                loja.setNome_loja(this.resultSet.getString("nome_loja"));
            }

        }catch (SQLException e){
            this.status = "Error";
            e.printStackTrace();
        }
        return loja;
    }

    //update
    public String update(Loja loja){
        try(Connection connection = new ConectaDB().getConexao() ){

            connection.setAutoCommit(false);

            this.sql = "UPDATE loja SET nome_loja = ? WHERE id_loja = ?";
            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, loja.getNome_loja());
            this.preparedStatement.setInt(2, loja.getId_loja());

            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();

            if(this.resultSet.getInt(1) > 0){
                loja.setId_loja(this.resultSet.getInt(1));
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
    public String create(Loja loja){
        try(Connection connection = new ConectaDB().getConexao() ){

            connection.setAutoCommit(false);

            this.sql = "INSERT INTO loja (nome_loja) VALUES nome_loja = ?";
            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, loja.getNome_loja());

            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();

            if(this.resultSet.getInt(1) > 0){
                loja.setId_loja(this.resultSet.getInt(1));
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
    public String delete(Loja loja){
        try(Connection connection = new ConectaDB().getConexao() ){

            connection.setAutoCommit(false);

            this.sql = "DELETE FROM loja WHERE id_loja = ?";
            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setInt(1, loja.getId_loja());

            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();

            if(this.resultSet.getInt(1) > 0){
                loja.setId_loja(this.resultSet.getInt(1));
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
