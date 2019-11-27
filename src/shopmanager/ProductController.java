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
public class ProductController {

    private ProductAddView AddView;
    private ProductEditView EditView;
    private ProductModel product;

    private JTable ProductTable;

    private int ProductSelectedId = -1;

    public ProductController(ProductAddView view, ProductModel product, JTable ProductTable) {
        this.ProductTable = ProductTable;

        this.product = product;

        this.AddView = view;

        AddProduct_InitController();
    }

    public ProductController(ProductEditView view, ProductModel product,JTable ProductTable)
    {
        this.ProductTable = ProductTable;
        
        this.product = product;
        
        this.EditView = view;
        
        ProductSelectedId = ProductTable.getSelectedRow();
        
        EditProduct_InitController();
    }
    
    public void AddProduct_InitController() {
        AddView.GetAcceptButton().addActionListener(e -> AddProduct_Accept());
        AddView.GetCancelButton().addActionListener(e -> AddProduct_Cancel());
    }

    private void AddProduct_Accept() {
        AddProduct();
    }

    private void AddProduct_Cancel() {
        AddView.getframe().dispose();
    }

    private void AddProduct() {        
        DefaultTableModel model = (DefaultTableModel) ProductTable.getModel();

        product.SetId(GetUniqueId(model));
        product.SetName(AddView.getNameField().getText());
        
        try
        {
            product.SetPrice(Float.parseFloat(AddView.getPriceField().getText()));
        }
        catch(NumberFormatException exception)
        {
            JOptionPane.showMessageDialog(null, "Zły format ceny");
        
            return;
        }
        
        model.addRow(new Object[]{
            product.GetId(),
            product.GetName(),
            product.GetPrice()
        });

        AddView.getframe().dispose();
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

  public void EditProduct_InitController() {
        EditView.GetAcceptButton().addActionListener(e -> EditProduct_Accept());
        EditView.GetCancelButton().addActionListener(e -> EditProduct_Cancel());
    }
 
 private void EditProduct_Accept()
 {
       EditProduct();
 }
 
 private void EditProduct_Cancel()
 {
     EditView.getframe().dispose();
 }
 
 private void EditProduct()
 {
     //TODO
     if(ProductSelectedId != -1)
     {
         DefaultTableModel model = (DefaultTableModel) this.ProductTable.getModel();
         
         int UniqueId = GetUniqueId(model);
         
//        int[] rows = ProductTable.getSelectedRows();
//        for(int i=0;i<rows.length;i++){
//          model.removeRow(rows[i]-i);
            model.setValueAt(EditView.getNameField().getText(), ProductSelectedId, 1);
            this.product.SetName(EditView.getNameField().getText());
            model.setValueAt(EditView.getPriceField().getText(), ProductSelectedId, 2);
            this.product.SetPrice(Float.parseFloat(EditView.getPriceField().getText()));
//        }
//        model.insertRow(ProductSelectedId, new Object[]
//        {
//            UniqueId, 
//            EditView.getNameField().getText(),
//            EditView.getPriceField().getText(),
//        });
        
        EditView.getframe().dispose();
    }
 }
}
