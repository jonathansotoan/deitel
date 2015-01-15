// Fig. 7.10: PaqueteDeCartas.java
// La clase PaqueteDeCartas representa un paquete de cartas de juego.
import java.util.Random;

public class PaqueteDeCartas
{
   private Carta paquete[]; // arreglo de objetos Carta
   private int cartaActual; // subíndice de la siguiente Carta a repartir
   private final int NUMERO_DE_CARTAS = 52; // número constante de Cartas
   private Random numerosAleatorios; // generador de números aleatorios
	public Carta[][] manos;
	private boolean[][] cartasUsadas; // pone en true las cartas que se usan para formar un par, terna o cuarta
	private byte[] pares;
	private boolean[] terna;
	private boolean[] cuarta;
	private boolean[] pintas;
	private boolean[] escalera;
	private boolean[] fullHouse;

   // el constructor llena el paquete de Cartas e inicializa la cantidad de manos especificadas, cada una con la cantidad de cartas especificadas
   public PaqueteDeCartas(byte cantidadDeManos, byte cantidadDeCartas)
   {
		String caras[] = { "As", "Dos", "Tres", "Cuatro", "Cinco", "Seis", 
								"Siete", "Ocho", "Nueve", "Diez", "J", "Q", "K" };
      String palos[] = { "Corazones", "Diamantes", "Treboles", "Espadas" };

      paquete = new Carta[ NUMERO_DE_CARTAS ]; // crea arreglo de objetos Carta
      cartaActual = 0; // establece cartaActual para que la primera Carta repartida sea paquete[ 0 ]
      numerosAleatorios = new Random(); // crea generador de números aleatorios

      // llena el paquete con objetos Carta
      for ( int cuenta = 0; cuenta < paquete.length; cuenta++ ) 
         paquete[ cuenta ] = 
            new Carta( caras[ cuenta % 13 ], palos[ cuenta / 13 ], (byte) (cuenta % 13) );
      
      manos = new Carta[cantidadDeManos][cantidadDeCartas];
      
      // inicializa la matriz de cartas usadas en false
      cartasUsadas = new boolean[cantidadDeManos][cantidadDeCartas];
		
		for (byte j = 0; j < cantidadDeManos; ++j)
			definirUsoDeLasCartas (j, false);
      
      pares = new byte[cantidadDeManos];
      terna = new boolean[cantidadDeManos];
      cuarta = new boolean[cantidadDeManos];
      escalera = new boolean[cantidadDeManos];
      pintas = new boolean[cantidadDeManos];
      fullHouse = new boolean[cantidadDeManos];
   } // fin del constructor de PaqueteDeCartas

   // baraja el paquete de Cartas con algoritmo de una pasada
   public void barajar()
   {
      // después de barajar, la repartición debe empezar en paquete[ 0 ] otra vez
      cartaActual = 0; // reinicializa cartaActual

      // para cada Carta, selecciona otra Carta aleatoria y las intercambia
      for ( int primera = 0; primera < paquete.length; primera++ ) 
      {
         // selecciona un número aleatorio entre 0 y 51 
         int segunda =  numerosAleatorios.nextInt( NUMERO_DE_CARTAS );
         
         // intercambia Carta actual con la Carta seleccionada al azar
         Carta temp = paquete[ primera ];        
         paquete[ primera ] = paquete[ segunda ];   
         paquete[ segunda ] = temp;            
      } // fin de for
   } // fin de método barajar

   // reparte una Carta
   public Carta repartirCarta()
   {
      // determina si quedan Cartas por repartir
      if ( cartaActual < paquete.length )
         return paquete[ cartaActual++ ]; // devuelve la Carta actual en el arreglo
      else        
         return null; // devuelve null para indicar que se repartieron todas las Cartas
   } // fin del método repartirCarta
   
   // Reparte a la mano que se especifica y cambia 3 cartas automaticamente si eleccion es igual a 'a'
   public void repartirMano (byte numeroDeMano) {
   	for (byte j = 0; j < manos[numeroDeMano].length; ++j)
   		manos[numeroDeMano][j] = repartirCarta ();
   	
   	definirEstadoMano (numeroDeMano);
   }
   
   // Devuelve el numero de pares que tiene la mano que se especifica
   private void definirNumeroDePares (byte numeroDeMano) {
		byte numeroDePares = 0;
		byte posicionAbloquear = 0;

		for (byte j = 0; j < manos[numeroDeMano].length; ++j) {
			byte coincidencias = 0;
			
			for (byte k = (byte) (j + 1); k < manos[numeroDeMano].length; ++k)
				if (manos[numeroDeMano][j].obtenerIndiceCara () == manos[numeroDeMano][k].obtenerIndiceCara ()
					&& !cartasUsadas[numeroDeMano][j]
					&& !cartasUsadas[numeroDeMano][k]) {
					posicionAbloquear = k;
					++coincidencias;
				}

			if (coincidencias == 1) {
				cartasUsadas[numeroDeMano][j] = true;
				cartasUsadas[numeroDeMano][posicionAbloquear] = true;
				++numeroDePares;
			}
			
			byte coincidenciasCartasEnEscalera = 0;
			for (byte k = (byte) (j + 1); k < manos[numeroDeMano].length; ++k)
				if (manos[numeroDeMano][j].obtenerPalo ().equals (manos[numeroDeMano][k].obtenerPalo ())
					&& sonContiguas (manos[numeroDeMano][j], manos[numeroDeMano][k])
					&& !cartasUsadas[numeroDeMano][j]
					&& !cartasUsadas[numeroDeMano][k]) {
					posicionAbloquear = k;
					++coincidenciasCartasEnEscalera;
				}
			
			if (coincidenciasCartasEnEscalera == 1) {
				cartasUsadas[numeroDeMano][j] = true;
				cartasUsadas[numeroDeMano][posicionAbloquear] = true;
				++numeroDePares;
			}
		}
		
		pares[numeroDeMano] = numeroDePares;
	}
	
   // Devuelve true si tiene una terna la mano especificada
	private void definirSiHayTerna (byte numeroDeMano) {
		boolean tieneTerna = false;

		for (byte j = 0; j < manos[numeroDeMano].length && !tieneTerna; ++j) {
			byte coincidencias = 0;
			byte[] posicionesAbloquear = new byte[2];
			
			for (byte k = (byte) (j + 1); k < manos[numeroDeMano].length; ++k)
				if (manos[numeroDeMano][j].obtenerIndiceCara () == manos[numeroDeMano][k].obtenerIndiceCara ()
					&& !cartasUsadas[numeroDeMano][j]
					&& !cartasUsadas[numeroDeMano][k]) {
					if (posicionesAbloquear[0] == 0)
						posicionesAbloquear[0] = k;
					else
						posicionesAbloquear[1] = k;

					++coincidencias;
				}

			if (coincidencias == 2) {
				cartasUsadas[numeroDeMano][j] = true;
				cartasUsadas[numeroDeMano][posicionesAbloquear[0]] = true;
				cartasUsadas[numeroDeMano][posicionesAbloquear[1]] = true;
				tieneTerna = true;
			}
		}
		
		terna[numeroDeMano] = tieneTerna;
	}
	
   // Devuelve true si tiene una cuarta la mano especificada
	public void definirSiHayCuarta (byte numeroDeMano) {
		boolean tieneCuarta = false;
		byte cartaNoUsada = 0;

		for (byte j = 0; j < manos[numeroDeMano].length && !tieneCuarta; ++j) {
			byte coincidencias = 0;
			
			for (byte k = (byte) (j + 1); k < manos[numeroDeMano].length; ++k)
				if (manos[numeroDeMano][j].obtenerIndiceCara () == manos[numeroDeMano][k].obtenerIndiceCara ()
					&& !cartasUsadas[numeroDeMano][j]
					&& !cartasUsadas[numeroDeMano][k])
					++coincidencias;
				else
					cartaNoUsada = k;

			if (coincidencias == 3) {
				tieneCuarta = true;
				definirUsoDeLasCartas (numeroDeMano, true);
				cartasUsadas[numeroDeMano][cartaNoUsada] = false;
			}
		}
		
		cuarta[numeroDeMano] = tieneCuarta;
	}
	
	
   // Devuelve true si todas las cartas de la mano especificada son de la misma pinta o palo
	public void definirSiSonTodasDelMismoPalo (byte numeroDeMano) {
		boolean sonDelMismoPalo = true;
		
		for (byte j = 1; sonDelMismoPalo && j < manos[numeroDeMano].length; ++j)
			if (!manos[numeroDeMano][j - 1].obtenerPalo ().equals (manos[numeroDeMano][j].obtenerPalo ()))
				sonDelMismoPalo = false;
		
		pintas[numeroDeMano] = sonDelMismoPalo;
	}
	
	// Ordena la mano
	public void ordenarMano (byte numeroDeMano) {
		for (byte j = 0; j < manos[numeroDeMano].length; ++j)
			for (byte k = (byte) (j + 1); k < manos[numeroDeMano].length; ++k)
				if (manos[numeroDeMano][j].obtenerIndiceCara () > manos[numeroDeMano][k].obtenerIndiceCara ()) {
					Carta temp = manos[numeroDeMano][j];
					manos[numeroDeMano][j] = manos[numeroDeMano][k];
					manos[numeroDeMano][k] = temp;
				}
	}
	
   // Devuelve true si tiene las cartas de la mano especificada son del mismo palo y son contiguas
	public void definirSiHayEscalera (byte numeroDeMano) {
		boolean tieneEscalera = pintas[numeroDeMano];

		if (tieneEscalera)
			for (byte j = 1; tieneEscalera && j < manos[numeroDeMano].length; ++j)
				if (!sonContiguas (manos[numeroDeMano][j -1], manos[numeroDeMano][j]))
					tieneEscalera = false;
			
		escalera[numeroDeMano] = tieneEscalera;
	}
	
	private boolean sonContiguas (Carta carta1, Carta carta2) {
		byte indice1 = carta1.obtenerIndiceCara (), indice2 = carta2.obtenerIndiceCara ();
	
		if (indice1 == indice2 + 1
			|| indice1 == indice2 - 1
			|| (indice1 == 12 && indice2 == 0)
			|| (indice1 == 0 && indice2 == 12))
			return true;
		else
			return false;
	}

   // Devuelve true si la mano especificada tiene una terna y un par
	public void definirSiHayFullHouse (byte numeroDeMano) {
		fullHouse[numeroDeMano] = terna[numeroDeMano] && pares[numeroDeMano] == 1 ? true : false;
	}
	
	// Imprime una mano
	public void imprimirMano (byte numeroDeMano, String jugador) {
		System.out.println ("Mano de" + jugador);
	
		for (byte j = 0; j < manos[numeroDeMano].length; ++j)
			System.out.println (manos[numeroDeMano][j]);
		
		System.out.printf ("Tiene:\n[%s]Fullhouse\n[%s]Escalera\n[%s]Pintas\n[%s]Cuarta\n[%s]Terna\ny %d pares.\n",
								fullHouse[numeroDeMano] ? "X": " ",
								escalera[numeroDeMano] ? "X": " ",
								pintas[numeroDeMano] ? "X": " ",
								cuarta[numeroDeMano] ? "X": " ",
								terna[numeroDeMano] ? "X": " ",
								pares[numeroDeMano]);
		System.out.println ();
	}
	
	// Imprime las manos y dice quien gana
	public void imprimirSituacion (String... jugadores) {
		byte[] nivelManos = new byte[manos.length];
		
		System.out.println ("Las manos están así:");
		
		for (byte j = 0; j < manos.length; ++j)// {
			imprimirMano (j, jugadores[j]);

		// Determina si alguien gana por fullhouse
		for (byte j = 0; j < nivelManos.length; ++j)
			nivelManos[j] = fullHouse[j] ? (byte) (nivelManos[j] + 1): nivelManos[j];
		
		// Determina si alguien gana por escalera
		if (sonIgualesNiveles (nivelManos))
			for (byte j = 0; j < nivelManos.length; ++j)
				nivelManos[j] = escalera[j] ? (byte) (nivelManos[j] + 1): nivelManos[j];
		
		// Determina si alguien gana por pintas
		if (sonIgualesNiveles (nivelManos))
			for (byte j = 0; j < nivelManos.length; ++j)
				nivelManos[j] = pintas[j] ? (byte) (nivelManos[j] + 1): nivelManos[j];
		
		// Determina si alguien gana por cuarta
		if (sonIgualesNiveles (nivelManos))
			for (byte j = 0; j < nivelManos.length; ++j)
				nivelManos[j] = cuarta[j] ? (byte) (nivelManos[j] + 1): nivelManos[j];
		
		// Determina si alguien gana por terna
		if (sonIgualesNiveles (nivelManos))
			for (byte j = 0; j < nivelManos.length; ++j)
				nivelManos[j] = terna[j] ? (byte) (nivelManos[j] + 1): nivelManos[j];
		
		// Determina si alguien gana por 2 pares
		if (sonIgualesNiveles (nivelManos))
			for (byte j = 0; j < nivelManos.length; ++j)
				nivelManos[j] = pares[j] == 2 ? (byte) (nivelManos[j] + 1): nivelManos[j];
		
		// Determina si alguien gana por 1 par
		if (sonIgualesNiveles (nivelManos))
			for (byte j = 0; j < nivelManos.length; ++j)
				nivelManos[j] = pares [j] == 1 ? (byte) (nivelManos[j] + 1): nivelManos[j];

		// Solo se hace la comparacion con 2 manos porque al final solo se necesitan esas
		if (nivelManos[0] == nivelManos[1])
			System.out.println ("Por eso hay un empate en el juego");
		else if (nivelManos[0] > nivelManos[1])
			System.out.println ("Por eso el ganador es el jugador con la mano 1");
		else
			System.out.println ("Por eso el ganador es el jugador con la mano 2");
	}
	
	private boolean sonIgualesNiveles (byte[] niveles) {
		boolean sonIguales = true;
		
		for (byte j = 1; sonIguales && j < niveles.length; ++j)
			if (niveles[j - 1] != niveles [j])
				sonIguales = false;
		
		return sonIguales;
	}
	
	// Bloquea o desbloquea todas las cartas usadas
	private void definirUsoDeLasCartas (byte numeroDeMano, boolean valor) {
		for (byte j = 0; j < cartasUsadas[numeroDeMano].length; ++j)
			cartasUsadas[numeroDeMano][j] = valor;
	}
	
	// Cambia hasta 3 de las cartas que sirven menos, es mas o menos un 70% falible para que no sea muy dificil ganarle
	public void cambiarPeoresCartas (byte numeroDeMano) {
  		// Si no tiene full house, escalera ni pintas las cambia
		if (!(fullHouse[numeroDeMano] || escalera[numeroDeMano] || pintas[numeroDeMano])) {
			byte cantidadDeCartasCambiadas = 0;
		
			for (byte j = 0; j < manos[numeroDeMano].length && cantidadDeCartasCambiadas < 4; ++j)
				if (!cartasUsadas[numeroDeMano][j])
					cambiarCarta (numeroDeMano, j);
		
			definirEstadoMano (numeroDeMano);
		}
	}
	
	public void cambiarCarta (byte numeroDeMano, byte indiceCarta) {
		manos[numeroDeMano][indiceCarta] = repartirCarta ();
	}
	
	// Define lo que tiene actualmente la mano
	public void definirEstadoMano (byte numeroDeMano) {
		definirUsoDeLasCartas (numeroDeMano, false);
   	ordenarMano (numeroDeMano);

   	definirSiHayCuarta (numeroDeMano);
   	definirSiHayTerna (numeroDeMano);
   	definirNumeroDePares (numeroDeMano);
   	definirSiSonTodasDelMismoPalo (numeroDeMano);
   	definirSiHayEscalera (numeroDeMano);
   	definirSiHayFullHouse (numeroDeMano);
	}
} // fin de la clase PaqueteDeCartas
