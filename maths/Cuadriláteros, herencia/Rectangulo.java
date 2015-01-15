// ejercicio 9.8
public class Rectangulo extends Cuadrilatero {

	//inicializa un rectangulo que tiene la esquina superior izquierda en el punto (x1, y1) y la esqina inferior derecha en el punto (x2, y2)
	public Rectangulo (short x1, short y1, short x2, short y2) {
		super (x1, y1, x2, y1, x1, y2, x2, y2);
	}
}
