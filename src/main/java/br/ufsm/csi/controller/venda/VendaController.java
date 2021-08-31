package br.ufsm.csi.controller.venda;

import br.ufsm.csi.dao.ClienteDAO;
import br.ufsm.csi.dao.ProdutoDAO;
import br.ufsm.csi.dao.VendaDAO;
import br.ufsm.csi.model.Cliente;
import br.ufsm.csi.model.Produto;
import br.ufsm.csi.model.Venda;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@WebServlet("venda-controller")
public class VendaController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        VendaDAO vendaDAO = new VendaDAO();
        String opcao = "";
        String retorno = "";

        if(req.getParameter("opcao")!=null){
            opcao = req.getParameter("opcao");
        }

        if(opcao.equals("excluir")){
            int id = Integer.parseInt(req.getParameter("id"));
            Venda venda = new VendaDAO().getVenda(id);
            retorno = vendaDAO.deletar(venda);

        }else if(opcao.equals("editar")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Venda venda = new VendaDAO().getVenda(id);
            req.setAttribute("venda", venda);

        }else{
            Double quantidade = Double.parseDouble(req.getParameter("quantidade"));
            String forma_pagamento = req.getParameter("forma_pagamento");
            int id_venda = Integer.parseInt(req.getParameter("idvenda"));
            int id_cliente = Integer.parseInt(req.getParameter("cliente"));
            int id_produto = Integer.parseInt(req.getParameter("produto"));
            Cliente cliente = new ClienteDAO().getCliente(id_cliente);
            Produto produto = new ProdutoDAO().getProduto(id_produto);

            if(id_venda>0){
                Venda venda = new Venda(id_venda, quantidade, forma_pagamento, produto, cliente);
                retorno = vendaDAO.atualizar(venda);
            }else{
                Venda venda = new Venda(quantidade, forma_pagamento, produto, cliente);
                retorno = vendaDAO.cadastrar(venda);
            }
        }
        req.setAttribute("retorno", retorno);
        RequestDispatcher rd = req.getRequestDispatcher("/vendas");
        rd.forward(req, resp);
    }
}
