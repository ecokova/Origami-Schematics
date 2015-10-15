import hsa.Console;

public class Scheme 
{
    private String name;
    private SchemeArray[] arrays;
    private int numArrays;
    private Console console;
    
    protected final String DEFAULT_NAME = "Unnamed scheme";
    
    public Scheme()
    {
	name = DEFAULT_NAME;
	arrays = new SchemeArray[1];
	numArrays = 0;
	console = new Console();
    }//default constructor
    
    public Scheme (String inName)
    {
	name = inName;
	arrays = new SchemeArray[1];
	numArrays = 0;
	console = new Console();
    } //Scheme (String inName)
    
    public Scheme (String inName, SchemeArray[] inArrays)
    {
	name = inName;
	arrays = inArrays;
	numArrays = arrays.length;
	console = new Console();
    } //Scheme (String inName, SchemeArray[] inArrays)
    
    public Scheme (String inName, SchemeArray[] inArrays, Console inConsole)
    {
	name = inName;
	arrays = inArrays;
	numArrays = arrays.length;
	console = inConsole;
    } //Scheme (String inName, SchemeArray[] inArrays, Console inConsole)
    
    public Scheme (String inName, Console inConsole)
    {
	name = inName;
	arrays = new SchemeArray[1];
	numArrays = 0;
	console = inConsole;
    } //Scheme (String inName, Console inConsole)
    
    public void addArray(SchemeArray inArray)
    {
	if (numArrays == arrays.length)
	    expand();
	
	arrays[numArrays] = inArray;
	numArrays++;
    }
    
    public String getName()
    {
	return name;
    } //getName() --> String
    
    public int totalPieces()
    {
	//TODO
	return 0;
    } // totalPieces() --> int
    
    public int[] totalByColors() 
    {
    //index corresponds to character val in coloring[]
	//TODO
	return new int[1];
    } //totalByColors() --> int[] 
    
    private void expand()
    {
	SchemeArray[] newArrays = new SchemeArray[numArrays + 10];
	for (int i = 0; i < numArrays; i++)
	{
	    newArrays[i] = arrays[i];
	}
	arrays = newArrays;
    } //expand() --> increases arrays[] capacity by 10
    
    //prints all arrays to the console
    public void print (boolean[] printOptions) 
    {
	console.println ("Scheme: " + name);
	for (int i = 0; i < numArrays; i++)
	{
	    arrays[i].print (printOptions);
	}
    } //print(printOptions) --> prints all arrays to the console
    
    //Prints only the scheme array with a given label.
    public void print (String label, boolean[] printOptions)
    {
	int index = getArrayByLabel (label);
	arrays[index].print (printOptions);
    } //print (String label, boolean[] printOptions) --> prints to console
    
    //Returns the index of the array with a given label. Returns -1 if 
    //array with given label was not found. If there are duplicates, it
    //returns the first index found.
    private int getArrayByLabel (String label)
    {
	for (int i = 0; i < numArrays; i++)
	{
	    if (arrays[i].getLabel().equalsIgnoreCase(label))
		return i;
	}
	return -1;
    } //getArrayByLabel (label) --> int
    
    //Returns the total number of unique colors in a scheme.
    public int getNumColors()
    {
	int counter = 0;
	for (int i = 0; i < numArrays; i++)
	{
	    counter += arrays [i].getNumColors();
	    //NOTE NOTE NOTE NOTE NOTE NOTE NOTE 
	    //THIS IS NOT CORRECT!!! It will double
	    //count duplicate colors across arrays!!
	}
	return counter;
    } //getNumColors() --> int
    
    //Returns the number of pieces in a particular scheme array. If the scheme
    //array was not found, it returns -1. If there are multiple arrays with the
    //same label, the first one is returned (see getArrayByLabel()).
    public int getNumPieces (String label)
    {
	int index = getArrayByLabel (label);
	if (index == -1) 
	    return -1;
	
	return arrays[index].getTotalPieces();
    } //getNumPieces (label) --> int
    
    //Returns the total number of pieces for the scheme.
    public int getTotalPieces ()
    {
	int counter = 0;
	for (int i = 0; i < numArrays; i++)
	{
	    counter += arrays[i].getTotalPieces();
	}
	return counter;
    }
}
