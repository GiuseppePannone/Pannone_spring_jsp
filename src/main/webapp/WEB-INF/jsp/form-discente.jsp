<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
    <title>
    <c:choose>
        <c:when test="${isEdit}">Modifica Discente</c:when>
        <c:otherwise>Nuovo Discente</c:otherwise>
    </c:choose>
    </title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
</head>
<body class="container mt-4">
    <h1>
        <c:choose>
        <c:when test="${isEdit}">Modifica Discente</c:when>
        <c:otherwise>Nuovo Discente</c:otherwise>
        </c:choose>
    </h1>

    <a class="btn btn-warning mb-3" href="<c:url value='/discenti/lista'/>">Torna a Lista discenti</a>
    <div class="row mt-4 justify-content-center">
        <div class="col-6">
         <form:form class="bg-light p-4 border rounded" method="POST" action="${pageContext.request.contextPath}/discenti/add" modelAttribute="discente" >
         <form:hidden path="id"/>

                <div class="mb-3">
                  <label for="exampleFormControlInput1" class="form-label">Nome</label>
                  <form:input type="text" cssClass="form-control" path="nome" placeholder="Inserisci il nome..."/>
                </div>
                 <div class="mb-3">
                    <label for="exampleFormControlInput1" class="form-label">Cognome</label>
                    <form:input type="text" cssClass="form-control" path="cognome" placeholder="Inserisci il cognome..." />
                 </div>
                  <div class="mb-3">
                      <label for="exampleFormControlInput1" class="form-label">Data di nascita</label>
                      <form:input type="date" cssClass="form-control" path="dataDiNascita"/>
                  </div>
                  <div class="mb-3">
                       <label for="exampleFormControlInput1" class="form-label">Città di residenza</label>
                       <form:input type="text" cssClass="form-control" path="cittaDiResidenza" placeholder="Inserisci la città di residenza"/>
                  </div>
                  <div class="mb-3">
                        <label for="exampleFormControlInput1" class="form-label">Voto</label>
                        <form:input type="number" cssClass="form-control" path="voto"/>
                  </div>
                  <div class="mb-3 d-flex justify-content-end">
                  <button type="submit" class="btn btn-success mt-4">
                     <a>Salva Discente</a>
                   </button>
                  </div>
            </form:form>
        </div>
    </div>

</body>
</html>
