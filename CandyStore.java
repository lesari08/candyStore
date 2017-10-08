package cs;

public class CandyStore 
{
	//================================================================
    // Fields
    //================================================================
	
	static int productCount;
	static double inventoryValue;
	static int inventoryCount;
	
	private int code;
	private String name;
	private double cost;
	private int candyCount;
    
    //================================================================
    // Constructors
    //================================================================
	
	public CandyStore()
	{
        productCount += 1;
        
        code = -1;
        name = "(not set)";
        cost = -1;
        candyCount = -1;
	}
	
	public CandyStore(int code, String name, double cost, int candyCount)
	{
		productCount = productCount + 1;
		inventoryCount = inventoryCount + candyCount;
		inventoryValue = inventoryValue + (cost * candyCount);
		
        this.code = code;
        this.name = name;
        this.cost = cost;
        this.candyCount = candyCount;
	}
    
    //================================================================
    // Methods
    //================================================================
    
	
    //----------------------------------------------------------------
    // get methods
    //----------------------------------------------------------------

	public static int getProductCount()
	{
		return productCount;
	}
	public static double getInventoryValue()
	{
		return inventoryValue;
	}
	public static int getInventoryCount()
	{
		return inventoryCount;
	}
	public int getCode()
	{
		return code;
	}
	public String getName()
    {
		return name;
	}

	public double getCost()
	{
		return cost;
	}
	
	public int getCandyCount()
	{
		return candyCount;
	}

	
    
    //----------------------------------------------------------------
    // set methods
    //----------------------------------------------------------------

	public static void setInventoryCount(int change)
	{
		
		inventoryCount = inventoryCount + change; //change is either positive
												//or negative depending on
												//the method that called it
	}
	public static void setProductCount()
	{
		productCount += 1;
	}
	public static void setInventoryValue(CandyStore obj)
	{
		inventoryValue = inventoryValue + (obj.getCost() * obj.getCandyCount());
	}
	public void setCode(int code)
	{
		this.code = code;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public void setCost(double cost)
	{
		this.cost = cost;
	}
	
	public void setCandyCount(int candyCount)
	{
		this.candyCount = candyCount;
	}

    
    //----------------------------------------------------------------
    // equals and toString methods
    //----------------------------------------------------------------

	public boolean equals (CandyStore b)
	{
		if ((this.code == b.code) &&
			(this.name.equalsIgnoreCase(b.name)) && 
			(this.cost == b.cost) &&
			(this.candyCount == b.candyCount))
			return true;
		else
			return false;
	}

	public String toString ()
	{
		return "Code: " + code + "\nName: " + name + "\nCost: " + cost 
				+ "\nCandy Count" + candyCount;        
	}
}