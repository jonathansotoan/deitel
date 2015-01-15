// Fig. 14.5: RegistroCuenta.java
// Una clase que representa un registro de informaci�n
package deitel.flow.ser; // se empaqueta para reutilizarla

import java.io.Serializable;

public class RegistroCuenta implements Serializable
{
   private int cuenta;
   private String primerNombre;
   private String apellidoPaterno;
   private double saldo;
   
   // el constructor sin argumentos llama a otro constructor con valores predeterminados
   public RegistroCuenta() 
   {
      this( 0, "", "", 0.0 ); // llama al constructor con cuatro argumentos
   } // fin del constructor de RegistroCuenta sin argumentos
  
   // inicializa un registro
   public RegistroCuenta( int cta, String nombre, String apellido, double sal )
   {
      establecerCuenta( cta );
      establecerPrimerNombre( nombre );
      establecerApellidoPaterno( apellido );
      establecerSaldo( sal );
   } // fin del constructor de RegistroCuenta con cuatro argumentos

   // establece el n�mero de cuenta 
   public void establecerCuenta( int cta )
   {
      cuenta = cta;
   } // fin del m�todo establecerCuenta

   // obtiene el n�mero de cuenta 
   public int obtenerCuenta() 
   { 
      return cuenta; 
   } // fin del m�todo obtenerCuenta
   
   // establece el primer nombre 
   public void establecerPrimerNombre( String nombre )
   {
      primerNombre = nombre;
   } // fin del m�todo establecerPrimerNombre

   // obtiene el primer nombre 
   public String obtenerPrimerNombre() 
   { 
      return primerNombre; 
   } // fin del m�todo obtenerPrimerNombre
   
   // establece el apellido paterno  
   public void establecerApellidoPaterno( String apellido )
   {
      apellidoPaterno = apellido;
   } // fin del m�todo establecerApellidoPaterno

   // obtiene el apellido paterno
   public String obtenerApellidoPaterno() 
   {
      return apellidoPaterno; 
   } // fin del m�todo obtenerApellidoPaterno
   
   // establece el saldo  
   public void establecerSaldo( double sal )
   {
      saldo = sal;
   } // fin del m�todo establecerSaldo

   // obtiene el saldo   
   public double obtenerSaldo() 
   { 
      return saldo; 
   } // fin del m�todo obtenerSaldo
   
   public void combinar (TransactionRegistry tr) {
   	establecerSaldo (obtenerSaldo () + tr.getAmount ());
   }
   
   @Override
   public String toString () {
   	return String.format ("Registro Cuenta con %s: %d, %s: %s, %s: %s y %s: %.2f",
   									"# de cuenta", obtenerCuenta (),
   									"nombre", obtenerPrimerNombre (),
   									"apellido paterno", obtenerApellidoPaterno (),
   									"saldo", obtenerSaldo ());
   }
} // fin de la clase RegistroCuenta
