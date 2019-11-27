/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopmanager;

/**
 *
 * @author Patryk
 */
public class ProductModel 
{
    private int Id;
    
    public int GetId()
    {
        return this.Id;
    }
    
    public void SetId(int Id)
    {
        this.Id = Id;
    }
    
    
    private String Name;
    
    public String GetName()
    {
        return this.Name;
    }
    
    public void SetName(String Name)
    {
        this.Name = Name;
    }
    
    
    
    private float Price;
    
    public float GetPrice()
    {
        return this.Price;
    }
    
    public void SetPrice(float Price)
    {
        this.Price = Price;
    }
    
    @Override
    public String toString()
    {
        return Name + " : " + Price + " zł";
    }
}
