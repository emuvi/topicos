package org.coursera.ita.control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.coursera.ita.base.UsuarioBase;
import org.coursera.ita.model.Usuario;

@WebServlet("/cadastrar")
public class CadastrarControl extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String nome = req.getParameter("nome");
            if (nome == null || nome.isBlank()) {
                throw new Exception("Você deve preencher o nome.");
            }
            String login = req.getParameter("login");
            if (login == null || login.isBlank()) {
                throw new Exception("Você deve preencher o login.");
            }
            String email = req.getParameter("email");
            if (email == null || email.isBlank()) {
                throw new Exception("Você deve preencher o email.");
            }
            String senha = req.getParameter("senha");
            if (senha == null || senha.isBlank()) {
                throw new Exception("Você deve preencher a senha.");
            }
            String confirma = req.getParameter("confirma");
            if (confirma == null || confirma.isBlank()) {
                throw new Exception("Você deve preencher a confirmação.");
            }
            if (!senha.equals(confirma)) {
                throw new Exception("A senha e a confirmação precisam ser iguai.");
            }
            new UsuarioBase().inserir(new Usuario(login, email, nome, senha, 0));
            req.setAttribute("mensagem", "Cadastro realizado com sucesso.");
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("mensagem", e.getMessage());
            getServletContext().getRequestDispatcher("/cadastrar.jsp").forward(req, resp);
        }
    }

}
