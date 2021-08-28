package br.ufsm.csi.controller.cliente;

import br.ufsm.csi.dao.ClienteDAO;
import br.ufsm.csi.model.Cliente;
import br.ufsm.csi.model.Usuario;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("cliente-controller")
public class ClienteController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ClienteDAO clienteDAO = new ClienteDAO();
        String opcao = "";

        if(req.getParameter("opcao")!=null){
            opcao = req.getParameter("opcao");
        }

        System.out.println("Em cima do controller");

        String retorno = "";

        if(opcao.equals("excluir")){
            String id = req.getParameter("id");
            System.out.println("ID do cliente a ser excluído: "+id);

            Cliente cliente = new ClienteDAO().getCliente(Integer.parseInt(id));
            System.out.println("ID_CLIENTE: "+cliente.getId_cliente());
            retorno = clienteDAO.deletar(cliente);
            System.out.println("excluído!!!");

        }else if(opcao.equals("editar")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Cliente cliente = new ClienteDAO().getCliente(id);
            req.setAttribute("cliente", cliente);
        }else{
            String nome = req.getParameter("nome");
            String rg = req.getParameter("rg");
            String cpf = req.getParameter("cpf");
            String telefone = req.getParameter("telefone");
            String email = req.getParameter("email");
            String senha = req.getParameter("senha");

            Usuario usuario = new Usuario(nome, rg, cpf, telefone, email, senha);
            int id = Integer.parseInt(req.getParameter("idcliente"));

            if(id>0){
                usuario.setId_usuario(Integer.parseInt(req.getParameter("idusuario")));
                Cliente cliente = new Cliente(id, usuario);
                retorno = clienteDAO.atualizar(cliente);
            }else{
                Cliente cliente = new Cliente(usuario);
                retorno = clienteDAO.cadastrar(cliente);
            }
        }

        req.setAttribute("retorno", retorno);
        RequestDispatcher rd = req.getRequestDispatcher("/clientes");
        rd.forward(req, resp);
    }
}
