/*

 * @Nombre    : ClienteServicioCNE
 * @Author    : Erick Rodriguez
 * @Copyright : Erick Rodriguez
 * @Creado el : 06-sep-2021, 03:21:42 PM
 */
package clienteserviciocne;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author erams
 */
public class ClienteServicioCNE {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        mostrarInicio();
    }
    
    public static void mostrarInicio() {
        ConsultaCNE f = new ConsultaCNE();//623, 359
        int w = 623;
        int h = 359;
        f.setMinimumSize(new Dimension(w, h));
        f.setMaximumSize(new Dimension(w, h));
        f.setResizable(false);
        f.setTitle("Consulta CNE WEB SERVICE");

        //obtener el tamaño de la pantalla
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        // Determinar la posición del inicio de pantalla
        int x = (dim.width - w) / 2;//Ancho de pantalla menos ancho de ventana dividido entre 2
        int y = (dim.height - h) / 2;//alto de pantalla menos alto de ventana dividido entre 2

        // Asignar la ventana
        f.setLocation(x, y);

        f.setVisible(true);
    }
    
}
