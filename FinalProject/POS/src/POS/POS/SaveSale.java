package POS;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.TableModel;

public class SaveSale 
{
    private TableModel model;
    public static List<String> saleDataList = new ArrayList<>();
    SaveSale()
    {
        model = SalesUI.model;
        
        try
        {
            PrintWriter writer = new PrintWriter("Sales.csv");
            for (int i = 0; i < model.getRowCount(); i++)
            {
                writer.println(model.getValueAt(i, 0) + "," + model.getValueAt(i, 1) + "," + model.getValueAt(i, 2)+ "," + model.getValueAt(i, 3));
               saleDataList.add(model.getValueAt(i, 0) + "," + model.getValueAt(i, 1) + "," + model.getValueAt(i, 2)+ "," + model.getValueAt(i, 3));
            }
            writer.close();
            SalesUI.model.setRowCount(0);
        }
        catch (Exception e)
        {
            System.out.println("Error saving sale: " + e.getMessage());
        }
    }
    




}