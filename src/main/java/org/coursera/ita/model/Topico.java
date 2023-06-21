package org.coursera.ita.model;

public class Topico {

    private Integer id;
    private String titulo;
    private String conteudo;
    private String login;

    public Topico() {
    }

    public Topico(Integer id, String titulo, String conteudo, String login) {
        this.id = id;
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.login = login;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "Topico{" + "id=" + id + ", titulo=" + titulo + ", conteudo=" + conteudo + ", login=" + login + '}';
    }

}
