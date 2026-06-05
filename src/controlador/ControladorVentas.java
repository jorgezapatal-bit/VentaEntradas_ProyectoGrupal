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
                // 1. Capturamos TODO lo que el usuario escribió
                String nombreZona = vista.cboZonas.getSelectedItem().toString();
                String cantTexto = vista.txtCantidad.getText();
                String numTarjeta = vista.txtTarjeta.getText();

                // 2. Validamos que los cuadros no estén vacíos
                if (cantTexto.trim().isEmpty() || numTarjeta.trim().isEmpty()) {
                    throw new Exception("Por favor, ingrese la cantidad de entradas y su número de tarjeta.");
                }

                // 3. ¡VALIDACIÓN DE TARJETA PRIMERO!
                // Validamos que al menos haya escrito algo coherente para una tarjeta
                if (numTarjeta.length() < 4) { 
                    throw new excepciones.TarjetaInvalidaException("Transacción denegada: Número de tarjeta inválido o muy corto.");
                }
                
                // Creamos la tarjeta ANTES de vender la entrada (pasándole el String)
                modelo.Tarjeta tarjetaUsada = new modelo.Tarjeta(numTarjeta, "Cliente", "12/28", 123);

                // 4. Convertimos la cantidad a número (Si pone letras aquí, salta al catch de abajo)
                int cantidad = Integer.parseInt(cantTexto);
                
                // 5. Buscamos la zona
                modelo.Zona zonaSeleccionada = general.Sistema.zonas.getZonaxNombre(nombreZona);
                if (zonaSeleccionada == null) {
                    throw new Exception("La zona seleccionada no existe en la base de datos.");
                }

                // 6. ¡LÓGICA DE NEGOCIO! (Generamos boletos y cobramos)
                modelo.Entrada[] boletosGenerados = zonaSeleccionada.venderEntrada(cantidad);
                double totalPagar = cantidad * zonaSeleccionada.getPrecio();
                
                // 7. REGISTRO HISTÓRICO DE LA VENTA
                modelo.Venta nuevaVenta = new modelo.Venta(new java.util.Date(), totalPagar, tarjetaUsada);
                for (int i = 0; i < boletosGenerados.length; i++) {
                    nuevaVenta.getEntradas().add(boletosGenerados[i]);
                }
                general.Sistema.ventas.add(nuevaVenta);
                System.out.println("Venta registrada exitosamente.");

                // 8. MOSTRAMOS LA BOLETA (El mensaje de éxito ahora sí va al final)
                String resumen = "¡Venta exitosa!\n\n";
                resumen += "Zona: " + nombreZona + "\n";
                resumen += "Cantidad: " + cantidad + "\n";
                resumen += "Tarjeta usada: " + numTarjeta + "\n";
                resumen += "Total a Pagar: S/ " + totalPagar + "\n\n";
                resumen += "Sus números de boleto son:\n";
                
                for (int i = 0; i < boletosGenerados.length; i++) {
                    resumen += "- Boleto #" + boletosGenerados[i].getNumero() + "\n";
                }

                javax.swing.JOptionPane.showMessageDialog(vista, resumen, "Boleta de Venta", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                
                // Limpiamos los cuadros
                vista.txtCantidad.setText(""); 
                vista.txtTarjeta.setText("");

            } catch (NumberFormatException ex) {
                // Esto solo saltará si ponen letras en la CANTIDAD de entradas
                javax.swing.JOptionPane.showMessageDialog(vista, "Error: La cantidad de entradas debe ser un número entero válido.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                // Atrapa las excepciones personalizadas de Tarjeta o Capacidad
                javax.swing.JOptionPane.showMessageDialog(vista, ex.getMessage(), "Alerta", javax.swing.JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}