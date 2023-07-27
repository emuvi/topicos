package org.coursera.ita.data;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.coursera.ita.model.Comentario;

public class ComentarioData implements Closeable {

    private final Connection connection;

    public ComentarioData() throws Exception {
        this.connection = Acesso.novo();
    }

    public ComentarioData(Connection connection) throws Exception {
        this.connection = connection;
    }

    public void inserir(Comentario c) throws Exception {
        PreparedStatement stm = connection
                .prepareStatement("INSERT INTO comentario (comentario, login, id_topico) VALUES (?, ?, ?)");
        stm.setString(1, c.getComentario());
        stm.setString(2, c.getLogin());
        stm.setInt(3, c.getIdTopico());
        stm.executeUpdate();
    }

    public Comentario recuperar(int id) throws Exception {
        PreparedStatement stm = connection
                .prepareStatement("SELECT comentario, login, id_topico FROM comentario WHERE id_comentario = ?");
        stm.setInt(1, id);
        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            Comentario result = new Comentario();
            result.setId(id);
            result.setComentario(rst.getString("comentario"));
            result.setLogin(rst.getString("login"));
            result.setIdTopico(rst.getInt("id_topico"));
            return result;
        } else {
            return null;
        }
    }
    
    

    public List<Comentario> listar(int idTopico) throws Exception {
        PreparedStatement stm = connection
                .prepareStatement("SELECT id_comentario, comentario, login FROM comentario WHERE id_topico = ?");
        stm.setInt(1, idTopico);
        ResultSet rst = stm.executeQuery();
        List<Comentario> result = new ArrayList<>();
        while (rst.next()) {
            Comentario c = new Comentario();
            c.setId(rst.getInt("id_comentario"));
            c.setComentario(rst.getString("comentario"));
            c.setLogin(rst.getString("login"));
            c.setIdTopico(idTopico);
            result.add(c);
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
