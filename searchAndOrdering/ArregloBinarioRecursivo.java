// Exercise 16.9
// Fig 16.4: ArregloBinario.java
// Clase que contiene un arreglo de enteros aleatorios y un método 
// que utiliza la búsqueda binaria para encontrar un entero.
package deitel.searchAndOrdering;

import java.util.Random;
import java.util.Arrays;

public class ArregloBinarioRecursivo
{
   private int[] datos; // arreglo de valores
   private static Random generador = new Random();

   // crea un arreglo de un tamaño dado y lo llena con enteros aleatorios
   public ArregloBinarioRecursivo( int tamanio )
   {
      datos = new int[ tamanio ]; // crea espacio para el arreglo

      // llena el arreglo con enteros aleatorios en el rango de 10 a 99
      for ( int i = 0; i < tamanio; i++ )
         datos[ i ] = 10 + generador.nextInt( 90 );

      Arrays.sort( datos );
   } // fin del constructor de ArregloBinario

   // realiza una búsqueda binaria en los datos
   public int busquedaBinaria( int elementoBusqueda )
   {
   	return busquedaRecursiva (elementoBusqueda, 0, datos.length - 1);
   } // fin del método busquedaBinaria
   
   private int busquedaRecursiva (final int CLAVE, final int INFERIOR, final int SUPERIOR) {
   	final int MEDIO = (INFERIOR + SUPERIOR) / 2;
   	
   	if (INFERIOR > SUPERIOR)
   		return -1;
   	if (datos[MEDIO] == CLAVE)
   		return MEDIO;
   	else if (CLAVE < datos[MEDIO])
   		return busquedaRecursiva (CLAVE, INFERIOR, MEDIO - 1);
   	else
   		return busquedaRecursiva (CLAVE, MEDIO + 1, SUPERIOR);
   }

   // método para imprimir ciertos valores en el arreglo
   public String elementosRestantes( int inferior, int superior )
   {
      StringBuilder temporal = new StringBuilder();

      // imprime espacios para alineación
      for ( int i = 0; i < inferior; i++ )
         temporal.append( "   " );

      // imprime los elementos que quedan en el arreglo
      for ( int i = inferior; i <= superior; i++ )
         temporal.append( datos[ i ] + " " );

      temporal.append( "\n" );
      return temporal.toString();
   } // fin del método elementosRestantes

   // método para imprimir los valores en el arreglo
   public String toString()
   {
      return elementosRestantes( 0, datos.length - 1 );
   } // fin del método toString  
   
   public static void main (String args[]) {
   	ArregloBinarioRecursivo abr = new ArregloBinarioRecursivo (20);
   	System.out.println (abr + "\nIngrese un número a buscar");
   	int resultado = abr.busquedaBinaria (new java.util.Scanner (System.in).nextInt ());
   	System.out.println ("Indice del elmento (-1 si no se encontró): " + resultado);
   } 
} // fin de la clase ArregloBinario
