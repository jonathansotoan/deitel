//Exercise 15.20
package deitel.graphicsExercises.fields.labyrinth;

public class LabyrinthApp {
	
	public static void main (String args[]) {
		char[][] fieldTest = {{'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
									 {'#', '.', '.', '.', '#', '.', '.', '.', '.', '.', '.', '#'},
									 {'.', '.', '#', '.', '#', '.', '#', '#', '#', '#', '.', '#'},
									 {'#', '#', '#', '.', '#', '.', '.', '.', '.', '#', '.', '#'},
									 {'#', '.', '.', '.', '.', '#', '#', '#', '.', '#', '.', '.'},
									 {'#', '#', '#', '#', '.', '#', '.', '#', '.', '#', '.', '#'},
									 {'#', '.', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#'},
									 {'#', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#'},
									 {'#', '.', '.', '.', '.', '.', '.', '.', '.', '#', '.', '#'},
									 {'#', '#', '#', '#', '#', '#', '.', '#', '#', '#', '.', '#'},
									 {'#', '.', '.', '.', '.', '.', '.', '#', '.', '.', '.', '#'},
									 {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}};
	
		LabyrinthSolverModel lsm = new LabyrinthSolverModel (new Labyrinth (fieldTest, '#', '.', 2, 0, 4, 11), 'x');
		lsm.solve ();
		System.out.println ("---Test 1: Elaborated labyrinth---\n" + lsm);
		
		LabyrinthSolverModel lsm = new LabyrinthSolverModel ();
		lsm.solve ();
		System.out.println ("---Test 2: Random labyrinth---\n" + lsm);
	}
}
