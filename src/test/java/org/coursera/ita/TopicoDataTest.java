package org.coursera.ita;

import org.coursera.ita.data.TopicoData;
import org.coursera.ita.data.UsuarioData;
import org.coursera.ita.model.Topico;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class TopicoDataTest {

    @Test
    public void testaInserir() throws Exception {
        Topico novo = new Topico();
        novo.setTitulo("Novo Tópico");
        novo.setConteudo("este é um novo tópico");
        novo.setLogin("joao");
        UsuarioData.get().criarMassaDeTeste();
        TopicoData.get().inserir(novo);
        var recuperado = TopicoData.get().recuperar(1);
        assertNotNull(recuperado);
    }

    @Test
    public void testaRecuperar() throws Exception {
        TopicoData.get().criarMassaDeTeste();
        Topico topico = TopicoData.get().recuperar(1);
        assertEquals(topico.getTitulo(), "As baleias jubarte");
        assertEquals(topico.getConteudo(), "As baleias jubartes são grandes");
        assertEquals(topico.getLogin(), "joao");
    }

}
