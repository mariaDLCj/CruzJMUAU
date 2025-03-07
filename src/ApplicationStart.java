package es.albarregas.digitalnest.events;

import es.albarregas.digitalnest.beans.Categoria;
import es.albarregas.digitalnest.dao.ICategoriaDAO;
import es.albarregas.digitalnest.dao.IProductoDAO;
import es.albarregas.digitalnest.daoFactory.DAOFactory;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author María
 */
@WebListener
public class ApplicationStart implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext contexto = sce.getServletContext();

        DAOFactory daoF = DAOFactory.getDAOFactory();
        IProductoDAO pDAO = daoF.getProductoDAO();
        List<String> marcas = pDAO.getMarcas();
        ICategoriaDAO cDAO = daoF.getCategoriaDAO();
        List<Categoria> categorias = cDAO.getCategorias();
        Double[] minMax = new Double[2];
        minMax = pDAO.getMinMax();
        contexto.setAttribute("min", minMax[0]);
        contexto.setAttribute("max", minMax[1]);
       
        contexto.setAttribute("categorias", categorias);
        contexto.setAttribute("marcas", marcas);
        System.out.println("Número de marcas obtenidas: " + marcas.size());
        System.out.println("Número de categorias obtenidas: " + categorias.size());

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Se ha ejecutado contextDestroyed");
    }

}
