package br.ufsm.csi.controller.login;

import br.ufsm.csi.dao.LoginDAO;
import br.ufsm.csi.model.Cliente;
import br.ufsm.csi.model.Usuario;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("login-controller")
public class LoginController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LoginDAO loginDAO = new LoginDAO();
        String opcao = "";
        String retorno = "";

        if(req.getParameter("opcao")!=null){
            opcao = req.getParameter("opcao");
        }

        if(opcao.equals("login")){

            String email_usuario = req.getParameter("email");
            String senha_usuario = req.getParameter("senha");
            Usuario usuario = new Usuario(email_usuario, senha_usuario);

            retorno = loginDAO.autorizar(usuario);

            if(retorno.equals("SUCCESS")){
                req.setAttribute("retorno", retorno);
                RequestDispatcher rd = req.getRequestDispatcher("/clientes");
                rd.forward(req, resp);
            }else{
                req.setAttribute("retorno", retorno);
                RequestDispatcher rd = req.getRequestDispatcher("/");
                rd.forward(req, resp);
            }

        }

    }
}
