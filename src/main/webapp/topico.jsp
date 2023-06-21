<!DOCTYPE html>
<html lang="en">

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
</body>

</html>