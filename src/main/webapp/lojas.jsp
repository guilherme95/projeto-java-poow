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
    <title>Lojas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
    <style>
        <%@include file="resources/css/lojas.css"%>
    </style>
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
                    <a class="nav-link" href="http://localhost:8080/Projeto/clientes">Clientes</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="http://localhost:8080/Projeto/entregadores">Entregadores</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="http://localhost:8080/Projeto/lojas">Lojas</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="http://localhost:8080/Projeto/produtos">Produtos</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="http://localhost:8080/Projeto/vendas">Vendas</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="http://localhost:8080/Projeto/entregas">Entregas</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<c:choose>
    <c:when test="${loja.id_loja != null}">
        <h1>Editar loja</h1>
        <input type="hidden" name="idloja" value="${loja.id_loja}">
    </c:when>
    <c:otherwise>
        <h1>Adicionar loja</h1>
        <input type="hidden" name="idloja" value="0">
    </c:otherwise>
</c:choose>

<form action="loja-controller" method="post">
    <div class="mb-3 col-md-3" id="label-left">
        <label class="form-label" for="nome">Nome</label>
        <input class="form-control" id="nome" name="nome" type="text" value="${loja.nome_loja}"/>
    </div>

    <div class="mb-3" id="divBtnSubmitClient">
        <input type="hidden" name="opcao" value="gravar">
        <input class="btn btn-success" id="btnSubmitClient" type="submit" value="Cadastrar"/>
    </div>
</form>

<c:if test="${retorno == 'OK'}">
    <h2>Loja cadastrada com sucesso</h2>
</c:if>

<c:if test="${retorno == 'Excluido'}">
    <h2>Loja excluída com sucesso</h2>
</c:if >

<section>

    <h1>Lojas</h1>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Nome</th>
            <th>Ações</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="loja" items="${lojas}">
            <tr>
                <td>${loja.nome_loja}</td>
                <td>
                    <a class="btn btn-primary" href="http://localhost:8080/Projeto/loja-controller?opcao=editar&&id=${loja.id_loja}">Editar</a>
                    <a class="btn btn-danger" href="http://localhost:8080/Projeto/loja-controller?opcao=excluir&&id=${loja.id_loja}">Excluir</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</section>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
</body>
</html>
