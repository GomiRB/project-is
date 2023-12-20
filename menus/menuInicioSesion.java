package menus;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import classes.usuario;
import gestores.gestorUsuarios;

public class menuInicioSesion {
	public static void iniciarMenu() {
	        try (Scanner scanner = new Scanner(System.in)) {
				List<usuario> usuarios =  gestorUsuarios.cargarUsuarios();

				int opcion;
				do {
				    mostrarMenuInicioSesion();
				    opcion = scanner.nextInt();

				    switch (opcion) {
				        case 1:
				            iniciarSesionUsuario(usuarios);
				            break;
				        case 2:
				        	iniciarSesionGestorAcademico(usuarios);
				            break;
				        case 3:
				        	menuVisita.visita();
				            break;
				        case 4:
				        	menuInicio.inicio();
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
			}

	        
	}
	
		
	    private static void mostrarMenuInicioSesion() {
	        System.out.println("=== Menú de Inicio de Sesión ===");
	        System.out.println("1. Iniciar sesión como Usuario");
	        System.out.println("2. Iniciar sesión como Gestor Academico");
	        System.out.println("3. Visitante");
	        System.out.println("4. Volver al menu anterior");
	        System.out.println("5. Salir");
	        System.out.print("Selecciona una opción: ");
	    }
	    
	    private static void iniciarSesionUsuario(List<usuario> usuarios) {
	        try (Scanner scanner = new Scanner(System.in)) {
	            System.out.print("Ingrese su DNI: ");
	            int dni = scanner.nextInt();
	            scanner.nextLine();  

	            System.out.print("Ingrese su contraseña: ");
	            String contraseña = scanner.nextLine();

	            usuario usuarioEncontrado = buscarUsuarioPorDniYContraseña(dni, contraseña, usuarios);

	            if (usuarioEncontrado != null) {
	                System.out.println("¡Inicio de sesión exitoso como Usuario!");
	                menuUsuario.menu(usuarioEncontrado);
	            } else {
	                System.out.println("Credenciales incorrectas. Inicio de sesión fallido.");
	            }
	        } catch (InputMismatchException e) {
	            System.out.println("Error: Ingresa un valor válido para el DNI.");
	        }
	    }

	    private static usuario buscarUsuarioPorDniYContraseña(int dni, String contraseña, List<usuario> usuarios) {
	        if (usuarios != null) {
	            for (usuario usuario : usuarios) {
	                if (usuario.getDni() == dni && usuario.getContraseña().equals(contraseña)) {
	                    return usuario;
	                }
	            }
	        } else {
	            System.err.println("Error: La lista de usuarios es null.");
	        }
	        return null;
	    }
    
	    private static void iniciarSesionGestorAcademico(List<usuario> gestores) {
	        try (Scanner scanner = new Scanner(System.in)) {
	            System.out.print("Ingrese su DNI como Gestor Académico: ");
	            int dni = scanner.nextInt();
	            scanner.nextLine();  // Consumir la nueva línea después del entero

	            System.out.print("Ingrese su contraseña como Gestor Académico: ");
	            String contraseña = scanner.nextLine();

	            usuario gestorEncontrado = buscarGestorPorDniYContraseña(dni, contraseña, gestores);

	            if (gestorEncontrado != null) {
	                System.out.println("¡Inicio de sesión exitoso como Gestor Académico!");
	                menuGestorAcademico.menu(gestorEncontrado);
	            } else {
	                System.out.println("Credenciales incorrectas o no tiene permisos de Gestor Académico. Inicio de sesión fallido.");
	            }
	        }
	    }
	    
	    private static usuario buscarGestorPorDniYContraseña(int dni, String contraseña, List<usuario> gestores) {
	        for (usuario gestor : gestores) {
	            if (gestor.getDni() == dni && gestor.getContraseña().equals(contraseña)) {
	                return gestor;
	            }
	        }
	        return null;
	    }

}