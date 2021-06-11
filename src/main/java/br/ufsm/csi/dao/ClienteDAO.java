package br.ufsm.csi.dao;

import br.ufsm.csi.model.Cliente;

import java.sql.*;
import java.util.ArrayList;

public class ClienteDAO {

    private String sql;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String status;

    //get all
    public ArrayList<Cliente> getClientes(){

        ArrayList<Cliente> clientes = new ArrayList<Cliente>();

        try(Connection connection = new ConectaDB().getConexao() ){

            this.sql = "SELECT * FROM cliente";
            this.statement = connection.createStatement();
            this.resultSet = this.statement.executeQuery(sql);

            while(this.resultSet.next()){
                Cliente cliente = new Cliente();
                cliente.setId_cliente(this.resultSet.getInt("id_cliente"));
                cliente.setNome_cliente(this.resultSet.getString("nome_cliente"));
                cliente.setCpf_cliente(this.resultSet.getString("cpf_cliente"));
                cliente.setRg_cliente(this.resultSet.getString("rg_cliente"));

                clientes.add(cliente);
            }

        }catch (SQLException e){
            this.status = "Error";
            e.printStackTrace();
        }
        return clientes;
    }

    //get one
    public Cliente getCliente(Cliente cliente){
        try(Connection connection = new ConectaDB().getConexao() ){

            this.sql = "SELECT * FROM cliente WHERE id_cliente = ? OR nome_cliente LIKE ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, cliente.getId_cliente());
            this.preparedStatement.setString(2, '%'+cliente.getNome_cliente()+'%');

            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getResultSet();
            this.resultSet.next();

            while(this.resultSet.next()){
                cliente.setId_cliente(this.resultSet.getInt("id_loja"));
                cliente.setNome_cliente(this.resultSet.getString("nome_cliente"));
                cliente.setRg_cliente(this.resultSet.getString("rg_cliente"));
                cliente.setCpf_cliente(this.resultSet.getString("cpf_cliente"));
            }

        }catch (SQLException e){
            this.status = "Error";
            e.printStackTrace();
        }
        return cliente;
    }

    //update
    public String update(Cliente cliente){
        try(Connection connection = new ConectaDB().getConexao() ){

            connection.setAutoCommit(false);

            this.sql = "UPDATE cliente SET nome_cliente = ?, rg_cliente = ?, cpf_cliente = ? WHERE id_cliente = ?";
            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, cliente.getNome_cliente());
            this.preparedStatement.setString(2, cliente.getRg_cliente());
            this.preparedStatement.setString(3, cliente.getCpf_cliente());
            this.preparedStatement.setInt(4, cliente.getId_cliente());

            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();

            if(this.resultSet.getInt(1) > 0){
                cliente.setId_cliente(this.resultSet.getInt(1));
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
    public String create(Cliente cliente){
        try(Connection connection = new ConectaDB().getConexao() ){

            connection.setAutoCommit(false);

            this.sql = "INSERT INTO cliente (nome_cliente, rg_cliente, cpf_cliente) VALUES nome_cliente = ?, rg_cliente = ?, cpf_cliente = ?";
            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, cliente.getNome_cliente());
            this.preparedStatement.setString(2, cliente.getRg_cliente());
            this.preparedStatement.setString(3, cliente.getCpf_cliente());

            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();

            if(this.resultSet.getInt(1) > 0){
                cliente.setId_cliente(this.resultSet.getInt(1));
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
    public String delete(Cliente cliente){
        try(Connection connection = new ConectaDB().getConexao() ){

            connection.setAutoCommit(false);

            this.sql = "DELETE FROM cliente WHERE id_cliente = ?";
            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setInt(1, cliente.getId_cliente());

            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();

            if(this.resultSet.getInt(1) > 0){
                cliente.setId_cliente(this.resultSet.getInt(1));
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
