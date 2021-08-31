package br.ufsm.csi.dao;

import br.ufsm.csi.connection.ConectaDB;
import br.ufsm.csi.model.Cliente;
import br.ufsm.csi.model.Produto;
import br.ufsm.csi.model.Venda;
import java.sql.*;
import java.util.ArrayList;

public class VendaDAO {

    private String sql;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String status;

    //get all sales DONE
    public ArrayList<Venda> getVendas(){

        ArrayList<Venda> vendas = new ArrayList<Venda>();

        try(Connection connection = new ConectaDB().getConexao() ){

            this.sql = "SELECT * FROM venda";
            this.statement = connection.createStatement();
            this.resultSet = this.statement.executeQuery(sql);

            while(this.resultSet.next()){
                Venda venda = new Venda();
                venda.setId_venda(this.resultSet.getInt("id_venda"));
                venda.setData_venda(this.resultSet.getDate("data_venda"));
                venda.setQuantidade(this.resultSet.getDouble("quantidade"));
                venda.setForma_pagamento(this.resultSet.getString("forma_pagamento"));

                Cliente cliente = new ClienteDAO().getCliente(this.resultSet.getInt("id_cliente"));
                venda.setCliente(cliente);

                Produto produto = new ProdutoDAO().getProduto(this.resultSet.getInt("id_produto"));
                venda.setProduto(produto);

                vendas.add(venda);
            }

        }catch (SQLException e){
            this.status = "Error";
            e.printStackTrace();
        }
        return vendas;
    }

    //retrieve sale DONE
    public Venda getVenda(int id){

        Venda venda = new Venda();

        try(Connection connection = new ConectaDB().getConexao()){

            this.sql = "SELECT * FROM venda WHERE id_venda = ?;";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id);
            this.resultSet = this.preparedStatement.executeQuery();

            while(this.resultSet.next()){
                venda.setId_venda(this.resultSet.getInt("id_venda"));
                venda.setData_venda(this.resultSet.getDate("data_venda"));
                venda.setQuantidade(this.resultSet.getDouble("quantidade"));
                venda.setForma_pagamento(this.resultSet.getString("forma_pagamento"));

                Cliente cliente = new ClienteDAO().getCliente(this.resultSet.getInt("id_cliente"));
                venda.setCliente(cliente);

                Produto produto = new ProdutoDAO().getProduto(this.resultSet.getInt("id_produto"));
                venda.setProduto(produto);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return venda;
    }

    //update sale DONE
    public String atualizar(Venda venda){

        try(Connection connection = new ConectaDB().getConexao() ){

            connection.setAutoCommit(false);

            this.sql = "UPDATE venda SET quantidade = ?, forma_pagamento = ?, id_produto = ?, id_cliente = ?  WHERE id_venda = ?;";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setDouble(1, venda.getQuantidade());
            this.preparedStatement.setString(2, venda.getForma_pagamento());
            this.preparedStatement.setInt(3, venda.getProduto().getId_produto());
            this.preparedStatement.setInt(4, venda.getCliente().getId_cliente());
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

    //create sale DONE
    public String cadastrar(Venda venda){
        try(Connection connection = new ConectaDB().getConexao() ){

            connection.setAutoCommit(false);

            this.sql = "INSERT INTO venda(data_venda, quantidade, forma_pagamento, id_produto, id_cliente) VALUES (CURRENT_DATE , ?, ?, ?, ?);";
            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setDouble(1, venda.getQuantidade());
            this.preparedStatement.setString(2, venda.getForma_pagamento());
            this.preparedStatement.setInt(3, venda.getProduto().getId_produto());
            this.preparedStatement.setInt(4, venda.getCliente().getId_cliente());
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

    //delete sale DONE
    public String deletar(Venda venda){

        try(Connection connection = new ConectaDB().getConexao() ){

            connection.setAutoCommit(false);

            this.sql = "DELETE FROM venda WHERE id_venda = ?;";
            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setInt(1, venda.getId_venda());
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
