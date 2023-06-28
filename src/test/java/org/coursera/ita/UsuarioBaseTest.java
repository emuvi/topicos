package org.coursera.ita;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;
import org.coursera.ita.base.Base;
import org.coursera.ita.base.UsuarioBase;
import org.coursera.ita.model.Usuario;

import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.junit.Before;
import org.junit.Test;

public class UsuarioBaseTest {

    UsuarioBase usuarioBase;
    JdbcDatabaseTester jdt;

    @Before
    public void setUp() throws Exception {
        usuarioBase = new UsuarioBase();
        jdt = new JdbcDatabaseTester(Base.driver, Base.caminho, Base.usuario, Base.senha);
        FlatXmlDataSet data = new FlatXmlDataSet(new File("data.xml"));
        jdt.setDataSet(data);
        jdt.onSetup();
    }

    @Test
    public void testaInserir() throws Exception {
        Usuario novo = new Usuario();
        novo.setLogin("novo-login");
        novo.setNome("Novo Login");
        novo.setEmail("novo-email@domain.com");
        novo.setPontos(5);
        usuarioBase.inserir(novo);
        Usuario inserido = usuarioBase.recuperar("novo-login");
        assertEquals(inserido.getLogin(), "novo-login");
        assertEquals(inserido.getNome(), "Novo Login");
        assertEquals(inserido.getEmail(), "novo-email@domain.com");
        assertEquals(inserido.getPontos(), (Integer) 5);
    }

    @Test
    public void testaRecuperar() throws Exception {
        Usuario antigo = usuarioBase.recuperar("jose");
        assertEquals(antigo.getLogin(), "jose");
        assertEquals(antigo.getNome(), "Jose");
        assertEquals(antigo.getEmail(), "jose@mail.com");
        assertEquals(antigo.getPontos(), (Integer) 6);
    }

    @Test
    public void testaAdicionarPontos() throws Exception {
        usuarioBase.adicionarPontos("jose", 4);
        Usuario adicionado = usuarioBase.recuperar("jose");
        assertEquals(adicionado.getPontos(), (Integer) 10);
    }

    @Test
    public void testaRanking() throws Exception {
        List<Usuario> ranking = usuarioBase.ranking();
        assertEquals(ranking.get(0).getLogin(), "maria");
        assertEquals(ranking.get(1).getLogin(), "jose");
        assertEquals(ranking.get(2).getLogin(), "joao");
    }

}
