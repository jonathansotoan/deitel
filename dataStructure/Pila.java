// Fig. 17.10: HerenciaPila.java
// Se deriva de la clase Lista.
package deitel.dataStructure;

public class Pila extends Lista 
{
   // constructor sin argumentos
   public Pila() 
   { 
      super( "pila" ); 
   } // fin del constructor de HerenciaPila sin argumentos

   // agrega objeto a la pila
   public void push( Object objeto )
   { 
      insertarAlFrente( objeto ); 
   } // fin del m�todo push
   
   public Object nextValue() throws ExcepcionListaVacia {
   	return obtenerValorDelFrente ();
   }

   // elimina objeto de la pila
   public Object pop() throws ExcepcionListaVacia
   { 
      return eliminarDelFrente(); 
   } // fin del m�todo pop
} // fin de la clase HerenciaPila
