//Exercise 19.21
// Fig. 19.17: PruebaPriorityQueue.java
// Programa de prueba de la clase PriorityQueue de la biblioteca estándar.
package deitel.collections;

import java.util.PriorityQueue;
import java.util.Comparator;

public class PruebaPriorityQueue 
{
   public static void main( String args[] ) 
   {
      // cola con capacidad de 11
      PriorityQueue< Double > cola = new PriorityQueue< Double >( 5, new InverseComparator() );

      // inserta elements en la cola
      cola.offer( 3.2 );
      cola.offer( 9.8 );
      cola.offer( 5.4 );

      System.out.print( "Sondeando de cola: " );

      // muestra los elementos en la cola
      while ( cola.size() > 0 )
      {
         System.out.printf( "%.1f ", cola.peek() ); // ve el elemento superior
         cola.poll(); // elimina el elemento superior
      } // fin de while
   } // fin de main
} // fin de la clase PruebaPriorityQueue

class InverseComparator implements Comparator< Double > {
	
	public int compare( Double d1, Double d2 ) {
		return d2.intValue() - d1.intValue();
	}
}
