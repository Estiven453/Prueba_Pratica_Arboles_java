/**
 * Nodo del Árbol Binario de Búsqueda (BST).
 * Cada nodo almacena un objeto Estudiante y referencias a sus hijos.
 */
public class NodoArbol {
 
    // ─── Atributos ────────────────────────────────────────────────────────────
    private Estudiante estudiante;   // Dato almacenado en el nodo
    private NodoArbol  hijoIzq;      // Referencia al hijo izquierdo
    private NodoArbol  hijoDer;      // Referencia al hijo derecho
 
    // ─── Constructor ──────────────────────────────────────────────────────────
    /**
     * Crea un nodo hoja (sin hijos) con el estudiante dado.
     */
    public NodoArbol(Estudiante estudiante) {
        this.estudiante = estudiante;
        this.hijoIzq    = null;
        this.hijoDer    = null;
    }
 
    //  Getters 
    public Estudiante getEstudiante() { return estudiante; }
    public NodoArbol  getHijoIzq()   { return hijoIzq; }
    public NodoArbol  getHijoDer()   { return hijoDer; }
 
    //  Setters 
    public void setEstudiante(Estudiante estudiante) { this.estudiante = estudiante; }
    public void setHijoIzq(NodoArbol hijoIzq)       { this.hijoIzq = hijoIzq; }
    public void setHijoDer(NodoArbol hijoDer)        { this.hijoDer = hijoDer; }
 
    // Utilidad 
    /**
     * Acceso directo a la cédula del estudiante almacenado.
     * Facilita las comparaciones en el árbol.
     *
     * @return Cédula del estudiante
     */
    public String getClave() {
        return estudiante.getCedula();
    }
}
 
