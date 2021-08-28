package br.ufsm.csi.controller.loja;

import br.ufsm.csi.dao.LojaDAO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/lojas")
public class IndexLojaController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("lojas", new LojaDAO().getLojas());
        RequestDispatcher rd = req.getRequestDispatcher("lojas.jsp");
        rd.forward(req, resp);
    }
}
