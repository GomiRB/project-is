package menus;

import java.util.List;
import java.util.Scanner;

import classes.curso;
import classes.usuario;
import gestores.gestorCursos;

public class menuUsuario {

    public static void menu(usuario usuario) {
        List<curso> cursos = gestorCursos.cargarCursos();
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
                    gestorCursos.mostrarRelacionCursosUsuarios();
                    break;
                case 4:
                    menuInicioSesion.iniciarMenu();
                    break;
                case 5:
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
                    System.exit(0);
                    break;
            }

        } while (opcion != 5);

        gestorCursos.guardarCursos(cursos);

        scanner.close();
    }

    private static int obtenerOpcion(Scanner scanner) {
        return scanner.nextInt();
    }

    private static void mostrarMenu() {

        System.out.println("=== Menú usuario ===");
        System.out.println("1. Ver cursos disponibles");
        System.out.println("2. Inscribirse en cursos");
        System.out.println("3. Darse de baja de cursos");
        System.out.println("4. Volver al menú anterior");
        System.out.println("5. Salir del menú");

        System.out.println("=== Menú de Usuario ===");
        System.out.println("1. Ver cursos disponibles");
        System.out.println("2. Inscribirse en cursos");
        System.out.println("3. Darse de baja de cursos");
        System.out.println("4. Volver al menu anterior");
        System.out.println("5. Salir del menu");

        System.out.print("Selecciona una opción: ");
    }
}
