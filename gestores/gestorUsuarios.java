package gestores;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import classes.usuario;



public class gestorUsuarios {
	
	private static final String GESTOR_FILE="gestor_academico.txt";
	private static final String USUARIOS_FILE = "usuarios.txt";
    
	
    public static void guardarUsuarios() {
    	List<usuario> usuarios=gestorUsuarios.cargarUsuarios();
        try (PrintWriter writer = new PrintWriter(new FileWriter(USUARIOS_FILE))) {
            for (usuario user : usuarios) {
                writer.println(user.getDni() + "," + user.getNombreCompleto() + "," +
                                user.getCorreoElectronico() + "," + user.getContraseña());
            }
            System.out.println("Datos de usuarios guardados correctamente en " + USUARIOS_FILE);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static void registrarUsuario(Scanner scanner,List<usuario> usuarios) {
        System.out.println("=== Registro de Usuario ===");
        scanner.nextLine();
        System.out.print("DNI: ");
        int dni=0;
        try {
            dni = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresa un número válido para el DNI.");
            return;
        }
        System.out.print("Nombre Completo: ");
        String nombreCompleto = scanner.nextLine();
        System.out.print("Correo Electrónico: ");
        String correoElectronico = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();

        usuario nuevoUsuario = new usuario(dni, nombreCompleto, correoElectronico, contraseña);
        usuarios.add(nuevoUsuario);

        System.out.println("Usuario registrado exitosamente.");
        scanner.close();
    }
    
	public static List<usuario> cargarUsuarios() {
        List<usuario> usuarios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(USUARIOS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                
                // Validar que haya suficientes partes en la línea antes de intentar acceder a ellas
                if (parts.length == 4) {
                    int dni = Integer.parseInt(parts[0]);
                    String nombreCompleto = parts[1];
                    String correo = parts[2];
                    String contraseña = parts[3];
                    
                    usuarios.add(new usuario(dni, nombreCompleto, correo, contraseña));
                } else {
                    // Manejar el formato incorrecto de la línea (puedes imprimir un mensaje o registrar un error)
                    System.err.println("Formato incorrecto en la línea: " + line);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();  // Considera un manejo más específico de errores
        }
        return usuarios;
    }

    public static List<usuario> cargarGestor() {
        List<usuario> usuarios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(GESTOR_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                
                // Validar que haya suficientes partes en la línea antes de intentar acceder a ellas
                if (parts.length == 4) {
                    int dni = Integer.parseInt(parts[0]);
                    String nombreCompleto = parts[1];
                    String correo = parts[2];
                    String contraseña = parts[3];
                    
                    usuarios.add(new usuario(dni, nombreCompleto, correo, contraseña));
                } else {
                    // Manejar el formato incorrecto de la línea (puedes imprimir un mensaje o registrar un error)
                    System.err.println("Formato incorrecto en la línea: " + line);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();  // Considera un manejo más específico de errores
        }
        return usuarios;
    }
    
    public static void mostrarUsuarios() {
    	List<usuario> usuarios=gestorUsuarios.cargarUsuarios();
        System.out.println("=== Usuarios Registrados ===");
        for (usuario usuario : usuarios) {
            System.out.println(usuario.getDni() + ". " + usuario.getNombreCompleto());
        }
    }

    public static usuario buscarUsuarioPorDni(int dni, List<usuario> usuarios) {
	if (usuarios == null) {
        System.err.println("Error: La lista de usuarios es null.");
        return null;
	}
    for (usuario user : usuarios) {
        if (user.getDni() == dni) {
            return user;
        }
    }
    return null;
}

    public static void eliminarUsuario(Scanner scanner) {
	List<usuario> usuarios=gestorUsuarios.cargarUsuarios();
    // Mostrar la lista de usuarios para que el usuario elija
    System.out.println("=== Eliminar Usuario ===");
    mostrarUsuarios();

    System.out.print("Ingrese el DNI del usuario que desea eliminar: ");
    int dniEliminar = scanner.nextInt();
    scanner.nextLine();  // Consumir la nueva línea después del entero

    // Buscar el usuario en la lista
    usuario usuarioEliminar = gestorUsuarios.buscarUsuarioPorDNI(dniEliminar, usuarios);

    if (usuarioEliminar != null) {
        // Eliminar el usuario de la lista
        usuarios.remove(usuarioEliminar);
        System.out.println("Usuario eliminado exitosamente.");
        
        // Guardar la lista actualizada en el archivo
        guardarUsuarios();
    } else {
        System.out.println("Usuario no encontrado.");
    }
    scanner.close();
}

    private static usuario buscarUsuarioPorDNI(int dni, List<usuario> usuarios) {
    for (usuario user : usuarios) {
        if (user.getDni() == dni) {
            return user;
        }
    }
    return null;
    }
}
