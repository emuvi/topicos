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
        <h2>Cadastro</h2>
    <c:if test="${mensagem != null && !mensagem.isBlank()}">
        <h3>${mensagem}</h3>
    </c:if>
    <form action="cadastrar" method="post">
        <table>
            <tr>
                <td><label for="nome">Nome:</label></td>
                <td><input type="text" name="nome" id="nome"></td>
            </tr>
            <tr>
                <td><label for="login">Login:</label></td>
                <td><input type="text" name="login" id="login"></td>
            </tr>
            <tr>
                <td><label for="email">Email:</label></td>
                <td><input type="text" name="email" id="email"></td>
            </tr>
            <tr>
                <td><label for="senha">Senha:</label></td>
                <td><input type="password" name="senha" id="senha"></td>
            </tr>
            <tr>
                <td><label for="confirma">Confirmação:</label></td>
                <td><input type="password" name="confirma" id="confirma"></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Cadastrar"></td>
            </tr>
        </table>
    </form>
</body>

</html>