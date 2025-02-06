package es.albarregas.controllers;

import es.albarregas.beans.Direccion;
import es.albarregas.beans.Profesor;
import es.albarregas.dao.IProfesorDAO;
import es.albarregas.daoFactory.DAOFactory;
import es.albarregas.models.Utilidades;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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

/**
 *
 * @author Maria
 */
@WebServlet(name = "Create", urlPatterns = {"/Create"})
public class Create extends HttpServlet {

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
        String sms = null;
        Boolean vacio = false;
        Boolean existe = false;
        Direccion direccion = null;

        String url = "index.jsp";
        if (request.getParameter("registrarse") != null) {
            Enumeration<String> nombres = request.getParameterNames();
            profesor = new Profesor();
            direccion = new Direccion();
            //Se obtienen los campos y se comprueba si están vacíos
            List<String> camposOpcionales = new ArrayList<String>();
            camposOpcionales.add("apellido2");
            vacio = Utilidades.campoVacio(nombres, camposOpcionales, request);

            //Si los campos no están vacíos y el profesor no es ya existente
            if (vacio == false && existe == false) {
                //Se crea la direcciónc argada
                try {
                    BeanUtils.populate(direccion, request.getParameterMap());
                } catch (IllegalAccessException | InvocationTargetException ex) {
                    Logger.getLogger(Create.class.getName()).log(Level.SEVERE, null, ex);
                }
                // Se carga el profesor
                try {
                    BeanUtils.populate(profesor, request.getParameterMap());
                } catch (IllegalAccessException | InvocationTargetException ex) {
                    Logger.getLogger(Create.class.getName()).log(Level.SEVERE, null, ex);
                }
                profesor.setDireccion(direccion);
                pdao.add(profesor);
                //Recupero el profesor ya que la fecha se establece al introducirlo en la bbdd
                request.getSession().setAttribute("profesor", profesor);
            } else {
                sms = "El código ya se encuentra registrado";
                request.setAttribute("smsOcupado", sms);
                url = "/jsp/create/create.jsp";
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
