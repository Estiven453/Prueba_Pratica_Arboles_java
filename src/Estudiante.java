/**
 * Clase que representa a un estudiante universitario.
 * Contiene los datos personales y académicos del estudiante.
 *
 * Universidad Técnica de Ambato - Sistema de Gestión Académica
 */
public class Estudiante {
 
    //   Atributos 
    private String cedula;       // Identificador único
    private String apellidos;
    private String nombres;
    private double notaFinal;    
    private String carrera;
    private String nivel;       
 
    //   Constructor completo 
    /**
     * Crea un estudiante con todos sus datos.
     *
     * @param cedula     Número de cédula (único, usado como clave en el árbol)
     * @param apellidos  Apellidos del estudiante
     * @param nombres    Nombres del estudiante
     * @param notaFinal  Nota final (0.0 – 10.0)
     * @param carrera    Nombre de la carrera
     * @param nivel      Nivel o semestre actual
     */
    public Estudiante(String cedula, String apellidos, String nombres,
                      double notaFinal, String carrera, String nivel) {
        this.cedula    = cedula;
        this.apellidos = apellidos;
        this.nombres   = nombres;
        this.notaFinal = notaFinal;
        this.carrera   = carrera;
        this.nivel     = nivel;
    }
 
    //   Getters 
    public String getCedula()    { return cedula; }
    public String getApellidos() { return apellidos; }
    public String getNombres()   { return nombres; }
    public double getNotaFinal() { return notaFinal; }
    public String getCarrera()   { return carrera; }
    public String getNivel()     { return nivel; }
 
    //     Setters
    public void setCedula(String cedula)       { this.cedula = cedula; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public void setNombres(String nombres)     { this.nombres = nombres; }
    public void setNotaFinal(double notaFinal) { this.notaFinal = notaFinal; }
    public void setCarrera(String carrera)     { this.carrera = carrera; }
    public void setNivel(String nivel)         { this.nivel = nivel; }
 
    //    Utilidades 
    /**
     * Indica si el estudiante está aprobado (nota >= 7.0).
     *
     * @return true si aprobó, false si reprobó
     */
    public boolean estaAprobado() {
        return notaFinal >= 7.0;
    }
 
    /**
     * Nombre completo: apellidos + nombres.
     */
    public String getNombreCompleto() {
        return apellidos + " " + nombres;
    }
 
    /**
     * Representación en texto del estudiante para mostrar en consola.
     */
    @Override
    public String toString() {
        return String.format(
            "┌─────────────────────────────────────────┐\n" +
            "│  Cédula   : %-29s│\n" +
            "│  Nombres  : %-29s│\n" +
            "│  Apellidos: %-29s│\n" +
            "│  Carrera  : %-29s│\n" +
            "│  Nivel    : %-29s│\n" +
            "│  Nota     : %-29s│\n" +
            "│  Estado   : %-29s│\n" +
            "└─────────────────────────────────────────┘",
            cedula, nombres, apellidos, carrera, nivel,
            String.format("%.2f", notaFinal),
            estaAprobado() ? "APROBADO ✓" : "REPROBADO ✗"
        );
    }
}