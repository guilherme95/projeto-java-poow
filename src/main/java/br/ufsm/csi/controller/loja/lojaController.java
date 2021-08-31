package br.ufsm.csi.controller.loja;

import br.ufsm.csi.dao.EntregadorDAO;
import br.ufsm.csi.dao.LojaDAO;
import br.ufsm.csi.model.Entregador;
import br.ufsm.csi.model.Loja;
import br.ufsm.csi.model.Usuario;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loja-controller")
public class lojaController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LojaDAO lojaDAO = new LojaDAO();
        String opcao = "";
        String retorno = "";

        if(req.getParameter("opcao")!=null){
            opcao = req.getParameter("opcao");
        }

        if(opcao.equals("excluir")){
            int id = Integer.parseInt(req.getParameter("id"));
            Loja loja = new LojaDAO().getLoja(id);
            retorno = lojaDAO.deletar(loja);

        }else if(opcao.equals("editar")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Loja loja = new LojaDAO().getLoja(id);
            req.setAttribute("loja", loja);

        }else{
            String nome = req.getParameter("nome");
            int id = Integer.parseInt(req.getParameter("idloja"));

            if(id>0){
                Loja loja = new Loja(id, nome);
                retorno = lojaDAO.atualizar(loja);
            }else{
                Loja loja = new Loja(nome);
                retorno = lojaDAO.cadastrar(loja);
            }
        }
        req.setAttribute("retorno", retorno);
        RequestDispatcher rd = req.getRequestDispatcher("/lojas");
        rd.forward(req, resp);
    }
}
