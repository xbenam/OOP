package GUI;

import Classes.Car;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;

/**
 * Class for buying a car from a seller. (removing a car from warehouse)
 */
public class CarBuyingPanel extends JPanel {

    private ApplicationManager seller;
    private List<Car> carList;
    private int currentIndex = 0;
    private JLabel headingLabel = new JLabel("Buy a Car");
    private JButton previousButton = new JButton("Previous");
    private JButton nextButton = new JButton("Next");
    private JButton buyButton = new JButton("Buy");
    private JPanel topPanel = new JPanel();
    private JPanel bottomPanel = new JPanel();
    private CarPanelLayout carComponents = new CarPanelLayout();
    private JPanel navigateButtonsPanel = new JPanel();
    private JPanel buyPanel = new JPanel();

    /**
     * Constructor which create a panel for buying from seller.
     * @param seller Main seller with warehouse.
     */
    public CarBuyingPanel(ApplicationManager seller){
        this.seller = seller;
        carList = this.seller.getSeller().getWarehouse().getAllCars();

        setLayout(new BorderLayout(0, 20));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));

        previousButton.addActionListener(this::actionPerformed);
        nextButton.addActionListener(this::actionPerformed);
        buyButton.addActionListener(this::actionPerformed);

        headingLabel.setAlignmentX(0.5f);
        headingLabel.setFont(new Font(getFont().getName(),Font.PLAIN, 18));

        topPanel.add(Box.createVerticalStrut(10));
        topPanel.add(headingLabel);
        topPanel.add(Box.createVerticalStrut(10));
        topPanel.add(carComponents);

        navigateButtonsPanel.add(previousButton);
        navigateButtonsPanel.add(nextButton);
        buyButton.setPreferredSize(new Dimension(145, 30));
        buyButton.setBackground(Color.GREEN);
        buyButton.setForeground(Color.BLACK);
        buyButton.setFont(new Font(buyButton.getName(), Font.BOLD, 14));
        buyPanel.add(buyButton);

        bottomPanel.add(navigateButtonsPanel);
        bottomPanel.add(buyPanel);

        carComponents.add(bottomPanel, "Center");
        if(carList.size() != 0){
            carComponents.displayDetails(carList.get(0));
            carComponents.setVisible(true);
        }else{
            carComponents.displayDetails(new Car());
        }

        add(topPanel, "North");
    }

    /**
     * Implemented method which handle action events such as clicking on buttons.
     * @param actionEvent Action event which happened.
     */
    public void actionPerformed(ActionEvent actionEvent){
        if (actionEvent.getSource() == nextButton)
            nextButtonClicked();
        else if (actionEvent.getSource() == previousButton)
            previousButtonClicked();
        else if (actionEvent.getSource() == buyButton)
            try {
                buyButtonClicked();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    /**
     * Showing a next available car from seller's warehouse if have any.
     */
    private void nextButtonClicked() {
        if (carList.size() == 1) {
            carComponents.displayDetails(carList.get(currentIndex));
            JOptionPane.showMessageDialog(seller, "There are no cars left to show.", "Alert", JOptionPane.ERROR_MESSAGE);

        }
        else if (currentIndex < carList.size() - 1){
                currentIndex++;
                carComponents.displayDetails(carList.get(currentIndex));
            }else
                JOptionPane.showMessageDialog(seller, "There are no cars left to show.", "Alert", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Showing a previous available car from seller's warehouse if have any.
     */
    private void previousButtonClicked() {
        if (carList.size() == 1){
            carComponents.displayDetails(carList.get(currentIndex));
            JOptionPane.showMessageDialog(seller, "There are no cars left to show.", "Alert", JOptionPane.ERROR_MESSAGE);

        }
        else if ( currentIndex > 0) {
                currentIndex--;
                carComponents.displayDetails(carList.get(currentIndex));
            } else
                JOptionPane.showMessageDialog(seller, "There are no cars left to show.", "Alert", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Buying a car from a seller and updating a "database" cars.dat.
     * @throws IOException Exception if something broke while saving.
     */
    private void buyButtonClicked() throws IOException {
        if (carList.size() > 0) {
            Car a = carList.get(currentIndex);
            seller.sellACar(a);
            if (currentIndex > 0)
                currentIndex--;
            JOptionPane.showMessageDialog(seller, "Car has been sold.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            if(carList.size() > 0)
                carComponents.displayDetails(carList.get(0));
            else {
                JOptionPane.showMessageDialog(seller, "There are no cars to buy.", "Alert", JOptionPane.ERROR_MESSAGE);
                carComponents.displayDetails(new Car());
            }
        }
        else
            JOptionPane.showMessageDialog(seller, "There are no cars to buy.", "Alert", JOptionPane.ERROR_MESSAGE);
    }

}
