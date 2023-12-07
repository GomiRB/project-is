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
	
	
}