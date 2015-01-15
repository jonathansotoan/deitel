// Exercise 17.6
package deitel.dataStructure;

public class DataStructureOperations {

	public static Lista concat (Lista list1, Lista list2) {
		Lista results = new Lista ();
		
		while (!list1.estaVacia ())
			results.insertarAlFinal (list1.eliminarDelFrente ());
		
		while (!list2.estaVacia ())
			results.insertarAlFinal (list2.eliminarDelFrente ());
		
		return results;
	}
	
	public static void main (String args[]) {
		Lista l = new Lista ();
		l.insertarAlFrente (1);
		l.insertarAlFrente (2);
		l.insertarAlFrente (3);
		l.insertarAlFinal (4);
		l.insertarAlFinal (5);
		l.insertarAlFinal (6);
		
		Lista g = new Lista ();
		g.insertarAlFinal (7);
		g.insertarAlFinal (8);
		g.insertarAlFinal (9);
		g.insertarAlFrente (10);
		g.insertarAlFrente (11);
		g.insertarAlFrente (12);
		
		System.out.print ("l: ");
		l.imprimir ();
		System.out.print ("\ng: ");
		g.imprimir ();
		System.out.print ("\nl + g: ");
		concat (l, g).imprimir ();
		System.out.println ();
	}
}
