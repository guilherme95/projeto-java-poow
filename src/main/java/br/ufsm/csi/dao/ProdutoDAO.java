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

    //get all products DONE
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
                produto.setValor_produto(this.resultSet.getString("valor_produto"));

                Loja loja = new LojaDAO().getLoja(this.resultSet.getInt("id_loja"));
                produto.setLoja(loja);

                produtos.add(produto);
            }

        }catch (SQLException e){
            this.status = "Error";
            e.printStackTrace();
        }
        return produtos;
    }

    //retrieve product DONE
    public Produto getProduto(int id){

        Produto produto = new Produto();

        try(Connection connection = new ConectaDB().getConexao()){

            this.sql = "SELECT * FROM produto WHERE id_produto = ?;";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id);
            this.resultSet = this.preparedStatement.executeQuery();

            while(this.resultSet.next()){
                produto.setId_produto(this.resultSet.getInt("id_produto"));
                produto.setNome_produto(this.resultSet.getString("nome_produto"));
                produto.setValor_produto(this.resultSet.getString("valor_produto"));

                Loja loja = new LojaDAO().getLoja(this.resultSet.getInt("id_loja"));
                produto.setLoja(loja);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return produto;
    }

    //update product DONE
    public String atualizar(Produto produto){

        try(Connection connection = new ConectaDB().getConexao() ){

            connection.setAutoCommit(false);

            this.sql = "UPDATE produto SET nome_produto = ?, valor_produto = ?, id_loja = ? WHERE id_produto = ?;";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, produto.getNome_produto());
            this.preparedStatement.setString(2, produto.getValor_produto());
            this.preparedStatement.setInt(3, produto.getLoja().getId_loja());
            this.preparedStatement.setInt(4, produto.getId_produto());
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

    //create product DONE
    public String cadastrar(Produto produto){

        try(Connection connection = new ConectaDB().getConexao() ){

            connection.setAutoCommit(false);

            this.sql = "INSERT INTO produto(nome_produto, valor_produto, id_loja) VALUES (?, ?, ?);";
            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, produto.getNome_produto());
            this.preparedStatement.setString(2, produto.getValor_produto());
            this.preparedStatement.setInt(3, produto.getLoja().getId_loja());
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

    //delete product DONE
    public String deletar(Produto produto){

        try(Connection connection = new ConectaDB().getConexao() ){

            connection.setAutoCommit(false);

            this.sql = "DELETE FROM produto WHERE id_produto = ?;";
            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setInt(1, produto.getId_produto());
            this.preparedStatement.executeUpdate();

            if(this.preparedStatement.getUpdateCount()>0){
                this.status = "Excluído";
                connection.commit();
            }

        }catch (SQLException e){
            this.status = "Error";
            e.printStackTrace();
        }
        return this.status;
    }
}
