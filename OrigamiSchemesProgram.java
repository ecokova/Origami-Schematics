import hsa.Console;
import java.io.*;
import java.awt.*;
import java.util.*;

public class OrigamiSchemesProgram
{
    static Console console;
    public static void main (String[] args)
    {
	console = new Console (35, 160, 12);
	console.println ("Hi there!");

	printMainMenu ();
	int mainMenuOption = console.readInt ();
	while (mainMenuOption != 0)
	{
	    if (mainMenuOption > 2 || mainMenuOption < 0)
	    {
		console.print ("Invalid choice.\nChoose an option: ");
		mainMenuOption = console.readInt ();
	    }
	    else
	    {
		if (mainMenuOption == 1) //create new design
		{
		    console.print ("Enter a name for the scheme: ");
		    String name = console.readLine ();
		    Scheme newScheme = new Scheme (name, console);


		    console.print ("Enter a name for the scheme array: ");
		    name = console.readLine ();

		    SchemeArray arr;
		    console.print ("Create a basic patterned scheme (1)" +
			    " or a custom scheme (2)? ");
		    int arrayOption = console.readInt ();
		    if (arrayOption == 1)
		    {
			int width, height;
			console.print ("Width: ");
			width = console.readInt ();
			console.print ("Height: ");
			height = console.readInt ();
			console.print ("Pattern: ");
			String sym = console.readLine ();

			if (sym.length () > width)
			    console.println ("Warning: Pattern will not fit in array.");

			String[] [] newArray = new String [width] [height];

			
			if (sym.length () == 1)
			{
			    arr = new SchemeArray (name, width, height, sym, console);
			}
			else
			{
			    int i;
			    for (int h = 0 ; h < newArray [0].length ; h++)
			    {
				i = 0;
				for (int w = 0 ; w < newArray.length ; w++)
				{
				    while (sym.charAt (i) == ' ')
					i++;

				    newArray [w] [h] = "" + sym.charAt (i);
				    i = (i + 1) % sym.length ();
				}
			    }

			    arr = new SchemeArray (name, newArray, console, true);
			}

			//arr.print ();
		    }
		    else
		    {
			console.println ("Draw the array. Enter \'quit\' on a "
			    + "new line to end.");
			String[] savedLines = new String[100];
			int lineCounter = 0;
			
			String lineInput = console.readLine();
			while (!lineInput.equalsIgnoreCase("quit"))
			{
			    
			    savedLines[lineCounter] = lineInput;
			    lineCounter++;
			    lineInput = console.readLine();
			    
			}
			int longest = 0;
			for (int i = 0; i < lineCounter; i++)
			{
			    console.println ("i: " + i + "Longest: " + longest + ", len: " + 
				savedLines[longest].length());
				
			    if (savedLines[longest].length() < savedLines[i].length())
				longest = i;
			}
			String[] [] newArray =
			    new String[savedLines[longest].length()][lineCounter];
			for (int h = 0; h < newArray[0].length; h++)
			{
			    for (int w = 0; w < newArray.length; w++)
			    {
				if (savedLines[h].length() <= w)
				    newArray[w][h] = "_";
				else 
				{
				    if (savedLines[h].charAt(w) == ' ')
					newArray[w][h] = "_";
				    else
					newArray[w][h] = savedLines[h].charAt(w) + "";
				}
			    }
			}
			arr = new SchemeArray (name, newArray, console, true);
		    }
		    newScheme.addArray (arr);
		    int blah = console.readInt ();

		    console.print ("Number of colors: " + newScheme.getNumColors ());
		    console.print ("\nNumber of pieces: " + newScheme.getTotalPieces ());
		    console.println ();
		    //arr.saveToFile ("testFile");

		    // SchemeArray arr2 = new SchemeArray ("testFile", 0, console);

		    boolean[] [] options = fillTestOptions ();
		    int row = console.getRow ();
		    int col = console.getColumn ();
		    //arr.print ();
		    while (true)
		    {

			if (blah >= 0 && blah < 32)
			{
			    if ((!(options [blah] [0] && options [blah] [2])) &&
				    (options [blah] [0] || options [blah] [2] || options [blah] [3]))
			    {
				if (options [blah] [0])
				    console.print ("showSym ");
				if (options [blah] [1])
				    console.print ("showRows ");
				if (options [blah] [2])
				    console.print ("showCols ");
				if (options [blah] [3])
				    console.print ("blocks ");
				if (options [blah] [4])
				    console.print ("narrow");
				console.println ();
				newScheme.print (options [blah]);
				//arr2.print (options [blah] [0], options [blah] [1],
				//        options [blah] [2], options [blah] [3],
				//        options [blah] [4], false);


			    }
			}

			blah = console.readInt ();
		    }





		}
		else //load design from file
		{


		}
		printMainMenu ();
		mainMenuOption = console.readInt ();
	    }
	}

    }


    public static boolean[] [] fillTestOptions ()
    {
	boolean one, two, three, four, five;
	one = two = three = four = five = false;
	boolean[] [] options = new boolean [32] [6];
	int counter = 0;
	for (int a = 0 ; a < 2 ; a++)
	{
	    for (int b = 0 ; b < 2 ; b++)
	    {
		for (int c = 0 ; c < 2 ; c++)
		{
		    for (int d = 0 ; d < 2 ; d++)
		    {
			for (int e = 0 ; e < 2 ; e++)
			{
			    options [counter] [0] = one;
			    options [counter] [1] = two;
			    options [counter] [2] = three;
			    options [counter] [3] = four;
			    options [counter] [4] = five;
			    options [counter] [5] = false;
			    counter++;
			    five = !five;
			}
			four = !four;
		    }
		    three = !three;
		}
		two = !two;
	    }
	    one = !one;
	}
	return options;
    }


    public static void printMainMenu ()
    {
	console.println ("Main Menu---------");
	console.println ("1 - Create new design");
	console.println ("2 - Load design from file");
	console.println ("0 - Quit");
	console.print ("Choose an option: ");
    }
}
/* Fills with numbers, each row a factor of the height (5x10)
	     01 02 03 04 05
	     11 12 13 14 15
	     21 22 23 24 25
	     ... etc

	for (int h = 0; h < newArray[0].length; h++){ //height
	    for (int w = 0; w < newArray.length; w++){ //width
		newArray[w][h] = (newArray[0].length*h + w + 1) + " ";
		if (newArray[w][h].length() < 3)
		    newArray[w][h] = "0" + newArray[w][h];
		//newArray[i][j] = "" + i + (j + 1) + " ";
	    }
	}
	*/
