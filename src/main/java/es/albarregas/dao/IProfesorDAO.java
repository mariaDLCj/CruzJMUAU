package es.albarregas.dao;

import es.albarregas.beans.Profesor;
import java.util.List;

public interface IProfesorDAO {

    public void add(Profesor profesor);

    public List<Profesor> get();

    public Profesor getOne(int id);

    public void update(Profesor profesor);

    public void delete(Profesor profesor);

}
