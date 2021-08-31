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
    <title>Vendas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
    <style>
        <%@include file="resources/css/vendas.css"%>
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
                    <a class="nav-link" href="http://localhost:8080/Projeto/lojas">Lojas</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="http://localhost:8080/Projeto/produtos">Produtos</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="http://localhost:8080/Projeto/vendas">Vendas</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="http://localhost:8080/Projeto/entregas">Entregas</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<c:choose>
    <c:when test="${venda.id_venda != null}">
        <h1>Editar venda</h1>
    </c:when>
    <c:otherwise>
        <h1>Adicionar venda</h1>
    </c:otherwise>
</c:choose>

<form action="venda-controller" method="post">

    <c:choose>
        <c:when test="${venda.id_venda != null}">
            <input type="hidden" name="idvenda" value="${venda.id_venda}">
        </c:when>
        <c:otherwise>
            <input type="hidden" name="idvenda" value="0">
        </c:otherwise>
    </c:choose>

    <c:choose>
        <c:when test="${venda.id_venda != null}">
            <div class="mb-3 col-md-3">
                <label class="form-label" for="data">Data da venda</label>
                <input class="form-control" id="data" name="data" type="text" value="${venda.data_venda}" disabled/>
            </div>
        </c:when>
    </c:choose>

    <div class="mb-3 col-md-3">
        <label class="form-label" for="quantidade">Quantidade</label>
        <input class="form-control" id="quantidade" name="quantidade" type="number" value="${venda.quantidade}"/>
    </div>

    <div class="mb-3 col-md-3">
        <label class="form-label" for="forma_pagamento">Forma do pagamento</label>
        <select class="form-select" aria-label="Default select example" id="forma_pagamento" name="forma_pagamento">
        <c:choose>
            <c:when test="${venda.id_venda != null}">
                <c:choose>
                    <c:when test="${venda.forma_pagamento=='Credito'}">
                        <option value="Credito" selected>Credito</option>
                        <option value="Debito">Debito</option>
                        <option value="Dinheiro">Dinheiro</option>
                    </c:when>
                    <c:when test="${venda.forma_pagamento=='Debito'}">
                        <option value="Credito">Credito</option>
                        <option value="Debito" selected>Debito</option>
                        <option value="Dinheiro">Dinheiro</option>
                    </c:when>
                    <c:when test="${venda.forma_pagamento=='Dinheiro'}">
                        <option value="Credito">Credito</option>
                        <option value="Debito">Debito</option>
                        <option value="Dinheiro" selected>Dinheiro</option>
                    </c:when>
                </c:choose>
            </c:when>
            <c:otherwise>
                <option selected disabled>Selecione forma de pagamento</option>
                <option value="Credito">Credito</option>
                <option value="Debito">Debito</option>
                <option value="Dinheiro">Dinheiro</option>
            </c:otherwise>
        </c:choose>
        </select>
    </div>

    <div class="mb-3 col-md-3">
        <label class="form-label" for="produto">Produto</label>

        <select class="form-select" aria-label="Default select example" id="produto" name="produto">

            <c:choose>
                <c:when test="${venda.produto.id_produto == null}">
                    <option selected disabled>Selecione uma produto</option>
                </c:when>
            </c:choose>
            <c:forEach var="prod" items="${produtos}">
                <option value="${prod.id_produto}">${prod.nome_produto}</option>
            </c:forEach>
        </select>
    </div>

    <div class="mb-3 col-md-3">
        <label class="form-label" for="cliente">Cliente</label>

        <select class="form-select" aria-label="Default select example" id="cliente" name="cliente">

            <c:choose>
                <c:when test="${venda.cliente.id_cliente == null}">
                    <option selected disabled>Selecione um cliente</option>
                </c:when>
            </c:choose>
            <c:forEach var="cli" items="${clientes}">
                <option value="${cli.id_cliente}">${cli.usuario.nome_usuario}</option>
            </c:forEach>
        </select>
    </div>

    <div class="mb-3" id="divBtnSubmitClient">
        <input type="hidden" name="opcao" value="gravar">
        <input class="btn btn-success" id="btnSubmitClient" type="submit" value="Cadastrar"/>
    </div>
</form>

<c:if test="${retorno == 'OK'}">
    <h2>Venda cadastrada com sucesso</h2>
</c:if>

<c:if test="${retorno == 'Excluido'}">
    <h2>Venda excluída com sucesso</h2>
</c:if >

<section>

    <h1>Vendas</h1>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Data da venda</th>
            <th>Quantidade</th>
            <th>Forma do pagamento</th>
            <th>Produto</th>
            <th>Cliente</th>
            <th>Ações</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="venda" items="${vendas}">
            <tr>
                <td>${venda.data_venda}</td>
                <td>${venda.quantidade}</td>
                <td>${venda.forma_pagamento}</td>
                <td>${venda.produto.nome_produto}</td>
                <td>${venda.cliente.usuario.nome_usuario}</td>
                <td>
                    <a class="btn btn-primary" href="http://localhost:8080/Projeto/venda-controller?opcao=editar&&id=${venda.id_venda}">Editar</a>
                    <a class="btn btn-danger" href="http://localhost:8080/Projeto/venda-controller?opcao=excluir&&id=${venda.id_venda}">Excluir</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</section>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
</body>
</html>
