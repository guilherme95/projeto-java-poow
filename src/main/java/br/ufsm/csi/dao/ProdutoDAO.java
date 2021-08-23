package br.ufsm.csi.dao;

import br.ufsm.csi.connection.ConectaDB;
import br.ufsm.csi.model.Loja;
import br.ufsm.csi.model.Produto;

import java.sql.*;
import java.util.ArrayList;

public class ProdutoDAO {

    private String sql;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String status;

    //get all
    public ArrayList<Produto> getProdutos(){

        ArrayList<Produto> produtos = new ArrayList<Produto>();

        try(Connection connection = new ConectaDB().getConexao() ){

            this.sql = "SELECT * FROM produto";
            this.statement = connection.createStatement();
            this.resultSet = this.statement.executeQuery(sql);

            while(this.resultSet.next()){
                Produto produto = new Produto();
                produto.setId_produto(this.resultSet.getInt("id_produto"));
                produto.setNome_produto(this.resultSet.getString("nome_produto"));
                produto.setLoja((Loja) this.resultSet.getObject("id_loja"));

                produtos.add(produto);
            }

        }catch (SQLException e){
            this.status = "Error";
            e.printStackTrace();
        }
        return produtos;
    }

    //get one
    public Produto getProduto(Produto produto){
        try(Connection connection = new ConectaDB().getConexao() ){

            this.sql = "SELECT * FROM produto, loja WHERE id_produto = ? OR nome_produto LIKE ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, produto.getId_produto());
            this.preparedStatement.setString(2, '%'+produto.getNome_produto()+'%');

            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getResultSet();
            this.resultSet.next();

            while(this.resultSet.next()){
                produto.setId_produto(this.resultSet.getInt("id_produto"));
                produto.setNome_produto(this.resultSet.getString("nome_produto"));
                produto.setLoja((Loja) this.resultSet.getObject("id_loja"));
            }

        }catch (SQLException e){
            this.status = "Error";
            e.printStackTrace();
        }
        return produto;
    }

    //update
    public String update(Produto produto){
        try(Connection connection = new ConectaDB().getConexao() ){

            connection.setAutoCommit(false);

            this.sql = "UPDATE produto SET nome_produto = ? WHERE id_produto = ?";
            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, produto.getNome_produto());
            this.preparedStatement.setInt(2, produto.getId_produto());

            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();

            if(this.resultSet.getInt(1) > 0){
                produto.setId_produto(this.resultSet.getInt(1));
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
    public String create(Produto produto){
        try(Connection connection = new ConectaDB().getConexao() ){

            connection.setAutoCommit(false);

            this.sql = "INSERT INTO produto (nome_produto, id_loja) VALUES nome_loja = ?";
            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, produto.getNome_produto());
            this.preparedStatement.setObject(2, produto.getLoja());

            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();

            if(this.resultSet.getInt(1) > 0){
                produto.setId_produto(this.resultSet.getInt(1));
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
    public String delete(Produto produto){
        try(Connection connection = new ConectaDB().getConexao() ){

            connection.setAutoCommit(false);

            this.sql = "DELETE FROM produto WHERE id_produto = ?";
            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setInt(1, produto.getId_produto());

            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();

            if(this.resultSet.getInt(1) > 0){
                produto.setId_produto(this.resultSet.getInt(1));
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
