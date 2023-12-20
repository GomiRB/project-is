package classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int dni;
	private String nombreCompleto;
	private String correoElectronico;
	private String contraseña;
	private List<Integer> cursosInscritos; 
		
	
	
	public usuario(int dni,String nombreCompleto,String correoElectronico,String contraseña){
		this.dni=dni;
		this.nombreCompleto=nombreCompleto;
		this.correoElectronico=correoElectronico;
		this.contraseña=contraseña;
		
	}
		
		
	public int getDni() {
		return dni;
	}
	
	public void setDni(int dni) {
		this.dni=dni;
	}
	
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto=nombreCompleto;
	}
	
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico=correoElectronico;
	}
	
	public String getContraseña() {
		return contraseña;
	}
	
	public void setContraseña(String contraseña) {
		this.contraseña=contraseña;
	}
	
	
	
	public boolean setestaInscritoEnCurso(int idCurso) {
	    if (cursosInscritos == null) {
	        cursosInscritos = new ArrayList<>();
	    }
	    return cursosInscritos.contains(idCurso);
	}


    public void inscribirEnCurso(int idCurso) {
        cursosInscritos.add(idCurso);
    }
    
	
	public void mostrarInformacionUsuario() {
		System.out.println("El DNI del Usuario: "+dni);
		System.out.println("El nombre completo del Usuario: "+nombreCompleto);
		System.out.println("El correo electronico del Usuario: "+correoElectronico);
	}
	
	public String toString() {
        return dni + "," + nombreCompleto + "," + correoElectronico + "," + contraseña;
    }
	

	}
