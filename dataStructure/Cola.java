// Fig. 17.13: Cola.java
// La clase Cola.
package deitel.dataStructure;

public class Cola 
{
   private Lista listaCola;

   // constructor sin argumentos
   public Cola() 
   { 
      listaCola = new Lista( "cola" ); 
   } // fin del constructor de Cola sin argumentos

   // agrega objeto a la cola
   public void enqueue( Object objeto )
   { 
      listaCola.insertarAlFinal( objeto ); 
   } // fin del método enqueue

   // elimina objeto de la cola
   public Object dequeue() throws ExcepcionListaVacia
   { 
      return listaCola.eliminarDelFrente(); 
   } // fin del método dequeue

   // determina si la cola está vacía
   public boolean estaVacia()
   {
      return listaCola.estaVacia();
   } // fin del método estaVacia

   // imprime el contenido de la cola
   public void imprimir()
   {
      listaCola.imprimir();
   } // fin del método imprimir
} // fin de la clase Cola
