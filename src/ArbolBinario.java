import java.util.LinkedList;
import java.util.Queue;
 
/**
 * Árbol Binario de Búsqueda (BST) para gestionar estudiantes.
 * La clave de ordenamiento es la cédula del estudiante (comparación lexicográfica).
 *
 * Funciones implementadas:
 *   insertarEstudiante, buscarEstudiante, eliminarEstudiante,
 *   recorridoInorden, recorridoPreorden, recorridoPostorden,
 *   recorridoPorNiveles (BFS), contarNodos, calcularAltura,
 *   buscarNotaMayor, buscarNotaMenor, mostrarAprobados, mostrarReprobados
 */
public class ArbolBinario {
 
    //  Raíz del árbol 
    private NodoArbol raiz;
 
    //  Constructor 
    /** Crea un árbol vacío. */
    public ArbolBinario() {
        this.raiz = null;
    }
 
    // ═════════════════════════════════════════════════════════════════════════
    // 1. INSERTAR ESTUDIANTE
    // ═════════════════════════════════════════════════════════════════════════
 
    /**
     * Inserta un nuevo estudiante en el árbol.
     * Si la cédula ya existe, no se inserta (no hay duplicados).
     *
     * @param estudiante Estudiante a insertar
     */
    public void insertarEstudiante(Estudiante estudiante) {
        raiz = insertarRecursivo(raiz, estudiante);
    }
 
    /**
     * Método recursivo auxiliar para insertar.
     *
     * @param nodo       Nodo actual en la recursión
     * @param estudiante Estudiante a insertar
     * @return Nodo actualizado
     */
    private NodoArbol insertarRecursivo(NodoArbol nodo, Estudiante estudiante) {
        // Caso base: posición vacía → crear nuevo nodo
        if (nodo == null) {
            System.out.println("✔ Estudiante insertado correctamente.");
            return new NodoArbol(estudiante);
        }
 
        int comparacion = estudiante.getCedula().compareTo(nodo.getClave());
 
        if (comparacion < 0) {
            // La cédula es menor → va al subárbol izquierdo
            nodo.setHijoIzq(insertarRecursivo(nodo.getHijoIzq(), estudiante));
        } else if (comparacion > 0) {
            // La cédula es mayor → va al subárbol derecho
            nodo.setHijoDer(insertarRecursivo(nodo.getHijoDer(), estudiante));
        } else {
            // Cédula duplicada
            System.out.println("⚠ Ya existe un estudiante con esa cédula.");
        }
 
        return nodo;
    }
 
    // ═════════════════════════════════════════════════════════════════════════
    // 2. BUSCAR ESTUDIANTE
    // ═════════════════════════════════════════════════════════════════════════
 
    /**
     * Busca un estudiante por su cédula.
     *
     * @param cedula Cédula a buscar
     * @return Estudiante encontrado, o null si no existe
     */
    public Estudiante buscarEstudiante(String cedula) {
        NodoArbol resultado = buscarRecursivo(raiz, cedula);
        return (resultado != null) ? resultado.getEstudiante() : null;
    }
 
    /**
     * Método recursivo auxiliar para buscar por cédula.
     */
    private NodoArbol buscarRecursivo(NodoArbol nodo, String cedula) {
        // Caso base: no encontrado o encontrado
        if (nodo == null || nodo.getClave().equals(cedula)) {
            return nodo;
        }
 
        int comparacion = cedula.compareTo(nodo.getClave());
 
        if (comparacion < 0) {
            return buscarRecursivo(nodo.getHijoIzq(), cedula);
        } else {
            return buscarRecursivo(nodo.getHijoDer(), cedula);
        }
    }
 
    // ═════════════════════════════════════════════════════════════════════════
    // 3. ELIMINAR ESTUDIANTE
    // ═════════════════════════════════════════════════════════════════════════
 
    /**
     * Elimina el estudiante con la cédula indicada.
     * Maneja los tres casos: nodo hoja, un hijo, dos hijos.
     *
     * @param cedula Cédula del estudiante a eliminar
     */
    public void eliminarEstudiante(String cedula) {
        if (buscarEstudiante(cedula) == null) {
            System.out.println("⚠ No se encontró un estudiante con esa cédula.");
            return;
        }
        raiz = eliminarRecursivo(raiz, cedula);
        System.out.println("✔ Estudiante eliminado correctamente.");
    }
 
    /**
     * Método recursivo auxiliar para eliminar.
     */
    private NodoArbol eliminarRecursivo(NodoArbol nodo, String cedula) {
        if (nodo == null) return null;
 
        int comparacion = cedula.compareTo(nodo.getClave());
 
        if (comparacion < 0) {
            // Buscar en el subárbol izquierdo
            nodo.setHijoIzq(eliminarRecursivo(nodo.getHijoIzq(), cedula));
 
        } else if (comparacion > 0) {
            // Buscar en el subárbol derecho
            nodo.setHijoDer(eliminarRecursivo(nodo.getHijoDer(), cedula));
 
        } else {
            // Nodo encontrado → aplicar uno de los tres casos
 
            // Caso 1: Nodo hoja (sin hijos)
            if (nodo.getHijoIzq() == null && nodo.getHijoDer() == null) {
                return null;
            }
 
            // Caso 2a: Solo tiene hijo derecho
            if (nodo.getHijoIzq() == null) {
                return nodo.getHijoDer();
            }
 
            // Caso 2b: Solo tiene hijo izquierdo
            if (nodo.getHijoDer() == null) {
                return nodo.getHijoIzq();
            }
 
            // Caso 3: Dos hijos → reemplazar con el sucesor en inorden
            // (el mínimo del subárbol derecho)
            NodoArbol sucesor = encontrarMinimo(nodo.getHijoDer());
            nodo.setEstudiante(sucesor.getEstudiante());
            nodo.setHijoDer(eliminarRecursivo(nodo.getHijoDer(), sucesor.getClave()));
        }
 
        return nodo;
    }
 
    /**
     * Encuentra el nodo con la cédula más pequeña en el subárbol dado.
     * Se usa como sucesor en inorden al eliminar un nodo con dos hijos.
     */
    private NodoArbol encontrarMinimo(NodoArbol nodo) {
        while (nodo.getHijoIzq() != null) {
            nodo = nodo.getHijoIzq();
        }
        return nodo;
    }
 
    // ═════════════════════════════════════════════════════════════════════════
    // 4. RECORRIDO INORDEN (Izquierda → Raíz → Derecha)
    // ═════════════════════════════════════════════════════════════════════════
 
    /**
     * Muestra todos los estudiantes en orden ascendente de cédula.
     */
    public void recorridoInorden() {
        System.out.println("\n══ Recorrido INORDEN (Orden ascendente por cédula) ══");
        if (esVacio()) { System.out.println("  El árbol está vacío."); return; }
        inordenRecursivo(raiz);
        System.out.println();
    }
 
    /** Auxiliar recursivo para inorden. */
    private void inordenRecursivo(NodoArbol nodo) {
        if (nodo == null) return;
        inordenRecursivo(nodo.getHijoIzq());
        System.out.println(nodo.getEstudiante());
        inordenRecursivo(nodo.getHijoDer());
    }
 
    // ═════════════════════════════════════════════════════════════════════════
    // 5. RECORRIDO PREORDEN (Raíz → Izquierda → Derecha)
    // ═════════════════════════════════════════════════════════════════════════
 
    /**
     * Muestra los estudiantes en preorden (raíz primero).
     */
    public void recorridoPreorden() {
        System.out.println("\n══ Recorrido PREORDEN (Raíz → Izq → Der) ══");
        if (esVacio()) { System.out.println("  El árbol está vacío."); return; }
        preordenRecursivo(raiz);
        System.out.println();
    }
 
    /** Auxiliar recursivo para preorden. */
    private void preordenRecursivo(NodoArbol nodo) {
        if (nodo == null) return;
        System.out.println(nodo.getEstudiante());
        preordenRecursivo(nodo.getHijoIzq());
        preordenRecursivo(nodo.getHijoDer());
    }
 
    // ═════════════════════════════════════════════════════════════════════════
    // 6. RECORRIDO POSTORDEN (Izquierda → Derecha → Raíz)
    // ═════════════════════════════════════════════════════════════════════════
 
    /**
     * Muestra los estudiantes en postorden (raíz al final).
     */
    public void recorridoPostorden() {
        System.out.println("\n══ Recorrido POSTORDEN (Izq → Der → Raíz) ══");
        if (esVacio()) { System.out.println("  El árbol está vacío."); return; }
        postordenRecursivo(raiz);
        System.out.println();
    }
 
    /** Auxiliar recursivo para postorden. */
    private void postordenRecursivo(NodoArbol nodo) {
        if (nodo == null) return;
        postordenRecursivo(nodo.getHijoIzq());
        postordenRecursivo(nodo.getHijoDer());
        System.out.println(nodo.getEstudiante());
    }
 
    // ═════════════════════════════════════════════════════════════════════════
    // 7. RECORRIDO POR NIVELES — BFS (Breadth-First Search)
    // ═════════════════════════════════════════════════════════════════════════
 
    /**
     * Muestra los estudiantes nivel por nivel usando una cola (Queue).
     * Implementa BFS (Búsqueda por Amplitud).
     */
    public void recorridoPorNiveles() {
        System.out.println("\n══ Recorrido por NIVELES / BFS ══");
        if (esVacio()) { System.out.println("  El árbol está vacío."); return; }
 
        Queue<NodoArbol> cola = new LinkedList<>();
        cola.offer(raiz);
        int nivel = 1;
 
        while (!cola.isEmpty()) {
            int tamañoNivel = cola.size();
            System.out.println("\n  ── Nivel " + nivel + " ──");
 
            // Procesar todos los nodos del nivel actual
            for (int i = 0; i < tamañoNivel; i++) {
                NodoArbol actual = cola.poll();
                System.out.println("  Cédula: " + actual.getClave()
                    + " | " + actual.getEstudiante().getNombreCompleto()
                    + " | Nota: " + actual.getEstudiante().getNotaFinal());
 
                // Encolar hijos para el siguiente nivel
                if (actual.getHijoIzq() != null) cola.offer(actual.getHijoIzq());
                if (actual.getHijoDer() != null) cola.offer(actual.getHijoDer());
            }
            nivel++;
        }
        System.out.println();
    }
 
    // ═════════════════════════════════════════════════════════════════════════
    // 8. CONTAR NODOS
    // ═════════════════════════════════════════════════════════════════════════
 
    /**
     * Cuenta el número total de estudiantes en el árbol.
     *
     * @return Número de nodos
     */
    public int contarNodos() {
        return contarRecursivo(raiz);
    }
 
    /** Auxiliar recursivo para contar nodos. */
    private int contarRecursivo(NodoArbol nodo) {
        if (nodo == null) return 0;
        return 1 + contarRecursivo(nodo.getHijoIzq())
                 + contarRecursivo(nodo.getHijoDer());
    }
 
    // ═════════════════════════════════════════════════════════════════════════
    // 9. CALCULAR ALTURA
    // ═════════════════════════════════════════════════════════════════════════
 
    /**
     * Calcula la altura del árbol.
     * Un árbol vacío tiene altura 0; un árbol con solo raíz tiene altura 1.
     *
     * @return Altura del árbol
     */
    public int calcularAltura() {
        return alturaRecursiva(raiz);
    }
 
    /** Auxiliar recursivo para calcular altura. */
    private int alturaRecursiva(NodoArbol nodo) {
        if (nodo == null) return 0;
        int alturaIzq = alturaRecursiva(nodo.getHijoIzq());
        int alturaDer = alturaRecursiva(nodo.getHijoDer());
        return 1 + Math.max(alturaIzq, alturaDer);
    }
 
    // ═════════════════════════════════════════════════════════════════════════
    // 10. BUSCAR NOTA MAYOR
    // ═════════════════════════════════════════════════════════════════════════
 
    /**
     * Devuelve el estudiante con la nota final más alta.
     *
     * @return Estudiante con mayor nota, o null si el árbol está vacío
     */
    public Estudiante buscarNotaMayor() {
        if (esVacio()) return null;
        return buscarMaxNota(raiz, raiz.getEstudiante());
    }
 
    /** Auxiliar recursivo para encontrar la nota mayor. */
    private Estudiante buscarMaxNota(NodoArbol nodo, Estudiante actual) {
        if (nodo == null) return actual;
 
        Estudiante candidato = actual;
        if (nodo.getEstudiante().getNotaFinal() > candidato.getNotaFinal()) {
            candidato = nodo.getEstudiante();
        }
 
        Estudiante desdeLaIzq = buscarMaxNota(nodo.getHijoIzq(), candidato);
        Estudiante desdeLaDer = buscarMaxNota(nodo.getHijoDer(), candidato);
 
        if (desdeLaIzq.getNotaFinal() >= desdeLaDer.getNotaFinal()) {
            return desdeLaIzq;
        } else {
            return desdeLaDer;
        }
    }
 
    // ═════════════════════════════════════════════════════════════════════════
    // 11. BUSCAR NOTA MENOR
    // ═════════════════════════════════════════════════════════════════════════
 
    /**
     * Devuelve el estudiante con la nota final más baja.
     *
     * @return Estudiante con menor nota, o null si el árbol está vacío
     */
    public Estudiante buscarNotaMenor() {
        if (esVacio()) return null;
        return buscarMinNota(raiz, raiz.getEstudiante());
    }
 
    /** Auxiliar recursivo para encontrar la nota menor. */
    private Estudiante buscarMinNota(NodoArbol nodo, Estudiante actual) {
        if (nodo == null) return actual;
 
        Estudiante candidato = actual;
        if (nodo.getEstudiante().getNotaFinal() < candidato.getNotaFinal()) {
            candidato = nodo.getEstudiante();
        }
 
        Estudiante desdeLaIzq = buscarMinNota(nodo.getHijoIzq(), candidato);
        Estudiante desdeLaDer = buscarMinNota(nodo.getHijoDer(), candidato);
 
        if (desdeLaIzq.getNotaFinal() <= desdeLaDer.getNotaFinal()) {
            return desdeLaIzq;
        } else {
            return desdeLaDer;
        }
    }
 
    // ═════════════════════════════════════════════════════════════════════════
    // 12. MOSTRAR APROBADOS  (nota >= 7.0)
    // ═════════════════════════════════════════════════════════════════════════
 
    /**
     * Muestra todos los estudiantes con nota final >= 7.0 (aprobados).
     */
    public void mostrarAprobados() {
        System.out.println("\n══ Estudiantes APROBADOS (nota ≥ 7.0) ══");
        if (esVacio()) { System.out.println("  El árbol está vacío."); return; }
        int[] contador = {0};
        aprobadosRecursivo(raiz, contador);
        System.out.println("\n  Total aprobados: " + contador[0]);
    }
 
    /** Auxiliar recursivo para listar aprobados. */
    private void aprobadosRecursivo(NodoArbol nodo, int[] contador) {
        if (nodo == null) return;
        aprobadosRecursivo(nodo.getHijoIzq(), contador);
        if (nodo.getEstudiante().estaAprobado()) {
            System.out.println(nodo.getEstudiante());
            contador[0]++;
        }
        aprobadosRecursivo(nodo.getHijoDer(), contador);
    }
 
    // ═════════════════════════════════════════════════════════════════════════
    // 13. MOSTRAR REPROBADOS  (nota < 7.0)
    // ═════════════════════════════════════════════════════════════════════════
 
    /**
     * Muestra todos los estudiantes con nota final < 7.0 (reprobados).
     */
    public void mostrarReprobados() {
        System.out.println("\n══ Estudiantes REPROBADOS (nota < 7.0) ══");
        if (esVacio()) { System.out.println("  El árbol está vacío."); return; }
        int[] contador = {0};
        reprobadosRecursivo(raiz, contador);
        System.out.println("\n  Total reprobados: " + contador[0]);
    }
 
    /** Auxiliar recursivo para listar reprobados. */
    private void reprobadosRecursivo(NodoArbol nodo, int[] contador) {
        if (nodo == null) return;
        reprobadosRecursivo(nodo.getHijoIzq(), contador);
        if (!nodo.getEstudiante().estaAprobado()) {
            System.out.println(nodo.getEstudiante());
            contador[0]++;
        }
        reprobadosRecursivo(nodo.getHijoDer(), contador);
    }
 
    // ═════════════════════════════════════════════════════════════════════════
    // UTILIDADES GENERALES
    // ═════════════════════════════════════════════════════════════════════════
 
    /**
     * Indica si el árbol está vacío.
     *
     * @return true si no tiene ningún nodo
     */
    public boolean esVacio() {
        return raiz == null;
    }
}