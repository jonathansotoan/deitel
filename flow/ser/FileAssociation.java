package deitel.flow.ser;

import java.io.IOException;
import java.util.Scanner;
import java.util.Formatter;
import java.util.NoSuchElementException;
import java.util.Vector;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.EOFException;

public class FileAssociation {
	private ObjectOutputStream master;
	private ObjectOutputStream transactions;

	public void openFiles () {
		try {
			master = new ObjectOutputStream (new FileOutputStream ("masterFile.ser"));
			transactions = new ObjectOutputStream (new FileOutputStream ("transactions.ser"));
		} catch (IOException ioEx) {
			System.err.println ("You can not write the file because you do not have permission");
			System.exit (1);
		}
	}
	
	public void closeFiles () {
		try {
			if (master != null)
				master.close ();
		
			if (transactions != null)
				transactions.close ();
		} catch (IOException ioEx) {
			System.out.println ("Error: We cannot create the file.");
			System.exit (1);
		}
	}
	
	public void addTransactions () {
		Scanner in = new Scanner (System.in);
		TransactionRegistry tr;
		
		System.out.printf ("%s\n%s\n\n%s",
								"To finish the input, write the end file indicator when you must write data input.",
								"In Unix/Linux/Mac OS X write <ctrl> d, in Windows write <ctrl> z; then press <enter>",
								"Write the account number (> 0) and the amount of the transaction\n? ");
		
		while (in.hasNext ()) {
			try {
				tr = new TransactionRegistry (in.nextInt (), in.nextDouble ());
				
				if (tr.getAccountNumber () > 0)
					transactions.writeObject (tr);
				else
					System.out.println ("The account number must be greater than 0");
			} catch (IOException ioEx) {
				System.err.println ("Error: We cannot write in the file");
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
      RegistroCuenta registro;
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
            registro = new RegistroCuenta( in.nextInt(), in.next(), in.next(), in.nextDouble() );

            if ( registro.obtenerCuenta() > 0 )
            {
               // escribe el nuevo registro
               master.writeObject( registro );
            } // fin de if
            else
            {
               System.out.println(
                  "The account number must be greater than 0." );
            } // fin de else
         } // fin de try
         catch ( IOException ioException )
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
		ObjectInputStream transactionsIn = null;
		ObjectInputStream masterIn = null;
		ObjectOutputStream newMaster = null;
		Formatter errors = null;

   	try {
			masterIn = new ObjectInputStream (new FileInputStream ("masterFile.ser"));
			transactionsIn = new ObjectInputStream (new FileInputStream ("transactions.ser"));
			newMaster = new ObjectOutputStream (new FileOutputStream ("newMasterFile.ser"));
			errors = new Formatter ("errors.txt");

			while (true)
				transactions.add (transactionsIn.readObject ());
		} catch (EOFException eofEx) {
		} catch (IOException ioEx) {
			System.err.println ("Error: We cannot read the file");
			mustClose = true;
		} catch (ClassNotFoundException classNoFoundEx) {
			System.err.println ("The class does not exist");
			mustClose = true;
		} finally {
			try {
				if (transactionsIn != null)
					transactionsIn.close ();
			} catch (IOException ioEx) {
				System.err.println ("Error in the process");
				System.exit (1);
			}
		
			if (mustClose)
				System.exit (1);
		}
		
   	try {
			while (true) {
				RegistroCuenta temp = (RegistroCuenta) masterIn.readObject ();
				
				for (short j = 0; j < transactions.size (); ++j) {
					TransactionRegistry tr = (TransactionRegistry) transactions.elementAt (j);
				
					if (temp.obtenerCuenta () == tr.getAccountNumber ()) {
						temp.establecerSaldo (temp.obtenerSaldo () + tr.getAmount ());
						transactions.removeElementAt (j--);
					}
				}
				
				newMaster.writeObject( temp );
			}
		} catch (EOFException eofEx) {
		} catch (IOException ioEx) {
			System.err.println ("Error in the process");
			mustClose = true;
		} catch (ClassNotFoundException classNotFoundEx) {
			System.err.println ("Error: The class does not exist");
			mustClose = true;
		} finally {
			try {
				transactionsIn.close ();
				newMaster.close ();
			} catch (IOException ioEx) {
				System.err.println ("Error in the process");
				System.exit (1);
			}
		
			if (mustClose)
				System.exit (1);
		}
		
		for (short j = 0; j < transactions.size (); ++j)
			errors.format ("There is a transaction registry with an account number such does not exist: %s\n",
								transactions.elementAt (j));
			errors.close ();
   }
   
   public void showFilesContent () {
   	ObjectInputStream master = null;
   	ObjectInputStream transactions = null;
   	ObjectInputStream newMaster = null;
   
   	try {
			master = new ObjectInputStream (new FileInputStream ("masterFile.ser"));
			transactions = new ObjectInputStream (new FileInputStream ("transactions.ser"));
			newMaster = new ObjectInputStream (new FileInputStream ("newMasterFile.ser"));
			
			System.out.println ("\n---masterFile.ser---");
			try {
				while (true)
					System.out.println (master.readObject ());
			} catch (EOFException eofEx) {
			}
			
			System.out.println ("\n---transactions.ser---");
			try {
				while (true)
					System.out.println (transactions.readObject ());
			} catch (EOFException eofEx) {
			}
			
			System.out.println ("\n---newMasterFile.ser---");
			try {
				while (true)
					System.out.println (newMaster.readObject ());
			} catch (EOFException eofEx) {
			}
   	} catch (IOException ioEx) {
   		System.err.println ("Error in the process");
   		ioEx.printStackTrace ();
   		System.exit (1);
   	} catch (ClassNotFoundException classNotFoundEx) {
			System.err.println ("The class does not exist");
			System.exit (1);
		} finally {
			try {
				if (master != null)
					master.close ();
			
				if (transactions != null)
					transactions.close ();
				
				if (newMaster != null)
					newMaster.close ();
			} catch (IOException ioEx) {
				System.err.println ("Error: We cannot close the files");
			}
		}
   }

	public static void main (String args[]) {
		FileAssociation fa = new FileAssociation ();
		
		System.out.printf ("%s\n%s\n%s\n",
									"Insert 0 to make the master and transaction files",
									"1 to associate the files",
									"or 2 to show the current files content");
		
		int choosen = new Scanner (System.in).nextInt ();
		if (choosen == 0) {
			fa.openFiles ();
			fa.addRegistries ();
			fa.addTransactions ();
			fa.closeFiles ();
		} else if (choosen == 1) {
			fa.associateFiles ();
		} else {
			fa.showFilesContent ();
		}
	}
}
