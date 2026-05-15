import java.util.Scanner;
 
/**
 * Clase responsable de la interfaz de usuario en consola.
 * Muestra el menú, lee entradas y llama a los métodos del árbol.
 */
public class Menu {
 
    //  Dependencias 
    private final ArbolBinario arbol;
    private final Scanner      scanner;
 
    //  Constructor
    /**
     * Crea el menú con un árbol y un scanner ya inicializados.
     *
     * @param arbol   Árbol binario de búsqueda a gestionar
     * @param scanner Scanner para leer entradas del usuario
     */
    public Menu(ArbolBinario arbol, Scanner scanner) {
        this.arbol   = arbol;
        this.scanner = scanner;
    }
 
    //  Bucle principal 
    /**
     * Inicia el bucle del menú. Repite hasta que el usuario elija "Salir".
     */
    public void iniciar() {
        int opcion;
        do {
            mostrarEncabezado();
            opcion = leerOpcion();
            procesarOpcion(opcion);
        } while (opcion != 14);
    }
 
    //  Visualización del menú 
    /** Imprime el encabezado y las 14 opciones del menú. */
    private void mostrarEncabezado() {
        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║   SISTEMA ACADÉMICO UTA - Árbol BST          ║");
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.println("║  1.  Insertar estudiante                     ║");
        System.out.println("║  2.  Buscar estudiante por cédula            ║");
        System.out.println("║  3.  Eliminar estudiante                     ║");
        System.out.println("║  4.  Recorrido Inorden                       ║");
        System.out.println("║  5.  Recorrido Preorden                      ║");
        System.out.println("║  6.  Recorrido Postorden                     ║");
        System.out.println("║  7.  Recorrido por niveles (BFS)             ║");
        System.out.println("║  8.  Contar estudiantes                      ║");
        System.out.println("║  9.  Calcular altura del árbol               ║");
        System.out.println("║  10. Mostrar estudiante con mayor nota       ║");
        System.out.println("║  11. Mostrar estudiante con menor nota       ║");
        System.out.println("║  12. Mostrar estudiantes aprobados           ║");
        System.out.println("║  13. Mostrar estudiantes reprobados          ║");
        System.out.println("║  14. Salir                                   ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.print("  Seleccione una opción: ");
    }
 
    //  Procesamiento de opciones 
    /**
     * Ejecuta la acción correspondiente a la opción seleccionada.
     *
     * @param opcion Número de opción (1–14)
     */
    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1  -> opcionInsertar();
            case 2  -> opcionBuscar();
            case 3  -> opcionEliminar();
            case 4  -> arbol.recorridoInorden();
            case 5  -> arbol.recorridoPreorden();
            case 6  -> arbol.recorridoPostorden();
            case 7  -> arbol.recorridoPorNiveles();
            case 8  -> System.out.println("\n  Total de estudiantes: " + arbol.contarNodos());
            case 9  -> System.out.println("\n  Altura del árbol: " + arbol.calcularAltura());
            case 10 -> opcionMayorNota();
            case 11 -> opcionMenorNota();
            case 12 -> arbol.mostrarAprobados();
            case 13 -> arbol.mostrarReprobados();
            case 14 -> System.out.println("\n  ¡Hasta luego!");
            default -> System.out.println("\n   Opción no válida. Ingrese un número del 1 al 14.");
        }
    }
 
    //  Acciones individuales  
 
    /** Solicita los datos del estudiante y lo inserta en el árbol. */
    private void opcionInsertar() {
        System.out.println("\n── Insertar nuevo estudiante ──");
 
        System.out.print("  Cédula    : ");
        String cedula = scanner.nextLine().trim();
 
        // Validar que la cédula no esté vacía
        if (cedula.isEmpty()) {
            System.out.println("  La cédula no puede estar vacía.");
            return;
        }
 
        System.out.print("  Apellidos : ");
        String apellidos = scanner.nextLine().trim();
 
        System.out.print("  Nombres   : ");
        String nombres = scanner.nextLine().trim();
 
        double nota = leerNota();
 
        System.out.print("  Carrera   : ");
        String carrera = scanner.nextLine().trim();
 
        System.out.print("  Nivel     : ");
        String nivel = scanner.nextLine().trim();
 
        Estudiante nuevo = new Estudiante(cedula, apellidos, nombres, nota, carrera, nivel);
        arbol.insertarEstudiante(nuevo);
    }
 
    /** Solicita una cédula y muestra el estudiante encontrado. */
    private void opcionBuscar() {
        System.out.println("\n── Buscar estudiante ──");
        System.out.print("  Ingrese la cédula: ");
        String cedula = scanner.nextLine().trim();
 
        Estudiante encontrado = arbol.buscarEstudiante(cedula);
        if (encontrado != null) {
            System.out.println("\n   Estudiante encontrado:");
            System.out.println(encontrado);
        } else {
            System.out.println("   No se encontró un estudiante con esa cédula.");
        }
    }
 
    /** Solicita una cédula y elimina el estudiante correspondiente. */
    private void opcionEliminar() {
        System.out.println("\n── Eliminar estudiante ──");
        System.out.print("  Ingrese la cédula: ");
        String cedula = scanner.nextLine().trim();
        arbol.eliminarEstudiante(cedula);
    }
 
    /** Muestra el estudiante con la nota más alta. */
    private void opcionMayorNota() {
        Estudiante mayor = arbol.buscarNotaMayor();
        if (mayor != null) {
            System.out.println("\n   Estudiante con mayor nota:");
            System.out.println(mayor);
        } else {
            System.out.println("  El árbol está vacío.");
        }
    }
 
    /** Muestra el estudiante con la nota más baja. */
    private void opcionMenorNota() {
        Estudiante menor = arbol.buscarNotaMenor();
        if (menor != null) {
            System.out.println("\n  Estudiante con menor nota:");
            System.out.println(menor);
        } else {
            System.out.println("  El árbol está vacío.");
        }
    }
 
    //  Lectura y validación de entradas 
 
    /**
     * Lee y valida la opción del menú (entero entre 1 y 14).
     *
     * @return Número de opción válido
     */
    private int leerOpcion() {
        while (true) {
            try {
                int opcion = Integer.parseInt(scanner.nextLine().trim());
                return opcion;
            } catch (NumberFormatException e) {
                System.out.print("   Ingrese un número válido: ");
            }
        }
    }
 
    /**
     * Lee y valida una nota entre 0.0 y 10.0.
     *
     * @return Nota válida ingresada por el usuario
     */
    private double leerNota() {
        while (true) {
            try {
                System.out.print("  Nota final (0.0 – 10.0): ");
                double nota = Double.parseDouble(scanner.nextLine().trim());
                if (nota < 0.0 || nota > 10.0) {
                    System.out.println("   La nota debe estar entre 0.0 y 10.0.");
                } else {
                    return nota;
                }
            } catch (NumberFormatException e) {
                System.out.println("   Ingrese un número decimal válido (use punto, no coma).");
            }
        }
    }
}