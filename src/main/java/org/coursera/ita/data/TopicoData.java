package org.coursera.ita.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.coursera.ita.model.Topico;

public class TopicoData {

    private final static TopicoData DATA = new TopicoData();

    public static TopicoData get() {
        return DATA;
    }

    private final Connection connection;

    private TopicoData() {
        this.connection = Acesso.acessar();
    }

    public void inserir(Topico t) throws Exception {
        PreparedStatement stm = connection
                .prepareStatement("INSERT INTO topico (titulo, conteudo, login) VALUES (?, ?, ?)");
        stm.setString(1, t.getTitulo());
        stm.setString(2, t.getConteudo());
        stm.setString(3, t.getLogin());
        stm.executeUpdate();
    }

    public Topico recuperar(int id) throws Exception {
        PreparedStatement stm = connection
                .prepareStatement("SELECT titulo, conteudo, login FROM topico WHERE id_topico = ?");
        stm.setInt(1, id);
        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            Topico topico = new Topico();
            topico.setId(id);
            topico.setTitulo(rst.getString("titulo"));
            topico.setConteudo(rst.getString("conteudo"));
            topico.setLogin(rst.getString("login"));
            return topico;
        } else {
            return null;
        }
    }

    public List<Topico> listar() throws Exception {
        List<Topico> result = new ArrayList<>();
        PreparedStatement stm = connection
                .prepareStatement("SELECT id_topico, titulo, conteudo, login FROM topico ORDER BY id_topico");
        ResultSet rst = stm.executeQuery();
        while (rst.next()) {
            Topico topico = new Topico();
            topico.setId(rst.getInt("id_topico"));
            topico.setTitulo(rst.getString("titulo"));
            topico.setConteudo(rst.getString("conteudo"));
            topico.setLogin(rst.getString("login"));
            result.add(topico);
        }
        return result;
    }

    private volatile boolean massaTesteCriada = false;

    public synchronized void criarMassaDeTeste() throws Exception {
        if (massaTesteCriada) {
            return;
        }
        UsuarioData.get().criarMassaDeTeste();
        inserir(new Topico(1, "As baleias jubarte", "As baleias jubartes são grandes", "joao"));
        inserir(new Topico(2, "Os pinguins imperadores", "Os pinguins imperadores gostam de governar", "maria"));
        inserir(new Topico(3, "As preguiças da cidade", "As preguiças da cidade gostam de dormir", "jose"));
        massaTesteCriada = true;
    }

}
