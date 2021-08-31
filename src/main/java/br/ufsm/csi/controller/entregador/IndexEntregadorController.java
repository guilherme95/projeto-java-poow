package br.ufsm.csi.controller.entregador;

import br.ufsm.csi.dao.ClienteDAO;
import br.ufsm.csi.dao.EntregadorDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/entregadores")
public class IndexEntregadorController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("entregadores", new EntregadorDAO().getEntregadores());
        RequestDispatcher rd = req.getRequestDispatcher("/entregadores.jsp");
        rd.forward(req, resp);
    }
}
