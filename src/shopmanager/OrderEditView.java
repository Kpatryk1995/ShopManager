/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopmanager;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Arassus Grand
 */
public class OrderEditView 
{
    CustomerModel Client;
    List<ProductModel> Products;
    
    private JFrame frame;
    
    private JTable ChosenProducts;
    private JButton RemoveProduct;
    
    private JLabel ProductsLabel;
    private JComboBox ProductsCombo;
    
    private JLabel ClientLabel;
    private JComboBox ClientCombo;
    private TextField ClientField;
    
    private JLabel SumaLabel;
    private TextField SumaField;
    
    private JButton AcceptButton;
    private JButton CancelButton;
    float newSum;
    
    
    //@TODO Po dodaniu zamówienia i usunięciu produktu, i edycji zamówienia, i zapisaniu wywala error
    public OrderEditView(OrderModel Order, List<CustomerModel> Clients, List<ProductModel> Products)
    {
        this.Client = Order.GetClient();
        
        this.Products = Order.GetProducts();
        
        frame = new JFrame("Edit Order");
        GroupLayout layout = new GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        Object columnNames[] = { "Id", "Name", "Price"};
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn(columnNames);
        model.addColumn(columnNames);
        model.addColumn(columnNames);
        for(int i=0;i<Order.GetProducts().size();i++)
        {
            ProductModel product = this.Products.get(i);
            model.addRow(new Object[]{String.valueOf(product.GetId()) ,product.GetName(),String.valueOf(product.GetPrice())});
        }
        ChosenProducts = new JTable(model);
        
        
        ProductsLabel = new JLabel("Products : ");
        
        ClientLabel = new JLabel("Client : ");
        ClientField = new TextField("");
        
        SumaLabel = new JLabel("Order Description : ");
        SumaField = new TextField("");
        SumaField.setEnabled(false);
        
        RemoveProduct = new JButton("Delete");

        RemoveProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ChosenProducts.getSelectedRowCount() > 0) {
                    DefaultTableModel model = (DefaultTableModel) ChosenProducts.getModel();

                    model.removeRow(ChosenProducts.getSelectedRow());

                    RefreshSum(model);
                }
            }
        });
        
        ProductsCombo = new JComboBox();
        for(int i=0; i<Products.size(); i++)
        {
            ProductsCombo.addItem(Products.get(i).GetName());
        }
        ProductsCombo.addActionListener (new ActionListener () 
        {
            public void actionPerformed(ActionEvent e) 
            {
                DefaultTableModel model = (DefaultTableModel) ChosenProducts.getModel();                
                int i = ProductsCombo.getSelectedIndex();
                ProductModel product = Products.get(i);
                model.addRow(new Object[]{String.valueOf(product.GetId()) ,product.GetName(),String.valueOf(product.GetPrice())});
                
                RefreshSum(model);
            }
        });
        
        ClientCombo = new JComboBox();
        for(int i=0; i<Clients.size(); i++)
        {
            ClientCombo.addItem(Clients.get(i).GetLastName());
        }
        
        AcceptButton = new JButton("Accept");
        CancelButton = new JButton("Cancel");
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
  
        layout.setHorizontalGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, true)  
          .addComponent(ProductsLabel)
          .addComponent(ProductsCombo)
          .addComponent(ChosenProducts)
          .addComponent(RemoveProduct)
          .addComponent(ClientLabel)
          .addComponent(ClientCombo)
          .addComponent(SumaLabel)
          .addComponent(SumaField)
          .addComponent(AcceptButton)
          .addComponent(CancelButton)));
  
  layout.setVerticalGroup(layout.createSequentialGroup()
          .addComponent(ProductsLabel)
          .addComponent(ProductsCombo)
          .addComponent(ChosenProducts)
          .addComponent(RemoveProduct)
          .addComponent(ClientLabel)
          .addComponent(ClientCombo)
          .addComponent(SumaLabel)
          .addComponent(SumaField)
          .addComponent(AcceptButton)
          .addComponent(CancelButton));
  
        RefreshSum(model);
    }
    
    void RefreshSum(DefaultTableModel model)
    {
        newSum = 0;

        for (int ii = 0; ii < model.getRowCount(); ii++) {
            newSum += Float.parseFloat(model.getValueAt(ii, 2).toString());
        }

        SumaField.setText("Suma : " + String.valueOf(newSum));
    }

    public JFrame getframe() {
        return frame;
    }

    public JTable getChosenProducts() {
        return ChosenProducts;
    }

    public JLabel getProductsLabel() {
        return ProductsLabel;
    }

    public JComboBox getProductsCombo() {
        return ProductsCombo;
    }

    public JLabel getClientLabel() {
        return ClientLabel;
    }

    public JComboBox getClientCombo() {
        return ClientCombo;
    }

    public JLabel getSumaLabel() {
        return SumaLabel;
    }

    public TextField getSumaField() {
        return SumaField;
    }
    
    public JButton GetAcceptButton()
    {
        return AcceptButton;
    }
    
    public JButton GetCancelButton()
    {
        return CancelButton;
    }
}
