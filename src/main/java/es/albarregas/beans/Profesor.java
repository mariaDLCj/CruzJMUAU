package es.albarregas.beans;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "profesores1a1A")
public class Profesor implements Serializable {

    @Id
    @GeneratedValue(generator = "generador")
    @GenericGenerator(name = "generador", strategy = "foreign",
            parameters = @Parameter(name = "property", value = "direccion"))
    @Column(name = "idProf")
    private int id;
    @Column(name = "nombre", length = 20, nullable = false)
    private String nombre;
    @Column(name = "apellido1", length = 15, nullable = false)
    private String apellido1;
    @Column(name = "apellido2", length = 15)
    private String apellido2;
    @OneToOne(cascade = {CascadeType.ALL})
    @PrimaryKeyJoinColumn
    private Direccion direccion;

    //Getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
    
}
