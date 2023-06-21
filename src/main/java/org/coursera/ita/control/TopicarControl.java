package org.coursera.ita.control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.coursera.ita.base.TopicoBase;
import org.coursera.ita.model.Topico;
import org.coursera.ita.model.Usuario;

@WebServlet("/topicar")
public class TopicarControl extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Usuario logado = (Usuario) req.getSession().getAttribute("logado");
            if (logado == null) {
                throw new Exception("Você deve estar logado para criar um tópico.");
            }
            String titulo = req.getParameter("titulo");
            if (titulo == null || titulo.isBlank()) {
                throw new Exception("Você deve preencher o título.");
            }
            String conteudo = req.getParameter("conteudo");
            if (conteudo == null || conteudo.isBlank()) {
                throw new Exception("Você deve preencher o conteúdo.");
            }
            new TopicoBase().inserir(new Topico(null, titulo, conteudo, logado.getLogin()));
            req.setAttribute("mensagem", "Novo tópico salvo com sucesso.");
            resp.sendRedirect(req.getContextPath() + "/topicos");
        } catch (Exception e) {
            req.setAttribute("mensagem", e.getMessage());
            getServletContext().getRequestDispatcher("/topicar.jsp").forward(req, resp);
        }
    }

}
