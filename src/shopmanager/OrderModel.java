/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopmanager;

import java.util.List;


/**
 *
 * @author Patryk
 */
public class OrderModel 
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
    
    
    
    private CustomerModel Customer;
    
    public CustomerModel GetClient()
    {
        return this.Customer;
    }
    
    public void SetClient(CustomerModel Customer)
    {
        this.Customer = Customer;
    }
    
    
    
    private List<ProductModel> Products;
    
    public List<ProductModel> GetProducts()
    {
        return this.Products;
    }
    
    public void SetProducts(List<ProductModel> Products)
    {
        this.Products = Products;
    }
    
    
    
    public float GetOverallPrice()
    {
        float Price = 0f;
        
        for(int i=0; i<Products.size(); i++)
        {
            Price += Products.get(i).GetPrice();
        }
        
        return Price;
    }
}
