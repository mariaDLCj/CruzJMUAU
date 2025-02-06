package es.albarregas.controllers;

import es.albarregas.beans.Direccion;
import es.albarregas.beans.Profesor;
import es.albarregas.dao.IProfesorDAO;
import es.albarregas.daoFactory.DAOFactory;
import es.albarregas.models.Utilidades;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.CalendarConverter;

/**
 *
 * @author Maria
 */
@WebServlet(name = "Update", urlPatterns = {"/Update"})
public class Update extends HttpServlet {

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
        Profesor profesor = null;
        String url = "index.jsp";
        String sms = null;
        int idProfesor = 0;
        Boolean vacio = false;
        Direccion direccion = null;

        if (request.getParameter("elegirActualizar") != null) {
            int id = Integer.parseInt(request.getParameter("idProfesor"));
            profesor = pdao.getOne(id);

            request.getSession().setAttribute("profesor", profesor);
            url = "/jsp/update/update.jsp";
        }

        if (request.getParameter("actualizar") != null) {
            // Comprobar que no haya campos vacíos
            Enumeration<String> nombres = request.getParameterNames();
            List<String> camposOpcionales = new ArrayList<String>();
            camposOpcionales.add("apellido2");
            camposOpcionales.add("id");
            camposOpcionales.add("idDirec");

            vacio = Utilidades.campoVacio(nombres, camposOpcionales, request);

            profesor = (Profesor) request.getSession().getAttribute("profesor");
            if (profesor != null) {
                if (vacio == false) {
                    direccion = profesor.getDireccion();
                    try {
                        BeanUtils.populate(direccion, request.getParameterMap());
                        BeanUtils.populate(profesor, request.getParameterMap());
                        profesor.setDireccion(direccion);
                        pdao.update(profesor);
                        request.getSession().removeAttribute("profesor");
                    } catch (IllegalAccessException | InvocationTargetException ex) {
                        Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    url = "/jsp/update/update.jsp";
                    sms = "Todos los campos con * son obligatorios";
                    request.setAttribute("smsVacio", sms);
                }
            }

        }

        request.getRequestDispatcher(url)
                .forward(request, response);
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
