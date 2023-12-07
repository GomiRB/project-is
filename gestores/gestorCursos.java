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
import menus.menuCursos;

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
                usuarios.add(new usuario(dni, nombreCompleto, correo, contraseña));
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
    
    private static void guardarUsuarios(List<usuario> usuarios) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(USUARIOS_FILE))) {
            for (usuario user : usuarios) {
                writer.println(user.getDni() + "," + user.getNombreCompleto() + "," +
                                user.getCorreoElectronico() + "," + user.getContraseña());
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

        usuario nuevoUsuario = new usuario(dni, nombreCompleto, correoElectronico, contraseña);
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
        if (!usuario.estaInscritoEnCurso(idCurso)) {
            usuario.inscribirEnCurso(idCurso);
            System.out.println("Inscripción exitosa al curso: " + cursoEncontrado.getcurso());
            
            // Guardar la relación en el archivo
            CursoUsuarioRelacion relacion = new CursoUsuarioRelacion(idCurso, usuario.getNombreCompleto());
            guardarRelacionCursosUsuarios(relacion);
        } else {
            System.out.println("Ya está inscrito en este curso.");
        }
    }
    
    public static void mostrarUsuariosInscritosEnCurso(List<curso> cursos, Scanner scanner) {
        // Mostrar la lista de cursos para que el usuario elija
        mostrarCursosDisponibles(cursos);

        System.out.println("Ingrese el ID del curso para ver los usuarios inscritos:");
        int idCursoSeleccionado = scanner.nextInt();
        scanner.nextLine();  // Consumir la nueva línea después del entero

        // Buscar el curso seleccionado por el usuario
        curso cursoSeleccionado = buscarCursoPorId(idCursoSeleccionado, cursos);

        if (cursoSeleccionado != null) {
            // Mostrar los usuarios inscritos en el curso seleccionado
            mostrarUsuariosInscritosEnCurso(cursoSeleccionado);
        } else {
            System.out.println("Curso no encontrado.");
        }
    }

    public static void mostrarUsuariosInscritosEnCurso(curso curso) {
    	
        System.out.println("Usuarios inscritos en el curso " + curso.getcurso() + ":");
        List<CursoUsuarioRelacion> relaciones = cargarRelacionCursosUsuarios();
        
        for (CursoUsuarioRelacion relacion : relaciones) {
            if (relacion.getIdCurso() == curso.getidCurso()) {
                System.out.println(relacion.getNombreCompleto());
            }
        }
    }
    
    

    
    private static void mostrarInformacionCurso(List<curso> cursos) {
    	 if (cursos.isEmpty()) {
    	        System.out.println("No hay cursos disponibles.");
    	    } else {
    	        System.out.println("Información de Cursos:");
    	        for (curso curso : cursos) {
    	            System.out.println("ID: " + curso.getidCurso());
    	            System.out.println("Nombre: " + curso.getcurso());
    	            System.out.println("Fecha de Inicio: " + curso.getfechaInicio());
    	            System.out.println("Fecha de Fin: " + curso.getfechaFin());
    	            System.out.println("El maximo de inscripciones: "+curso.getmaxins());
    	            System.out.println("DNI ponenete: " + curso.getponente());
    	            System.out.println("Descripción: " + curso.getdescripcion());
    	            System.out.println("--------------");
    	        }
    	    }	
    }

    private static void mostrarUsuarios(List<usuario> usuarios) {
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
   }
