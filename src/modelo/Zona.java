package modelo;

import excepciones.CapacidadExcedidaException;
import excepciones.LimiteEntradasException;

public class Zona {
    private String nombre;
    private int capacidad;
    private int precio;
    private final EntradaArreglo entradas; 
    private int entradasVendidas; // Para llevar la cuenta manual

    public Zona(String nombre, int capacidad, int precio) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.precio = precio;
        this.entradas = new EntradaArreglo(capacidad);
        this.entradasVendidas = 0;
    }

    public Entrada[] venderEntrada(int numero) throws CapacidadExcedidaException, LimiteEntradasException {
        if (numero > 4) {
            throw new LimiteEntradasException("No se puede vender más de 4 entradas por transacción.");
        }
        if (this.entradasVendidas + numero > capacidad) {
            throw new CapacidadExcedidaException("No hay suficiente capacidad en la zona " + nombre);
        }
        
        // Creamos un arreglo temporal para guardar las entradas que se acaban de comprar
        Entrada[] entradasCompradas = new Entrada[numero];
        
        for (int i = 0; i < numero; i++) {
            // El número de entrada será el correlativo de las vendidas + 1
            int numeroBoleto = this.entradasVendidas + 1;
            Entrada nuevaEntrada = new Entrada(numeroBoleto, "VENDIDA");
            
            // Guardamos en nuestro arreglo de la zona y en el arreglo del cliente
            this.entradas.add(nuevaEntrada); 
            entradasCompradas[i] = nuevaEntrada;
            
            this.entradasVendidas++;
        }
        
        // Ahora sí devolvemos las entradas reales en vez de null
        return entradasCompradas; 
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }
    public int getPrecio() { return precio; }
    public void setPrecio(int precio) { this.precio = precio; }
}