package controlador;

import general.Sistema;
import modelo.Zona;
import vista.frmVentas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ControladorVentas implements ActionListener {
    
    private frmVentas vista;

    public ControladorVentas(frmVentas vista) {
        this.vista = vista;
        
        // Llenamos la lista desplegable con los nombres exactos de las zonas
        this.vista.cboZonas.removeAllItems();
        this.vista.cboZonas.addItem("VIP");
        this.vista.cboZonas.addItem("General");
        
        // Activamos el botón
        this.vista.btnComprar.addActionListener(this);
    }

    public void iniciar() {
        vista.setTitle("Sistema de Ventas - Módulo de Compra");
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == vista.btnComprar) {
            try {
                // 1. Capturamos los datos de la vista
                String nombreZona = vista.cboZonas.getSelectedItem().toString();
                String cantTexto = vista.txtCantidad.getText();

                // 2. Validamos que el cuadro no esté vacío
                if (cantTexto.trim().isEmpty()) {
                    throw new Exception("Por favor, ingrese la cantidad de entradas.");
                }

                // 3. Convertimos el texto a número
                int cantidad = Integer.parseInt(cantTexto);
                
                // 4. Buscamos la zona en el Sistema global
                Zona zonaSeleccionada = Sistema.zonas.getZonaxNombre(nombreZona);

                if (zonaSeleccionada == null) {
                    throw new Exception("La zona seleccionada no existe en la base de datos.");
                }

                // 5. ¡AQUÍ ESTÁ LA LÓGICA DE NEGOCIO ACTUALIZADA!
                
                // Leemos el número de tarjeta del nuevo cuadro
                String numTarjeta = vista.txtTarjeta.getText();
                
                // Si el cuadro está vacío, lanzamos TU excepción personalizada
                if (numTarjeta.trim().isEmpty()) {
                    throw new excepciones.TarjetaInvalidaException("Transacción denegada: Debe ingresar una tarjeta para procesar el pago.");
                }
                
                // Si sí hay tarjeta, generamos los boletos
                modelo.Entrada[] boletosGenerados = zonaSeleccionada.venderEntrada(cantidad);
                
                // Calculamos el total
                double totalPagar = cantidad * zonaSeleccionada.getPrecio();
                
                // Armamos el texto de la boleta
                String resumen = "¡Venta exitosa!\n\n";
                resumen += "Zona: " + nombreZona + "\n";
                resumen += "Cantidad: " + cantidad + "\n";
                resumen += "Tarjeta usada: " + numTarjeta + "\n";
                resumen += "Total a Pagar: S/ " + totalPagar + "\n\n";
                resumen += "Sus números de boleto son:\n";
                
                for (int i = 0; i < boletosGenerados.length; i++) {
                    resumen += "- Boleto #" + boletosGenerados[i].getNumero() + "\n";
                }

                JOptionPane.showMessageDialog(vista, resumen, "Boleta de Venta", JOptionPane.INFORMATION_MESSAGE);
                
                // Limpiamos los cuadros para la siguiente compra
                vista.txtCantidad.setText(""); 
                vista.txtTarjeta.setText("");

            } catch (NumberFormatException ex) {
                // Atrapa el error si el usuario escribe letras en vez de números
                JOptionPane.showMessageDialog(vista, "Error: La cantidad debe ser un número entero válido.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                // ¡Este bloque mágico atrapa tanto CapacidadExcedidaException como LimiteEntradasException!
                JOptionPane.showMessageDialog(vista, ex.getMessage(), "Alerta de Compra", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}