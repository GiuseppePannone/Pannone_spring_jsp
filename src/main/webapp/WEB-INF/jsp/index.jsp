<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Elenco Corsi</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
</head>
<body class="container mt-4">
    <h1 class="text-center mb-4">Università degli Studi RomaTrenta</h1>
    <div class="d-flex flex-column justify-content-center align-items-center vh-100">
        <a class="btn btn-primary mb-3" href="<c:url value='/docenti/lista'/>">Lista dei docenti</a>
        <a class="btn btn-primary mb-3" href="<c:url value ='/discenti/lista' />">Lista dei discenti</a>
        <a class="btn btn-primary mb-3" href="<c:url value='/corsi/lista'/>">Lista dei corsi</a>
    </div>
</body>
</html>