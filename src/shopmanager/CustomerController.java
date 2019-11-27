/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopmanager;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Patryk
 */
public class CustomerController {

    private CustomerAddView AddView;
    
    private CustomerEditView EditView;
    
    private CustomerModel customer;
    
    private JTable CustomerTable;
    
    private int CustomerSelectedId = -1;
    
    
    
    public CustomerController(CustomerAddView view, CustomerModel customer,JTable customerTable)
    {
        this.CustomerTable = customerTable;
        
        this.customer = customer;
        
        this.AddView = view;
        
        AddCustomer_InitController();
    }
    
    public CustomerController(CustomerEditView view, CustomerModel customer,JTable customerTable)
    {
        this.CustomerTable = customerTable;
        
        this.customer = customer;
        
        this.EditView = view;
        
        CustomerSelectedId = customerTable.getSelectedRow();
        
        EditCustomer_InitController();
    }
    
    
    
    public void AddCustomer_InitController() 
    {
        AddView.GetAcceptButton().addActionListener(e -> AddCustomer_Accept());

        AddView.GetCancelButton().addActionListener(e -> AddCustomer_Cancel());
    }
 
    private void AddCustomer_Accept() 
    {
        AddCustomer();
    }

    private void AddCustomer_Cancel() 
    {
        AddView.getframe().dispose();
    }

    private void AddCustomer() {
        DefaultTableModel model = (DefaultTableModel) CustomerTable.getModel();

        customer.SetId(GetUniqueId(model));
        customer.SetFirstName(AddView.getFirstNameField().getText());
        customer.SetLastName(AddView.getLastNameField().getText());
        customer.SetAddress(AddView.getAddressField().getText());

        model.addRow(new Object[]{customer.GetId(), customer.GetLastName(), customer.GetFirstName(), customer.GetAddress()});
        AddView.getframe().dispose();
    }

    public void EditCustomer_InitController() {
        EditView.GetAcceptButton().addActionListener(e -> EditCustomer_Accept());
        EditView.GetCancelButton().addActionListener(e -> EditCustomer_Cancel());
    }

    private void EditCustomer_Accept() {
        EditCustomer();
    }

    private void EditCustomer_Cancel() {
        EditView.getframe().dispose();
    }

    private void EditCustomer() {
        if (CustomerSelectedId != -1) {
            DefaultTableModel model = (DefaultTableModel) this.CustomerTable.getModel();
            int[] rows = CustomerTable.getSelectedRows();
            for (int i = 0; i < rows.length; i++) {
//                model.removeRow(rows[i] - i);
                model.setValueAt(EditView.getLastNameField().getText(), rows[i], 1);
                this.customer.SetLastName(EditView.getLastNameField().getText());
                model.setValueAt(EditView.getFirstNameField().getText(), rows[i], 2);
                this.customer.SetFirstName(EditView.getFirstNameField().getText());
                model.setValueAt(EditView.getAddressField().getText(), rows[i], 3);
                this.customer.SetAddress(EditView.getAddressField().getText());
            }
//            model.insertRow(ClientSelectedId, new Object[]{0, EditView.getLastNameField().getText(), EditView.getFirstNameField().getText(), EditView.getAddressField().getText()});
            EditView.getframe().dispose();
        }
    }

    private int GetUniqueId(DefaultTableModel model) {
        int NewId = 0;

        for (int i = 0; i < 100000; i++) {
            boolean AlreadyExists = false;

            for (int rowId = 0; rowId < model.getRowCount(); rowId++) {
                if ((int) model.getValueAt(rowId, 0) == i) {
                    AlreadyExists = true;
                }
            }

            if (AlreadyExists != true) {
                NewId = i;

                break;
            }
        }

        return NewId;
    }
}
