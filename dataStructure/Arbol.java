// Exercise 17.19 - 17.25
// Fig. 17.17: Arbol.java
// Definición de las clases NodoArbol y Arbol.
package deitel.dataStructure;

// definición de la clase NodoArbol
class NodoArbol 
{
   // miembros de acceso del paquete
   NodoArbol nodoIzq; // nodo izquierdo 
   int datos; // valor del nodo
   NodoArbol nodoDer; // nodo derecho

   // el constructor inicializa los datos y hace de este nodo un nodo raíz
   public NodoArbol( int datosNodo )
   { 
      datos = datosNodo;              
      nodoIzq = nodoDer = null; // el nodo no tiene hijos
   } // fin del constructor de NodoArbol

   // localiza el punto de inserción e inserta un nuevo nodo; ignora los valores duplicados
   public void insertar( int valorInsertar )
   {
      // inserta en el subárbol izquierdo
      if ( valorInsertar < datos ) 
      {
         // inserta nuevo NodoArbol
         if ( nodoIzq == null )
            nodoIzq = new NodoArbol( valorInsertar );
         else // continúa recorriendo el subárbol izquierdo
            nodoIzq.insertar( valorInsertar ); 
      } // fin de if
      else if ( valorInsertar > datos ) // inserta en el subárbol derecho
      {
         // inserta nuevo NodoArbol
         if ( nodoDer == null )
            nodoDer = new NodoArbol( valorInsertar );
         else // continúa recorriendo el subárbol derecho
            nodoDer.insertar( valorInsertar ); 
      } // fin de else if
   } // fin del método insertar
} // fin de la clase NodoArbol

// definición de la clase Arbol
public class Arbol 
{
   private NodoArbol raiz;

   // el constructor inicializa un Arbol vacío de enteros
   public Arbol() 
   { 
      raiz = null; 
   } // fin del constructor de Arbol sin argumentos

   // inserta un nuevo nodo en el árbol de búsqueda binaria
   public void insertarNodo( int valorInsertar )
   {
      if ( raiz == null )
         raiz = new NodoArbol( valorInsertar ); // crea el nodo raíz aquí
      else
         raiz.insertar( valorInsertar ); // llama al método insertar
   } // fin del método insertarNodo

   // comienza el recorrido preorden
   public void recorridoPreorden()
   { 
      ayudantePreorden( raiz ); 
   } // fin del método recorridoPreorden

   // método recursivo para realizar el recorrido preorden
   private void ayudantePreorden( NodoArbol nodo )
   {
      if ( nodo == null )
         return;

      System.out.printf( "%d ", nodo.datos ); // imprime los datos del nodo
      ayudantePreorden( nodo.nodoIzq );       // recorre el subárbol izquierdo
      ayudantePreorden( nodo.nodoDer );      // recorre el subárbol derecho
   } // fin del método ayudantePreorden

   // comienza recorrido inorden
   public void recorridoInorden()
   { 
      ayudanteInorden( raiz ); 
   } // fin del método recorridoInorden

   // método recursivo para realizar el recorrido inorden
   private void ayudanteInorden( NodoArbol nodo )
   {
      if ( nodo == null )
         return;

      ayudanteInorden( nodo.nodoIzq );        // recorre el subárbol izquierdo
      System.out.printf( "%d ", nodo.datos ); // imprime los datos del nodo
      ayudanteInorden( nodo.nodoDer );       // recorre el subárbol derecho
   } // fin del método ayudanteInorden

   // comienza recorrido postorden
   public void recorridoPostorden()
   { 
      ayudantePostorden( raiz ); 
   } // fin del método recorridoPostorden

   // método recursivo para realizar el recorrido postorden
   private void ayudantePostorden( NodoArbol nodo )
   {
      if ( nodo == null )
         return;
  
      ayudantePostorden( nodo.nodoIzq );      // recorre el subárbol izquierdo
      ayudantePostorden( nodo.nodoDer );     // recorre el subárbol derecho
      System.out.printf( "%d ", nodo.datos ); // imprime los datos del nodo
   } // fin del método ayudantePostorden

	public int getDepth() {
		return getDepth( raiz, 0 );
	}
	
	private int getDepth( final NodoArbol node, final int currentDepth ) {
      if ( node == null )
         return currentDepth;

      final int leftDepth = getDepth( node.nodoIzq, currentDepth + 1 );        // recorre el subárbol izquierdo
      final int rightDepth = getDepth( node.nodoDer, currentDepth + 1 );       // recorre el subárbol derecho
      
      return Math.max( leftDepth, rightDepth );
	}
	
	public void delete( final int VALUE_TO_DELETE ) {
		delete( VALUE_TO_DELETE, raiz );
	}
	
	private void delete( final int VALUE_TO_DELETE, NodoArbol node ) {
		if( node == null )
			return;
		else if( node.nodoIzq != null && node.nodoIzq.datos == VALUE_TO_DELETE ) {
			Pila stack = saveChildren( node.nodoIzq, new Pila() );
			node.nodoIzq = null;
			insert( stack );
		} else if( node.nodoDer != null && node.nodoDer.datos == VALUE_TO_DELETE ) {
			Pila stack = saveChildren( node.nodoDer, new Pila() );
			node.nodoDer = null;
			insert( stack );
		} else {
			delete( VALUE_TO_DELETE, node.nodoIzq );
			delete( VALUE_TO_DELETE, node.nodoDer );
		}
	}
	
	private Pila saveChildren( NodoArbol node, Pila stack) {
		if( node == null )
			return stack;
		else {
			if( node.nodoIzq != null )
				stack.push( node.nodoIzq.datos );
			
			if( node.nodoDer != null )
				stack.push( node.nodoDer.datos );
		}
		
		return saveChildren( node.nodoIzq, stack );
	}
	
	public void insert( Lista list ) {
		while( !list.estaVacia() )
			insertarNodo( (Integer) list.eliminarDelFinal() );
	}
	
	public NodoArbol search( final int VALUE ) {
		return search( raiz, VALUE );
	}
	
	private NodoArbol search( final NodoArbol NODE, final int VALUE ) {
		if( NODE.datos == VALUE )
			return NODE;
		else if( VALUE > NODE.datos )
			if( NODE.nodoDer == null )
				return null;
			else
				return search( NODE.nodoDer, VALUE );
		else
			if( NODE.nodoIzq == null )
				return null;
			else
				return search( NODE.nodoIzq, VALUE );
	}
	
	public void printByLevels() {
		Cola queue = new Cola();
		
		queue.enqueue( raiz );
		
		while( !queue.estaVacia() ) {
			NodoArbol node = (NodoArbol) queue.dequeue();
			System.out.print( node.datos + " " );
			
			if( node.nodoIzq != null )
				queue.enqueue( node.nodoIzq );
			
			if( node.nodoDer != null )
				queue.enqueue( node.nodoDer );
		}
		
		System.out.println();
	}
	
	public void showTree() {
		showTree( 0, raiz );
	}
	
	private void showTree( int spaceWidth, NodoArbol node ) {
		while( node != null ) {
			showTree( spaceWidth + 5, node.nodoDer );
			
			for( byte j = 0; j < spaceWidth; ++j)
				System.out.print( " " );
			
			System.out.println( node.datos );
			node = node.nodoIzq;
			spaceWidth += 5;
		}
	}
	
	public static void main( String args[] ) {
		Arbol a = new Arbol();
		a.insertarNodo( 47 );
		a.insertarNodo( 25 );
		a.insertarNodo( 77 );
		a.insertarNodo( 11 );
		a.insertarNodo( 43 );
		a.insertarNodo( 65 );
		a.insertarNodo( 93 );
		a.insertarNodo( 7 );
		a.insertarNodo( 17 );
		a.insertarNodo( 31 );
		a.insertarNodo( 44 );
		a.insertarNodo( 68 );
		
		a.printByLevels();
		
		System.out.println( "Depth: " + a.getDepth() );
		a.recorridoInorden();
		System.out.println();
		
		a.delete( 5 );
		System.out.println( "Después de eliminar el 5" );
		a.recorridoInorden();
		System.out.println();
		
		a.delete( 68 );
		System.out.println( "Después de eliminar el 68" );
		a.recorridoInorden();
		System.out.println();
		
		a.delete( 43 );
		System.out.println( "Después de eliminar el 43" );
		a.recorridoInorden();
		System.out.println();
		
		a.delete( 25 );
		System.out.println( "Después de eliminar el 25" );
		a.recorridoInorden();
		System.out.println();
		a.recorridoPreorden();
		System.out.println();
		
		NodoArbol na = a.search( 25 );
		System.out.println( "\n25 exists? " + ( na != null ) );
		
		na = a.search( 65 );
		System.out.println( "\n65 exists? " + ( na != null ) );
		
		a.printByLevels();
		
		System.out.println( "\n---Tree 2---" );
		Arbol a2 = new Arbol();
		a2.insertarNodo( 49 );
		a2.insertarNodo( 28 );
		a2.insertarNodo( 83 );
		a2.insertarNodo( 97 );
		a2.insertarNodo( 71 );
		a2.insertarNodo( 40 );
		a2.insertarNodo( 18 );
		a2.insertarNodo( 44 );
		a2.insertarNodo( 32 );
		a2.insertarNodo( 19 );
		a2.insertarNodo( 99 );
		a2.insertarNodo( 69 );
		a2.insertarNodo( 11 );
		a2.insertarNodo( 92 );
		a2.insertarNodo( 72 );
		
		a2.showTree();
	}
} // fin de la clase Arbol
