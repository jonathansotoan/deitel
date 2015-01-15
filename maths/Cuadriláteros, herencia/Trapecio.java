// ejercicio 9.8
public class Trapecio extends Cuadrilatero {

	//inizializa un cuadrado con los cuatro lados iguales; x y y son la posicion de la esquina superior izquierda
	public Trapecio (short x1, short y1, short x2, short y2, short x3, short y3, short x4, short y4) {
		super (x1, y1, x2, y2, x3, y3, x4, y4);
	}
	
	@Override
	public float perimetro () {
		short menor;
		short mayor;
		short menor2;
		short mayor2;
		
		if (obtenerPunto (3).obtenerX () > obtenerPunto (1).obtenerX ()) {
			mayor = obtenerPunto (3).obtenerX ();
			menor = obtenerPunto (1).obtenerX ();
		} else {
			mayor = obtenerPunto (1).obtenerX ();
			menor = obtenerPunto (3).obtenerX ();
		}
		
		if (obtenerPunto (2).obtenerX () > obtenerPunto (0).obtenerX ()) {
			mayor2 = obtenerPunto (2).obtenerX ();
			menor2 = obtenerPunto (0).obtenerX ();
		} else {
			mayor2 = obtenerPunto (0).obtenerX ();
			menor2 = obtenerPunto (2).obtenerX ();
		}
	
		return (float) ((obtenerPunto (1).obtenerX () - obtenerPunto (0).obtenerX ()) +
					(obtenerPunto (3).obtenerX () - obtenerPunto (2).obtenerX ()) +
					hipotenusa (mayor - menor, obtenerPunto (3).obtenerY () - obtenerPunto (1).obtenerY ()) +
					hipotenusa (mayor2 - menor2, obtenerPunto (2).obtenerY () - obtenerPunto (0).obtenerY ()));
	}
	
	@Override
	public float area () {
		return (float) (((obtenerPunto (1).obtenerX () - obtenerPunto (0).obtenerX ()) +
					(obtenerPunto (3).obtenerX () - obtenerPunto (2).obtenerX ())) *
					(obtenerPunto (2).obtenerY () - obtenerPunto (0).obtenerY ())) / 2;
	}
	
	protected float hipotenusa (int lado1, int lado2) {
		return (float) Math.sqrt (Math.pow (lado1, 2) + Math.pow (lado2, 2));
	}
}
