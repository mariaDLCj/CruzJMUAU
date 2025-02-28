
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


---------------------------------------------------------

import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

public class FileManager {
    private static Scanner scanner = new Scanner(System.in);
    private static Path currentDirectory = Paths.get(System.getProperty("user.dir"));

    public static void main(String[] args) {
        int option;
        do {
            showMenu();
            option = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea
            
            switch (option) {
                case 1 -> navigateDirectories();
                case 2 -> listDirectoryContents();
                case 3 -> createDirectory();
                case 4 -> createFile();
                case 5 -> deleteFileOrDirectory();
                case 6 -> renameFileOrDirectory();
                case 7 -> readFile();
                case 8 -> writeFile();
                case 9 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida.");
            }
        } while (option != 9);
    }
    
    private static void showMenu() {
        System.out.println("\n--- Gestor de Archivos ---");
        System.out.println("1. Navegar entre directorios");
        System.out.println("2. Listar contenido del directorio");
        System.out.println("3. Crear directorio");
        System.out.println("4. Crear fichero");
        System.out.println("5. Eliminar fichero o directorio");
        System.out.println("6. Renombrar fichero o directorio");
        System.out.println("7. Leer archivo");
        System.out.println("8. Escribir en fichero");
        System.out.println("9. Salir");
        System.out.print("Seleccione una opción: ");
    }
    
    private static void navigateDirectories() {
        System.out.print("Ingrese la ruta del directorio: ");
        String path = scanner.nextLine();
        Path newPath = currentDirectory.resolve(path).normalize();
        if (Files.isDirectory(newPath)) {
            currentDirectory = newPath;
            System.out.println("Directorio cambiado a: " + currentDirectory);
        } else {
            System.out.println("Directorio no encontrado.");
        }
    }
    
    private static void listDirectoryContents() {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(currentDirectory)) {
            System.out.println("Contenido de " + currentDirectory + ":");
            for (Path entry : stream) {
                System.out.println(entry.getFileName());
            }
        } catch (IOException e) {
            System.out.println("Error al listar el contenido: " + e.getMessage());
        }
    }
    
    private static void createDirectory() {
        System.out.print("Ingrese el nombre del directorio: ");
        String dirName = scanner.nextLine();
        Path dirPath = currentDirectory.resolve(dirName);
        try {
            Files.createDirectories(dirPath);
            System.out.println("Directorio creado: " + dirPath);
        } catch (IOException e) {
            System.out.println("Error al crear directorio: " + e.getMessage());
        }
    }
    
    private static void createFile() {
        System.out.print("Ingrese el nombre del fichero: ");
        String fileName = scanner.nextLine();
        Path filePath = currentDirectory.resolve(fileName);
        try {
            Files.createFile(filePath);
            System.out.println("Fichero creado: " + filePath);
        } catch (IOException e) {
            System.out.println("Error al crear fichero: " + e.getMessage());
        }
    }
    
    private static void deleteFileOrDirectory() {
        System.out.print("Ingrese el nombre del fichero o directorio a eliminar: ");
        String name = scanner.nextLine();
        Path path = currentDirectory.resolve(name);
        try {
            Files.delete(path);
            System.out.println("Eliminado correctamente: " + path);
        } catch (IOException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }
    }
    
    private static void renameFileOrDirectory() {
        System.out.print("Ingrese el nombre actual: ");
        String oldName = scanner.nextLine();
        System.out.print("Ingrese el nuevo nombre: ");
        String newName = scanner.nextLine();
        Path oldPath = currentDirectory.resolve(oldName);
        Path newPath = currentDirectory.resolve(newName);
        try {
            Files.move(oldPath, newPath);
            System.out.println("Renombrado a: " + newPath);
        } catch (IOException e) {
            System.out.println("Error al renombrar: " + e.getMessage());
        }
    }
    
    private static void readFile() {
        System.out.print("Ingrese el nombre del fichero a leer: ");
        String fileName = scanner.nextLine();
        Path filePath = currentDirectory.resolve(fileName);
        try {
            Files.lines(filePath).forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("Error al leer fichero: " + e.getMessage());
        }
    }
    
    private static void writeFile() {
        System.out.print("Ingrese el nombre del fichero a escribir: ");
        String fileName = scanner.nextLine();
        Path filePath = currentDirectory.resolve(fileName);
        System.out.println("Ingrese el texto a escribir (se sobrescribirá el contenido): ");
        String content = scanner.nextLine();
        try {
            Files.write(filePath, content.getBytes());
            System.out.println("Texto escrito en: " + filePath);
        } catch (IOException e) {
            System.out.println("Error al escribir en el fichero: " + e.getMessage());
        }
    }
}



