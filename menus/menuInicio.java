package menus;

import java.util.InputMismatchException;
import java.util.Scanner;

public class menuInicio {

    public static void main(String[] args) {
        inicio();
    }

    public static void inicio() {
        try (Scanner scanner = new Scanner(System.in)) {
            int opcion = 0;
            do {
                mostrarMenuInicioSesion();
                try {
                    opcion = scanner.nextInt();
                    scanner.nextLine();  // Consumir la nueva línea después del entero
                    procesarOpcion(opcion,scanner);
                } catch (InputMismatchException e) {
                    System.out.println("Error: Ingresa un valor entero válido.");
                    scanner.nextLine();  // Consumir la nueva línea después del token no válido
                }
            } while (opcion != 2);
        }
    }

    private static void mostrarMenuInicioSesion() {
        System.out.println("=== Menú Principal ===");
        System.out.println("1. Inicio");
        System.out.println("2. Salir");
        System.out.print("Selecciona una opción: ");
    }

    private static void procesarOpcion(int opcion,Scanner scanner) {
        switch (opcion) {
            case 1:
                menuInicioSesion.iniciarMenu(scanner);
                break;
            case 2:
                System.out.println("Saliendo del programa. ¡Hasta luego!");
                System.exit(0);
                break;
            default:
                System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
        }
    }
}

