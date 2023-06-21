<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Tópicos</title>
    </head>

    <body>
        <h1>Tópicos</h1>
        <h2>Entrada</h2>
        <c:if test="${mensagem != null && !mensagem.isBlank()}">
            <h3>${mensagem}</h3>
        </c:if>
        <form action="login" method="post">
            <table>
                <tbody>
                    <tr>
                        <td><label for="login">Login:</label></td>
                        <td><input type="text" name="login" id="login"></td>
                    </tr>
                    <tr>
                        <td><label for="senha">Senha:</label></td>
                        <td><input type="password" name="senha" id="senha"></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Entrar"></td>
                    </tr>
                </tbody>
            </table>
        </form>
        <h3><a href="cadastrar.jsp">Cadastrar</a></h3>
    </body>

</html>