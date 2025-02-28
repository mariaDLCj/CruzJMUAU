
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




