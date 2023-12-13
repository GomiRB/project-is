package gestores;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import classes.CursoUsuarioRelacion;
import classes.curso;
import classes.usuario;

public class gestorCursos {

    private static final String USUARIOS_FILE = "usuarios.txt";
    private static final String CURSOS_FILE = "cursos.txt";
    private static final String RELACION_FILE = "relacion_cursos_usuarios.txt";

    public static List<usuario> cargarUsuarios() {
        List<usuario> usuarios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(USUARIOS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int dni = Integer.parseInt(parts[0]);
                String nombreCompleto = parts[1];
                String correo = parts[2];
                String contraseña = parts[3];
                boolean esGestorAcademico = Boolean.parseBoolean(parts[4]);
                usuarios.add(new usuario(dni, nombreCompleto, correo, contraseña, esGestorAcademico));
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return usuarios;
    }
    
    public static List<curso> cargarCursos() {
        List<curso> cursos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CURSOS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int idCurso = Integer.parseInt(parts[0]);
                String nombreCurso = parts[1];
                String fechaInicioStr = parts[2];
                String fechaFinStr = parts[3];
                String nombreponente = parts[4];
                int maxinscripciones = Integer.parseInt(parts[5]);
                String descripcion = parts[6];

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaInicio = dateFormat.parse(fechaInicioStr);
                Date fechaFin = dateFormat.parse(fechaFinStr);
                usuario ponente = buscarUsuarioPorNombre(nombreponente, usuarios);

                if (ponente == null) {
                    System.out.println("Error: Ponente no encontrado.");
                    continue;  // Saltar al siguiente ciclo si el ponente no se encuentra
                }
                cursos.add(new curso(idCurso, nombreCurso,fechaInicio,fechaFin,ponente,maxinscripciones,descripcion));
            }
        } catch (IOException | NumberFormatException | ParseException e) {
            e.printStackTrace();
        }
        return cursos;
    }
   
    public static List<CursoUsuarioRelacion> cargarRelacionCursosUsuarios() {
        List<CursoUsuarioRelacion> relaciones = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(RELACION_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int idCurso = Integer.parseInt(parts[0]);
                String nombreCompleto = parts[1];
                relaciones.add(new CursoUsuarioRelacion(idCurso, nombreCompleto));
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return relaciones;
    }
    
    public static void guardarUsuarios(List<usuario> usuarios) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(USUARIOS_FILE))) {
            for (usuario user : usuarios) {
                writer.println(user.getDni() + "," + user.getNombreCompleto() + "," +
                                user.getCorreoElectronico() + "," + user.getContraseña() + "," + user.getesGestorAcademico());
            }
            System.out.println("Datos de usuarios guardados correctamente en " + USUARIOS_FILE);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
            e.printStackTrace();
        }
    }

           
    public static void guardarCursos(List<curso> cursos) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CURSOS_FILE))) {
            for (curso curso : cursos) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                writer.println(
                    curso.getidCurso() + "," +
                    curso.getcurso() + "," +
                    dateFormat.format(curso.getfechaInicio()) + "," +
                    dateFormat.format(curso.getfechaFin()) + "," +
                    curso.getponente().getDni() + "," +  
                    curso.getponente().getesGestorAcademico() + "," +  // Añadir esta línea
                    curso.getmaxins() + "," +
                    curso.getdescripcion()
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    
    public static void guardarRelacionCursosUsuarios(CursoUsuarioRelacion relacion) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(RELACION_FILE, true))) {
            writer.println(relacion.getIdCurso() + "," + relacion.getNombreCompleto());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    
    private static List<usuario> usuarios;
    private static List<curso> cursos;

    public static void registrarUsuario(Scanner scanner,List<usuario> usuarios) {
        System.out.println("=== Registro de Usuario ===");
        scanner.nextLine();
        System.out.print("DNI: ");
        int dni=0;
        try {
            dni = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresa un número válido para el DNI.");
            return;
        }
        System.out.print("Nombre Completo: ");
        String nombreCompleto = scanner.nextLine();
        System.out.print("Correo Electrónico: ");
        String correoElectronico = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();

        usuario nuevoUsuario = new usuario(dni, nombreCompleto, correoElectronico, contraseña, false);
        usuarios.add(nuevoUsuario);

        System.out.println("Usuario registrado exitosamente.");
    }

    public static void mostrarCursosDisponibles(List<curso> cursos) {
        System.out.println("=== Cursos Disponibles ===");
        for (curso curso : cursos) {
            System.out.println(curso.getidCurso() + ". " + curso.getcurso());
        }
    }

    public static void registrarEnCurso(Scanner scanner, usuario usuario, List<curso> listaCursos) {
        System.out.println("=== Registro en Curso ===");
        System.out.println("Ingrese el ID del curso al que desea inscribirse:");
        int idCurso = scanner.nextInt();
        scanner.nextLine();

        curso cursoEncontrado = buscarCursoPorId(idCurso, listaCursos);

        if (cursoEncontrado == null) {
            System.out.println("Curso no encontrado.");
            return;
        }

        // Verificar si el usuario ya está inscrito en ese curso antes de inscribirlo
        if (!usuario.setestaInscritoEnCurso(idCurso)) {
            usuario.inscribirEnCurso(idCurso);
            System.out.println("Inscripción exitosa al curso: " + cursoEncontrado.getcurso());
            
            // Guardar la relación en el archivo
            CursoUsuarioRelacion relacion = new CursoUsuarioRelacion(idCurso, usuario.getNombreCompleto());
            guardarRelacionCursosUsuarios(relacion);
        } else {
            System.out.println("Ya está inscrito en este curso.");
        }
    }
   
    public static void mostrarRelacionCursosUsuarios() {
        List<CursoUsuarioRelacion> relaciones = cargarRelacionCursosUsuarios();

        if (relaciones.isEmpty()) {
            System.out.println("No hay relaciones entre usuarios y cursos.");
        } else {
            System.out.println("Relaciones entre usuarios y cursos:");
            for (CursoUsuarioRelacion relacion : relaciones) {
                System.out.println("ID Curso: " + relacion.getIdCurso() + ", Nombre Usuario: " + relacion.getNombreCompleto());
            }
        }
    }

    public static void crearCurso(Scanner scanner, List<usuario> usuarios) {
    	List<curso> cursos = new ArrayList<>();
        System.out.println("=== Crear Nuevo Curso ===");

        // Solicitar detalles del curso al gestor academico
        System.out.print("Ingrese el id del curso: ");
        int idCurso = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Ingrese el nombre del curso: ");
        String nombreCurso = scanner.nextLine();

        System.out.print("Ingrese la fecha de inicio (yyyy-MM-dd): ");
        String fechaInicioStr = scanner.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaInicio;
        try {
            fechaInicio = dateFormat.parse(fechaInicioStr);
        } catch (ParseException e) {
            System.out.println("Error: Formato de fecha incorrecto. Use el formato yyyy-MM-dd.");
            return;
        }

        System.out.print("Ingrese la fecha de fin (yyyy-MM-dd): ");
        String fechaFinStr = scanner.nextLine();
        Date fechaFin;
        try {
            fechaFin = dateFormat.parse(fechaFinStr);
        } catch (ParseException e) {
            System.out.println("Error: Formato de fecha incorrecto. Use el formato yyyy-MM-dd.");
            return;
        }

        // Solicitar el DNI del ponente y buscarlo en la lista de usuarios
        System.out.print("Ingrese el DNI del ponente: ");
        int dniPonente = scanner.nextInt();
        scanner.nextLine();  // Consumir la nueva línea después del entero
        usuario ponente = buscarUsuarioPorDni(dniPonente, usuarios);

        if (ponente == null) {
            System.out.println("Error: Ponente no encontrado.");
            return;
        }

        System.out.print("Ingrese el máximo de inscripciones: ");
        int maxInscripciones = scanner.nextInt();
        scanner.nextLine();  // Consumir la nueva línea después del entero

        System.out.print("Ingrese la descripción del curso: ");
        String descripcion = scanner.nextLine();

        // Crear el objeto curso con la información proporcionada
        curso nuevoCurso = new curso(idCurso,nombreCurso, fechaInicio, fechaFin, ponente, maxInscripciones, descripcion);
        
        // Agregar el nuevo curso a la lista de cursos
        cursos.add(nuevoCurso);

        System.out.println("Nuevo curso creado con éxito: " + nuevoCurso.getcurso());
        gestorCursos.guardarCursos(cursos);
        scanner.close();
    }

    public static void editarCurso(Scanner scanner, List<curso> cursos) {
        mostrarCursosDisponibles(cursos);
        System.out.print("Ingrese el ID del curso que desea editar: ");
        int idCursoEditar = scanner.nextInt();
        scanner.nextLine();  

        curso cursoEditar = buscarCursoPorId(idCursoEditar, cursos);

        if (cursoEditar == null) {
            System.out.println("Curso no encontrado.");
            return;
        }

        System.out.println("=== Editar Curso ===");
        System.out.print("Nuevo nombre del curso (" + cursoEditar.getcurso() + "): ");
        String nuevoNombreCurso = scanner.nextLine();
        cursoEditar.setcurso(nuevoNombreCurso);

        System.out.print("Ingrese nueva la fecha de inicio (yyyy-MM-dd, actual " + cursoEditar.getfechaInicio() + "): ");
        String nuevafechaInicioStr = scanner.nextLine();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date nuevafechaInicio = dateFormat.parse(nuevafechaInicioStr);
            cursoEditar.setfechaInicio(nuevafechaInicio);
        } catch (ParseException e) {
            System.out.println("Error al convertir la fecha. El formato debe ser yyyy-MM-dd.");
        }

        System.out.print("Ingrese nueva la fecha de fin (yyyy-MM-dd, actual " + cursoEditar.getfechaFin() + "): ");
        String nuevafechaFinStr = scanner.nextLine();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date nuevafechaFin = dateFormat.parse(nuevafechaFinStr);
            cursoEditar.setfechaFin(nuevafechaFin);
        } catch (ParseException e) {
            System.out.println("Error al convertir la fecha. El formato debe ser yyyy-MM-dd.");
        }

        System.out.print("Ingrese el nuevo máximo de inscripciones (" + cursoEditar.getmaxins() + "): ");
        int nuevamaxInscripciones = scanner.nextInt();
        cursoEditar.setmasins(nuevamaxInscripciones);
        scanner.nextLine();  // Consumir la nueva línea después del entero

        System.out.print("Ingrese la nueva descripción del curso (actual " + cursoEditar.getdescripcion() + "): ");
        String nuevadescripcion = scanner.nextLine();
        cursoEditar.setdescripcion(nuevadescripcion);

        
        guardarCursos(cursos);

        System.out.println("Curso editado exitosamente.");
    }

    
    public static void mostrarUsuarios(List<usuario> usuarios) {
        System.out.println("=== Usuarios Registrados ===");
        for (usuario usuario : usuarios) {
            System.out.println(usuario.getDni() + ". " + usuario.getNombreCompleto());
        }
    }

    private static usuario buscarUsuarioPorDni(int dni, List<usuario> usuarios) {
        for (usuario user : usuarios) {
            if (user.getDni() == dni) {
                return user;
            }
        }
        return null;
    }
    
    

    public static curso buscarCursoPorId(int idCurso, List<curso> cursos) {
        for (curso curso : cursos) {
            if (curso.getidCurso() == idCurso) {
                return curso;
            }
        }
        return null;
    }
    
    private static usuario buscarUsuarioPorNombre(String nombre, List<usuario> usuarios) {
        for (usuario usuario : usuarios) {
            if (usuario.getNombreCompleto().equals(nombre)) {
                return usuario;
            }
        }
        return null; // Devolver null si no se encuentra ningún usuario con ese nombre
    }

public static void eliminarUsuario(Scanner scanner, List<usuario> usuarios) {
    // Mostrar la lista de usuarios para que el usuario elija
    System.out.println("=== Eliminar Usuario ===");
    mostrarUsuarios(usuarios);

    System.out.print("Ingrese el DNI del usuario que desea eliminar: ");
    int dniEliminar = scanner.nextInt();
    scanner.nextLine();  // Consumir la nueva línea después del entero

    // Buscar el usuario en la lista
    usuario usuarioEliminar = buscarUsuarioPorDNI(dniEliminar, usuarios);

    if (usuarioEliminar != null) {
        // Eliminar el usuario de la lista
        usuarios.remove(usuarioEliminar);
        System.out.println("Usuario eliminado exitosamente.");
        
        // Guardar la lista actualizada en el archivo
        guardarUsuarios(usuarios);
    } else {
        System.out.println("Usuario no encontrado.");
    }
}

private static usuario buscarUsuarioPorDNI(int dni, List<usuario> usuarios) {
    for (usuario user : usuarios) {
        if (user.getDni() == dni) {
            return user;
        }
    }
    return null;
    }

public static void eliminarCurso(Scanner scanner, List<curso> cursos) {
    // Mostrar la lista de cursos para que el usuario elija
    System.out.println("=== Eliminar Curso ===");
    mostrarCursosDisponibles(cursos);

    System.out.print("Ingrese el ID del curso que desea eliminar: ");
    int idCursoEliminar = scanner.nextInt();
    scanner.nextLine();  // Consumir la nueva línea después del entero

    // Buscar el curso en la lista
    curso cursoEliminar = buscarCursoPorId(idCursoEliminar, cursos);

    if (cursoEliminar != null) {
        // Eliminar el curso de la lista
        cursos.remove(cursoEliminar);
        System.out.println("Curso eliminado exitosamente.");

        // Guardar la lista actualizada en el archivo
        guardarCursos(cursos);
    } else {
        System.out.println("Curso no encontrado.");
    }
}

}