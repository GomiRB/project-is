package classes;

import java.util.List;

import gestores.gestorCursos;

public class inscrito extends usuario{
	
	private static final long serialVersionUID = 1L;

	public inscrito(int dni, String nombreCompleto, String correoElectronico, String contraseña) {
        super(dni, nombreCompleto, correoElectronico, contraseña);
    }

	public inscrito(int dni, String nombreCompleto, String correoElectronico, String contraseña,int idCurso) {
        super(dni, nombreCompleto, correoElectronico, contraseña);
        
	}
	
	public void inscribirseEnCurso(int idCurso,List<curso> cursos) {
        // Lógica para inscribir al inscrito en el curso
        curso curso = gestorCursos.buscarCursoPorId(idCurso, cursos);
        if (curso != null) {
            curso.inscribirInscrito(this);
            System.out.println("Inscrito correctamente en el curso: " + curso.getcurso());
        } else {
            System.out.println("Curso no encontrado.");
        }
    }


    public void darseDeAltaEnCurso(int idCurso,List<curso> cursos) {
        // Lógica para darse de alta en el curso
        curso curso = gestorCursos.buscarCursoPorId(idCurso, cursos);
        if (curso != null) {
            curso.darDeAltaInscrito(this);
            System.out.println("Dado de alta correctamente en el curso: " + curso.getcurso());
        } else {
            System.out.println("Curso no encontrado.");
        }
    }
}