package es.albarregas.controllers;

import es.albarregas.beans.Direccion;
import es.albarregas.beans.Profesor;
import es.albarregas.dao.IProfesorDAO;
import es.albarregas.daoFactory.DAOFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Maria
 */
@WebServlet(name = "Delete", urlPatterns = {"/Delete"})
public class Delete extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOFactory daof = DAOFactory.getDAOFactory();
        IProfesorDAO pdao = daof.getProfesorDAO();
        String url = "index.jsp";
        Profesor profesor = null;
        Direccion direccion = null;

        if (request.getParameter("eliminar") != null) {
            url = "/jsp/delete/confirmar.jsp";

            int id = Integer.parseInt(request.getParameter("idProfesor"));
            profesor = pdao.getOne(id);

            if (profesor != null) {
                //lo establezco en la sesión
                request.getSession().setAttribute("profesorEliminar", profesor);
            }

        }

        if (request.getParameter("confirmarElminiar") != null) {
            //Comprobar que el profesor no venga nullo
            profesor = (Profesor) request.getSession().getAttribute("profesorEliminar");
            if (profesor != null) {
                pdao.delete(profesor);
                request.getSession().removeAttribute("profesor");
            }
        }

        if (request.getParameter("cancelar") != null) {
            //Hay que borrar la sesión si se cancela
            if (request.getSession().getAttribute("profesorEliminar") != null) {
                request.getSession().removeAttribute("profesorEliminar");
            }
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
