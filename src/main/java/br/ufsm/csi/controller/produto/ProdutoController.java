package br.ufsm.csi.controller.produto;

import br.ufsm.csi.dao.EntregadorDAO;
import br.ufsm.csi.dao.LojaDAO;
import br.ufsm.csi.dao.ProdutoDAO;
import br.ufsm.csi.model.Entregador;
import br.ufsm.csi.model.Loja;
import br.ufsm.csi.model.Produto;
import br.ufsm.csi.model.Usuario;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("produto-controller")
public class ProdutoController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        String opcao = "";
        String retorno = "";

        if(req.getParameter("opcao")!=null){
            opcao = req.getParameter("opcao");
        }

        if(opcao.equals("excluir")){
            int id = Integer.parseInt(req.getParameter("id"));
            Produto produto = new ProdutoDAO().getProduto(id);
            retorno = produtoDAO.deletar(produto);

        }else if(opcao.equals("editar")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Produto produto = new ProdutoDAO().getProduto(id);
            req.setAttribute("produto", produto);

        }else{
            String nome = req.getParameter("nome");
            String valor = req.getParameter("valor");
            int id_loja = Integer.parseInt(req.getParameter("loja"));
            int id_produto = Integer.parseInt(req.getParameter("idproduto"));
            Loja loja = new LojaDAO().getLoja(id_loja);

            if(id_produto>0){
                Produto produto = new Produto(id_produto, nome, valor, loja);
                retorno = produtoDAO.atualizar(produto);
            }else{
                Produto produto = new Produto(nome, valor, loja);
                retorno = produtoDAO.cadastrar(produto);
            }
        }
        req.setAttribute("retorno", retorno);
        RequestDispatcher rd = req.getRequestDispatcher("/produtos");
        rd.forward(req, resp);
    }
}
