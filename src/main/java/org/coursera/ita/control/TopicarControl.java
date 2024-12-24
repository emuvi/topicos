package org.coursera.ita.control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.coursera.ita.data.TopicoData;
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
            TopicoData.get().inserir(new Topico(null, titulo, conteudo, logado.getLogin()));
            req.setAttribute("mensagem", "Novo tópico salvo com sucesso.");
            resp.sendRedirect(req.getContextPath() + "/topicos");
        } catch (Exception e) {
            req.setAttribute("mensagem", e.getMessage());
            getServletContext().getRequestDispatcher("/topicar.jsp").forward(req, resp);
        }
    }

}
