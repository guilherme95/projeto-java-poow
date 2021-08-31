package br.ufsm.csi.controller.entrega;

import br.ufsm.csi.dao.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/entregas")
public class IndexEntregaController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("entregadores", new EntregadorDAO().getEntregadores());
        req.setAttribute("vendas", new VendaDAO().getVendas());
        req.setAttribute("entregas", new EntregaDAO().getEntregas());
        RequestDispatcher rd = req.getRequestDispatcher("/entregas.jsp");
        rd.forward(req, resp);
    }
}
