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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
    <link href="resources/css/clientes.css" rel="stylesheet">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Compra Fácil</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" href="http://localhost:8080/Projeto/">Login</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="http://localhost:8080/Projeto/clientes">Clientes</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="http://localhost:8080/Projeto/entregadores">Entregadores</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="http://localhost:8080/Projeto/lojas">Lojas</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
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
        <div class="mb-3 col-md-3">
            <label class="form-label" for="nome">Nome</label>
            <input class="form-control" id="nome" name="nome" type="text" value="${cliente.usuario.nome_usuario}"/>
        </div>

        <div class="mb-3 col-md-3">
            <label class="form-label" for="rg">RG</label>
            <input class="form-control" id="rg" type="text" name="rg" value="${cliente.usuario.rg_usuario}"/>
        </div>

        <div class="mb-3 col-md-3">
            <label class="form-label" for="cpf">CPF</label>
            <input class="form-control" id="cpf" type="text" name="cpf" value="${cliente.usuario.cpf_usuario}"/>
        </div>

        <div class="mb-3 col-md-3">
            <label class="form-label" for="telefone">Telefone</label>
            <input class="form-control" id="telefone" type="text" name="telefone" value="${cliente.usuario.tel_usuario}"/>
        </div>

        <div class="mb-3 col-md-3">
            <label class="form-label" for="email">Email</label>
            <input class="form-control" id="email" type="email" name="email" value="${cliente.usuario.email_usuario}"/>
        </div>

        <div class="mb-3 col-md-3">
            <label class="form-label" for="senha">Senha</label>
            <input class="form-control" id="senha" type="password" name="senha" value="${cliente.usuario.senha_usuario}"/>
        </div>

        <div class="mb-3" id="divBtnSubmitClient">
            <input type="hidden" name="opcao" value="gravar">
            <input class="btn btn-success" id="btnSubmitClient" type="submit" value="Cadastrar"/>
        </div>
    </form>

    <c:if test="${retorno == 'OK'}">
        <h2>Cliente cadastrado com sucesso</h2>
    </c:if>

    <c:if test="${retorno == 'Excluido'}">
        <h2>Cliente excluído com sucesso</h2>
    </c:if >

    <section>

    <h1>Clientes</h1>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Nome</th>
                    <th>RG</th>
                    <th>CPF</th>
                    <th>Telefone</th>
                    <th>Email</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="cli" items="${clientes}">
                <tr>
                    <td>${cli.usuario.nome_usuario}</td>
                    <td>${cli.usuario.rg_usuario}</td>
                    <td>${cli.usuario.cpf_usuario}</td>
                    <td>${cli.usuario.tel_usuario}</td>
                    <td>${cli.usuario.email_usuario}</td>
                    <td>
                        <a class="btn btn-primary" href="http://localhost:8080/Projeto/cliente-controller?opcao=editar&&id=${cli.id_cliente}">Editar</a>
                        <a class="btn btn-danger" href="http://localhost:8080/Projeto/cliente-controller?opcao=excluir&&id=${cli.id_cliente}">Excluir</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </section>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
</body>
</html>
