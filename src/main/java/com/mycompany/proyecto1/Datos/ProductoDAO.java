package com.mycompany.proyecto1.Datos;

import com.mycompany.proyecto1.modelo.Producto;
import com.mycompany.proyecto1.modelo.muestraTomadaCasa;
import com.mycompany.proyecto1.modelo.muestraTomadaLab;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    public void guardar(Producto producto) throws SQLException {
        String sql = "INSERT INTO productos VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, producto.getCodigo());
            pstmt.setString(2, producto.getNombre());
            pstmt.setString(3, producto.getDescripcion());
            pstmt.setDouble(4, producto.getPrecio());
            pstmt.setDouble(5, producto.getImpuesto());
            pstmt.setInt(6, producto.getActivo() ? 1 : 0);

            if (producto instanceof muestraTomadaCasa casa) {
                pstmt.setString(7, "CASA");
                pstmt.setString(8, casa.getTipoMuestra());
                pstmt.setDouble(9, casa.getKilometraje());
                pstmt.setString(10, casa.getPlaca_vehiculo());
            } else if (producto instanceof muestraTomadaLab lab) {
                pstmt.setString(7, "LAB");
                pstmt.setString(8, lab.getTipoMuestra());
                pstmt.setNull(9, Types.REAL);
                pstmt.setNull(10, Types.VARCHAR);
            }
            pstmt.executeUpdate();
        }
    }

    public List<Producto> cargarTodos() throws SQLException {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos";

        try (Connection conn = ConexionBD.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String codigo = rs.getString("codigo");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                double precio = rs.getDouble("precio");
                double impuesto = rs.getDouble("impuesto");
                boolean activo = rs.getInt("activo") == 1;
                String tipoClase = rs.getString("tipo_clase");
                String tipoMuestra = rs.getString("tipo_muestra");

                if ("CASA".equals(tipoClase)) {
                    double km = rs.getDouble("kilometraje");
                    String placa = rs.getString("placa");
                    lista.add(new muestraTomadaCasa(codigo, nombre, descripcion, precio, impuesto, activo, km, placa, tipoMuestra));
                } else if ("LAB".equals(tipoClase)) {
                    lista.add(new muestraTomadaLab(codigo, nombre, descripcion, precio, impuesto, activo, tipoMuestra));
                }
            }
        }
        return lista;
    }

    public void actualizar(Producto producto) throws SQLException {
        String sql = "UPDATE productos SET nombre=?, descripcion=?, precio=?, impuesto=?, activo=?, tipo_muestra=?, kilometraje=?, placa=? WHERE codigo=?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, producto.getNombre());
            pstmt.setString(2, producto.getDescripcion());
            pstmt.setDouble(3, producto.getPrecio());
            pstmt.setDouble(4, producto.getImpuesto());
            pstmt.setInt(5, producto.getActivo() ? 1 : 0);

            if (producto instanceof muestraTomadaCasa casa) {
                pstmt.setString(6, casa.getTipoMuestra());
                pstmt.setDouble(7, casa.getKilometraje());
                pstmt.setString(8, casa.getPlaca_vehiculo());
            } else if (producto instanceof muestraTomadaLab lab) {
                pstmt.setString(6, lab.getTipoMuestra());
                pstmt.setNull(7, Types.REAL);
                pstmt.setNull(8, Types.VARCHAR);
            }
            pstmt.setString(9, producto.getCodigo());
            pstmt.executeUpdate();
        }
    }

    public void eliminar(String codigo) throws SQLException {
        String sql = "DELETE FROM productos WHERE codigo = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, codigo);
            pstmt.executeUpdate();
        }
    }
}