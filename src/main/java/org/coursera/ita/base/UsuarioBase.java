package org.coursera.ita.base;

import java.io.Closeable;
import java.io.IOException;
import org.coursera.ita.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioBase implements Closeable {

    private final Connection connection;

    public UsuarioBase() throws Exception {
        this.connection = Base.nova();
    }

    public UsuarioBase(Connection connection) throws Exception {
        this.connection = connection;
    }

    public void inserir(Usuario u) throws Exception {
        PreparedStatement stm = connection
                .prepareStatement("INSERT INTO usuario (login, email, nome, senha, pontos) VALUES (?, ?, ?, ?, ?)");
        stm.setString(1, u.getLogin());
        stm.setString(2, u.getEmail());
        stm.setString(3, u.getNome());
        stm.setString(4, u.getSenha());
        stm.setInt(5, u.getPontos());
        stm.executeUpdate();
    }

    public Usuario recuperar(String login) throws Exception {
        PreparedStatement stm = connection
                .prepareStatement("SELECT login, email, nome, senha, pontos FROM usuario WHERE login = ?");
        stm.setString(1, login);
        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            Usuario usuario = new Usuario();
            usuario.setLogin(rst.getString("login"));
            usuario.setEmail(rst.getString("email"));
            usuario.setNome(rst.getString("nome"));
            usuario.setSenha(rst.getString("senha"));
            usuario.setPontos(rst.getInt("pontos"));
            return usuario;
        } else {
            return null;
        }
    }

    public void adicionarPontos(String login, int pontos) throws Exception {
        PreparedStatement stm = connection.prepareStatement("UPDATE usuario SET pontos = pontos + ? WHERE login = ?");
        stm.setInt(1, pontos);
        stm.setString(2, login);
        stm.executeUpdate();
    }

    public List<Usuario> ranking() throws Exception {
        List<Usuario> usuarios = new ArrayList<>();
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM usuario ORDER BY pontos DESC");
        ResultSet rst = stm.executeQuery();
        while (rst.next()) {
            Usuario usuario = new Usuario();
            usuario.setLogin(rst.getString("login"));
            usuario.setEmail(rst.getString("email"));
            usuario.setNome(rst.getString("nome"));
            usuario.setSenha(rst.getString("senha"));
            usuario.setPontos(rst.getInt("pontos"));
            usuarios.add(usuario);
        }
        return usuarios;
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
