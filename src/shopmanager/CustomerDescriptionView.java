/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopmanager;

import java.awt.TextField;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Patryk
 */
public class CustomerDescriptionView 
{
    private JFrame frame;
    
    private JLabel IdLabel;;
    private TextField IdField;
    
    private JLabel FirstNameLabel;
    private TextField FirstNameField;
    
    private JLabel LastNameLabel;
    private TextField LastNameField;
    
    private JLabel AddressLabel;
    private TextField AddressField;
    
    public CustomerDescriptionView(OrderModel Order)
    {
        frame = new JFrame("Customer of order : " + Order.GetId());
        GroupLayout layout = new GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        IdLabel = new JLabel("Id: ");
        FirstNameLabel = new JLabel("First Name: ");
        LastNameLabel = new JLabel("Last Name: ");
        AddressLabel = new JLabel("Address: ");
        
        IdField = new TextField("");
        FirstNameField = new TextField("");
        LastNameField = new TextField("");
        AddressField = new TextField("");
        
  layout.setAutoCreateGaps(true);
  layout.setAutoCreateContainerGaps(true);
  
  layout.setHorizontalGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, true)  
          .addComponent(IdLabel)
          .addComponent(IdField)
          .addComponent(FirstNameLabel)
          .addComponent(FirstNameField)
          .addComponent(LastNameLabel)
          .addComponent(LastNameField)
          .addComponent(AddressLabel)
          .addComponent(AddressField)));
  
  layout.setVerticalGroup(layout.createSequentialGroup()
          .addComponent(IdLabel)
          .addComponent(IdField)
          .addComponent(FirstNameLabel)
          .addComponent(FirstNameField)
          .addComponent(LastNameLabel)
          .addComponent(LastNameField) 
          .addComponent(AddressLabel)
          .addComponent(AddressField));
    }

    public JFrame getframe() {
        return frame;
    }

    public JLabel getIdLabel() {
        return IdLabel;
    }
 
    public JLabel getFirstNameLabel() {
        return FirstNameLabel;
    }

    public JLabel getLastNameLabel() {
        return LastNameLabel;
    }

    public JLabel getAddressLabel() {
        return AddressLabel;
    }

    public TextField getIdField() {
        return IdField;
    }

    public TextField getFirstNameField() {
        return FirstNameField;
    }

    public TextField getLastNameField() {
        return LastNameField;
    }

    public TextField getAddressField() {
        return AddressField;
    }
}
