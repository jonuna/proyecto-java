# Sistema de Gestión de Catálogo de Productos

Aplicación de escritorio en Java para la gestión de productos y muestras médicas (laboratorio y domicilio), implementando conceptos de Programación Orientada a Objetos, colecciones genéricas, interfaz gráfica con JavaFX y persistencia en base de datos local con SQLite.

---

## 🛠️ Tecnologías y Requisitos

* **Lenguaje:** Java 17 o superior (compatible con Java 21)
* **IDE Recomendado:** NetBeans IDE 12+ / 17+ / 21+
* **Gestor de Construcción:** Maven
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
