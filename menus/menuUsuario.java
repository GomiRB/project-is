package menus;

import java.util.List;
import java.util.Scanner;

import classes.curso;
import classes.usuario;
import gestores.gestorCursos;

public class menuUsuario {
	public static void menuusuario(usuario usuario, List<curso> cursos) {
	    try (Scanner scanner = new Scanner(System.in)) {
			int opcion;
			do {
			    mostrarOpcionesDespuesInicioSesion();
			    opcion = scanner.nextInt();

			    switch (opcion) {
			        case 1:
			        	gestorCursos.mostrarCursosDisponibles(cursos);
			        	break;
			        case 2:
			            inscribirEnCursos(usuario, cursos);
			            break;
			        case 3:
			            System.out.println("Saliendo del menú. ¡Hasta luego!");
			            break;
			        default:
			            System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
			            break;
			    }
			} while (opcion != 3);
		}
	}

	private static void mostrarOpcionesDespuesInicioSesion() {
	    System.out.println("=== Menú después de iniciar sesión ===");
	    System.out.println("1. Ver cursos disponibles");
	    System.out.println("2. Inscribirse en cursos");
	    System.out.println("3. Salir del menú");
	    System.out.print("Selecciona una opción: ");
	}


	private static void inscribirEnCursos(usuario usuario, List<curso> cursos) {
	    // Lógica para inscribir al usuario en cursos
	    // Puedes mostrar la lista de cursos disponibles y permitir al usuario seleccionar uno para inscribirse
	    System.out.println("Ingrese el ID del curso al que desea inscribirse:");
	    Scanner scanner = new Scanner(System.in);
	    int idCurso = scanner.nextInt();
	    curso cursoSeleccionado = buscarCursoPorId(idCurso, cursos);

	    if (cursoSeleccionado != null) {
	        // Verificar si el usuario ya está inscrito en ese curso antes de inscribirlo
	        if (!usuario.estaInscritoEnCurso(idCurso)) {
	            usuario.inscribirEnCurso(idCurso);
	            System.out.println("Inscripción exitosa al curso: " + cursoSeleccionado.getCurso());
	        } else {
	            System.out.println("Ya está inscrito en este curso.");
	        }
	    } else {
	        System.out.println("Curso no encontrado.");
	    }
	}

	private static curso buscarCursoPorId(int idCurso, List<curso> cursos) {
	    for (curso curso : cursos) {
	        if (curso.getidCurso() == idCurso) {
	            return curso;
	        }
	    }
	    return null;
	}

}
