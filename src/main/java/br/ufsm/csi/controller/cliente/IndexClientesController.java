package br.ufsm.csi.controller.cliente;

import br.ufsm.csi.dao.ClienteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/clientes")
public class IndexClientesController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("clientes", new ClienteDAO().getClientes());
        RequestDispatcher rd = req.getRequestDispatcher("/clientes.jsp");
        rd.forward(req, resp);
        System.out.println("...index");
    }
}