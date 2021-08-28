package br.ufsm.csi.dao;

import br.ufsm.csi.connection.ConectaDB;
import br.ufsm.csi.model.Entregador;
import br.ufsm.csi.model.Usuario;

import java.sql.*;
import java.util.ArrayList;

public class EntregadorDAO {

    private String sql;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String status;

    //get all deliverymens DONE
    public ArrayList<Entregador> getEntregadores(){

        ArrayList<Entregador> entregadores = new ArrayList<Entregador>();

        try(Connection connection = new ConectaDB().getConexao() ){

            this.sql = "SELECT " +
                    "u.id_usuario, e.id_entregador, u.nome_usuario, u.rg_usuario, u.cpf_usuario, u.tel_usuario, e.cnh_entregador, u.email_usuario, u.senha_usuario " +
                    "FROM " +
                    "usuario u, entregador e " +
                    "WHERE " +
                    "u.id_usuario = e.id_usuario;";
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

                Entregador entregador = new Entregador();
                entregador.setCnh_entregador(this.resultSet.getString("cnh_entregador"));
                entregador.setUsuario(usuario);
                entregador.setId_entregador(this.resultSet.getInt("id_entregador"));
                entregadores.add(entregador);
            }

        }catch (SQLException e){
            this.status = "Error";
            e.printStackTrace();
        }
        return entregadores;
    }

    //retrieve deliverymen DONE
    public Entregador getEntregador(int id){
        Entregador entregador = null;
        try(Connection connection = new ConectaDB().getConexao()){
            this.sql = "SELECT * FROM entregador WHERE id_entregador = ?;";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id);
            this.resultSet = this.preparedStatement.executeQuery();

            while(this.resultSet.next()){
                int id_usuario = this.resultSet.getInt("id_usuario");
                String cnh_entregador = this.resultSet.getString("cnh_entregador");
                entregador = new Entregador(id, cnh_entregador, new UsuarioDAO().getUsuario(id_usuario));
                System.out.println(entregador.getId_entregador());
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return entregador;
    }

    //update deliverymen DONE
    public String atualizar(Entregador entregador){
        try(Connection connection = new ConectaDB().getConexao() ){

            connection.setAutoCommit(false);

            String retorno = new UsuarioDAO().atualizar(entregador.getUsuario(), connection);

            if(retorno.equals("OK")){

                this.sql = "UPDATE entregador SET cnh_entregador = ? WHERE id_entregador = ?;";
                this.preparedStatement = connection.prepareStatement(this.sql);
                this.preparedStatement.setString(1, entregador.getCnh_entregador());
                this.preparedStatement.setInt(2, entregador.getId_entregador());
                this.preparedStatement.executeUpdate();

                if(this.preparedStatement.getUpdateCount() > 0){
                    this.status = "OK";
                    connection.commit();
                }

            }

        }catch (SQLException e){
            this.status = "Error";
            e.printStackTrace();
        }
        return this.status;
    }

    //create deliverymen DONE
    public String cadastrar(Entregador entregador){
        try(Connection connection = new ConectaDB().getConexao() ){

            connection.setAutoCommit(false);

            Usuario usuario = new UsuarioDAO().cadastrar(entregador.getUsuario(), connection);

            if(usuario != null){
                this.sql = "INSERT INTO entregador(id_usuario, cnh_entregador) VALUES (?, ?);";
                this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
                this.preparedStatement.setInt(1, usuario.getId_usuario());
                this.preparedStatement.setString(2, entregador.getCnh_entregador());
                this.preparedStatement.execute();

                this.resultSet = this.preparedStatement.getGeneratedKeys();
                this.resultSet.next();

                if(this.resultSet.getInt(1) > 0){
                    this.status = "OK";
                    connection.commit();
                }
            }

        }catch (SQLException e){
            this.status = "Error";
            e.printStackTrace();
        }
        return this.status;
    }

    //delete deliverymen DONE
    public String deletar(Entregador entregador){

        try(Connection connection = new ConectaDB().getConexao() ){

            connection.setAutoCommit(false);

            this.sql = "DELETE FROM entregador WHERE id_entregador = ?;";
            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setInt(1, entregador.getId_entregador());
            this.preparedStatement.executeUpdate();

            if(this.preparedStatement.getUpdateCount()>0){
                String retorno = new UsuarioDAO().deletar(entregador.getUsuario(), connection);
                if(retorno.equals("OK")){
                    this.status = "OK";
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
