package br.ufsm.csi.controller.produto;

import br.ufsm.csi.dao.ClienteDAO;
import br.ufsm.csi.dao.LojaDAO;
import br.ufsm.csi.dao.ProdutoDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/produtos")
public class IndexProdutoController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("produtos", new ProdutoDAO().getProdutos());
        req.setAttribute("lojas", new LojaDAO().getLojas());
        RequestDispatcher rd = req.getRequestDispatcher("/produtos.jsp");
        rd.forward(req, resp);
    }
}
