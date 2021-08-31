package br.ufsm.csi.controller.venda;

import br.ufsm.csi.dao.ClienteDAO;
import br.ufsm.csi.dao.ProdutoDAO;
import br.ufsm.csi.dao.VendaDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/vendas")
public class IndexVendaController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("produtos", new ProdutoDAO().getProdutos());
        req.setAttribute("clientes", new ClienteDAO().getClientes());
        req.setAttribute("vendas", new VendaDAO().getVendas());
        RequestDispatcher rd = req.getRequestDispatcher("/vendas.jsp");
        rd.forward(req, resp);
    }
}
