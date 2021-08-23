<%--
  Created by IntelliJ IDEA.
  User: guile
  Date: 21/06/2021
  Time: 18:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Clientes</title>
</head>
<body>
    <form action="cliente-controller" method="post">

        <c:choose>
            <c:when test="${cliente.id_cliente != null}">
                <h1>Editar cliente</h1>
                <input type="hidden" name="idcliente" value="${cliente.id_cliente}">
                <input type="hidden" name="idusuario" value="${cliente.usuario.id_usuario}">
            </c:when>
            <c:otherwise>
                <h1>Adicionar cliente</h1>
                <input type="hidden" name="idcliente" value="0">
            </c:otherwise>
        </c:choose>

        Nome: <input type="text" name="nome" value="${cliente.usuario.nome_usuario}"/> <br/>
        RG: <input type="text" name="rg" value="${cliente.usuario.rg_usuario}"/> <br/>
        CPF: <input type="text" name="cpf" value="${cliente.usuario.cpf_usuario}"/> <br/>
        Telefone: <input type="text" name="telefone" value="${cliente.usuario.tel_usuario}"/> <br/>
        Email: <input type="text" name="email" value="${cliente.usuario.email_usuario}"/> <br/>
        Senha: <input type="password" name="senha" value="${cliente.usuario.senha_usuario}"/> <br/>

        <input type="hidden" name="opcao" value="gravar">
        <input type="submit" value="GRAVAR"/>
    </form>

    <c:if test="${retorno == 'OK'}">
        <h2>Cliente cadastrado com sucesso</h2>
    </c:if>

    <c:if test="${retorno == 'Excluido'}">
        <h2>Cliente excluído com sucesso</h2>
    </c:if >

    <h1>Clientes</h1>
    <table>
        <tr>
            <th>Nome</th>
            <th>RG</th>
            <th>CPF</th>
            <th>Telefone</th>
            <th>Email</th>
            <th>Ações</th>
        </tr>
        <c:forEach var="cli" items="${clientes}">

            <tr>
                <td>${cli.usuario.nome_usuario}</td>
                <td>${cli.usuario.rg_usuario}</td>
                <td>${cli.usuario.cpf_usuario}</td>
                <td>${cli.usuario.tel_usuario}</td>
                <td>${cli.usuario.email_usuario}</td>
                <td>
                    <a href="http://localhost:8080/Projeto/cliente-controller?opcao=excluir&&id=${cli.id_cliente}">Excluir</a>
                    <a href="http://localhost:8080/Projeto/cliente-controller?opcao=editar&&id=${cli.id_cliente}">Editar</a>
                </td>
            </tr>

        </c:forEach>
    </table>
</body>
</html>
