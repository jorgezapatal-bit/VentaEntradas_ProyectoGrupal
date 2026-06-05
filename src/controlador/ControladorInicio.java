package controlador;

import general.Sistema;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.PersonaArreglo;
import vista.frmPrincipal; 

public class ControladorInicio {
    
    private PersonaArreglo modelo;
    private frmPrincipal vista; 

    public ControladorInicio(PersonaArreglo modelo, frmPrincipal vista) {
        this.modelo = modelo;
        this.vista = vista;
        
        // Asignamos el evento al botón de ingresar
        this.vista.btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Capturamos las credenciales ingresadas en las cajas de texto
                    String dni = vista.txtDni.getText();
                    String pass = vista.txtContrasena.getText();
                    
                    if (dni.trim().isEmpty() || pass.trim().isEmpty()) {
                        throw new Exception("Por favor, ingrese su DNI y Contraseña.");
                    }
                    
                    // Buscamos a la Persona (Usuario o Cliente) en el arreglo polimórfico
                    Sistema.personaConectada = modelo.ingresar(dni, pass);
                    
                    if (Sistema.personaConectada != null) {
                        JOptionPane.showMessageDialog(vista, "¡Bienvenido al sistema de Conciertos!");
                        
                        // Obtenemos el CardLayout del panel central y avanzamos a la siguiente carta (Ventas)
                        java.awt.CardLayout layout = (java.awt.CardLayout) vista.panCentro.getLayout();
                        layout.next(vista.panCentro);
                        
                    } else {
                        JOptionPane.showMessageDialog(vista, "Error: DNI o contraseña incorrectos", "Aviso", JOptionPane.ERROR_MESSAGE);
                    }
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, ex.getMessage(), "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }
}