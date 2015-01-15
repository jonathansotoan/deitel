// Fig. 7.9: Carta.java
// La clase Carta representa una carta de juego.

public class Carta 
{
   private String cara; // cara de la carta ("As", "Dos", ...)
   private String palo; // palo de la carta ("Corazones", "Diamantes", ...)
   private byte indiceCara;// indice del palo para comparaciones

   // el constructor de dos argumentos inicializa la cara y el palo de la carta
   public Carta( String caraCarta, String paloCarta, byte indiceCara )
   {
      cara = caraCarta; // inicializa la cara de la carta
      palo = paloCarta; // inicializa el palo de la carta
      this.indiceCara = indiceCara;// inicializa el indice del palo
   } // fin del constructor de Carta con dos argumentos

   // devuelve representación String de Carta
   public String toString() 
   { 
      return cara + " de " + palo;
   } // fin del método toString
   
   public String obtenerCara () {
   	return cara;
   }
   
   public String obtenerPalo () {
   	return palo;
   }
   
   public byte obtenerIndiceCara () {
   	return indiceCara;
   }
} // fin de la clase Carta
