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
  <title>Entregas</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
  <style>
    <%@include file="resources/css/entregadores.css"%>
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
          <a class="nav-link" href="http://localhost:8080/Projeto/vendas">Vendas</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" href="http://localhost:8080/Projeto/entregas">Entregas</a>
        </li>
      </ul>
    </div>
  </div>
</nav>

<c:choose>
  <c:when test="${venda.id_venda != null}">
    <h1>Editar entrega</h1>
    <input type="hidden" name="identrega" value="${venda.id_venda}">
  </c:when>
  <c:otherwise>
    <h1>Adicionar entrega</h1>
    <input type="hidden" name="identrega" value="0">
  </c:otherwise>
</c:choose>

<form action="entrega-controller" method="post">
  <div class="mb-3 col-md-3">
    <label class="form-label" for="endereco">Endereço da entrega</label>
    <input class="form-control" id="endereco" name="endereco" type="text" value="${entrega.endereco_entrega}"/>
  </div>

  <div class="mb-3 col-md-3">
    <label class="form-label" for="venda">Venda</label>

    <select class="form-select" aria-label="Default select example" id="venda" name="venda">

      <c:choose>
        <c:when test="${venda.id_venda == null}">
          <option selected disabled>Selecione uma venda</option>
        </c:when>
      </c:choose>
      <c:forEach var="venda" items="${vendas}">
        <option value="${venda.id_venda}">${venda.id_venda}</option>
      </c:forEach>
    </select>
  </div>

  <div class="mb-3 col-md-3">
    <label class="form-label" for="entregador">Entregador</label>

    <select class="form-select" aria-label="Default select example" id="entregador" name="entregador">

      <c:choose>
        <c:when test="${entregador.id_entregador == null}">
          <option selected disabled>Selecione um entregador</option>
        </c:when>
      </c:choose>
      <c:forEach var="ent" items="${entregadores}">
        <option value="${ent.id_entregador}">${ent.usuario.nome_usuario}</option>
      </c:forEach>
    </select>
  </div>

  <div class="mb-3" id="divBtnSubmitClient">
    <input type="hidden" name="opcao" value="gravar">
    <input class="btn btn-success" id="btnSubmitClient" type="submit" value="Cadastrar"/>
  </div>
</form>

<c:if test="${retorno == 'OK'}">
  <h2>Entrega cadastrada com sucesso</h2>
</c:if>

<c:if test="${retorno == 'Excluido'}">
  <h2>Entrega excluída com sucesso</h2>
</c:if >

<section>

  <h1>Entregas</h1>
  <table class="table table-striped">
    <thead>
    <tr>
      <th>Endereço</th>
      <th>Entregador</th>
      <th>Venda</th>
      <th>Ações</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="entrega" items="${entregas}">
      <tr>
        <td>${entrega.endereco_entrega}</td>
        <td>${entrega.entregador.usuario.nome_usuario}</td>
        <td>${entrega.venda.id_venda}</td>
        <td>
          <a class="btn btn-primary" href="http://localhost:8080/Projeto/entrega-controller?opcao=editar&&id=${entrega.id_entrega}">Editar</a>
          <a class="btn btn-danger" href="http://localhost:8080/Projeto/entrega-controller?opcao=excluir&&id=${entrega.id_entrega}">Excluir</a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>

</section>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
</body>
</html>
