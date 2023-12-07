package menus;

import java.util.List;
import java.util.Scanner;

import classes.curso;
import classes.usuario;
import gestores.gestorCursos;

public class menuUsuario {

	
    
	private static final String USUARIOS_FILE = "usuarios.txt";
    private static final String CURSOS_FILE = "cursos.txt";

    private static List<usuario> usuarios;
    private static List<curso> cursos;

    public static void menuUsuario(usuario usuario) {
    	
        List<curso> cursos = gestorCursos.cargarCursos();
        usuarios = gestorCursos.cargarUsuarios();
        cursos = gestorCursos.cargarCursos();
        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
                mostrarMenu();
                opcion = obtenerOpcion(scanner);

                
				switch (opcion) {
                    case 1:
                        gestorCursos.mostrarCursosDisponibles(cursos);
                        break;
                    case 2:
                        gestorCursos.registrarEnCurso(scanner, usuario, cursos);
                        break;
                    case 3:
                        gestorCursos.mostrarUsuariosInscritosEnCurso(cursos,scanner);
                        break;
                    case 4:
                    	menuInicioSesion.iniciarMenu();
                    	break;
                    case 5:
                        System.out.println("Saliendo del programa. ¡Hasta luego!");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
                        break;
                }

            } while (opcion != 5);

            // Puedes elegir guardar los cursos aquí o en otro momento según tu lógica
            gestorCursos.guardarCursos(cursos);

            scanner.close();
    
    }
    
    static int obtenerOpcion(Scanner scanner) {
        return scanner.nextInt();
    }

    private static void mostrarMenu() {
        System.out.println("=== Menú después de iniciar sesión ===");
        System.out.println("1. Ver cursos disponibles");
        System.out.println("2. Inscribirse en cursos");
        System.out.println("3. Darse de baja de cursos");
        System.out.println("4. Volver al menu anterior");
        System.out.println("5. Salir del menú");
        System.out.print("Selecciona una opción: ");
    }

    
    

    /*private static void darseDeBajaDeCursos(usuario usuario, List<curso> cursos) {
        // Lógica para darse de baja del curso
        System.out.println("Ingrese el ID del curso del que desea darse de baja:");
        Scanner scanner = new Scanner(System.in);
        int idCurso = scanner.nextInt();
        curso cursoSeleccionado = buscarCursoPorId(idCurso, cursos);

        if (cursoSeleccionado != null) {
            // Verificar si el usuario está inscrito en ese curso antes de darse de baja
            if (usuario.estaInscritoEnCurso(idCurso)) {
                usuario.darseDeBajaEnCurso(idCurso);
                System.out.println("Dado de baja exitosamente del curso: " + cursoSeleccionado.getCurso());
            } else {
                System.out.println("No está inscrito en este curso.");
            }
        } else {
            System.out.println("Curso no encontrado.");
        }
    }

   */
}


