package br.ufsm.csi.dao;

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

    //get all
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
                venda.setValor_venda(this.resultSet.getDouble("valor_venda"));
                venda.setForma_pagamento(this.resultSet.getString("forma_pagamento"));
                venda.setQuantidade(this.resultSet.getDouble("quantidade"));
                venda.setCliente((Cliente) this.resultSet.getObject("id_cliente"));
                venda.setProduto((Produto) this.resultSet.getObject("id_produto"));

                vendas.add(venda);
            }

        }catch (SQLException e){
            this.status = "Error";
            e.printStackTrace();
        }
        return vendas;
    }

    //get one
    public Venda getCliente(Venda venda){
        try(Connection connection = new ConectaDB().getConexao() ){

            this.sql = "SELECT * FROM venda WHERE id_vendae = ? OR data_venda = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, venda.getId_venda());
            this.preparedStatement.setDate(2, (Date) venda.getData_venda());

            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getResultSet();
            this.resultSet.next();

            while(this.resultSet.next()){
                venda.setId_venda(this.resultSet.getInt("id_venda"));
                venda.setData_venda(this.resultSet.getDate("data_venda"));
                venda.setValor_venda(this.resultSet.getDouble("valor_venda"));
                venda.setForma_pagamento(this.resultSet.getString("forma_pagamento"));
                venda.setQuantidade(this.resultSet.getDouble("quantidade"));
                venda.setCliente((Cliente) this.resultSet.getObject("id_cliente"));
                venda.setProduto((Produto) this.resultSet.getObject("id_produto"));
            }

        }catch (SQLException e){
            this.status = "Error";
            e.printStackTrace();
        }
        return venda;
    }

    //update
    public String update(Venda venda){
        try(Connection connection = new ConectaDB().getConexao() ){

            connection.setAutoCommit(false);

            this.sql = "UPDATE venda SET data_venda = ?, valor_venda = ?, quantidade = ?, forma_pagamento = ? WHERE id_cliente = ?";
            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setDate(1, (Date) venda.getData_venda());
            this.preparedStatement.setDouble(2, venda.getValor_venda());
            this.preparedStatement.setDouble(3, venda.getQuantidade());
            this.preparedStatement.setString(4, venda.getForma_pagamento());
            this.preparedStatement.setInt(5, venda.getId_venda());

            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();

            if(this.resultSet.getInt(1) > 0){
                venda.setId_venda(this.resultSet.getInt(1));
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
    public String create(Venda venda){
        try(Connection connection = new ConectaDB().getConexao() ){

            connection.setAutoCommit(false);

            this.sql = "INSERT INTO venda (data_venda, valor_venda, quantidade, forma_pagamento, id_produto, id_cliente) VALUES data_venda = ?, valor_venda = ?, quantidade = ?, forma_pagamento = ?, id_produto = ? , id_cliente = ?";
            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setDate(1, (Date) venda.getData_venda());
            this.preparedStatement.setDouble(2, venda.getValor_venda());
            this.preparedStatement.setDouble(3, venda.getQuantidade());
            this.preparedStatement.setString(4, venda.getForma_pagamento());
            this.preparedStatement.setObject(5, venda.getProduto());
            this.preparedStatement.setObject(6, venda.getCliente());

            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();

            if(this.resultSet.getInt(1) > 0){
                venda.setId_venda(this.resultSet.getInt(1));
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
    public String delete(Venda venda){
        try(Connection connection = new ConectaDB().getConexao() ){

            connection.setAutoCommit(false);

            this.sql = "DELETE FROM venda WHERE id_venda = ?";
            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setInt(1, venda.getId_venda());

            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();

            if(this.resultSet.getInt(1) > 0){
                venda.setId_venda(this.resultSet.getInt(1));
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
