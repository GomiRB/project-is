package menus;

import java.util.InputMismatchException;
import java.util.Scanner;

import gestores.gestorCursos;

public class menuVisita {
	public static void visita(Scanner scanner) {
	    int opcion=0;
	    do {
	        mostrarMenuVisita();
	        try {
	            opcion = scanner.nextInt();
	            switch (opcion) {
	                case 1:
	                    gestorCursos.mostrarCursosDisponibles();
	                    break;
	                case 2:
	                    menuInicioSesion.iniciarMenu(scanner);
	                    break;
	                case 3:
	                    System.out.println("Saliendo del programa. ¡Hasta luego!");
	                    System.exit(0);
	                    break;
	                default:
	                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
	                    break;
	            }
	        } catch (InputMismatchException e) {
	            System.out.println("Error: Ingresa un valor entero válido.");
	            scanner.nextLine();  // Consumir la nueva línea después del token no válido
	        }
	    } while (opcion != 3);
	}
	
	private static void mostrarMenuVisita() {
        System.out.println("=== Menú Visita ===");
        System.out.println("1. Ver cursos");
        System.out.println("2. Volver al menu anterior");
        System.out.println("3. Salir");
        System.out.print("Selecciona una opción: ");
    

	}
}
