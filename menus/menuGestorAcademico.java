package menus;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import classes.curso;
import classes.usuario;
import gestores.gestorCursos;
import gestores.gestorUsuarios;

public class menuGestorAcademico {

    public static void menu(usuario usuario) {
        
        List<usuario> usuarios=gestorUsuarios.cargarUsuarios();
        List<curso> cursos = gestorCursos.cargarCursos(usuarios);


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
                    gestorCursos.mostrarRelacionCursosUsuarios();
                    break;
                case 3:
                	
                	gestorUsuarios.mostrarUsuarios();
                    
                    break;
                case 4:
                	gestorUsuarios.guardarUsuarios();
                    
                    break;
                case 5:
                	 gestorUsuarios.eliminarUsuario(scanner);
                    
                    break;
                case 6:
                	 gestorCursos.mostrarCursosDisponibles();                   
                    break;
                case 7:
                	gestorCursos.crearCurso(scanner);
                    
                    break;
                case 8:
                	gestorCursos.editarCurso(scanner);
                    
                    break;
                case 9:
                	gestorCursos.eliminarCurso(scanner);
                    
                    break;
                case 10:
                	menuInicioSesion.iniciarMenu();
                    break;
                case 11:
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
    }
        

        
        

    
    
    private static void mostrarMenu() {
        System.out.println("=== Menú Gestor Académico ===");
        System.out.println("1. Ver cursos disponibles");
        System.out.println("2. Ver relación cursos-usuarios");
        System.out.println("3. Ver usuarios");
        System.out.println("4. Guardar usuarios");
        System.out.println("5. Eliminar usuario");
        System.out.println("6. Mostrar cursos");
        System.out.println("7. Crear curso");
        System.out.println("8. Editar curso");
        System.out.println("9. Eliminar curso");
        System.out.println("10. Volver al menú anterior");
        System.out.println("11. Salir del programa");
        System.out.print("Selecciona una opción: ");
    }
}