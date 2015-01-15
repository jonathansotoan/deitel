// Exercise 16.8
// Fig 16.2: ArregloLineal.java
// Clase que contiene un arreglo de enteros aleatorios y un método 
// que busca en ese arreglo, en forma secuencial.
package  deitel.searchAndOrdering;

import java.util.Random;

public class ArregloLinealRecursivo
{
   private int[] datos; // arreglo de valores
   private static Random generador = new Random();

   // crea un arreglo de un tamaño dado, y lo rellena con enteros aleatorios
   public ArregloLinealRecursivo( int tamanio )
   {
      datos = new int[ tamanio ]; // crea un espacio para el arreglo

      // llena el arreglo con valores int aleatorios, en el rango de 10 a 99
      for ( int i = 0; i < tamanio; i++ )
         datos[ i ] = 10 + generador.nextInt( 90 );
   } // fin del constructor de ArregloLineal

   // realiza una búsqueda lineal en los datos
   public int busquedaLineal( int claveBusqueda )
   {
      return busquedaRecursiva (claveBusqueda, 0);
   } // fin del método busquedaLineal
   
   private int busquedaRecursiva (final int CLAVE, final int INDICE) {
   	if (INDICE == datos.length)
   		return -1;
   	else if (datos[INDICE] == CLAVE)
   		return INDICE;
   	else
   		return busquedaRecursiva (CLAVE, INDICE + 1);
   }

   // método para imprimir los valores del arreglo
   public String toString()
   {
      StringBuilder temporal = new StringBuilder();

      // itera a través del arreglo
      for ( int elemento : datos )
         temporal.append( elemento + " " );

      temporal.append( "\n" ); // agrega el carácter de nueva línea
      return temporal.toString();
   } // fin del método toString
   
   public static void main (String args[]) {
   	ArregloLinealRecursivo alr = new ArregloLinealRecursivo (20);
   	System.out.println (alr + "\nInserte una número a buscar");
   	int resultado = alr.busquedaLineal (new java.util.Scanner (System.in).nextInt ());
   	System.out.println ("Indice del elmento (-1 si no se encontró): " + resultado);
   }
} // fin de la clase ArregloLineal
