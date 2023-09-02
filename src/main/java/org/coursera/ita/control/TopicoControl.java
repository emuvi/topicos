package org.coursera.ita.control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.coursera.ita.data.ComentarioData;
import org.coursera.ita.data.TopicoData;
import org.coursera.ita.model.Usuario;

@WebServlet("/topico")
public class TopicoControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Usuario logado = (Usuario) req.getSession().getAttribute("logado");
            if (logado == null) {
                throw new Exception("Você deve estar logado para ver um tópico.");
            }
            String id = req.getParameter("id");
            if (id == null || id.isBlank()) {
                throw new Exception("Você deve passar o id do tópico.");
            }
            req.setAttribute("topico", new TopicoData().recuperar(Integer.parseInt(id)));
            req.setAttribute("comentarios", new ComentarioData().listar(Integer.parseInt(id)));
            getServletContext().getRequestDispatcher("/topico.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("mensagem", e.getMessage());
            getServletContext().getRequestDispatcher("/topicos").forward(req, resp);
        }
    }

}
