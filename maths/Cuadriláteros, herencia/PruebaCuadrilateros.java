// ejercicio 9.8
public class PruebaCuadrilateros {
	
	public static void main (String args[]) {
		Rectangulo r = new Rectangulo ((short) 2, (short) 2, (short) 10, (short) 6);
		System.out.printf ("Rectangulo\n%s\n\n", r);
		
		Cuadrado c = new Cuadrado ((short) 5, (short) 3, (short) 3);
		System.out.printf ("Cuadrado\n%s\n\n", c);
		
		Trapecio t = new Trapecio ((short) 5, (short) 1, (short) 8, (short) 1, (short) 4, (short) 5, (short) 10, (short) 5);
		System.out.printf ("Trapecio\n%s\n\n", t);
		
		Paralelogramo p = new Paralelogramo ((short) 3, (short) 1, (short) 9, (short) 1, (short) 1, (short) 4, (short) 7, (short) 4);
		System.out.printf ("Paralelogramo\n%s\n\n", p);
	}
}
