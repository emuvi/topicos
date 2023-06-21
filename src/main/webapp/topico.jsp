<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>T�picos</title>
    </head>

    <body>
        <h1><a href="topicos">T�picos</a></h1>
        <h2>T�pico</h2>
    <c:if test="${mensagem != null && !mensagem.isBlank()}">
        <h3>${mensagem}</h3>
    </c:if>
    <div><label for="nome">Titulo:</label></div>
    <div>${topico.titulo}</div>
    <hr>
    <div>Conte�do:</div>
    <div>${topico.conteudo}</div>
    <hr>
    <div>Usu�rio</div>
    <div>${topico.login}</div>
    <hr>
    <table>
        <tr>
            <td>Coment�rio</td>
            <td>Usu�rio</td>
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