package br.ufsm.csi.dao;

import br.ufsm.csi.model.Usuario;
import java.sql.*;

public class LoginDAO {

    private String sql;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String status;

    public String autorizar(Usuario usuario) {
        if(usuario.getEmail_usuario().equals("super@email.com") && usuario.getSenha_usuario().equals("123456789")){
            this.status = "SUCCESS";
        }else{
            this.status = "ERROR";
        }
        return this.status;
    }

}
