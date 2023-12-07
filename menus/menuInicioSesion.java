package menus;
import java.util.List;
import java.util.Scanner;

import classes.usuario;
import gestores.gestorCursos;
public class menuInicioSesion {
	public static void iniciarMenu() {
	        Scanner scanner = new Scanner(System.in);

	        List<usuario> usuarios =  gestorCursos.cargarUsuarios();
	        //List<ponente> ponentes = obtenerListaDePonentes();  

	        int opcion;
	        do {
	            mostrarMenuInicioSesion();
	            opcion = scanner.nextInt();

	            switch (opcion) {
	                case 1:
	                    iniciarSesionUsuario(usuarios);
	                    break;
	                case 2:
	                    //iniciarSesionPonente(ponentes);
	                    break;
	                case 3:
	                	menuVisita.visita();
	                    break;
	                case 4:
	                	menuInicio.inicio();
	                case 5:
	                	System.out.println("Saliendo del programa. ¡Hasta luego!");
	                default:
	                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
	                    break;
	            }
	        } while (opcion != 5);

	        scanner.close();
	}
	
		
	    private static void mostrarMenuInicioSesion() {
	        System.out.println("=== Menú de Inicio de Sesión ===");
	        System.out.println("1. Iniciar sesión como Usuario");
	        System.out.println("2. Iniciar sesión como Gestor Academico");
	        System.out.println("3. Visitante");
	        System.out.println("4. Volver menu anterior");
	        System.out.println("5. Salir");
	        System.out.print("Selecciona una opción: ");
	    }

	    private static void iniciarSesionUsuario(List<usuario> usuarios) {
	        try (Scanner scanner = new Scanner(System.in)) {
				System.out.print("Ingrese su DNI: ");
				int dni = scanner.nextInt();
				scanner.nextLine();  // Consumir la nueva línea después del entero

				System.out.print("Ingrese su contraseña: ");
				String contraseña = scanner.nextLine();

				usuario usuarioEncontrado = buscarUsuarioPorDniYContraseña(dni, contraseña, usuarios);

				if (usuarioEncontrado != null) {
				    System.out.println("¡Inicio de sesión exitoso como Usuario!");
				    menuUsuario.menuUsuario(usuarioEncontrado);
				} else {
				    System.out.println("Credenciales incorrectas. Inicio de sesión fallido.");
				}
			}
	    }

	    private static usuario buscarUsuarioPorDniYContraseña(int dni, String contraseña, List<usuario> usuarios) {
	        for (usuario usuario : usuarios) {
	            if (usuario.getDni() == dni && usuario.getContraseña().equals(contraseña)) {
	                return usuario;
	            }
	        }
	        return null;
	    }

	    
}