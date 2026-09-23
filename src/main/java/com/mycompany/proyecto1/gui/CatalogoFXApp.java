package com.mycompany.proyecto1.gui;

import com.mycompany.proyecto1.Datos.ConexionBD;
import com.mycompany.proyecto1.Datos.ProductoDAO;
import com.mycompany.proyecto1.modelo.CatalogoProductos;
import com.mycompany.proyecto1.modelo.Producto;
import com.mycompany.proyecto1.modelo.muestraTomadaCasa;
import com.mycompany.proyecto1.modelo.muestraTomadaLab;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.SQLException;

public class CatalogoFXApp extends Application {

    private final CatalogoProductos catalogo = new CatalogoProductos();
    private final ProductoDAO productoDAO = new ProductoDAO();
    private final ObservableList<Producto> tablaModel = FXCollections.observableArrayList();

    private TextField txtCodigo, txtNombre, txtDescripcion, txtPrecio, txtImpuesto, txtTipoMuestra, txtKm, txtPlaca;
    private CheckBox chkActivo;
    private ComboBox<String> cbTipoProducto;
    private TableView<Producto> tablaProductos;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ConexionBD.inicializarBD();
        cargarDatosDesdeBD();

        primaryStage.setTitle("Gestión de Catálogo de Productos (JavaFX + SQLite)");

        // Formulario
        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(10);
        formGrid.setPadding(new Insets(15));

        txtCodigo = new TextField();
        txtNombre = new TextField();
        txtDescripcion = new TextField();
        txtPrecio = new TextField();
        txtImpuesto = new TextField();
        txtTipoMuestra = new TextField();
        txtKm = new TextField();
        txtPlaca = new TextField();
        chkActivo = new CheckBox("Activo");
        chkActivo.setSelected(true);

        cbTipoProducto = new ComboBox<>(FXCollections.observableArrayList("Muestra Laboratorio", "Muestra Domicilio"));
        cbTipoProducto.setValue("Muestra Laboratorio");

        // Evento para habilitar/deshabilitar campos según el tipo
        cbTipoProducto.setOnAction(e -> actualizarVisibilidadCampos());

        formGrid.add(new Label("Tipo Producto:"), 0, 0); formGrid.add(cbTipoProducto, 1, 0);
        formGrid.add(new Label("Código:"), 0, 1); formGrid.add(txtCodigo, 1, 1);
        formGrid.add(new Label("Nombre:"), 0, 2); formGrid.add(txtNombre, 1, 2);
        formGrid.add(new Label("Descripción:"), 0, 3); formGrid.add(txtDescripcion, 1, 3);
        formGrid.add(new Label("Precio ($):"), 0, 4); formGrid.add(txtPrecio, 1, 4);
        formGrid.add(new Label("Impuesto (%):"), 0, 5); formGrid.add(txtImpuesto, 1, 5);
        formGrid.add(new Label("Tipo Muestra:"), 0, 6); formGrid.add(txtTipoMuestra, 1, 6);
        formGrid.add(new Label("Kilometraje:"), 0, 7); formGrid.add(txtKm, 1, 7);
        formGrid.add(new Label("Placa Vehículo:"), 0, 8); formGrid.add(txtPlaca, 1, 8);
        formGrid.add(chkActivo, 1, 9);

        // Botones CRUD
        Button btnAgregar = new Button("Agregar");
        Button btnBuscar = new Button("Buscar");
        Button btnActualizar = new Button("Actualizar");
        Button btnEliminar = new Button("Eliminar");
        Button btnLimpiar = new Button("Limpiar");

        HBox panelBotones = new HBox(10, btnAgregar, btnBuscar, btnActualizar, btnEliminar, btnLimpiar);
        panelBotones.setPadding(new Insets(10));

        // Eventos de los Botones
        btnAgregar.setOnAction(e -> ejecutarAgregar());
        btnBuscar.setOnAction(e -> ejecutarBuscar());
        btnActualizar.setOnAction(e -> ejecutarActualizar());
        btnEliminar.setOnAction(e -> ejecutarEliminar());
        btnLimpiar.setOnAction(e -> limpiarFormulario());

        // Configuración de la Tabla
        tablaProductos = new TableView<>(tablaModel);
        
        TableColumn<Producto, String> colCodigo = new TableColumn<>("Código");
        colCodigo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCodigo()));

        TableColumn<Producto, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombre()));

        TableColumn<Producto, String> colPrecio = new TableColumn<>("Precio");
        colPrecio.setCellValueFactory(data -> new SimpleStringProperty(String.format("$%.2f", data.getValue().getPrecio())));

        TableColumn<Producto, String> colResumen = new TableColumn<>("Detalles");
        colResumen.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().resumen()));

        tablaProductos.getColumns().addAll(colCodigo, colNombre, colPrecio, colResumen);
        tablaProductos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, p) -> cargarEnFormulario(p));

        VBox layoutDerecha = new VBox(10, new Label("Catálogo Registrado"), tablaProductos);
        layoutDerecha.setPadding(new Insets(15));
        VBox.setVgrow(tablaProductos, Priority.ALWAYS);

        BorderPane mainLayout = new BorderPane();
        mainLayout.setLeft(new VBox(10, formGrid, panelBotones));
        mainLayout.setCenter(layoutDerecha);

        actualizarVisibilidadCampos();

        primaryStage.setScene(new Scene(mainLayout, 950, 500));
        primaryStage.show();
    }

    private void cargarDatosDesdeBD() {
        try {
            var listaBD = productoDAO.cargarTodos();
            catalogo.cargarDesdeLista(listaBD);
            tablaModel.setAll(catalogo.listarProductos());
        } catch (SQLException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error BD", "No se pudieron cargar los datos: " + e.getMessage());
        }
    }

    private void ejecutarAgregar() {
        try {
            Producto nuevo = construirProductoDesdeFormulario();
            if (catalogo.buscarProducto(nuevo.getCodigo()) != null) {
                mostrarAlerta(Alert.AlertType.WARNING, "Duplicado", "El producto con código " + nuevo.getCodigo() + " ya existe.");
                return;
            }

            productoDAO.guardar(nuevo);
            catalogo.agregarProducto(nuevo);
            refrescarTabla();
            limpiarFormulario();
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Producto agregado correctamente.");
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de Datos", e.getMessage());
        }
    }

    private void ejecutarBuscar() {
        String codigo = txtCodigo.getText().trim();
        if (codigo.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campo Vacío", "Ingrese un código para buscar.");
            return;
        }

        Producto p = catalogo.buscarProducto(codigo);
        if (p != null) {
            cargarEnFormulario(p);
            tablaProductos.getSelectionModel().select(p);
            mostrarAlerta(Alert.AlertType.INFORMATION, "Encontrado", "Producto localizado correctamente.");
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "No Encontrado", "El código ingresado no existe en el catálogo.");
        }
    }

    private void ejecutarActualizar() {
        try {
            String codigo = txtCodigo.getText().trim();
            Producto pExistente = catalogo.buscarProducto(codigo);

            if (pExistente == null) {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "El producto a actualizar no existe.");
                return;
            }

            Producto nuevo = construirProductoDesdeFormulario();
            productoDAO.actualizar(nuevo);
            catalogo.actualizarProducto(codigo, nuevo);
            refrescarTabla();
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Producto actualizado correctamente.");
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error al Actualizar", e.getMessage());
        }
    }

    private void ejecutarEliminar() {
        String codigo = txtCodigo.getText().trim();
        if (codigo.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campo Vacío", "Ingrese el código a eliminar.");
            return;
        }

        try {
            if (catalogo.eliminarProducto(codigo)) {
                productoDAO.eliminar(codigo);
                refrescarTabla();
                limpiarFormulario();
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Producto eliminado de la base de datos.");
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se encontró el producto a eliminar.");
            }
        } catch (SQLException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error BD", "Error al eliminar en la BD: " + e.getMessage());
        }
    }

    private Producto construirProductoDesdeFormulario() {
        String codigo = txtCodigo.getText().trim();
        String nombre = txtNombre.getText().trim();
        String descripcion = txtDescripcion.getText().trim();
        double precio = Double.parseDouble(txtPrecio.getText().trim());
        double impuesto = Double.parseDouble(txtImpuesto.getText().trim());
        boolean activo = chkActivo.isSelected();
        String tipoMuestra = txtTipoMuestra.getText().trim();

        if ("Muestra Domicilio".equals(cbTipoProducto.getValue())) {
            double km = Double.parseDouble(txtKm.getText().trim());
            String placa = txtPlaca.getText().trim();
            return new muestraTomadaCasa(codigo, nombre, descripcion, precio, impuesto, activo, km, placa, tipoMuestra);
        } else {
            return new muestraTomadaLab(codigo, nombre, descripcion, precio, impuesto, activo, tipoMuestra);
        }
    }

    private void cargarEnFormulario(Producto p) {
        if (p == null) return;
        txtCodigo.setText(p.getCodigo());
        txtNombre.setText(p.getNombre());
        txtDescripcion.setText(p.getDescripcion());
        txtPrecio.setText(String.valueOf(p.getPrecio()));
        txtImpuesto.setText(String.valueOf(p.getImpuesto()));
        chkActivo.setSelected(p.getActivo());

        if (p instanceof muestraTomadaCasa casa) {
            cbTipoProducto.setValue("Muestra Domicilio");
            txtTipoMuestra.setText(casa.getTipoMuestra());
            txtKm.setText(String.valueOf(casa.getKilometraje()));
            txtPlaca.setText(casa.getPlaca_vehiculo());
        } else if (p instanceof muestraTomadaLab lab) {
            cbTipoProducto.setValue("Muestra Laboratorio");
            txtTipoMuestra.setText(lab.getTipoMuestra());
        }
        actualizarVisibilidadCampos();
    }

    private void actualizarVisibilidadCampos() {
        boolean esCasa = "Muestra Domicilio".equals(cbTipoProducto.getValue());
        txtKm.setDisable(!esCasa);
        txtPlaca.setDisable(!esCasa);
    }

    private void limpiarFormulario() {
        txtCodigo.clear();
        txtNombre.clear();
        txtDescripcion.clear();
        txtPrecio.clear();
        txtImpuesto.clear();
        txtTipoMuestra.clear();
        txtKm.clear();
        txtPlaca.clear();
        chkActivo.setSelected(true);
        tablaProductos.getSelectionModel().clearSelection();
    }

    private void refrescarTabla() {
        tablaModel.setAll(catalogo.listarProductos());
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}