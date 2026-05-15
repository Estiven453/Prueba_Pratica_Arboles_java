import java.util.Scanner;
 
/**
 * Punto de entrada del sistema académico UTA.
 * Inicializa el árbol BST, precarga datos de ejemplo y lanza el menú.
 */
public class Main {
 
    public static void main(String[] args) {
 
        // Crear árbol e instanciar el escáner 
        ArbolBinario arbol   = new ArbolBinario();
        Scanner      scanner = new Scanner(System.in);
 
        //  Precarga de estudiantes de ejemplo 
        // (facilita las pruebas sin tener que ingresarlos manualmente)
        arbol.insertarEstudiante(new Estudiante("1804000001", "Pérez García",    "Carlos Alberto",  8.5, "Ingeniería en Sistemas",   "Quinto"));
        arbol.insertarEstudiante(new Estudiante("1804000002", "López Martínez",  "Ana Sofía",        6.0, "Medicina",                  "Segundo"));
        arbol.insertarEstudiante(new Estudiante("1804000003", "Torres Villareal", "Luis Fernando",   9.2, "Ingeniería Civil",          "Séptimo"));
        arbol.insertarEstudiante(new Estudiante("1804000004", "Cárdenas Ruiz",   "Valeria Estefanía",5.5, "Contabilidad y Auditoría", "Primero"));
        arbol.insertarEstudiante(new Estudiante("1804000005", "Ramos Salazar",   "Diego Alejandro",  7.8, "Arquitectura",             "Cuarto"));
        arbol.insertarEstudiante(new Estudiante("1804000006", "Navas Herrera",   "Gabriela del R.",  3.0, "Psicología",               "Tercero"));
        arbol.insertarEstudiante(new Estudiante("1804000007", "Castillo Vega",   "Andrés Sebastián", 10.0,"Ingeniería en Sistemas",   "Octavo"));
 
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║  Bienvenido al Sistema Académico UTA         ║");
        System.out.println("║  Se cargaron 7 estudiantes de ejemplo.       ║");
        System.out.println("╚══════════════════════════════════════════════╝");
 
        // Iniciar el menú interactivo
        Menu menu = new Menu(arbol, scanner);
        menu.iniciar();
 
        // Cerrar recursos 
        scanner.close();
    }
}
 