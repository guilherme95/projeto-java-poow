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
        <title>Login</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
        <link href="resources/css/login.css" rel="stylesheet">
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
                        <a class="nav-link active" href="http://localhost:8080/Projeto/">Login</a>
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
                </ul>
            </div>
        </div>
    </nav>

        <h1>Faça seu Login</h1>
        <form action="login-controller" method="post">

            <div class="mb-3 col-md-3">
                <label class="form-label" for="email">Email</label>
                <input class="form-control" id="email" name="email" type="text" placeholder="digite seu email"/>
            </div>

            <div class="mb-3 col-md-3">
                <label class="form-label" for="senha">Senha</label>
                <input class="form-control" id="senha" type="password" name="senha" placeholder="digite sua senha"/>
            </div>

            <div class="mb-3" id="divBtnSubmitClient">
                <input type="hidden" name="opcao" value="login">
                <input class="btn btn-success" id="btnSubmitClient" type="submit" value="Acessar"/>
            </div>
        </form>
        <c:if test="${retorno == 'ERROR'}">
            <h2>Email ou senha incorretas</h2>
        </c:if >
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
    </body>
</html>
