package modelo;

public class Tarjeta {
    private String numero; // ¡Cambiado a String!
    private String nombre;
    private String fecha;
    private int cvv;

    public Tarjeta(String numero, String nombre, String fecha, int cvv) {
        this.numero = numero;
        this.nombre = nombre;
        this.fecha = fecha;
        this.cvv = cvv;
    }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public int getCvv() { return cvv; }
    public void setCvv(int cvv) { this.cvv = cvv; }
}
