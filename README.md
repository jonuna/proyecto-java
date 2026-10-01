# Sistema de Gestión de Productos - Pila, Cola y Repository Pattern

Este proyecto implementa estructuras de datos lineales (Pila LIFO y Cola Circular FIFO) desarrolladas sobre arreglos estáticos en Java, junto con el patrón de diseño **Repository** para la abstracción de operaciones del dominio.

---

## 🔗 Repositorio del Proyecto
* **GitHub Repository:** `https://github.com/TU_USUARIO/TU_REPOSITORIO`

---

## 🛠️ Tecnologías Utilizadas
* **Lenguaje:** Java (Versión 17+)
* **IDE Recomendado:** NetBeans IDE 12+ / 17+ / 21+
* **Pruebas Unitarias:** JUnit 5 (Jupiter API)
* **Gestor de Dependencias:** Maven
* **Librerías / Frameworks:**
  * **JavaFX** (`javafx-controls`, `javafx-fxml`) v21.0.2
  * **SQLite JDBC** v3.45.1.0

---

## 📁 Estructura del Proyecto

```text
src/main/java/com/mycompany/proyecto1/
│
├── MainApp.java                      # Clase principal lanzadora
│
├── datos/
│   ├── ConexionBD.java               # Gestión de conexión SQLite e inicialización de tabla
│   └── ProductoDAO.java              # Operaciones CRUD en la base de datos
│
├── gui/
│   └── CatalogoFXApp.java            # Interfaz Gráfica con JavaFX y controladores
|
├── pilaYcola/
│   └── PilaYCola.java                # Pila y cola de productos
│
└── modelo/
    ├── Cliente.java                  # Clase abstracta Cliente
    ├── clienteMayosita.java          # Subclase Cliente Mayorista (RUC)
    ├── clienteMinorista.java         # Subclase Cliente Minorista (Cédula)
    ├── Iidentificacion.java          # Interfaz para identificación
    ├── Producto.java                 # Clase base Producto
    ├── muestraTomadaCasa.java        # Subclase de Producto (Muestra a domicilio)
    ├── muestraTomadaLab.java         # Subclase de Producto (Muestra en laboratorio)
    ├── CatalogoProductos.java        # Gestión del catálogo con ArrayList y HashSet
    ├── ItemProforma.java             # Detalle de ítem de proforma
    └── Proforma.java                 # Entidad Proforma

src/main/java/com/mycompany/Test/
│
├── tester.java                       # Clase con el conjunto de pruebas unitarias implementadas en JUnit 5.

```
---


## 🚀 Pasos para Ejecutar la Aplicación
1. Abrir el Proyecto en NetBeans:
    Abre NetBeans y ve a File -> Open Project....
    Selecciona la carpeta del proyecto proyecto1.

2. Configurar la Clase Principal (Main Class):

    Haz clic derecho sobre el proyecto proyecto1 en la pestaña Projects.
    Selecciona Properties.
    En el menú lateral de la izquierda, selecciona Run.
    En la casilla Main Class, presiona Browse... y selecciona: com.mycompany.proyecto1.MainApp
    Haz clic en OK.

3. Compilar y Construir:
    Haz clic derecho sobre el proyecto y selecciona Clean and Build (Limpiar y Construir).

4. Ejecutar:
    Presiona la tecla F6 o haz clic en el botón verde Run Project.

---

## 💻 Funcionalidades de la Aplicación

Agregar Producto: Permite registrar muestras de laboratorio o de domicilio. Valida duplicados por código de producto.

Buscar Producto: Carga los datos del producto en el formulario ingresando su código único.

Actualizar Producto: Permite modificar los datos de un producto previamente registrado.

Eliminar Producto: Elimina el registro seleccionado tanto de la colección en memoria como de la base de datos SQLite.

Persistencia Automática: Al iniciar la aplicación, todos los registros almacenados en catalogo.db se cargan en la interfaz gráfica.   


---
## 🚀 Instrucciones para Ejecutar la Aplicación

1. **Clonar el repositorio:**
   ```bash
   git clone (https://github.com/jonuna/proyecto-java/tree/version-clase7)
   cd TU_REPOSITORIO

2. **Compilar y ejecutar el punto de entrada principal**

En NetBeans / IDE: Abrir el proyecto, hacer clic derecho sobre PilaYCola.java y seleccionar Run File (o Shift + F6).

Mediante línea de comandos (Maven):

  mvn clean compile
  mvn exec:java -Dexec.mainClass="com.mycompany.proyecto1.pilaYcola.PilaYCola"

3. 🧪 Instrucciones para Ejecutar las Pruebas Unitarias
El proyecto incluye pruebas unitarias que verifican la lógica de LIFO, FIFO, comportamiento circular, manejo de desbordamiento/subdesbordamiento y restricciones de nulidad.

En NetBeans:

Ubica la clase de prueba tester.java dentro del paquete de pruebas (Test Packages).

Haz clic derecho sobre el archivo y selecciona Test File (o presiona CTRL + F6).

Mediante línea de comandos (Maven):
  mvn test

