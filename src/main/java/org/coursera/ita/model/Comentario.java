package org.coursera.ita.model;

public class Comentario {
    
    private Integer id;
    private String comentario;
    private String login;
    private Integer idTopico;

    public Comentario() {
    }

    public Comentario(Integer id, String comentario, String login, Integer idTopico) {
        this.id = id;
        this.comentario = comentario;
        this.login = login;
        this.idTopico = idTopico;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getIdTopico() {
        return idTopico;
    }

    public void setIdTopico(Integer idTopico) {
        this.idTopico = idTopico;
    }

    @Override
    public String toString() {
        return "Comentario{" + "id=" + id + ", comentario=" + comentario + ", login=" + login + ", idTopico=" + idTopico + '}';
    }
    
}
