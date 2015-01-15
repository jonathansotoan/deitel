// Ejercicio 8.19, pendiente CuatroEnRayaTridimensional
import java.util.Random;

public class TresEnRayaBidimensional {
	private char campo[][];
	private byte jerarquiaMovs[][][];
	private static final char JUGADOR1 = '0', JUGADOR2 = 'X';
	private boolean juegoTerminado;

	public TresEnRayaBidimensional () {
		campo = new char[3][3];
		jerarquiaMovs = new byte[2][3][3];
		reiniciarCampo ();
	}
	
	public void reiniciarCampo () {
		juegoTerminado = false;
	
		for (byte j = 0; j < campo.length; ++j)
			for (byte k = 0; k < campo[j].length; ++k) {
				campo[j][k] = 'e';
				jerarquiaMovs[0][j][k] = 0;
				jerarquiaMovs[1][j][k] = 0;
			}
	}
	
	public void hacerMovimiento (char idJugador, byte fila, byte columna) {
		if (fila >= 0 && fila < campo.length && columna >= 0 && columna < campo[fila].length) {
			byte ixJugador = idJugador == JUGADOR1 ? 0 : (byte) 1;
			campo[fila][columna] = idJugador;
			jerarquiaMovs[0][fila][columna] = -1;
			jerarquiaMovs[1][fila][columna] = -1;
			actualizarJerarquia (idJugador, fila, columna);
		}
	}
	
	public void hacerMovimiento (char idJugador) {
		byte fila = -1;
		byte columna = -1;
	
		if (estaVacio ()) {
			Random numsAlAzar = new Random ();
			fila = (byte) (numsAlAzar.nextInt (campo.length));
			columna = (byte) (numsAlAzar.nextInt (campo[0].length));
		} else {
			byte mayorPrioridad = 0;

			for (byte j = (byte) (jerarquiaMovs.length - 1); j >= 0; --j) {
				for (byte k = 0; k < jerarquiaMovs[j].length; ++k) {
					for (byte l = 0; l < jerarquiaMovs[j][k].length; ++l) {
						if (mayorPrioridad < jerarquiaMovs[j][k][l]) {
							mayorPrioridad = jerarquiaMovs[j][k][l];
							fila = k;
							columna = l;
							//System.out.println ("fila " + k + " columna " + l);
						}
					}
				}
			}
		}
		
		hacerMovimiento (idJugador, fila, columna);
	}
	
	public boolean estaVacio () {
		boolean estaVacio = true;
		
		for (byte j = 0; estaVacio && j < campo.length; ++j)
			for (byte k = 0; estaVacio && k < campo.length; ++k)
				if (campo[j][k] != 'e')
					estaVacio = false;
		
		return estaVacio;
	}
	
	public boolean estaLleno () {
		boolean estaLleno = true;
		
		for (byte j = 0; estaLleno && j < campo.length; ++j)
			for (byte k = 0; estaLleno && k < campo.length; ++k)
				if (campo[j][k] == 'e')
					estaLleno = false;
		
		return estaLleno;
	}
	
	// direccion puede ser 'v' para vertical, 'h' para horizontal o 'd' para diagonal
	private byte numeroDeSeguidas (char idJugador, char direccion) {
		byte numeroDeSeguidas = 0;
		byte posicion;// en caso de 'h' es la fila, 'v' la columna y en caso de 'd' 0 para la diagonal de la esquina
							//superior izquierda a la esquina inferior derecha y 1 para la diagonal de la esquina
							//superior derecha a la esquina inferior izquierda.
	
		switch (direccion) {
			case 'h':
				for (byte j = 0; numeroDeSeguidas < 3 && j < campo.length; ++j) {
					numeroDeSeguidas = 0;
					
					for (byte k = 0; numeroDeSeguidas < 3 && k < campo[j].length; ++k)
						numeroDeSeguidas = campo[j][k] == idJugador ? (byte) (numeroDeSeguidas + 1) : numeroDeSeguidas;
				}
				break;
				
			case 'v':
				for (byte j = 0; numeroDeSeguidas < 3 && j < campo[0].length; ++j) {
					numeroDeSeguidas = 0;
					
					for (byte k = 0; numeroDeSeguidas < 3 && k < campo.length; ++k)
						numeroDeSeguidas = campo[k][j] == idJugador ? (byte) (numeroDeSeguidas + 1) : numeroDeSeguidas;
				}
				break;
				
			case 'd':
				for (byte j = 0; j < campo.length; ++j)
					numeroDeSeguidas = campo[j][j] == idJugador ? (byte) (numeroDeSeguidas + 1) : numeroDeSeguidas;

				byte numeroDeSeguidasAux = 0;
				for (byte j = 0, k = (byte) (campo.length - 1); j < campo.length && k >= 0; ++j, --k)
					numeroDeSeguidasAux = campo[j][k] == idJugador ? (byte) (numeroDeSeguidasAux + 1) : numeroDeSeguidasAux;
				
				numeroDeSeguidas = numeroDeSeguidas > numeroDeSeguidasAux ? numeroDeSeguidas: numeroDeSeguidasAux;
		}
		
		return numeroDeSeguidas;
	}
	
	private char obtenerGanador () {
		if (numeroDeSeguidas (JUGADOR1, 'v') == 3)
			return JUGADOR1;
		else if (numeroDeSeguidas (JUGADOR1, 'h') == 3)
			return JUGADOR1;
		else if (numeroDeSeguidas (JUGADOR1, 'd') == 3)
			return JUGADOR1;
		else if (numeroDeSeguidas (JUGADOR2, 'v') == 3)
			return JUGADOR2;
		else if (numeroDeSeguidas (JUGADOR2, 'h') == 3)
			return JUGADOR2;
		else if (numeroDeSeguidas (JUGADOR2, 'd') == 3)
			return JUGADOR2;
		else
			return 'n';
	}
	
	private void imprimirSituacion () {
		System.out.println ("_______");
		
		for (byte j = 0; j < campo.length; ++j) {
			System.out.print ("|");
		
			for (byte k = 0; k < campo[j].length; ++k)
				System.out.print ((campo[j][k] == 'e' ? " " : campo[j][k]) + "|");
				
			System.out.print ("\n_______\n");
		}
		
		if (obtenerGanador () != 'n' || estaLleno ()) {
			System.out.println ("El juego ha terminado, " + (estaLleno () ? "hay un empate." : "el ganador es el jugador con la figura " + obtenerGanador ()));
			juegoTerminado = true;
		}
	}
	
	public boolean estaElJuegoTerminado () {
		return juegoTerminado;
	}
	
	private void actualizarJerarquia (char idJugador, byte fila, byte columna) {
		byte numFichas = 0;
		byte ixJugador = idJugador == JUGADOR1 ? 0 : (byte) 1;
		
		if (fila == columna) {
			for (byte j = 0; j < campo.length; ++j)
				if (campo[j][j] == idJugador)
					++numFichas;
			
			for (byte j = 0; j < campo.length; ++j)
				if (campo[j][j] == 'e')
					jerarquiaMovs[ixJugador][j][j] = numFichas;
			
			numFichas = 0;
		}
		
		if (fila == campo.length - 1 - columna) {
			for (byte j = 0; j < campo.length; ++j)
				if (campo[j][campo.length - 1 - j] == idJugador)
					++numFichas;
			
			for (byte j = 0; j < campo.length; ++j)
				if (campo[j][campo.length - 1 - j] == 'e')
					jerarquiaMovs[ixJugador][j][campo.length - 1 - j] = numFichas;
			
			numFichas = 0;
		}

		byte numFichas2 = 0;
				
		for (byte j = 0; j < campo.length; ++j) {
			if (campo[fila][j] == idJugador)
				++numFichas;
			
			if (campo[j][columna] == idJugador)
				++numFichas2;
		}
		
		for (byte j = 0; j < campo.length; ++j) {
			if (campo[fila][j] == 'e')
				jerarquiaMovs[ixJugador][fila][j] = numFichas;
			
			if (campo[j][columna] == 'e')
				jerarquiaMovs[ixJugador][j][columna] = numFichas2;
		}
	}
	
	public void jugar () {
		java.util.Scanner entrada = new java.util.Scanner (System.in);
		
		do {
			System.out.println ("Ingrese el número de la fila y después el de la columna en la que quiere hacer el siguiente moviento");
			hacerMovimiento (JUGADOR1, (byte) entrada.nextInt (), (byte) entrada.nextInt ());
			hacerMovimiento (JUGADOR2);
			imprimirSituacion ();
		} while (!estaElJuegoTerminado ());
	}
	
	public static void main (String args[]) {
		TresEnRayaBidimensional terb = new TresEnRayaBidimensional ();
		terb.jugar ();
	}
}
