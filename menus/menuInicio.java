package menus;

import java.util.Scanner;

public class menuInicio {

	public static void main(String[] args) {
		inicio();
	}
	
	
	public static void inicio() {
		Scanner scanner = new Scanner(System.in); 

        int opcion;
        do {
            mostrarMenuInicioSesion();
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                	menuInicioSesion.iniciarMenu();
                break;
                case 2:
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
                    break;
            }
        } while (opcion != 2);

        scanner.close();

	}

    private static void mostrarMenuInicioSesion() {
        System.out.println("=== Menú Principal ===");
        System.out.println("1. Inicio");
        System.out.println("2. Salir");
        System.out.print("Selecciona una opción: ");
    

	}

}
