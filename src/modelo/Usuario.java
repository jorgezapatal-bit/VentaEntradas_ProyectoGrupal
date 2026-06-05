package modelo;

import modelo.Persona;

public class Usuario extends Persona {
    private boolean estado;

    public Usuario(String nombres, String apellidos, String dni, String contrasena, boolean estado) {
        super(nombres, apellidos, dni, contrasena);
        this.estado = estado;
    }

    public void registrarZonas() { }

    @Override 
    public boolean registrarTarjeta() throws excepciones.TarjetaInvalidaException { 
        return true; 
    }
    @Override public boolean eliminarTarjeta() { return false; }
    @Override public boolean anularVenta() { return false; }
    @Override public boolean comprar() { return false; }

    public boolean isEstado() { return estado; }
    public void setEstado(boolean estado) { this.estado = estado; }
}