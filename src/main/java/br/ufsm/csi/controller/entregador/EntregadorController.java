package br.ufsm.csi.controller.entregador;

import br.ufsm.csi.dao.EntregadorDAO;
import br.ufsm.csi.model.Entregador;
import br.ufsm.csi.model.Usuario;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("entregador-controller")
public class EntregadorController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntregadorDAO entregadorDAO = new EntregadorDAO();
        String opcao = "";
        String retorno = "";

        if(req.getParameter("opcao")!=null){
            opcao = req.getParameter("opcao");
        }

        if(opcao.equals("excluir")){
            int id = Integer.parseInt(req.getParameter("id"));
            Entregador entregador = new EntregadorDAO().getEntregador(id);
            retorno = entregadorDAO.deletar(entregador);

        }else if(opcao.equals("editar")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Entregador entregador = new EntregadorDAO().getEntregador(id);
            req.setAttribute("entregador", entregador);
        }else{
            String nome = req.getParameter("nome");
            String rg = req.getParameter("rg");
            String cpf = req.getParameter("cpf");
            String telefone = req.getParameter("telefone");
            String cnh = req.getParameter("cnh");
            String email = req.getParameter("email");
            String senha = req.getParameter("senha");
            Usuario usuario = new Usuario(nome, rg, cpf, telefone, email, senha);
            int id = Integer.parseInt(req.getParameter("identregador"));

            if(id>0){
                usuario.setId_usuario(Integer.parseInt(req.getParameter("idusuario")));
                Entregador entregador = new Entregador(id, cnh, usuario);
                retorno = entregadorDAO.atualizar(entregador);
            }else{
                Entregador entregador = new Entregador(cnh, usuario);
                retorno = entregadorDAO.cadastrar(entregador);
            }
        }
        req.setAttribute("retorno", retorno);
        RequestDispatcher rd = req.getRequestDispatcher("/entregadores");
        rd.forward(req, resp);
    }
}
