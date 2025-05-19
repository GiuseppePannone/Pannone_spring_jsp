<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
    <title>
        <c:choose>
            <c:when test="${isEdit}">Modifica Corso</c:when>
            <c:otherwise>Nuovo Corso</c:otherwise>
        </c:choose>
    </title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
</head>
<body class="container mt-4">
    <h1>
        <c:choose>
            <c:when test="${isEdit}">Modifica Corso</c:when>
            <c:otherwise>Nuovo Corso</c:otherwise>
        </c:choose>
    </h1>

    <a class="btn btn-warning mb-3" href="<c:url value='/corsi/lista'/>">Torna a Lista corsi</a>
    <div class="row mt-4 justify-content-center">
        <div class="col-6">
         <form:form class="bg-light p-4 border rounded" method="POST" action="${pageContext.request.contextPath}/corsi/add" modelAttribute="corso" >
         <form:hidden path="id"/>

                <div class="mb-3">
                  <label for="exampleFormControlInput1" class="form-label">Nome del corso</label>
                  <form:input type="text" cssClass="form-control" path="nomeCorso" placeholder="Inserisci il nome..."/>
                </div>
                 <div class="mb-3">
                    <label for="exampleFormControlInput1" class="form-label">Ore del corso</label>
                    <form:input type="number" cssClass="form-control" path="oreCorso"/>
                 </div>
                  <div class="mb-3">
                      <label for="exampleFormControlInput1" class="form-label">Anno accademico</label>
                      <form:input type="number" cssClass="form-control" path="annoAccademico"  />
                  </div>
                  <div class="mb-3">
                      <label for="docente" class="form-label">Scegli il docente</label>
                      <form:select path="docente.id" class="form-control">
                          <c:forEach var="docente" items="${docenti}">
                              <option value="${docente.id}"
                                      <c:if test="${corso.docente.id != null}">selected="selected"</c:if>>
                                      ${docente.nome} ${docente.cognome}
                              </option>
                          </c:forEach>
                      </form:select>
                  </div>
             <div class="mb-3">
                 <label for="discente" class="form-label">Scegli gli alunni</label>
                 <div class="form-check">
                     <c:forEach var="discente" items="${discenti}">
                         <div class="form-check">
                             <form:checkbox path="discentiIds" value="${discente.id}" class="form-check-input" />
                             <label class="form-check-label">
                                     ${discente.nome} ${discente.cognome}
                             </label>
                         </div>
                     </c:forEach>
                 </div>
             </div>



             <div class="mb-3 d-flex justify-content-end">
                  <button type="submit" class="btn btn-success mt-4">
                     <a>Salva Corso</a>
                   </button>
                  </div>
            </form:form>
        </div>
    </div>

</body>
</html>
