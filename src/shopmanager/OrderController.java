/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopmanager;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Patryk
 */

//@TODO buttony po stworzeniu zamówienia nie wyświetlają informacji o polach
//@TODO walidacja zamówienia po usunięciu klientów
public class OrderController 
{
    private OrderAddView AddView;
    
    private OrderEditView EditView;
    
    private OrderModel Order;
    
    private List<ProductModel> AvailableProducts;
    
    private List<CustomerModel> AvailableClients;
    
    private JTable AvailableProductTable;
    
    private JTable ClientTable;
    
    private JTable OrderTable;
    
    private int SelectedId = -1;
    
    private boolean CorrectOrder = true;
    
    
    
    public OrderController(OrderAddView AddView, OrderModel Order, List<ProductModel> AvailableProducts, List<CustomerModel> AvailableClients, JTable OrderTable, JTable ClientTable)
    {
        this.AddView = AddView;
        
        this.Order = Order;
        
        this.AvailableProducts = AvailableProducts;
        
        this.AvailableClients = AvailableClients;
        
        this.OrderTable = OrderTable;
        
        this.ClientTable = ClientTable;
        
        AddOrder_InitController();
    }
    
    public OrderController(OrderEditView EditView, OrderModel Order, List<ProductModel> AvailableProducts, List<CustomerModel> AvailableClients, JTable OrderTable, JTable ClientTable)
    {
        this.EditView = EditView;
        
        this.Order = Order;
        
        this.AvailableProducts = AvailableProducts;
        
        this.AvailableClients = AvailableClients;
        
        this.OrderTable = OrderTable;
        
        this.ClientTable = ClientTable;
        
        EditOrder_InitController();
    }
    
    
    
    public void AddOrder_InitController() 
    {
        AddView.GetAcceptButton().addActionListener(e -> AddOrder_Accept());
        
        AddView.GetCancelButton().addActionListener(e -> AddOrder_Cancel());
    }
 
    private void AddOrder_Accept()
    {
        AddOrder();
    }
 
    private void AddOrder_Cancel()
    {
        AddView.getframe().dispose();
    }
    
    private void AddOrder()
    {
        CorrectOrder = true;
        
        DefaultTableModel OrderModel = (DefaultTableModel) this.OrderTable.getModel();
        
        SetupOrder_Id(OrderModel);

        SetupOrder_ChosenProducts((DefaultTableModel) AddView.getChosenProducts().getModel());
        
        SetupOrder_Client_Add();
        
        if(!CorrectOrder) return;
        
        SetupOrder_OrderRecord_Add(OrderModel);
        
        AddView.getframe().dispose();
    }
    
    void SetupOrder_Id(DefaultTableModel AvailableProductsModel)
    {
        Order.SetId(GetUniqueId(AvailableProductsModel));
    }
    
    void SetupOrder_ChosenProducts(DefaultTableModel ChosenProductsProductsModel)
    {
        List<ProductModel> ChosenProducts = new ArrayList<ProductModel>();
        
        if(ChosenProductsProductsModel.getRowCount() == 0)
        {
            MainJFrame.Message("Choose at least 1 product", "Warning");
            
            CorrectOrder = false;
        }
        
        for(int i=0; i<ChosenProductsProductsModel.getRowCount(); i++)
        {
            int ChosenProductId = Integer.parseInt((String) ChosenProductsProductsModel.getValueAt(i, 0));
            
            ChosenProducts.add(AvailableProducts.get(ChosenProductId));
        }
        
        Order.SetProducts(ChosenProducts);
    }
    
    void SetupOrder_Client_Add()
    {
        int ChosenClientId = AddView.getClientCombo().getSelectedIndex();
        
        if(ChosenClientId == -1)
        {
            MainJFrame.Message("You must choose a Client", "Warning");
            
            CorrectOrder = false;
        }
        
        CustomerModel ChosenClient = AvailableClients.get(ChosenClientId);
        
        Order.SetClient(ChosenClient);
    }
    
    void SetupOrder_Client_Edit()
    {
        int ChosenClientId = EditView.getClientCombo().getSelectedIndex();
        
        if(ChosenClientId == -1)
        {
            MainJFrame.Message("You must choose a Client", "Warning");
            
            CorrectOrder = false;
        }
        
        CustomerModel ChosenClient = AvailableClients.get(ChosenClientId);
        
        Order.SetClient(ChosenClient);
    }
    
    void SetupOrder_OrderRecord_Add(DefaultTableModel OrderTableModel)
    {
        OrderTableModel.addRow(new Object[]{Order.GetId(), Order.GetClient().GetLastName(), String.valueOf(Order.GetProducts().size()), Order.GetOverallPrice()});
        
        String ClientDescription = Order.GetClient().toString();
        
        String ProductsDescription = "Products\n";
        
        List<ProductModel> products = Order.GetProducts();
        
        for(int i = 0; i<products.size(); i++)
        {
            ProductModel product = products.get(i);
            
            ProductsDescription += "\t" + product.toString() + "\n";
        }
        
        ProductsDescription += "\nSUMA : " + Order.GetOverallPrice() + " zł";
        
        JButtonOrderTable button = new JButtonOrderTable(OrderTable, ClientDescription, ProductsDescription, Order.GetId());
    }
    
    void SetupOrder_OrderRecord_Edit(DefaultTableModel OrderTableModel)
    {
        SelectedId = OrderTable.getSelectedRow();

        OrderTableModel.setValueAt(Order.GetId(), SelectedId, 0);
        
        OrderTableModel.setValueAt(Order.GetClient().GetLastName(), SelectedId, 1);
        
        OrderTableModel.setValueAt(String.valueOf(Order.GetProducts().size()), SelectedId, 2);
        
        OrderTableModel.setValueAt(Order.GetOverallPrice(), SelectedId, 3);
        
        String ClientDescription = Order.GetClient().toString();
        
        String ProductsDescription = "Products\n";
        
        List<ProductModel> products = Order.GetProducts();
        
        for(int i = 0; i<products.size(); i++)
        {
            ProductModel product = products.get(i);
            
            ProductsDescription += "\t" + product.toString() + "\n";
        }
        
        ProductsDescription += "\nSUMA : " + Order.GetOverallPrice() + " zł";

        JButtonOrderTable button = new JButtonOrderTable(OrderTable, ClientDescription, ProductsDescription, Order.GetId());
    }
    
    
    
    public void EditOrder_InitController() 
    {
        EditView.GetAcceptButton().addActionListener(e -> EditOrder_Accept());
        
        EditView.GetCancelButton().addActionListener(e -> EditOrder_Cancel());
    }
 
    private void EditOrder_Accept()
    {
        EditOrder();
    }
 
    private void EditOrder_Cancel()
    {
        EditView.getframe().dispose();
    }
    
    private void EditOrder()
    {
        CorrectOrder = true;
        
        DefaultTableModel OrderModel = (DefaultTableModel) this.OrderTable.getModel();
        
        SetupOrder_ChosenProducts((DefaultTableModel) EditView.getChosenProducts().getModel());
        
        SetupOrder_Client_Edit();
        
        if(!CorrectOrder) return;
        
        SetupOrder_OrderRecord_Edit(OrderModel);
        
        EditView.getframe().dispose();
    }
    
    
    
    private int GetUniqueId(DefaultTableModel model) {
        int NewId = 0;

        for (int i = 0; i < 100000; i++) 
        {
            boolean AlreadyExists = false;

            for (int rowId = 0; rowId < model.getRowCount(); rowId++) 
            {
                if ((int) model.getValueAt(rowId, 0) == i) 
                {
                    AlreadyExists = true;
                }
            }

            if (AlreadyExists != true) 
            {
                NewId = i;

                break;
            }
        }

        return NewId;
    }
}
