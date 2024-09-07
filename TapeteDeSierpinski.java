/**
# Autor: Jonathan Hernández
# Fecha: 07 Septiembre 2024
# Descripción: Código para simulacion del tapete de Sierpinski 
# GitHub: https://github.com/Jona163
*/

/**
 * Tapete de Sierpinski recursivo en Java.
 * @author Jony
 */

 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.MouseAdapter;
 import java.awt.event.MouseEvent;
 
 public class TapeteDeSierpinski extends JPanel {
 
     // Tamaño inicial del cuadrado y margen
     private final int ladoInicial = 400;
     private final int margen = 20;
 
     public TapeteDeSierpinski() {
         // Detectar clic del ratón para iniciar el dibujo
         addMouseListener(new MouseAdapter() {
             @Override
             public void mousePressed(MouseEvent e) {
                 repaint();  // Redibuja cuando se presiona el ratón
             }
         });
     }
 
     @Override
     protected void paintComponent(Graphics g) {
         super.paintComponent(g);  // Limpia la pantalla
         setBackground(Color.WHITE);  // Fondo blanco
 
         Graphics2D g2d = (Graphics2D) g;
 
         // Habilitar suavizado de bordes
         g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
 
         // Dibujar el tapete de Sierpinski a partir del cuadrado inicial
         dibujarTapete(g2d, margen, margen, ladoInicial, 0);
     }
 
     // Método recursivo para dibujar el tapete de Sierpinski
     private void dibujarTapete(Graphics2D g, int x, int y, int lado, int nivel) {
         // Cambiar el color según el nivel de recursión
         g.setColor(interpolarColor(new Color(30, 144, 255), new Color(255, 69, 0), nivel / 6.0f));
 
         // Dibujar el cuadrado exterior
         g.fillRect(x, y, lado, lado);
 
         // Si el cuadrado es suficientemente grande, continuar dividiendo
         if (lado > 10) {
             int nuevoLado = lado / 3;
 
             // Dibujar los 8 cuadrados alrededor del cuadrado central
             for (int i = 0; i < 3; i++) {
                 for (int j = 0; j < 3; j++) {
                     // Evitar dibujar en el centro (tapete de Sierpinski)
                     if (i == 1 && j == 1) {
                         g.setColor(Color.WHITE);
                         g.fillRect(x + nuevoLado, y + nuevoLado, nuevoLado, nuevoLado);  // Crear el agujero central
                     } else {
                         dibujarTapete(g, x + i * nuevoLado, y + j * nuevoLado, nuevoLado, nivel + 1);
                     }
                 }
             }
         }
     }
 
     // Método para interpolar entre dos colores
     private Color interpolarColor(Color c1, Color c2, float ratio) {
         int rojo = (int) (c1.getRed() * (1 - ratio) + c2.getRed() * ratio);
         int verde = (int) (c1.getGreen() * (1 - ratio) + c2.getGreen() * ratio);
         int azul = (int) (c1.getBlue() * (1 - ratio) + c2.getBlue() * ratio);
         return new Color(rojo, verde, azul);
     }
 
     public static void main(String[] args) {
         // Crear la ventana principal
         JFrame ventana = new JFrame("Tapete de Sierpinski Recursivo");
         TapeteDeSierpinski panel = new TapeteDeSierpinski();
 
         // Configuración de la ventana
         ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         ventana.add(panel);
         ventana.setSize(500, 500);  // Tamaño de la ventana ajustado
         ventana.setLocationRelativeTo(null);  // Centrar ventana
         ventana.setVisible(true);
     }
 }
 
