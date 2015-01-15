// Fig. 7.11: PruebaPaqueteDeCartas.java Edit: ejercicio de 7.30 a 7.33
// Aplicación para barajar y repartir cartas.
import java.util.Scanner;

public class PruebaPaqueteDeCartas
{
   // ejecuta la aplicación
   public static void main( String args[] )
   {
   	final byte MANO_COMPUTADORA = 0;
   	final byte MANO_USUARIO = 1;
   	final String COMPUTADORA = " la computadora";
   	final String USUARIO = "l jugador";
   
      PaqueteDeCartas miPaqueteDeCartas = new PaqueteDeCartas((byte) 2, (byte) 5);
      miPaqueteDeCartas.barajar(); // coloca las Cartas en orden aleatorio

		miPaqueteDeCartas.repartirMano (MANO_COMPUTADORA);
		miPaqueteDeCartas.repartirMano (MANO_USUARIO);
		
		System.out.println ("Las manos están actualmente así");
		miPaqueteDeCartas.imprimirMano (MANO_COMPUTADORA, COMPUTADORA);
		miPaqueteDeCartas.imprimirMano (MANO_USUARIO, USUARIO);
		
		Scanner entrada = new Scanner (System.in);
		System.out.println ("Ingrese de a uno (separados con la tecla <enter>) los índices de las cartas que desea cambiar (de 1 a 5) o 0 para terminar de ingresarlos. Son máximo 3.");
		
		byte en = (byte) entrada.nextInt ();
		for (byte j = 0; en != 0 && j < 2; ++j) {
			miPaqueteDeCartas.cambiarCarta (MANO_USUARIO, --en);
			en = (byte) entrada.nextInt ();
		}
		
		miPaqueteDeCartas.definirEstadoMano (MANO_USUARIO);
		miPaqueteDeCartas.cambiarPeoresCartas (MANO_COMPUTADORA);
		
		miPaqueteDeCartas.imprimirSituacion (COMPUTADORA, USUARIO);
   } // fin de main
} // fin de la clase PruebaPaqueteDeCartas
