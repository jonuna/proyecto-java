# Sistema de Gestión de Productos - Pila, Cola y Repository Pattern

Este proyecto implementa estructuras de datos lineales (Pila LIFO y Cola Circular FIFO) desarrolladas sobre arreglos estáticos en Java, junto con el patrón de diseño **Repository** para la abstracción de operaciones del dominio.

---

## 🔗 Repositorio del Proyecto
* **GitHub Repository:** `https://github.com/TU_USUARIO/TU_REPOSITORIO`

---

## 🛠️ Tecnologías Utilizadas
* **Lenguaje:** Java (Versión 17+)
* **Pruebas Unitarias:** JUnit 5 (Jupiter API)
* **Gestor de Dependencias:** Maven

---

## 📁 Estructura del Código
* `com.mycompany.proyecto1.pilaYcola.PilaYCola`:
  * `PilaProductos`: Implementación estática LIFO.
  * `ColaProductos`: Implementación estática de Cola Circular FIFO.
  * `RepositorioProductos`: Capa de abstracción basada en el patrón Repository.
* `tester`: Clase con el conjunto de pruebas unitarias implementadas en JUnit 5.

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
