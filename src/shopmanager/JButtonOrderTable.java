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
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

public class JButtonOrderTable
{
    String ClientDescription, ProductsDescription;
    ButtonEditor buttonEditor1;
    ButtonEditor buttonEditor2;
    
    public JButtonOrderTable(JTable table, String ClientDescription, String ProductsDescription, int RowId) 
    {
        this.ClientDescription = ClientDescription;
        this.ProductsDescription = ProductsDescription;
        
        buttonEditor1 = new ButtonEditor(new JCheckBox(), MainJFrame.CustomerDescription);//ClientDescription);
        JButton button1 = buttonEditor1.button;
        buttonEditor1.ButtonAction = new Thread(new Runnable() {
            public void run() {
                Show_Description(button1, MainJFrame.CustomerDescription);//ClientDescription);
            }
        });
        
        table.getColumn("Client").setCellRenderer(new ButtonRenderer());
        table.getColumn("Client").setCellEditor(buttonEditor1);

        buttonEditor2 = new ButtonEditor(new JCheckBox(), MainJFrame.ProductDescription);//ProductsDescription);
        JButton button2 = buttonEditor2.button;
        buttonEditor2.ButtonAction = new Thread(new Runnable() {
            public void run() {
                Show_Description(button1, MainJFrame.ProductDescription);//ProductsDescription);
            }
        });
        table.getColumn("Products").setCellRenderer(new ButtonRenderer());
        table.getColumn("Products").setCellEditor(buttonEditor2);

        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
    }
    
    public void Show_Description(JButton button, String Description)
    {
        JOptionPane.showMessageDialog(button, Description);
    }
    
    public static void InvokeTheMethod(Callable<Integer> method)
    {
        try 
        {
            method.call();
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(JButtonOrderTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
    
class ButtonRenderer extends JButton implements TableCellRenderer {

    public ButtonRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
            setBackground(UIManager.getColor("Button.background"));
        }
        setText((value == null) ? "" : value.toString());
        return this;
    }
}

class ButtonEditor extends DefaultCellEditor {

    public JButton button;
    private String label;
    private boolean isPushed;
    
    String Description;
    
    //private Callable<Integer> ButtonAction;
    
    public Thread ButtonAction;
    
    public ButtonEditor(JCheckBox checkBox, String Description){//, Callable<Integer> ButtonAction) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener((ActionEvent e) -> {
            fireEditingStopped();
        });
        
        this.Description = Description;
        
        //this.ButtonAction = ButtonAction;
    }
//    
//    public void SetupDynamicButton(Runnable method)
//    {
//        this.ButtonAction = method;
//    }
    
    

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(table.getBackground());
        }
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) 
        {
            try 
            {
                ButtonAction.start();
                
                ButtonAction = new Thread(new Runnable() {
                    public void run() {
                        Show_Description(button, Description);
                    }
                });
                //ButtonAction.call();
                //JButtonTableExample.InvokeTheMethod(ButtonAction);
            } 
            catch (Exception ex) 
            {
                Logger.getLogger(ButtonEditor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        isPushed = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }
    
    public void Show_Description(JButton button, String Description)
    {
        JOptionPane.showMessageDialog(button, Description);
    }
}
