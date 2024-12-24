package org.coursera.ita.control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.coursera.ita.data.ComentarioData;
import org.coursera.ita.model.Comentario;
import org.coursera.ita.model.Usuario;

@WebServlet("/comentar")
public class ComentarControl extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Usuario logado = (Usuario) req.getSession().getAttribute("logado");
            if (logado == null) {
                throw new Exception("Você deve estar logado para criar um tópico.");
            }
            String comentario = req.getParameter("comentario");
            if (comentario == null || comentario.isBlank()) {
                throw new Exception("Você deve preencher o comentário.");
            }
            String id = req.getParameter("id");
            if (id == null || id.isBlank()) {
                throw new Exception("Você deve passar o id do tópico.");
            }
            ComentarioData.get().inserir(new Comentario(null, comentario, logado.getLogin(), Integer.parseInt(id)));
            resp.sendRedirect("topico?id=" + id);
        } catch (Exception e) {
            req.setAttribute("mensagem", e.getMessage());
            getServletContext().getRequestDispatcher("/topico").forward(req, resp);
        }
    }

}
