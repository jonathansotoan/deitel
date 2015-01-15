// Pagina 312

public class CrapsTest {

	public static void main (String args[]) {
		Craps crapsGame = new Craps ();
		
		for (short j = 0; j <= 1000; ++j)
			crapsGame.jugar ();
			
		crapsGame.imprimirFrecuencia ();
	}
}
