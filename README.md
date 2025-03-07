
# Proyecto CRUD Uno a Uno con Hibernate

# Descripción del Proyecto

Esta aplicación realiza el crud de la entidad `Profesor` que se relaciona de manera unidireccional con la entidad  `Direccion` a través de sus claves primarias mediante anotaciones. 

- **Profesor:**  
  - Atributos: `id`, `nombre`, `apellido1`, `apellido2`.

- **Direccion:**  
  - Atributos: `idDirec`, `calle`, `numero`, `localidad`, `provincia`.

### Funcionalidades Implementadas:

1. **Create**: Agregar nuevos profesores, con su dirección y guarda en la base de datos el profesor y la dirección en sus respectivas tablas.
2. **Read**: Consultar la información de los profesores y su dirección correspondiente.
3. **Update**: Modificar los datos de un profesor y su dirección evitando que los campos obligatorios se queden vacíos.
4. **Delete**: Eliminar un profesor y su dirección correspondiente.


PARA MI COPIAR

public interface IGenericoDao<T>{

    public void insertOrUpdate(T objeto);

    public <T> List<T> selectAll(Class<T> claseEntidad);

    public <T> T getById(Serializable pk, Class<T> claseEntidad);

    public void delete(T objeto);
}


//eL SIGUIENTE

public class GenericoDao<T> implements IGenericoDao<T> {

    private Session sesion;

    private void startTransaction() {
        sesion = HibernateUtil.getSessionFactory().openSession();
        sesion.getTransaction().begin();
    }

    private void endTransaction() {
        if (sesion.getTransaction().getStatus().equals(TransactionStatus.ACTIVE)) {
            sesion.getTransaction().commit();
        }
        sesion.close();
    }

    private void handleExcepcion(HibernateException he) throws HibernateException {
        sesion.getTransaction().rollback();
        throw he;
    }

    @Override
    public void insertOrUpdate(T objeto) {
        try {
            startTransaction();
            sesion.saveOrUpdate(objeto);
            sesion.flush();
        } catch (HibernateException he) {
            handleExcepcion(he);
        } finally {
            endTransaction();
        }
    }

    @Override
    public <T> List<T> selectAll(Class<T> claseEntidad) {
        List<T> listadoResultados = null;
        try {
            startTransaction();
            listadoResultados = sesion.createQuery("from " + claseEntidad.getSimpleName()).list();
        } catch (HibernateException he) {
            this.handleExcepcion(he);
        } finally {
            this.endTransaction();
        }
        return listadoResultados;
    }

    @Override
    public <T> T getById(Serializable pk, Class<T> claseEntidad) {
        T objetoRecuperado = null;
        try {
            startTransaction();
            objetoRecuperado = (T) sesion.get(claseEntidad, pk);
        } catch (HibernateException he) {
            this.handleExcepcion(he);
        } finally {
            this.endTransaction();
        }
        return objetoRecuperado;
    }

    @Override
    public void delete(T objeto) {
        try {
            startTransaction();
            sesion.delete(objeto);
        } catch (HibernateException he) {
            this.handleExcepcion(he);
        } finally {
            this.endTransaction();
        }
    }
}



package es.albarregas.dao;

import es.albarregas.persistence.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;

/**
 *
 * @author Maria
 */public class GenericoDao<T> implements IGenericoDao<T> {

    private Session sesion;

    private void startTransaction() {
        sesion = HibernateUtil.getSessionFactory().openSession();
        sesion.getTransaction().begin();
    }

    private void endTransaction() {
        if (sesion.getTransaction().getStatus().equals(TransactionStatus.ACTIVE)) {
            sesion.getTransaction().commit();
        }
        sesion.close();
    }

    private void handleExcepcion(HibernateException he) throws HibernateException {
        sesion.getTransaction().rollback();
        throw he;
    }

    @Override
    public void insertOrUpdate(T objeto) {
        try {
            startTransaction();
            sesion.saveOrUpdate(objeto);
            sesion.flush();
        } catch (HibernateException he) {
            handleExcepcion(he);
        } finally {
            endTransaction();
        }
    }

    @Override
    public <T> List<T> selectAll(Class<T> claseEntidad) {
        List<T> listadoResultados = null;
        try {
            startTransaction();
            listadoResultados = sesion.createQuery("from " + claseEntidad.getSimpleName()).list();
        } catch (HibernateException he) {
            this.handleExcepcion(he);
        } finally {
            this.endTransaction();
        }
        return listadoResultados;
    }

    @Override
    public <T> T getById(Serializable pk, Class<T> claseEntidad) {
        T objetoRecuperado = null;
        try {
            startTransaction();
            objetoRecuperado = (T) sesion.get(claseEntidad, pk);
        } catch (HibernateException he) {
            this.handleExcepcion(he);
        } finally {
            this.endTransaction();
        }
        return objetoRecuperado;
    }

    @Override
    public void delete(T objeto) {
        try {
            startTransaction();
            sesion.delete(objeto);
        } catch (HibernateException he) {
            this.handleExcepcion(he);
        } finally {
            this.endTransaction();
        }
    }
}



