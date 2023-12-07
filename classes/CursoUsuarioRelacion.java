package classes;

public class CursoUsuarioRelacion {
    private int idCurso;
    private String nombreCompleto;

    public CursoUsuarioRelacion(int idCurso, String nombreCompleto) {
        this.idCurso = idCurso;
        this.nombreCompleto = nombreCompleto;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }
}
