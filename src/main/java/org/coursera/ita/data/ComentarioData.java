package org.coursera.ita.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.coursera.ita.model.Comentario;

public class ComentarioData {
    
    private final static ComentarioData DATA = new ComentarioData();
    
    public static ComentarioData get() {
        return DATA;
    }

    private final Connection connection;

    private ComentarioData() {
        this.connection = Acesso.acessar();
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
    
    private volatile boolean massaTesteCriada = false;

    public synchronized void criarMassaDeTeste() throws Exception {
        if (massaTesteCriada) {
            return;
        }
        TopicoData.get().criarMassaDeTeste();
        inserir(new Comentario(1, "As baleias são lindas", "maria", 1));
        inserir(new Comentario(2, "As baleias comem peixes", "jose", 1));
        inserir(new Comentario(3, "Os pinguins são maneiros", "joao", 2));
        inserir(new Comentario(4, "Os pinguins gostam de nadar", "jose", 2));
        inserir(new Comentario(5, "kkkkk, eu conheço algumas", "joao", 3));
        inserir(new Comentario(6, "Aí que pegadinha falar isso", "maria", 3));
        massaTesteCriada = true;
    }

}
