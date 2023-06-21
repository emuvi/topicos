<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Tópicos</title>
    </head>

    <body>
        <h1><a href="topicos">Tópicos</a></h1>
        <h2>Tópico</h2>
    <c:if test="${mensagem != null && !mensagem.isBlank()}">
        <h3>${mensagem}</h3>
    </c:if>
    <div><label for="nome">Titulo:</label></div>
    <div>${topico.titulo}</div>
    <hr>
    <div>Conteúdo:</div>
    <div>${topico.conteudo}</div>
    <hr>
    <div>Usuário</div>
    <div>${topico.login}</div>
    <hr>
    <table>
        <tr>
            <td>Comentário</td>
            <td>Usuário</td>
        </tr>
        <c:forEach var="c" items="${comentarios}">
            <tr>
                <td>${c.comentario}</td>
                <td>${c.login}</td>
            </tr>
        </c:forEach>
    </table>
    <hr>
    <form action="comentar" method="post">
        <input type="hidden" name="id" id="id" value="${topico.id}" />
        <textarea name="comentario" id="comentario" ></textarea>
        <input type="submit" value="Comentar" />
    </form>
</body>

</html>