<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Elenco Docenti</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
</head>
<body class="container mt-4">
<h1>Elenco Docenti</h1>

<a class="btn btn-primary mb-3" href="<c:url value='/docenti/nuovo'/>">Nuovo Docente</a>
<a class="btn btn-warning mb-3" href="<c:url value='/' />">Torna indietro</a>

<table class="table table-striped">
    <thead>
    <tr>
        <th>ID</th><th>Nome</th><th>Cognome</th><th>Data di nascita</th><th>Corso associato</th><th>Azioni</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="d" items="${docenti}">
        <tr>
            <td>${d.id}</td>
            <td>${d.nome}</td>
            <td>${d.cognome}</td>
            <td>${d.dataDiNascita}</td>
            <td>
                <c:choose>
                <c:when test="${!d.corsi.isEmpty()}">
                <c:forEach var="corso" items="${d.corsi}">
                                        ${corso.nomeCorso}<br/>
                                    </c:forEach>
                </c:when>
                <c:otherwise>
                    Nessun corso associato
                </c:otherwise>

                </c:choose>
            </td>
            <td>
                <a class="btn btn-sm btn-secondary" href="<c:url value='/docenti/${d.id}/edit'/>">Modifica</a>
                <a class="btn btn-sm btn-danger"
                   href="<c:url value='/docenti/${d.id}/delete'/>"
                   onclick="return confirm('Sei sicuro?')">Elimina</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
