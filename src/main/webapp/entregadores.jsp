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
  <title>Entregadores</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
  <link href="resources/css/entregadores.css" rel="stylesheet">
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
            <a class="nav-link active" href="http://localhost:8080/Projeto/entregadores">Entregadores</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="http://localhost:8080/Projeto/lojas">Lojas</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="http://localhost:8080/Projeto/produtos">Produtos</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>

<form action="entregador-controller" method="post">

  <c:choose>
    <c:when test="${entregador.id_entregador != null}">
      <h1>Editar entregador</h1>
      <input type="hidden" name="identregador" value="${entregador.id_entregador}">
      <input type="hidden" name="idusuario" value="${entregador.usuario.id_usuario}">
    </c:when>
    <c:otherwise>
      <h1>Adicionar entregador</h1>
      <input type="hidden" name="identregador" value="0">
    </c:otherwise>
  </c:choose>
  <div class="mb-3 col-md-3">
    <label class="form-label" for="nome">Nome</label>
    <input class="form-control" id="nome" name="nome" type="text" value="${entregador.usuario.nome_usuario}"/>
  </div>

  <div class="mb-3 col-md-3">
    <label class="form-label" for="rg">RG</label>
    <input class="form-control" id="rg" type="text" name="rg" value="${entregador.usuario.rg_usuario}"/>
  </div>

  <div class="mb-3 col-md-3">
    <label class="form-label" for="cpf">CPF</label>
    <input class="form-control" id="cpf" type="text" name="cpf" value="${entregador.usuario.cpf_usuario}"/>
  </div>

  <div class="mb-3 col-md-3">
    <label class="form-label" for="telefone">Telefone</label>
    <input class="form-control" id="telefone" type="text" name="telefone" value="${entregador.usuario.tel_usuario}"/>
  </div>

  <div class="mb-3 col-md-3">
    <label class="form-label" for="cnh">CNH</label>
    <input class="form-control" id="cnh" type="text" name="cnh" value="${entregador.cnh_entregador}"/>
  </div>

  <div class="mb-3 col-md-3">
    <label class="form-label" for="email">Email</label>
    <input class="form-control" id="email" type="email" name="email" value="${entregador.usuario.email_usuario}"/>
  </div>

  <div class="mb-3 col-md-3">
    <label class="form-label" for="senha">Senha</label>
    <input class="form-control" id="senha" type="password" name="senha" value="${entregador.usuario.senha_usuario}"/>
  </div>

  <div class="mb-3" id="divBtnSubmitClient">
    <input type="hidden" name="opcao" value="gravar">
    <input class="btn btn-success" id="btnSubmitClient" type="submit" value="Cadastrar"/>
  </div>
</form>

<c:if test="${retorno == 'OK'}">
  <h2>Entregador cadastrado com sucesso</h2>
</c:if>

<c:if test="${retorno == 'Excluido'}">
  <h2>Entregador excluído com sucesso</h2>
</c:if >

<section>

  <h1>Entregadores</h1>
  <table class="table table-striped">
    <thead>
    <tr>
      <th>Nome</th>
      <th>RG</th>
      <th>CPF</th>
      <th>Telefone</th>
      <th>CNH</th>
      <th>Email</th>
      <th>Ações</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="ent" items="${entregadores}">
      <tr>
        <td>${ent.usuario.nome_usuario}</td>
        <td>${ent.usuario.rg_usuario}</td>
        <td>${ent.usuario.cpf_usuario}</td>
        <td>${ent.usuario.tel_usuario}</td>
        <td>${ent.cnh_entregador}</td>
        <td>${ent.usuario.email_usuario}</td>
        <td>
          <a class="btn btn-primary" href="http://localhost:8080/Projeto/entregador-controller?opcao=editar&&id=${ent.id_entregador}">Editar</a>
          <a class="btn btn-danger" href="http://localhost:8080/Projeto/entregador-controller?opcao=excluir&&id=${ent.id_entregador}">Excluir</a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>

</section>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
</body>
</html>
