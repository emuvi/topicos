package org.coursera.ita.base;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.coursera.ita.model.Comentario;

public class ComentarioBase implements Closeable {

    private final Connection connection;

    public ComentarioBase() throws Exception {
        this.connection = Base.nova();
    }

    public ComentarioBase(Connection connection) throws Exception {
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
                .prepareStatement("SELECT comentario, login, id_topico FROM topico WHERE id_comentario = ?");
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

    @Override
    public void close() throws IOException {
        try {
            connection.close();
        } catch (SQLException ex) {
            throw new IOException(ex);
        }
    }

}
