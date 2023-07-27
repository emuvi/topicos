package org.coursera.ita.control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.coursera.ita.data.UsuarioData;
import org.coursera.ita.model.Usuario;

@WebServlet("/login")
public class LoginControl extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String login = req.getParameter("login");
            String senha = req.getParameter("senha");
            if (login == null || login.isBlank() || senha == null || senha.isBlank()) {
                throw new Exception("Você precisa preencher o login e senha.");
            }
            Usuario usuario = new UsuarioData().recuperar(login);
            if (usuario == null || !senha.equals(usuario.getSenha())) {
                throw new Exception("Usuário e/ou senha não encontrados.");
            }
            req.getSession().setAttribute("logado", usuario);
            resp.sendRedirect(req.getContextPath() + "/topicos");
        } catch (Exception ex) {
            req.getSession().setAttribute("logado", null);
            req.setAttribute("mensagem", ex.getMessage());
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }

}
