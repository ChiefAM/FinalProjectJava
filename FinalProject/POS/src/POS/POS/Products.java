package POS;

public class Products 
{
    //creates the variables
    String name;
    double price;
    int quantity;
    String currency;
    //creates the constructor
    public Products(String name, double price, float f)
    {
        //sets the variables
        this.name = name;
        this.price = price;
        this.quantity = (int) f;
    }
    //creates the getters and setters
    public String getName()
    {
        return name;
    }
    public double getPrice()
    {
        return price;
    }
    public void setCurrency(String currency)
    {
        this.currency = currency;
    }
    public String getCurrency()
    {
        return currency;
    }
    public int getQuantity()
    {
        return quantity;
    }
    
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setPrice(double price)
    {
        this.price = price;
    }
    //creates the toString method
    public String toString()
    {
        return name + " " + price + " " + quantity;
    }
    
}