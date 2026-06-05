package ventaentradas;

import controlador.ControladorInicio;
import controlador.ControladorVentas; // Importamos el controlador de ventas
import general.Sistema;
import modelo.Usuario;
import modelo.Zona;
import vista.frmPrincipal; // Cambiamos el import a la ventana unificada

public class VentaEntradas {

    public static void main(String[] args) {
        // 1. Agregamos objetos de DISTINTAS clases al mismo arreglo (Polimorfismo)
        general.Sistema.personas.add(new modelo.Usuario("Jorge", "Zavaleta", "12345678", "secreto123", true));
        general.Sistema.personas.add(new modelo.Cliente("Maria", "Gomez", "87654321", "cliente1", 0)); // Un cliente
        general.Sistema.personas.add(new modelo.Cliente("Luis", "Perez", "11112222", "cliente2", 0));  // Otro cliente
        
        // Zonas de prueba
        general.Sistema.zonas.add(new modelo.Zona("VIP", 50, 200));     
        general.Sistema.zonas.add(new modelo.Zona("General", 100, 50)); 
        
        // 2. Instanciamos la NUEVA vista unificada
        vista.frmPrincipal ventana = new vista.frmPrincipal();
        
        // Le pasamos el nuevo arreglo de personas y la MISMA ventana a ambos controladores
        controlador.ControladorInicio ctrlInicio = new controlador.ControladorInicio(general.Sistema.personas, ventana);
        controlador.ControladorVentas ctrlVentas = new controlador.ControladorVentas(ventana);
        
        // 3. ¡Arrancamos el programa!
        ventana.setTitle("Sistema de Conciertos - Venta de Entradas");
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
}