<!DOCTYPE html>
<html lang="en">

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
</body>

</html>