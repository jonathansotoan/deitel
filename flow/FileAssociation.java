package deitel.flow;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Formatter;
import java.util.NoSuchElementException;
import java.util.FormatterClosedException;
import java.util.Vector;

public class FileAssociation {
	private Formatter master;
	private Formatter transactions;

	public void openFiles () {
		try {
			master = new Formatter ("masterFile.txt");
			transactions = new Formatter ("transactions.txt");
		} catch (SecurityException securityEx) {
			System.err.println ("You can not write the file because you do not have permission");
			System.exit (1);
		} catch (FileNotFoundException fileNotFoundEx) {
			System.err.println ("The file does not exist");
			System.exit (1);
		}
	}
	
	public void closeFiles () {
		if (master != null)
			master.close ();
		
		if (transactions != null)
			transactions.close ();
	}
	
	public void addTransactions () {
		Scanner in = new Scanner (System.in);
		TransactionRegistry tr = new TransactionRegistry ();
		
		System.out.printf ("%s\n%s\n\n%s",
								"To finish the input, write the end file indicator when you must write data input.",
								"In Unix/Linux/Mac OS X write <ctrl> d, in Windows write <ctrl> z; then press <enter>",
								"Write the account number (> 0) and the amount of the transaction\n? ");
		
		while (in.hasNext ()) {
			try {
				tr.setAccountNumber (in.nextInt ());
				tr.setAmount (in.nextDouble ());
				
				if (tr.getAccountNumber () > 0)
					transactions.format ("%d %.2f\n", tr.getAccountNumber (), tr.getAmount ());
				else
					System.out.println ("The account number must be greater than 0");
			} catch (FormatterClosedException formatterClosedEx) {
				System.err.println ("Error: We can not write in the file");
			} catch (NoSuchElementException noSuchElementEx) {
				System.err.println ("Input wrong, try again");
				in.nextLine ();
			}
			
			System.out.print ("Write the account number (> 0) and the amount of the transaction\n? ");
		}
	}
	
	public void addRegistries()
   {
      // objeto que se va a escribir en el archivo
      RegistroCuenta registro = new RegistroCuenta();
      Scanner in = new Scanner( System.in );

      System.out.printf ("%s\n%s\n\n%s",
								"To finish the input, write the end file indicator when you must write data input.",
								"In Unix/Linux/Mac OS X write <ctrl> d, in Windows write <ctrl> z; then press <enter>",
								"Write the account number (> 0), first name, last name and the account balance\n? ");

      while ( in.hasNext() ) // itera hasta encontrar el indicador de fin de archivo
      {
         try // envía valores al archivo
         {
            // obtiene los datos que se van a enviar
            registro.establecerCuenta( in.nextInt() ); // lee el número de cuenta
            registro.establecerPrimerNombre( in.next() ); // lee el primer nombre
            registro.establecerApellidoPaterno( in.next() ); // lee el apellido paterno
            registro.establecerSaldo( in.nextDouble() ); // lee el saldo

            if ( registro.obtenerCuenta() > 0 )
            {
               // escribe el nuevo registro
               master.format( "%d %s %s %.2f\n", registro.obtenerCuenta(), 
                  registro.obtenerPrimerNombre(), registro.obtenerApellidoPaterno(),
                  registro.obtenerSaldo() );
            } // fin de if
            else
            {
               System.out.println(
                  "The account number must be greater than 0." );
            } // fin de else
         } // fin de try
         catch ( FormatterClosedException formatterClosedException )
         {
            System.err.println( "Error: We can not write in the file." );
            return;
         } // fin de catch
         catch ( NoSuchElementException elementException )
         {
            System.err.println( "Input wrong, try again" );
            in.nextLine(); // descarta la entrada para que el usuario intente de nuevo
         } // fin de catch

         System.out.print ("Write the account number (> 0), first name, last name and the account balance\n? ");
      } // fin de while
   }
   
   public void associateFiles () {
   	boolean mustClose = false;
		Vector transactions = new Vector (5, 5);
		Scanner transactionsIn = null;
		Scanner masterIn = null;
		Formatter newMaster = null;
		Formatter errors = null;
   	
   	try {
			masterIn = new Scanner (new File ("masterFile.txt"));
			transactionsIn = new Scanner (new File ("transactions.txt"));
			newMaster = new Formatter ("newMasterFile.txt");
			errors = new Formatter ("errors.txt");

			while (transactionsIn.hasNext ()) {
				TransactionRegistry temp = new TransactionRegistry (transactionsIn.nextInt (),
																						transactionsIn.nextDouble ());
				transactions.add (temp);
			}
		} catch (FileNotFoundException fileNotFoundEx) {
			System.err.println ("The file does not exist");
			mustClose = true;
		} catch (NoSuchElementException noSuchElementEx) {
			System.err.println ("The file is malformed");
			mustClose = true;
		} catch (IllegalStateException illegalStateEx) {
			System.err.println ("Error: We can not read the file");
			mustClose = true;
		} finally {
			if (transactionsIn != null)
				transactionsIn.close ();
		
			if (mustClose)
				System.exit (1);
		}
   	
   	try {
			while (masterIn.hasNext ()) {
				RegistroCuenta temp = new RegistroCuenta (masterIn.nextInt (),
																		masterIn.next (),
																		masterIn.next (),
																		masterIn.nextDouble ());
				
				for (short j = 0; j < transactions.size (); ++j) {
					TransactionRegistry tr = (TransactionRegistry) transactions.elementAt (j);
				
					if (temp.obtenerCuenta () == tr.getAccountNumber ()) {
						temp.establecerSaldo (temp.obtenerSaldo () + tr.getAmount ());
						transactions.removeElementAt (j--);
					}
				}
				
				newMaster.format( "%d %s %s %.2f\n", temp.obtenerCuenta(), 
                  temp.obtenerPrimerNombre(), temp.obtenerApellidoPaterno(),
                  temp.obtenerSaldo() );
			}

			for (short j = 0; j < transactions.size (); ++j)
				errors.format ("There is a transaction registry with an account number such does not exist: %s\n",
									transactions.elementAt (j));
		} catch (NoSuchElementException noSuchElementEx) {
			System.err.println ("The file is malformed");
			mustClose = true;
		} catch (IllegalStateException illegalStateEx) {
			System.err.println ("Error: We can not read the file");
			mustClose = true;
		} finally {
			transactionsIn.close ();
			newMaster.close ();
			errors.close ();
		
			if (mustClose)
				System.exit (1);
		}
   }

	public static void main (String args[]) {
		FileAssociation fa = new FileAssociation ();
		
		System.out.println ("Insert 0 to make the master and transaction files or 1 to associate the files");
		
		if (new Scanner (System.in).nextInt () == 0) {
			fa.openFiles ();
			fa.addRegistries ();
			fa.addTransactions ();
			fa.closeFiles ();
		} else {
			fa.associateFiles ();
		}
	}
}
