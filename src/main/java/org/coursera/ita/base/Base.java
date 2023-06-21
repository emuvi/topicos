package org.coursera.ita.base;

import java.sql.Connection;
import java.sql.DriverManager;

public class Base {

    public static String driver;
    public static String caminho;
    public static String usuario;
    public static String senha;

    static {
        driver = "org.postgresql.Driver";
        caminho = "jdbc:postgresql://localhost/Topicos";
        usuario = System.getenv("QIN_AD_TEST_USER");
        senha = System.getenv("QIN_AD_TEST_PASS");
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public static Connection nova() throws Exception {
        return DriverManager.getConnection(caminho, usuario, senha);
    }

}
