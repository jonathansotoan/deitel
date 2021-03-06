// Pagina 312
// La clase Craps simula el juego de dados "craps".
import java.util.Random;

public class Craps 
{
   // crea un generador de n�meros aleatorios para usarlo en el m�todo tirarDado
   private Random numerosAleatorios = new Random(); 

   // enumeraci�n con constantes que representan el estado del juego
   private enum Estado { CONTINUA, GANO, PERDIO };

   // constantes que representan tiros comunes del dado
   private final static int DOS_UNOS = 2;
   private final static int TRES = 3;
   private final static int SIETE = 7;
   private final static int ONCE = 11;
   private final static int DOCE = 12;
   
   // entero para saber en qu� tantas veces se tir� los dados antes de que se termina el juego
   private int cantidadTiros;
   
   private short[] gana = new short[40];   
   private short[] pierde = new short[40];

   // ejecuta un juego de craps
   public void jugar()
   {
      int miPunto = 0; // punto si no gana o pierde en el primer tiro
      Estado estadoJuego; // puede contener CONTINUA, GANO o PERDIO

      int sumaDeDados = tirarDados(); // primer tiro de los dados

      // determina el estado del juego y el punto con base en el primer tiro
      switch ( sumaDeDados ) 
      {
         case SIETE: // gana con 7 en el primer tiro
         case ONCE: // gana con 11 en el primer tiro 
            estadoJuego = Estado.GANO;
            break;
         case DOS_UNOS: // pierde con 2 en el primer tiro
         case TRES: // pierde con 3 en el primer tiro
         case DOCE: // pierde con 12 en el primer tiro
            estadoJuego = Estado.PERDIO;
            break;
         default: // no gan� ni perdi�, por lo que guarda el punto 
            estadoJuego = Estado.CONTINUA; // no ha terminado el juego
            miPunto = sumaDeDados; // guarda el punto
            break; // opcional al final del switch
      } // fin de switch 

      // mientras el juego no est� terminado
      while ( estadoJuego == Estado.CONTINUA ) // no GANO ni PERDIO
      { 
         sumaDeDados = tirarDados(); // tira los dados de nuevo

         // determina el estado del juego
         if ( sumaDeDados == miPunto ) // gana haciendo un punto
            estadoJuego = Estado.GANO;
         else
            if ( sumaDeDados == SIETE ) // pierde al tirar 7 antes del punto
               estadoJuego = Estado.PERDIO;
      } // fin de while 

      // muestra mensaje de que gan� o perdi�
      if ( estadoJuego == Estado.GANO )
         ++gana[cantidadTiros - 1];
      else
         ++pierde[cantidadTiros - 1];
         
      cantidadTiros = 0;
   } // fin del m�todo jugar

   // tira los dados, calcula la suma y muestra los resultados
   public int tirarDados()
   {
      // elige valores aleatorios para los dados
      int dado1 = 1 + numerosAleatorios.nextInt( 6 ); // primer tiro del dado
      int dado2 = 1 + numerosAleatorios.nextInt( 6 ); // segundo tiro del dado
      int suma = dado1 + dado2; // suma de los valores de los dados

		++cantidadTiros;

      return suma; // devuelve la suma de los dados
   } // find el m�todo tirarDados
   
   public void imprimirFrecuencia () {
   
   	System.out.println ("Tiro N Victorias Derrotas");
   
   	for (byte j = 0; j < 40; ++j) {
   		System.out.printf ("%6d %9d %8d\n", j + 1, gana[j], pierde[j]);
   	}
   }
} // fin de la clase Craps

/**************************************************************************
 * (C) Copyright 1992-2007 por Deitel & Associates, Inc. y                *
 * Pearson Education, Inc. Todos los derechos reservados.                 *
 *                                                                        *
 * RENUNCIA: Los autores y el editor de este libro han realizado su mejor *
 * esfuerzo para preparar este libro. Esto incluye el desarrollo, la      *
 * investigaci�n y prueba de las teor�as y programas para determinar su   *
 * efectividad. Los autores y el editor no hacen ninguna garant�a de      *
 * ning�n tipo, expresa o impl�cita, en relaci�n con estos programas o    *
 * con la documentaci�n contenida en estos libros. Los autores y el       *
 * editor no ser�n responsables en ning�n caso por los da�os consecuentes *
 * en conexi�n con, o que surjan de, el suministro, desempe�o o uso de    *
 * estos programas.                                                       *
 *************************************************************************/

