package org.coursera.ita.data;

import java.sql.Connection;
import java.sql.DriverManager;

public class Acesso {

    public final static String DRIVER = "org.h2.Driver";
    public final static String CAMINHO = "jdbc:h2:mem:topicos";
    public final static String USUARIO = "sa";
    public final static String SENHA = "password";

    private static volatile Connection CONNECTION = null;
    
    private Acesso() {}
    
    private static void conectar() throws Exception {
        Class.forName(DRIVER);
        CONNECTION = DriverManager.getConnection(CAMINHO, USUARIO, SENHA);
        CONNECTION.createStatement().execute("""
                                               CREATE TABLE usuario
                                               (
                                                 login text NOT NULL,
                                                 email text,
                                                 nome text,
                                                 senha text,
                                                 pontos integer,
                                                 CONSTRAINT usuario_pkey PRIMARY KEY (login)
                                               );
                                               """);
        CONNECTION.createStatement().execute("""
                                               CREATE TABLE topico
                                               (
                                                 id_topico integer auto_increment NOT NULL,
                                                 titulo text,
                                                 conteudo text,
                                                 login text,
                                                 CONSTRAINT topico_pkey PRIMARY KEY (id_topico),
                                                 CONSTRAINT topico_login_fkey FOREIGN KEY (login) REFERENCES usuario (login)
                                               );
                                               """);
        CONNECTION.createStatement().execute("""
                                               CREATE TABLE comentario
                                               (
                                                 id_comentario integer auto_increment NOT NULL,
                                                 comentario text,
                                                 login text,
                                                 id_topico integer,
                                                 CONSTRAINT comentario_pkey PRIMARY KEY (id_comentario),
                                                 CONSTRAINT comentario_id_topico_fkey FOREIGN KEY (id_topico) REFERENCES topico (id_topico),
                                                 CONSTRAINT comentario_login_fkey FOREIGN KEY (login) REFERENCES usuario (login)
                                               );
                                               """);
        
    }
    
    public static synchronized Connection acessar() {
        if (CONNECTION == null) {
            try {
                conectar();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return CONNECTION;
    }

}
