package menus;

import java.util.List;
import java.util.Scanner;

import classes.curso;
import gestores.gestorCursos;

public class menuVisita {
	public static void visita() {
		Scanner scanner = new Scanner(System.in); 
		List<curso> cursos=gestorCursos.cargarCursos();
		
        int opcion;
        do {
        	mostrarMenuVisita();
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                	gestorCursos.mostrarCursosDisponibles(cursos);
                break;
                case 2:
                	menuInicioSesion.iniciarMenu();
                break;
                case 3:
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
                    break;
            }
        } while (opcion != 3);

        scanner.close();

	}
	
	private static void mostrarMenuVisita() {
        System.out.println("=== Menú Visita ===");
        System.out.println("1. Ver cursos");
        System.out.println("2. Volver al menu anterior");
        System.out.println("3. Salir");
        System.out.print("Selecciona una opción: ");
    

	}
}
