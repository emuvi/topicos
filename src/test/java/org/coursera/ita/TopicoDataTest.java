package org.coursera.ita;

import static org.junit.Assert.assertEquals;

import java.io.File;
import org.coursera.ita.data.Acesso;
import org.coursera.ita.data.TopicoData;
import org.coursera.ita.model.Topico;

import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.junit.Before;
import org.junit.Test;

public class TopicoDataTest {

    TopicoData topicoData;
    JdbcDatabaseTester jdt;

    @Before
    public void setUp() throws Exception {
        topicoData = new TopicoData();
        jdt = new JdbcDatabaseTester(Acesso.driver, Acesso.caminho, Acesso.usuario, Acesso.senha);
        FlatXmlDataSet data = new FlatXmlDataSet(new File("data.xml"));
        jdt.setDataSet(data);
        jdt.onSetup();
    }

    @Test
    public void testaInserir() throws Exception {
        Topico novo = new Topico();
        novo.setTitulo("Novo Tópico");
        novo.setConteudo("este é um novo tópico");
        novo.setLogin("joao");
        topicoData.inserir(novo);
    }

    @Test
    public void testaRecuperar() throws Exception {
        Topico topico = topicoData.recuperar(1);
        assertEquals(topico.getTitulo(), "As baleias jubarte");
        assertEquals(topico.getConteudo(), "As baleias jubartes são grandes");
        assertEquals(topico.getLogin(), "joao");
    }

}
