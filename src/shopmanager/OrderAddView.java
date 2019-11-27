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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Patryk
 */
public class OrderAddView {
    
    List<CustomerModel> Clients;
    
    List<ProductModel> Products;
    
    private JFrame frame;
    
    private GroupLayout layout;
    
    private JTable ChosenProducts;
    
    private JButton RemoveProduct;
    
    private JLabel ProductsLabel;
    
    private JLabel ChosenProductsLabel;
    
    private JComboBox ProductsCombo;
    
    private JLabel ClientLabel;
    
    private JComboBox ClientCombo;
    
    private TextField ClientField;
    
    private JLabel SumLabel;
    
    private TextField SumField;
    
    private JButton AcceptButton;
    
    private JButton CancelButton;
    
    float newSum;
    
    
    
    public OrderAddView(List<CustomerModel> Clients, List<ProductModel> Products) {
        this.Clients = Clients;
        
        this.Products = Products;
        
        Initialize_Frame("Add Order", 800, 500);
        
        Initialize_Products();
         
        Initialize_Clients();
        
        Initialize_FinalButtons();
        
        Initialize_Sum();
        
        Initialize_Layout();
    }
    
    void Initialize_Frame(String FrameName, int FrameSize_x, int FrameSize_y) {
        frame = new JFrame(FrameName);

        frame.setSize(FrameSize_x, FrameSize_y);

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    void Initialize_Layout() {
        layout = new GroupLayout(frame.getContentPane());

        frame.getContentPane().setLayout(layout);

        layout.setAutoCreateGaps(true);

        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, true)
                        .addComponent(ProductsLabel)
                        .addComponent(ProductsCombo)
                        .addComponent(ChosenProductsLabel)
                        .addComponent(ChosenProducts)
                        .addComponent(RemoveProduct)
                        .addComponent(ClientLabel)
                        .addComponent(ClientCombo)
                        .addComponent(SumLabel)
                        .addComponent(SumField)
                        .addComponent(AcceptButton)
                        .addComponent(CancelButton)));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(ProductsLabel)
                .addComponent(ProductsCombo)
                .addComponent(ChosenProductsLabel)
                .addComponent(ChosenProducts)
                .addComponent(RemoveProduct)
                .addComponent(ClientLabel)
                .addComponent(ClientCombo)
                .addComponent(SumLabel)
                .addComponent(SumField)
                .addComponent(AcceptButton)
                .addComponent(CancelButton));
    }

    void Initialize_Products() {
        ChosenProductsLabel = new JLabel("Chosen Products : ");

        Object columnNames[] = {"Id", "Name", "Price"};

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn(columnNames);

        model.addColumn(columnNames);

        model.addColumn(columnNames);

        ChosenProducts = new JTable(model);

        ProductsLabel = new JLabel("Available Products : ");

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

        for (int i = 0; i < Products.size(); i++) {
            ProductsCombo.addItem(Products.get(i).GetName());
        }

        ProductsCombo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) ChosenProducts.getModel();

                int i = ProductsCombo.getSelectedIndex();

                ProductModel product = Products.get(i);

                model.addRow(new Object[]{String.valueOf(product.GetId()), product.GetName(), String.valueOf(product.GetPrice())});

                RefreshSum(model);
            }
        });
    }

    void Initialize_Clients() {
        ClientLabel = new JLabel("Client : ");

        ClientField = new TextField("");

        ClientCombo = new JComboBox();

        for (int i = 0; i < Clients.size(); i++) {
            ClientCombo.addItem(Clients.get(i).GetLastName());
        }
    }

    void Initialize_FinalButtons() {
        AcceptButton = new JButton("Accept");

        CancelButton = new JButton("Cancel");
    }
    
    void Initialize_Sum() {
        SumLabel = new JLabel("Order Description : ");

        SumField = new TextField("");

        SumField.setEnabled(false);
    }
    
    void RefreshSum(DefaultTableModel model) {
        newSum = 0;

        for (int ii = 0; ii < model.getRowCount(); ii++) {
            newSum += Float.parseFloat(model.getValueAt(ii, 2).toString());
        }

        SumField.setText("Sum : " + String.valueOf(newSum));
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

    public JLabel getSumLabel() {
        return SumLabel;
    }

    public TextField getSumField() {
        return SumField;
    }
    
    public JButton GetAcceptButton() {
        return AcceptButton;
    }

    public JButton GetCancelButton() {
        return CancelButton;
    }
}
