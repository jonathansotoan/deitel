// ejercicio 9.8
//Realmente deberia heredar de cuadrilatero, y rectangulo deberia heredar de paralelogramos, pero en este caso es mejor que herede de trapecio porque se pueden reutilizar mas metodos (hipotenusa y perimetro)
public class Paralelogramo extends Trapecio {

	public Paralelogramo (short x1, short y1, short x2, short y2, short x3, short y3, short x4, short y4) {
		super (x1, y1, x2, y2, x3, y3, x4, y4);
	}
	
	@Override
	public float area () {
		short mayor;
		short menor;

		if (obtenerPunto (2).obtenerX () > obtenerPunto (0).obtenerX ()) {
			mayor = obtenerPunto (2).obtenerX ();
			menor = obtenerPunto (0).obtenerX ();
		} else {
			mayor = obtenerPunto (0).obtenerX ();
			menor = obtenerPunto (2).obtenerX ();
		}

		return (obtenerPunto (1).obtenerX () - obtenerPunto (0).obtenerX ()) *
					hipotenusa (mayor - menor, obtenerPunto (2).obtenerY () - obtenerPunto (0).obtenerY ());
	}
}
