// ejercicio 9.8
public class Cuadrilatero {
	private Punto[] puntos = new Punto[4];// Orden: superiorIzquierdo, superiorDerecho, inferiorIzquierdo y inferiorDerecho
	
	public Cuadrilatero (short x1, short y1, short x2, short y2, short x3, short y3, short x4, short y4) {
		puntos[0] = new Punto (x1, y1);
		puntos[1] = new Punto (x2, y2);
		puntos[2] = new Punto (x3, y3);
		puntos[3] = new Punto (x4, y4);
	}
	
	public float perimetro () {
		return (float) (2 * (puntos[1].obtenerX () - puntos[0].obtenerX ()) +
					2 * (puntos[2].obtenerY () - puntos[0].obtenerY ()));
	}
	
	public float area () {
		return  (float) ((puntos[1].obtenerX () - puntos[0].obtenerX ()) *
					(puntos[2].obtenerY () - puntos[0].obtenerY ()));
	}
	
	public void establecerPunto (byte indice, short x, short y) {
		puntos[indice].establecerX (x);
		puntos[indice].establecerY (y);
	}
	
	public Punto obtenerPunto (int indice) {
		return puntos[indice];
	}
	
	public String toString () {
		return String.format ("Puntos:\n%s (%d, %d)\n%s (%d, %d)\n%s (%d, %d)\n%s (%d, %d)\n%s: %.2f\n%s: %.2f",
									"Superior izquierdo", puntos[0].obtenerX (), puntos[0].obtenerY (),
									"Superior derecho", puntos[1].obtenerX (), puntos[1].obtenerY (),
									"Inferior izquierdo", puntos[2].obtenerX (), puntos[2].obtenerY (),
									"Inferior derecho", puntos[3].obtenerX (), puntos[3].obtenerY (),
									"Perímetro", perimetro (),
									"Área", area ());
	}
}

class Punto {
	private short x;
	private short y;
	
	public Punto (short x, short y) {
		establecerX (x);
		establecerY (y);
	}
	
	public void establecerX (short nuevaX) {
		x = nuevaX < 0 || nuevaX > 32767 ? 0 : nuevaX;
	}
	
	public short obtenerX () {
		return x;
	}
	
	public void establecerY (short nuevaY) {
		y = nuevaY < 0 || nuevaY > 32767 ? 0 : nuevaY;
	}
	
	public short obtenerY () {
		return y;
	}
}
