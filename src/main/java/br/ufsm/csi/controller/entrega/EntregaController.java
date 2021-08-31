package br.ufsm.csi.controller.entrega;

import br.ufsm.csi.dao.*;
import br.ufsm.csi.model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("entrega-controller")
public class EntregaController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntregaDAO entregaDAO = new EntregaDAO();
        String opcao = "";
        String retorno = "";

        if(req.getParameter("opcao")!=null){
            opcao = req.getParameter("opcao");
        }

        if(opcao.equals("excluir")){
            int id = Integer.parseInt(req.getParameter("id"));
            Entrega entrega = new EntregaDAO().getEntrega(id);
            retorno = entregaDAO.deletar(entrega);

        }else if(opcao.equals("editar")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Entrega entrega = new EntregaDAO().getEntrega(id);
            Venda venda = new VendaDAO().getVenda(entrega.getVenda().getId_venda());
            Entregador entregador = new EntregadorDAO().getEntregador(entrega.getEntregador().getId_entregador());
            req.setAttribute("entrega", entrega);
            req.setAttribute("entregador", entregador);
            req.setAttribute("venda", venda);

        }else{
            String endereco = req.getParameter("endereco");
            int id_entrega = Integer.parseInt(req.getParameter("identrega"));
            int id_entregador = Integer.parseInt(req.getParameter("entregador"));
            int id_venda = Integer.parseInt(req.getParameter("venda"));
            Entregador entregador = new EntregadorDAO().getEntregador(id_entregador);
            Venda venda = new VendaDAO().getVenda(id_venda);

            if(id_entrega>0){
                Entrega entrega = new Entrega(id_entrega, endereco, entregador, venda);
                retorno = entregaDAO.atualizar(entrega);
            }else{
                Entrega entrega = new Entrega(endereco, entregador, venda);
                retorno = entregaDAO.cadastrar(entrega);
            }
        }

        req.setAttribute("retorno", retorno);
        RequestDispatcher rd = req.getRequestDispatcher("/entregas");
        rd.forward(req, resp);
    }
}
