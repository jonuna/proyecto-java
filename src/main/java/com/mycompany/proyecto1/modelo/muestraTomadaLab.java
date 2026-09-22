package com.mycompany.proyecto1.modelo;

public class muestraTomadaLab extends Producto {
    private String tipoMuestra;

    public muestraTomadaLab(
            String codigo,
            String nombre,
            String tipoMuestra) {
        super(codigo, nombre);
        setTipoMuestra(tipoMuestra);
    }

    public muestraTomadaLab(
            String codigo,
            String nombre,
            String descripcion,
            double precio,
            double impuestoXciento,
            boolean activo,
            String tipoMuestra) {
        super(codigo, nombre, descripcion, precio, impuestoXciento, activo);
        setTipoMuestra(tipoMuestra);
    }

    public String getTipoMuestra() {
        return tipoMuestra;
    }

    public void setTipoMuestra(String tipoMuestra) {
        this.tipoMuestra = super.textoObligatorio(tipoMuestra, "Se debe especificar el tipo de muestra");
    }

    @Override
    public String resumen() {
        return super.resumen() + "- Tipo de muestra : " + tipoMuestra;
    }
}
