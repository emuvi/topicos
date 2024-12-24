package org.coursera.ita;

import java.util.List;
import org.coursera.ita.data.UsuarioData;
import org.coursera.ita.model.Usuario;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class UsuarioDataTest {

    @Test
    public void testaInserir() throws Exception {
        Usuario novo = new Usuario();
        novo.setLogin("novo-login");
        novo.setNome("Novo Login");
        novo.setEmail("novo-email@domain.com");
        novo.setPontos(5);
        UsuarioData.get().inserir(novo);
        Usuario inserido = UsuarioData.get().recuperar("novo-login");
        assertEquals(inserido.getLogin(), "novo-login");
        assertEquals(inserido.getNome(), "Novo Login");
        assertEquals(inserido.getEmail(), "novo-email@domain.com");
        assertEquals(inserido.getPontos(), (Integer) 5);

    }

    @Test
    public void testaRecuperar() throws Exception {
        UsuarioData.get().criarMassaDeTeste();
        Usuario antigo = UsuarioData.get().recuperar("jose");
        assertEquals(antigo.getLogin(), "jose");
        assertEquals(antigo.getNome(), "Jose");
        assertEquals(antigo.getEmail(), "jose@mail.com");
        assertEquals(antigo.getPontos(), (Integer) 6);
    }

    @Test
    public void testaAdicionarPontos() throws Exception {
        UsuarioData.get().criarMassaDeTeste();
        UsuarioData.get().adicionarPontos("jose", 4);
        Usuario adicionado = UsuarioData.get().recuperar("jose");
        assertEquals(adicionado.getPontos(), (Integer) 10);
    }

    @Test
    public void testaRanking() throws Exception {
        UsuarioData.get().criarMassaDeTeste();
        List<Usuario> ranking = UsuarioData.get().ranking();
        assertEquals(ranking.get(0).getLogin(), "maria");
        assertEquals(ranking.get(1).getLogin(), "jose");
        assertEquals(ranking.get(2).getLogin(), "joao");
    }
}
