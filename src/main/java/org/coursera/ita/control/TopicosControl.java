package org.coursera.ita.control;

import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import org.coursera.ita.data.TopicoData;
import org.coursera.ita.model.Topico;
import org.coursera.ita.model.Usuario;

@WebServlet("/topicos")
public class TopicosControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Usuario logado = (Usuario) req.getSession().getAttribute("logado");
            if (logado == null) {
                throw new Exception("Você deve estar logado para listar os tópicos.");
            }
            List<Topico> topicos = new TopicoData().listar();
            req.setAttribute("topicos", topicos);
            getServletContext().getRequestDispatcher("/topicos.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("mensagem", e.getMessage());
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }

}
