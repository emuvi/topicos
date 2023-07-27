package org.coursera.ita.data;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.coursera.ita.model.Topico;

public class TopicoData implements Closeable {

    private final Connection connection;

    public TopicoData() throws Exception {
        this.connection = Acesso.novo();
    }

    public TopicoData(Connection connection) throws Exception {
        this.connection = connection;
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

    @Override
    public void close() throws IOException {
        try {
            connection.close();
        } catch (SQLException ex) {
            throw new IOException(ex);
        }
    }

}
