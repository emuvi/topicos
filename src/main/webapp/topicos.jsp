<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>T�picos</title>
    </head>

    <body>
        <h1><a href="index.jsp">T�picos</a></h1>
        <h2>Lista</h2>
        <c:if test="${mensagem != null && !mensagem.isBlank()}">
            <h3>${mensagem}</h3>
        </c:if>
        <h3><a href="topicar.jsp">Novo</a></h3>
        <h3><a href="ranking.jsp">Ranking</a></h3>
        <table>
            <tr>
                <td>T�tulo</td>
                <td>Usu�rio</td>
            </tr>
            <c:forEach var="topico" items="${topicos}">
                <tr>
                    <td><a href="topico?id=${topico.id}">${topico.titulo}</a></td>
                    <td>${topico.login}</td>
                </tr>
            </c:forEach>
        </table>
    </body>

</html>