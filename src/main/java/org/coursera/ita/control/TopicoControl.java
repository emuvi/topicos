package org.coursera.ita.control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.coursera.ita.base.TopicoBase;
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
            req.setAttribute("topico", new TopicoBase().recuperar(Integer.parseInt(id)));
            getServletContext().getRequestDispatcher("/topico.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("mensagem", e.getMessage());
            getServletContext().getRequestDispatcher("/topicos").forward(req, resp);
        }
    }

}
