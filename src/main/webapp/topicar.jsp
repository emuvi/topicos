<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Tópicos</title>
    </head>

    <body>
        <h1><a href="index.jsp">Tópicos</a></h1>
        <h2>Novo</h2>
    <c:if test="${mensagem != null && !mensagem.isBlank()}">
        <h3>${mensagem}</h3>
    </c:if>
    <form action="topicar" method="post">
        <table>
            <tr>
                <td><label for="nome">Titulo:</label></td>
                <td><input type="text" name="titulo" id="titulo"></td>
            </tr>
            <tr>
                <td><label for="login">Conteúdo:</label></td>
                <td><textarea type="text" name="conteudo" id="conteudo"></textarea></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Salvar"></td>
            </tr>
        </table>
    </form>
</body>

</html>