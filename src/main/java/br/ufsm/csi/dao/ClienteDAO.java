package br.ufsm.csi.dao;

import br.ufsm.csi.connection.ConectaDB;
import br.ufsm.csi.model.Cliente;
import br.ufsm.csi.model.Usuario;
import java.sql.*;
import java.util.ArrayList;

public class ClienteDAO {

    private String sql;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String status;

    //get all clients DONE
    public ArrayList<Cliente> getClientes(){

        ArrayList<Cliente> clientes = new ArrayList<Cliente>();

        try(Connection connection = new ConectaDB().getConexao() ){

            this.sql = "SELECT " +
                    "u.id_usuario, c.id_cliente, u.nome_usuario, u.rg_usuario, u.cpf_usuario, u.tel_usuario, u.email_usuario, u.senha_usuario " +
                    "FROM " +
                    "usuario u, cliente c " +
                    "WHERE " +
                    "u.id_usuario = c.id_usuario;";
            this.statement = connection.createStatement();
            this.resultSet = this.statement.executeQuery(sql);

            while(this.resultSet.next()){
                Usuario usuario = new Usuario();
                usuario.setId_usuario(this.resultSet.getInt("id_usuario"));
                usuario.setNome_usuario(this.resultSet.getString("nome_usuario"));
                usuario.setRg_usuario(this.resultSet.getString("rg_usuario"));
                usuario.setCpf_usuario(this.resultSet.getString("cpf_usuario"));
                usuario.setTel_usuario(this.resultSet.getString("tel_usuario"));
                usuario.setEmail_usuario(this.resultSet.getString("email_usuario"));
                usuario.setSenha_usuario(this.resultSet.getString("senha_usuario"));

                Cliente cliente = new Cliente(usuario);
                cliente.setId_cliente(this.resultSet.getInt("id_cliente"));
                clientes.add(cliente);
            }

        }catch (SQLException e){
            this.status = "Error";
            e.printStackTrace();
        }
        return clientes;
    }

    //retrieve client DONE
    public Cliente getCliente(int id){

        Cliente cliente = null;

        try(Connection connection = new ConectaDB().getConexao()){

            this.sql = "SELECT * FROM cliente WHERE id_cliente = ?;";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id);
            this.resultSet = this.preparedStatement.executeQuery();

            while(this.resultSet.next()){
                int id_usuario = this.resultSet.getInt("id_usuario");
                cliente = new Cliente(id, new UsuarioDAO().getUsuario(id_usuario));
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return cliente;
    }

    //update client DONE
    public String atualizar(Cliente cliente){
        try(Connection connection = new ConectaDB().getConexao() ){

            connection.setAutoCommit(false);

            String retorno = new UsuarioDAO().atualizar(cliente.getUsuario(), connection);

            if(retorno.equals("OK")){
                this.status = "OK";
                connection.commit();
            }

        }catch (SQLException e){
            this.status = "Error";
            e.printStackTrace();
        }
        return this.status;
    }

    //create client DONE
    public String cadastrar(Cliente cliente){
        try(Connection connection = new ConectaDB().getConexao() ){

            connection.setAutoCommit(false);

            Usuario usuario = new UsuarioDAO().cadastrar(cliente.getUsuario(), connection);

            if(usuario != null){
                this.sql = "INSERT INTO cliente(id_usuario) VALUES (?);";
                this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
                this.preparedStatement.setInt(1, usuario.getId_usuario());
                this.preparedStatement.execute();
                this.resultSet = this.preparedStatement.getGeneratedKeys();
                this.resultSet.next();

                if(this.resultSet.getInt(1) > 0){
                    this.status = "OK";
                }

                if(this.status.equals("OK")){
                    connection.commit();
                }
            }

        }catch (SQLException e){
            this.status = "Error";
            e.printStackTrace();
        }
        return this.status;
    }

    //delete client DONE
    public String deletar(Cliente cliente){

        try(Connection connection = new ConectaDB().getConexao() ){

            connection.setAutoCommit(false);

            this.sql = "DELETE FROM cliente WHERE id_cliente = ?;";
            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setInt(1, cliente.getId_cliente());
            this.preparedStatement.executeUpdate();

            if(this.preparedStatement.getUpdateCount()>0){
                String retorno = new UsuarioDAO().deletar(cliente.getUsuario(), connection);

                if(retorno.equals("Excluído")){
                    this.status = "Excluído";
                    connection.commit();
                }
            }

        }catch (SQLException e){
            this.status = "Error";
            e.printStackTrace();
        }
        return this.status;
    }

}
