package com.mycompany.proyecto1.Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBD {
    private static final String URL = "jdbc:sqlite:catalogo.db";

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void inicializarBD() {
        String sql = "CREATE TABLE IF NOT EXISTS productos ("
                   + "codigo TEXT PRIMARY KEY, "
                   + "nombre TEXT NOT NULL, "
                   + "descripcion TEXT, "
                   + "precio REAL NOT NULL, "
                   + "impuesto REAL NOT NULL, "
                   + "activo INTEGER NOT NULL, "
                   + "tipo_clase TEXT NOT NULL, "
                   + "tipo_muestra TEXT NOT NULL, "
                   + "kilometraje REAL, "
                   + "placa TEXT"
                   + ");";

        try (Connection conn = obtenerConexion();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Error al inicializar la base de datos: " + e.getMessage());
        }
    }
}
