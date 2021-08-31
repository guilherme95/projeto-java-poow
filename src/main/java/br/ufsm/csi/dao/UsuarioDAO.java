package br.ufsm.csi.dao;

import br.ufsm.csi.connection.ConectaDB;
import br.ufsm.csi.model.Usuario;
import java.sql.*;
import java.util.ArrayList;

// CRUD
public class UsuarioDAO {

    private String sql;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String status;

    // get all users
    public ArrayList<Usuario> getUsuarios(){

        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

        try(Connection connection = new ConectaDB().getConexao()){

            this.sql = "SELECT * FROM usuario";
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

                usuarios.add(usuario);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return usuarios;
    }

    // get user by id
    public Usuario getUsuario(int id){
        Usuario usuario = new Usuario();

        try(Connection connection = new ConectaDB().getConexao()){

            this.sql = "select * from usuario where id_usuario = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id);
            this.resultSet = this.preparedStatement.executeQuery();

            while(this.resultSet.next()){
                usuario.setId_usuario(this.resultSet.getInt("id_usuario"));
                usuario.setNome_usuario(this.resultSet.getString("nome_usuario"));
                usuario.setRg_usuario(this.resultSet.getString("rg_usuario"));
                usuario.setCpf_usuario(this.resultSet.getString("cpf_usuario"));
                usuario.setTel_usuario(this.resultSet.getString("tel_usuario"));
                usuario.setEmail_usuario(this.resultSet.getString("email_usuario"));
                usuario.setSenha_usuario(this.resultSet.getString("senha_usuario"));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return usuario;
    }

    // create user
    public Usuario cadastrar(Usuario usuario, Connection connection) throws SQLException{

        this.sql = "INSERT INTO usuario(nome_usuario, rg_usuario, cpf_usuario, tel_usuario, email_usuario, senha_usuario) " +
                "VALUES(?, ?, ?, ?, ?, ?);";
        this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
        this.preparedStatement.setString(1, usuario.getNome_usuario());
        this.preparedStatement.setString(2, usuario.getRg_usuario());
        this.preparedStatement.setString(3, usuario.getCpf_usuario());
        this.preparedStatement.setString(4, usuario.getTel_usuario());
        this.preparedStatement.setString(5, usuario.getEmail_usuario());
        this.preparedStatement.setString(6, usuario.getSenha_usuario());

        this.preparedStatement.execute();
        this.resultSet = this.preparedStatement.getGeneratedKeys();
        this.resultSet.next();

        if(this.resultSet.getInt(1) > 0){
            usuario.setId_usuario(this.resultSet.getInt(1));
            this.status = "OK";
        }
        return usuario;
    }

    // update user DONE
    public String atualizar(Usuario usuario, Connection connection) throws SQLException{

        this.sql = "UPDATE usuario SET nome_usuario = ?, rg_usuario = ?, " +
                                    "cpf_usuario = ?, tel_usuario = ?, email_usuario = ?, " +
                                    "senha_usuario = ? WHERE id_usuario = ?";
        this.preparedStatement = connection.prepareStatement(this.sql);
        this.preparedStatement.setString(1, usuario.getNome_usuario());
        this.preparedStatement.setString(2, usuario.getRg_usuario());
        this.preparedStatement.setString(3, usuario.getCpf_usuario());
        this.preparedStatement.setString(4, usuario.getTel_usuario());
        this.preparedStatement.setString(5, usuario.getEmail_usuario());
        this.preparedStatement.setString(6, usuario.getSenha_usuario());
        this.preparedStatement.setInt(7, usuario.getId_usuario());
        this.preparedStatement.executeUpdate();

        if(this.preparedStatement.getUpdateCount() > 0){
            this.status = "OK";
        }else{
            this.status = "Error";
        }
        return this.status;
    }

    // delete user DONE
    public String deletar(Usuario usuario, Connection connection) throws SQLException{

        this.sql = "DELETE FROM usuario WHERE id_usuario = ?;";
        this.preparedStatement = connection.prepareStatement(this.sql);
        this.preparedStatement.setInt(1, usuario.getId_usuario());
        this.preparedStatement.executeUpdate();

        if(this.preparedStatement.getUpdateCount() > 0){
            this.status = "Excluído";
        }else{
            this.status = "Error";
        }
        return this.status;
    }
}
