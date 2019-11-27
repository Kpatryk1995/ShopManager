/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopmanager;

import java.awt.TextField;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Patryk
 */
class ProductEditView {
    private JFrame frame;
    private JLabel NameLabel;
    private JLabel PriceLabel;
    private TextField NameField;
    private TextField PriceField;
    private JButton AcceptButton;
    private JButton CancelButton;
    
    public ProductEditView(ProductModel product)
    {
        frame = new JFrame("Add Product");
        GroupLayout layout = new GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
                
        NameLabel = new JLabel("Name: ");
        PriceLabel = new JLabel("Price: ");
        
        NameField = new TextField(product.GetName());
        PriceField = new TextField(String.valueOf(product.GetPrice()));
        
        AcceptButton = new JButton("Accept");
        CancelButton = new JButton("Cancel");
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
  
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, true)  
            .addComponent(NameLabel)
            .addComponent(NameField)
            .addComponent(PriceLabel)
            .addComponent(PriceField)
            .addComponent(AcceptButton)
            .addComponent(CancelButton)));
  
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addComponent(NameLabel)
            .addComponent(NameField)
            .addComponent(PriceLabel)
            .addComponent(PriceField)
            .addComponent(AcceptButton)
            .addComponent(CancelButton));
        
        
    }
    
    public JFrame getframe() {
    return frame;
    }
 
    public JLabel getNameLabel() {
        return NameLabel;
    }

    public JLabel getPriceLabel() {
        return PriceLabel;
    }

    public TextField getNameField() {
        return NameField;
    }

    public TextField getPriceField() {
        return PriceField;
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
