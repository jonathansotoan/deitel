// ejercicio 9.8
public class Cuadrado extends Rectangulo {

	//inizializa un cuadrado con los cuatro lados iguales; x y y son la posicion de la esquina superior izquierda
	public Cuadrado (short lado, short x, short y) {
		super (x, y, (short) (x + lado), (short) (y + lado));
	}
}
