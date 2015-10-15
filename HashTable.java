import java.awt.*;

public class HashTable
{
    private Color[] table;
    private int size;
    
    private final int INIT_CAPACITY = 10;
    private final double CAP_FACTOR = 0.7;
    
    private int collisions;
    private int reHashCollisions;
    
    public HashTable ()
    {
	table = new Color [INIT_CAPACITY];
	size = 0;
	collisions = 0;
	reHashCollisions = 0;
    } //default constructor
    
    private void expand ()
    {
	Color[] newTable = new Color [size * 2];
	for (int i = 0; i < size; i++)
	{
	    if (table[i] == null)
		continue;
	    int hash = getHashKey (table[i],newTable.length,newTable);
	    newTable [hash] = table [i];
	}
	
	table = newTable;
    }
    
    private int hash (Color value)
    {  
	return value.hashCode() * -1;
    }
    private int getHashKey (Color value, int capacity)
    {
	int hash = hash (value) % capacity;
	while (table [hash] != null)
	{
	    hash = (hash+1)%capacity;
	    collisions++;
	}
	return hash;
    }
    
    private int getHashKey (Color value, int capacity, Color[] hTable)
    {
	int hash = hash (value) % capacity;
	while (hTable [hash] != null)
	{
	    hash = (hash+1)%capacity;
	    reHashCollisions++;
	}
	return hash;
    }
    
    public void add (Color value)
    {
	if (size >= table.length * CAP_FACTOR)
	    expand();
	
	int hash = getHashKey (value, table.length);
	table [hash] = value;
	size++;
    }
    
    public boolean find (Color value)
    {
	int hash = getHashKey (value, table.length);
	
	if (table[hash] == null)
	    return false;
	    
	int iter = 0;
	while (!(table [hash].equals(value)) && (iter < table.length))
	{
	    hash = (hash+1) % table.length;
	    iter++;
	}
	if (table [hash].equals(value)) return true;
	else return false;
    }
    
    public void getEfficiencyMap ()
    {
	System.out.println ("Collisions: " + collisions + "/" + size + " elements");
	System.out.println ("Re-hash Collisions: " + reHashCollisions + "/" + size + " elements");
	for (int i = 0; i < table.length; i++)
	{
	    if (i % 10 == 0) System.out.println();
	    if (table[i] == null) System.out.print("  ");
	    else System.out.print ("* ");
	}
	System.out.println ();
    }
    public int getCollisions()
    {
	return collisions;
    }
    
}
