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
public class CustomerEditView {
    
    private JFrame frame;
    private JLabel IdLabel;
    private JLabel FirstNameLabel;
    private JLabel LastNameLabel;
    private JLabel AddressLabel;
    private TextField IdField;
    private TextField FirstNameField;
    private TextField LastNameField;
    private TextField AddressField;
    private JButton AcceptButton;
    private JButton CancelButton;
    
    private CustomerModel client;

    public CustomerEditView(CustomerModel client) {
        this.client = client;
        
        frame = new JFrame("Edit Customer");
        GroupLayout layout = new GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        FirstNameLabel = new JLabel("First Name: ");
        LastNameLabel = new JLabel("Last Name: ");
        AddressLabel = new JLabel("Address: ");
        
        FirstNameField = new TextField(client.GetFirstName());
        LastNameField = new TextField(client.GetLastName());
        AddressField = new TextField(client.GetAddress());
        
        AcceptButton = new JButton("Accept");
        CancelButton = new JButton("Cancel");
        
  layout.setAutoCreateGaps(true);
  layout.setAutoCreateContainerGaps(true);
  
  layout.setHorizontalGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, true)  
          .addComponent(FirstNameLabel)
          .addComponent(FirstNameField)
          .addComponent(LastNameLabel)
          .addComponent(LastNameField)
          .addComponent(AddressLabel)
          .addComponent(AddressField)
          .addComponent(AcceptButton)
          .addComponent(CancelButton)));
  
  layout.setVerticalGroup(layout.createSequentialGroup()
          .addComponent(FirstNameLabel)
          .addComponent(FirstNameField)
          .addComponent(LastNameLabel)
          .addComponent(LastNameField) 
          .addComponent(AddressLabel)
          .addComponent(AddressField)
          .addComponent(AcceptButton)
          .addComponent(CancelButton));
 
  //layout.linkSize(SwingConstants.HORIZONTAL, AcceptButton);
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
    
    public JButton GetAcceptButton()
    {
        return AcceptButton;
    }
    
    public JButton GetCancelButton()
    {
        return CancelButton;
    }
}
