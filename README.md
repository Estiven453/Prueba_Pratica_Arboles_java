#  Sistema Académico UTA — Árboles Binarios de Búsqueda (BST)

Este proyecto es una implementación práctica para la **Universidad Técnica de Ambato** que gestiona registros de estudiantes mediante una estructura de datos de **Árbol Binario de Búsqueda (BST)**, desarrollada bajo el paradigma de **Programación Orientada a Objetos (POO)** en Java.

---

##  Descripción

El sistema permite almacenar, buscar, eliminar y recorrer registros de estudiantes universitarios.  
Cada estudiante se identifica por su número de cédula, que funciona como la clave única del BST.

Se implementan todos los recorridos clásicos del árbol, además de funciones avanzadas como:

- BFS (Breadth-First Search)
- Cálculo de altura del árbol
- Conteo total de nodos
- Filtrado dinámico por rendimiento académico

---

#  Estructura del Proyecto

```text
prueba-practica-arboles-cpp-java/
│
├── java/
│   ├── Estudiante.java      # Modelo de datos y formato visual
│   ├── NodoArbol.java       # Nodo del árbol con encapsulamiento
│   ├── ArbolBinario.java    # Lógica completa del BST (13 funciones)
│   ├── Menu.java            # Interfaz de usuario y validaciones
│   └── Main.java            # Punto de entrada y precarga de datos
│
│
└── README.md                # Documentación del proyecto
```

---

# Clases Java (Arquitectura POO)

| Clase | Responsabilidad |
|---|---|
| `Estudiante` | Modelo con cédula, nombres, apellidos, nota, carrera y nivel |
| `NodoArbol` | Contenedor de un estudiante con referencias a sus hijos (`izq` y `der`) |
| `ArbolBinario` | Toda la lógica del BST (inserción, búsqueda, eliminación y recorridos) |
| `Menu` | Manejo de la interfaz de consola, lectura de entradas y validación |
| `Main` | Inicialización del sistema, precarga de estudiantes y arranque del menú |

---

#  Compilación y Ejecución (Java)

##  Requisitos

- Java 17 o superior
- JDK instalado y configurado
- Visual Studio Code (opcional)
- Extensión **Extension Pack for Java** instalada en VS Code

---

#  Ejecutar desde Visual Studio Code

##  Abrir el proyecto

- Abrir Visual Studio Code
- Seleccionar **File → Open Folder**
- Abrir la carpeta del proyecto

---

##  Ejecutar el programa

1. Entrar a la carpeta `java`
2. Abrir el archivo `Main.java`
3. Presionar el botón **Run ** que aparece arriba del método `main`
4. El programa se ejecutará en la terminal integrada de VS Code

---

#  Ejecución desde Terminal

##  Pasos

```bash
# 1. Entrar a la carpeta Java
cd java

# 2. Compilar los archivos
javac *.java

# 3. Ejecutar el programa
java Main
```

---

#  Funciones Implementadas

| # | Función | Descripción |
|---|---|---|
| 1 | `insertarEstudiante()` | Inserta estudiantes por cédula y evita duplicados |
| 2 | `buscarEstudiante()` | Búsqueda eficiente O(log n) |
| 3 | `eliminarEstudiante()` | Maneja los 3 casos clásicos del BST |
| 4 | `recorridoInorden()` | Izq → Raíz → Der |
| 5 | `recorridoPreorden()` | Raíz → Izq → Der |
| 6 | `recorridoPostorden()` | Izq → Der → Raíz |
| 7 | `recorridoPorNiveles()` | BFS usando Queue |
| 8 | `contarNodos()` | Cuenta estudiantes registrados |
| 9 | `calcularAltura()` | Calcula profundidad máxima |
| 10 | `buscarNotaMayor()` | Encuentra el mejor promedio |
| 11 | `buscarNotaMenor()` | Encuentra el promedio más bajo |
| 12 | `mostrarAprobados()` | Muestra estudiantes con nota ≥ 7 |
| 13 | `mostrarReprobados()` | Muestra estudiantes con nota < 7 |

---

#  Reglas Técnicas Aplicadas

 Implementación de Árbol Binario de Búsqueda (BST)  
 Uso de recursividad en operaciones principales  
 BFS implementado con `Queue` y `LinkedList`  
 Validación de datos y control de errores  
 Código modular y organizado  
Principios de Programación Orientada a Objetos  
 Comentarios y documentación tipo Javadoc  
 Separación de responsabilidades por clases  

---

# Tecnologías Utilizadas

- Java 17
- Programación Orientada a Objetos (POO)
- Consola interactiva
