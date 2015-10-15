import hsa.Console;
import java.awt.*;
import hsa.TextOutputFile;
import hsa.TextInputFile;

public class SchemeArray
{
    private String label;
    private String[] [] array;
    private Console console;
    private boolean colored;

    private Color[] coloring;
    private int[] symCounter = new int[94];
    
    protected final String DEFAULT_CHAR =  "" + (char)(3);
    protected final String DEFAULT_LABEL = "Misc scheme array";
    protected final String BLANK_CHAR = "_";
    protected final String TERMINATOR_CHAR = "X";

    //default constructor
    public SchemeArray ()
    {
	label = DEFAULT_LABEL;
	createDefaultArray (10, 10, DEFAULT_CHAR);
	setSymCounterAt(DEFAULT_CHAR,10*10);
	console = new Console ("Default array");
	colored = false;
    } //default constructor

    public SchemeArray (Console inConsole)
    {
	label = DEFAULT_LABEL;
	createDefaultArray (10, 10, DEFAULT_CHAR);
	setSymCounterAt(DEFAULT_CHAR,10*10);
	console = inConsole;
	colored = false;
    } //SchemeArray (Console inConsole)


    public SchemeArray (String symbol)
    {
	label = DEFAULT_LABEL;
	createDefaultArray (10, 10, symbol);
	setSymCounterAt(DEFAULT_CHAR,10*10);
	console = new Console ();
	colored = false;
    } //SchemeArray (String symbol)


    public SchemeArray (String symbol, Console inConsole)
    {
	label = DEFAULT_LABEL;
	createDefaultArray (10, 10, symbol);
	setSymCounterAt(DEFAULT_CHAR,10*10);
	console = inConsole;
	colored = false;
    } //SchemeArray (String symbol, Console inConsole)


    public SchemeArray (int width, int height)
    {
	label = DEFAULT_LABEL;
	createDefaultArray (width, height, DEFAULT_CHAR);
	setSymCounterAt(DEFAULT_CHAR,width*height);
	console = new Console ();
	colored = false;
    } //SchemeArray (int width, int height)


    public SchemeArray (String inLabel, int width, int height, Console inConsole)
    {
	label = inLabel;
	createDefaultArray (width, height, DEFAULT_CHAR);
	setSymCounterAt(DEFAULT_CHAR,width*height);
	console = inConsole;
	colored = false;
    } //SchemeArray (String inLabel, int width, int height, Console inConsole)


    public SchemeArray (String inLabel, int width, int height, String symbol)
    {
	label = inLabel;
	createDefaultArray (width, height, symbol);
	setSymCounterAt(symbol,width*height);
	console = new Console ();
	colored = false;
    } //SchemeArray (String inLabel, int width, int height, String symbol)


    public SchemeArray (String inLabel, int width, int height, String symbol, 
	Console inConsole)
    {
	label = inLabel;
	createDefaultArray (width, height, symbol);
	setSymCounterAt(symbol,width*height);
	console = inConsole;
	colored = false;
    } //SchemeArray (String inLabel, int width, int height, String symbol, 
      //Console inConsole)


    public SchemeArray (String inLabel, String[] [] inArray)
    {
	label = inLabel;
	array = inArray;
	updateSymCounter();
	console = new Console ();
	colored = false;
    } //SchemeArray (String inLabel, String[] [] inArray)


    public SchemeArray (String inLabel, String[] [] inArray, Console inConsole)
    {
	label = inLabel;
	array = inArray;
	updateSymCounter();
	console = inConsole;
	colored = false;
    } //SchemeArray (String inLabel, String[] [] inArray, Console inConsole)


    public SchemeArray (String inLabel, String[] [] inArray, boolean inColored)
    {
	label = inLabel;
	array = inArray;
	updateSymCounter();
	console = new Console ();
	colored = inColored;
	if (colored)
	    assignColors ();
    } //SchemeArray (String inLabel, String[] [] inArray, boolean inColored)


    public SchemeArray (String inLabel, String[] [] inArray, Console inConsole, 
	boolean inColored)
    {
	label = inLabel;
	array = inArray;
	updateSymCounter();
	console = inConsole;
	colored = inColored;
	if (colored)
	    assignColors ();
    } //SchemeArray (String inLabel, String[] [] inArray, Console inConsole,
      // boolean inColored)
    

    //Makes array of given dimensions and fills with given symbol
    private void createDefaultArray (int width, int height, String sym)
    {
	array = new String [width] [height];
	for (int h = 0 ; h < array [0].length ; h++)
	{
	    for (int w = 0 ; w < array.length ; w++)
	    {
		array [w] [h] = sym;
	    }
	}
    } //createDefaultArray (int width, int height, String sym)


    //loops through unique characters and assigns them a color
    private void assignColors ()
    {
	coloring = new Color [94];
	for (int h = 0 ; h < array [0].length ; h++)
	{
	    for (int w = 0 ; w < array.length ; w++)
	    {
		if (array [w] [h].equals(BLANK_CHAR))
		    setColorAt (array [w] [h], new Color (240,240,240));
		else if (array [w] [h].equals(TERMINATOR_CHAR))
		    setColorAt (array [w] [h], new Color (15,15,15));
		else 
		{
		    Color color = getColorAt (array [w] [h]);
		    if (color == null) //unassigned color
		    {
		       console.print ("Enter a color for \'" +
				array [w] [h] + "\': ");
			
		       setColorAt (array [w] [h], getColorInput ());
		    }
		}
	    }
	}
    } //assignColors() --> defines and fills coloring


    //processes input given at assignColors() and returns the appropriate
    //color. can distinguish between rgb input and keyword input
    private Color getColorInput ()
    {
	String inColor = console.readLine();
	String[] input = inColor.split (" ");
	
	while (validColorInput(input) == null) {
	    console.println ("Invalid color. Re-enter name or RGB values.");
	    inColor = console.readLine();
	    input = inColor.split (" ");             
	}
	return validColorInput(input);
    } //getColorInput() --> Color


    //helper function for getColorInput()
    private Color validColorInput (String[] input)
    {
	//incorrect length
	if (input.length != 3 && input.length != 1)
	    return null;
	//tried to enter color's name
	if (input.length == 1)
	{
	    //invalid color name
	    if (stringToColor (input [0]) == null)
		return null;
	    else //valid color
		return stringToColor(input[0]);
	}
	else //entered RGB values
	{
	    //saved for reference **
	    //int r = (int) (Double.valueOf (input [0]).doubleValue ());
	    int r = Integer.parseInt(input[0]);
	    int g = Integer.parseInt(input [1]);
	    int b = Integer.parseInt(input [2]);
	    if (((r < 0) || (g < 0) || (b < 0)) ||
		((r > 255) || (g > 255) || (b > 255)))
		return null;
	    else
	       return (new Color (r,g,b)); //return valid color
	}

    } //validColorInput (String[] input) --> Color


    //Returns the color at index "searchStr" in coloring. searchStr should
    //be only one character long.
    private Color getColorAt (String searchStr)
    {
	int searchInt = searchStr.charAt (0) - 33;
	return coloring [searchInt];
    } //getColorAt (String searchStr) --> Color


    //Sets the color at index "searchStr" in coloring to "color". searchStr
    //should be only one character long.
    private void setColorAt (String searchStr, Color color)
    {
	int searchInt = searchStr.charAt (0) - 33;
	coloring [searchInt] = color;
    } //setColorAt (String searchStr, Color color) --> modifies coloring


    //Returns the value at index "ch".
    private int getSymCounterAt (String ch)
    {
	int index = ch.charAt(0) - 33;
	return symCounter[index];
    } //getSymCounterAt (String ch) --> int
    
    //Sets the value at index "ch" to num.
    private void setSymCounterAt (String ch, int num)
    {
	int index = ch.charAt(0) - 33;
	symCounter[index] = num; 
    } //setSymCounterAt (String ch, int num) --> symCounter[ch] = num
    
    //Goes through and calculates symCounter values for all symbols.
    private void updateSymCounter()
    {
	for (int h = 0; h < array[0].length; h++)
	{
	    for (int w = 0; w < array.length; w++)
	    {
		setSymCounterAt(array[w][h], getSymCounterAt(array[w][h]) + 1);
	    }
	}
    } //updateSymCounter() --> modifies symCounter
    
    private Color stringToColor (String str)
    {
	if (str.equalsIgnoreCase ("white"))
	    return Color.white;
	else if (str.equalsIgnoreCase ("blue"))
	    return Color.blue;
	else if (str.equalsIgnoreCase ("green"))
	    return Color.green;
	else if (str.equalsIgnoreCase ("magenta") ||
		str.equalsIgnoreCase ("purple"))
	    return Color.magenta;
	else if (str.equalsIgnoreCase ("yellow"))
	    return Color.yellow;
	else if (str.equalsIgnoreCase ("pink"))
	    return Color.pink;
	else if (str.equalsIgnoreCase ("gray"))
	    return Color.gray;
	else if (str.equalsIgnoreCase("black"))
	    return Color.black;
	else if (str.equalsIgnoreCase ("orange"))
	    return Color.orange;
	else if (str.equalsIgnoreCase ("red"))
	    return Color.red;
	else if (str.equalsIgnoreCase ("cyan"))
	    return Color.cyan;
	else
	    return null;

    } //stringToColor (String str) --> Color


    private String colorToString (Color color)
    {
	if (color.equals(Color.white))
	    return "white";
	else if (color.equals(Color.blue))
	    return "blue";
	else if (color.equals(Color.green))
	    return "green";
	else if (color.equals(Color.magenta))
	    return "magenta";
	else if (color.equals(Color.yellow))
	    return "yellow";
	else if (color.equals(Color.pink))
	    return "pink";
	else if (color.equals(Color.gray))
	    return "gray";
	else if (color.equals(Color.black))
	    return "black";
	else if (color.equals(Color.orange))
	    return "orange";
	else if (color.equals(Color.red))
	    return "red";
	else if (color.equals(Color.cyan))
	    return "cyan";
	else
	    return null;
	    
	//return color.toString();

    } //colorToString (Color color) --> String

    //returns the label for the scheme array
    public String getLabel()
    {
	return label;
    } //getLabel() --> String
    
    public int getTotalPieces()
    {
	return (array[0].length * array.length -
	    getNumOfChar(TERMINATOR_CHAR) - getNumOfChar(BLANK_CHAR));
    }
    
   /* public int[] getTotalByColor()
    {
    }*/
    
    //Returns the number of unique colors in the scheme array, ignoring blanks
    //and terminators.
    public int getNumColors()
    {
	int counter = 0;
	HashTable hTable = new HashTable();
	for (int i = 0; i < coloring.length; i++)
	{
	    if ((i == TERMINATOR_CHAR.charAt(0) - 33) ||
		(i == BLANK_CHAR.charAt(0) - 33) ||
		(coloring[i] == null))
	    {
		continue;
	    }
	    if (!hTable.find (coloring [i]))
	    {
		hTable.add (coloring [i]);
		counter++;
	    }
	}
	return counter;
    } //getNumColors() --> int
    
    //Returns the number of unique symbols with the option to ignore/include
    //special characters like BLANK_CHAR and TERMINATOR_CHAR.
    public int getNumSymbols(boolean includeSpecialChars)
    {
	int counter = 0;
	if (includeSpecialChars)
	{
	    for (int i = 0; i < symCounter.length; i++)
	    {
		if (symCounter[i] > 0) 
		    counter++;
	    }
	}
	else
	{
	    for (int i = 0; i < symCounter.length; i++)
	    {
		if ((i == BLANK_CHAR.charAt(0) - 33) ||
		    (i == TERMINATOR_CHAR.charAt(0) - 33))
		    continue;
		if (symCounter[i] > 0) 
		    counter++;
	    }
	}
	return counter;
    } //getNumSymbols(boolean includeSpecialChars) --> int
    
    //Returns the number of times a certain character appears in the scheme
    //array, using symCounter.
    public int getNumOfChar(String ch)
    {
	return getSymCounterAt(ch);
    } //getNumOfChar (String ch) --> int
    
    //Returns the number of times a certain color appears in the scheme array
    //by summing the number of times symbols of that color appear.
    public int getNumOfColor(Color color)
    {
	int counter = 0;
	for (int i = 0; i < coloring.length; i++)
	{  
	   if (coloring[i] == color)
		counter += symCounter[i]; 
	}
	return counter;
    } //getNumOfColor (Color color) --> int
    
    //Customizable printing method
    public void print (boolean showSym, boolean showRows, boolean showCols,
	boolean blocks, boolean narrow, boolean blockPadding)
    {
	console.setTextColor(Color.black);
	console.setTextBackgroundColor(Color.white);
	console.println (label);
	for (int h = 0 ; h < array [0].length ; h++)
	{
	    if (h % 2 == 0) //offset even lines
	    {
		console.setTextBackgroundColor(Color.white);
		console.setTextColor(Color.black);
		if (showRows) console.print ((h%10+1)%10);
		else console.print (" ");
		
		if (blockPadding) console.print (" ");
	    }
	    for (int w = 0 ; w < array.length ; w++)
	    {
		if (colored)
		{
		    if ((showSym || showCols) && !blocks)
		    {   
			console.setTextColor (getColorAt (array [w] [h]));
			
			//edge case
			if (getColorAt (array [w] [h]) == Color.white)
			    console.setTextBackgroundColor (Color.lightGray);
		    }
		    if (blocks)
		    {
		       if ((getColorAt(array[w][h]).getRed() +
			    getColorAt(array[w][h]).getGreen() +
			    getColorAt(array[w][h]).getBlue())/3 < (255 * 0.3))
		       {
			    console.setTextColor(new Color(255,255,255,60));
		       }    
		       else
		       { 
			    //console.setTextColor(new Color(0,0,0,70));
			    //transparent black doesn't work as well as darker()
			    console.setTextColor(getColorAt(array[w][h]).darker().darker());
		       }
		       console.setTextBackgroundColor(getColorAt(array[w][h]));
		    }
		}
		//default vals
		boolean firstPad = true;
		boolean secondPad = true;
		if (showCols && !narrow) 
		{   //ensures blocks are 3-char wide
		    if (w > 9) firstPad = false;
		    if (w > 99) secondPad = false;
		}
		//first character
		if (blocks && !narrow && firstPad) console.print(" "); //first pad
		//middle character: symbols, numbers, or blank
		if (showSym) console.print (array [w] [h]); //symbols
		if (showCols)
		{
		    if (array [w] [h].equals (BLANK_CHAR)) 
			console.print ("-");
		    else if (array [w] [h].equals (TERMINATOR_CHAR))
			console.print ("X");   
		    else if (narrow) console.print ((w%10+1)%10); //1-0 vals
		    else console.print (w); //full values
		}
		if (blocks && !showCols && !showSym) console.print(" "); //blank
		//last character
		if (blocks && !narrow && secondPad) console.print(" "); //second pad 
		
		//reset console background for spacing
		if (colored) console.setTextBackgroundColor (Color.white); 
		if (!blocks || blockPadding) console.print (" "); //space after each symbol
	    }
	    console.setTextColor(Color.black);
	    console.setTextBackgroundColor(Color.white);
	    console.println ();
	}
    } //print (showSym, showRows, showCols, blocks, narrow, blockPadding)
    
    //Customizable printing method with option to enter array of booleans
    //instead of individual booleans.
    public void print (boolean[] printOptions) 
    {
	print (printOptions[0], printOptions[1], printOptions[2],
	    printOptions[3], printOptions[4], printOptions[5]);
    } //print (printOptions)
    
    //Prints array with offset and spacing
    public void print ()
    {
	console.println (label);
	for (int h = 0 ; h < array [0].length ; h++)
	{
	    if (h % 2 == 0) //even lines are offset
		console.print (" ");
	    for (int w = 0 ; w < array.length ; w++)
	    {
		if (colored)
		{
		    console.setTextColor (getColorAt (array [w] [h]));
		    if (getColorAt (array [w] [h]) == Color.white)
			console.setTextBackgroundColor (Color.gray);
		}
		console.print (array [w] [h]);
		console.setTextBackgroundColor (Color.white); 
		console.print (" "); //space after each symbol
	    }
	    console.println ();
	}
    } //print() --> prints symbols with spaces to console
    
    public void saveToFile(String fileName) 
    {
	//not appending
	TextOutputFile file = new TextOutputFile (fileName+".txt",false);
	file.println(array.length + " " + array[0].length);
	for (int h = 0; h < array[0].length; h++)
	{
	    for (int w = 0; w < array.length; w++)
	    {
		file.print(array[w][h]);
	    }
	    file.println();
	}
	file.close();
    }
    public void loadFromFile(String fileName)
    {
	TextInputFile file = new TextInputFile(fileName+".txt");
	int width = file.readInt();
	int height = file.readInt();
	System.out.println(width);
	System.out.println(height);
	file.readLine();
	array = new String[width][height];
	for (int h = 0; h < height; h++)
	{
	    for (int w = 0; w < width; w++)
	    {
		array[w][h] = file.readChar()+"";
		System.out.print(":"+array[w][h]);
	    }
	    //file.readLine();
	}
	file.close();
    }
    
    
}


