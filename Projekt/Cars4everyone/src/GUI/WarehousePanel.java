package GUI;

import Classes.Car;
import Classes.Warehouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Panel which shows the information of all cars which are in Seller's warehouse.
 */
public class WarehousePanel extends JPanel {

    private Warehouse warehouse;
    private JLabel topLabel = new JLabel("Warehouse");
    private JTextArea warehouseInfo = new JTextArea(20,40);
    private JScrollPane scroll = new JScrollPane(warehouseInfo, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
    private JButton reloadButton = new JButton("Reload stock");
    private JPanel statsPanel = new JPanel ();

    /**
     * Constructor for warhouse panel.
     * Creating the button for reloading the stocks
     * and text area with the cars information.
     * @param seller Seller.
     */
    public WarehousePanel(ApplicationManager seller){
        warehouse = seller.getSeller().getWarehouse();

        reloadButton.addActionListener(this::actionPerformed);

        warehouseInfo.setEditable(false);

        topLabel.setFont(new Font(getFont().getName(),Font.PLAIN, 18));
        topLabel.setAlignmentX(0.5f);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalStrut(10));
        add(topLabel, "Center");

        statsPanel.add(reloadButton);
        statsPanel.add(Box.createVerticalStrut(10));
        statsPanel.add(scroll);

        add(statsPanel, "Center");

    }

    /**
     * Implemented method which handle action events such as clicking on buttons.
     * @param actionEvent Action event which happened.
     */
    private void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == reloadButton){
            clearTextArea();
            updateStats();
        }
    }

    /**
     * Reload the information about the seller's cars.
     */
    private void updateStats() {
        for (Car car : warehouse.getAllCars()) {
            warehouseInfo.append("  Brand: \t" + car.getBrand() + "\n");
            warehouseInfo.append("  Model: \t" + car.getModel() + "\n");
            warehouseInfo.append("  Year: \t" +car.getYear() + "\n");
            warehouseInfo.append("  Price: \t" +  car.getPrice() + "€\n");
            warehouseInfo.append("  Traveled: \t" + car.getTraveledDistance() + "km\n");
            warehouseInfo.append("-------------------------------------------------------\n");
        }
    }

    /**
     * Clear the warehouse text area.
     */
    private void clearTextArea(){
        warehouseInfo.setText("");
    }
}
