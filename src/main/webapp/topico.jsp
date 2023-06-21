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
        <h2>T�pico</h2>
    <c:if test="${mensagem != null && !mensagem.isBlank()}">
        <h3>${mensagem}</h3>
    </c:if>
    <div><label for="nome">Titulo:</label></div>
    <div><input type="text" name="titulo" id="titulo"></div>
    <div>Conte�do:</div>
    <div><textarea type="text" name="conteudo" id="conteudo"></textarea></div>
    <div>Usu�rio</div>
    <div><input type="submit" value="Salvar"></div>
</body>

</html>