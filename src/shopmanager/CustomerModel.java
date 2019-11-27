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
public class CustomerModel 
{
    private int Id;
    private int LastId;
    
    public int GetId()
    {
        return this.Id;
    }
    
    public void SetId(int Id)
    {
        this.Id = Id;
    }
    public void GetLastId(int Id)
    {
        this.LastId = LastId;
    }
    
    
    
    private String LastName;
    
    public String GetLastName()
    {
        return this.LastName;
    }
    
    public void SetLastName(String LastName)
    {
        this.LastName = LastName;
    }
    
    
    private String FirstName;
    
    public String GetFirstName()
    {
        return this.FirstName;
    }
    
    public void SetFirstName(String FirstName)
    {
        this.FirstName = FirstName;
    }
    
    
    
    private String Address;
    
    public String GetAddress()
    {
        return this.Address;
    }
    
    public void SetAddress(String Address)
    {
        this.Address = Address;
    }
    
    @Override
    public String toString()
    {
        return "Klient : " + GetLastName() + " " + GetFirstName() + " " + GetAddress();
    }
}
