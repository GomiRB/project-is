package menus;

import java.util.InputMismatchException;
import java.util.Scanner;

import classes.usuario;
import gestores.gestorCursos;

public class menuUsuario {

    public static void menu(usuario usuario) {

        
        try (Scanner scanner = new Scanner(System.in)) {
			int opcion=0;
			do {
			    mostrarMenu();			    
			    try {
			        opcion = scanner.nextInt();
			    } catch (InputMismatchException e) {
			        System.out.println("Error: Ingresa un valor entero válido.");
			        scanner.nextLine();  // Consumir la nueva línea después del token no válido
			        continue;  // O realiza alguna acción para manejar el error
			    }
			    switch (opcion) {
                case 1:
                    gestorCursos.mostrarCursosDisponibles();
                    break;
                case 2:
                    gestorCursos.registrarEnCurso(scanner, usuario);
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
        scanner.close();
    }        
}

    private static void mostrarMenu() {

        System.out.println("=== Menú usuario ===");
        System.out.println("1. Ver cursos disponibles");
        System.out.println("2. Inscribirse en cursos");
        System.out.println("3. Darse de baja de cursos");
        System.out.println("4. Volver al menú anterior");
        System.out.println("5. Salir del menú");

        System.out.print("Selecciona una opción: ");
    }
}
