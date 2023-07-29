package GUI;

import Classes.Car;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * Abstract method which implements the basic GUI staff for classes SearchByBrandPanel and
 * SearchByAgePanel.
 */
public abstract class SearchPanel extends JPanel {


    private List<Car> carList;
    private ApplicationManager seller;
    private int currentIndex = 0;

    private JButton searchButton = new JButton("Search");
    private JButton resetButton = new JButton("Reset");
    private JButton previousButton = new JButton("Previous");
    private JButton nextButton = new JButton("Next");

    private JPanel topPanel = new JPanel();

    private JPanel searchButtonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel navigateButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private CarPanelLayout carComponents = new CarPanelLayout();

    /**
     * Getting the top panel. This getter is used in children classes.
     * @return JPanel.
     */
    public JPanel getTopPanel() {
        return topPanel;
    }

    /**
     * Getting the search button. This getter is used in SearchByBrandPanel to add a tooltip.
     * @return JButton.
     */
    public JButton getSearchButton() {
        return searchButton;
    }

    /**
     * Getting the seller.
     * @return Seller
     */
    public ApplicationManager getSeller() {
        return seller;
    }

    /**
     * Setter for list of cars.
     * @param carList List of cars.
     */
    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }


    /**
     * Constructor which create the main core of searching GUI which will be shared for children.
     * @param seller Seller.
     * @param name String, which will be displayed on top the panel.
     */
    public SearchPanel(ApplicationManager seller, String name) {
        this.seller = seller;
        setLayout(new BorderLayout(0, 20));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        previousButton.addActionListener(this::actionPerformed);
        nextButton.addActionListener(this::actionPerformed);
        resetButton.addActionListener(this::actionPerformed);
        searchButton.addActionListener(this::actionPerformed);


        searchButtonsPanel.add(searchButton);
        searchButtonsPanel.add(resetButton);

        navigateButtonsPanel.add(previousButton);
        navigateButtonsPanel.add(nextButton);

        JLabel headingLabel = new JLabel(name);
        headingLabel.setFont(new Font(getFont().getName(),Font.PLAIN, 18));
        headingLabel.setAlignmentX(0.5f);

        topPanel.add(Box.createVerticalStrut(10));
        topPanel.add(headingLabel);
        topPanel.add(Box.createVerticalStrut(10));
        topPanel.add(searchButtonsPanel);
        topPanel.add(Box.createVerticalStrut(15));

        carComponents.add(navigateButtonsPanel, "Center");
        carComponents.setVisible(false);

        add(topPanel, "North");
        add(carComponents, "Center");
    }

    /**
     * Implemented method which handle action events such as clicking on buttons.
     * @param actionEvent Action event which happened.
     */
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == searchButton)
            searchButtonClicked();
        else if (actionEvent.getSource() == previousButton)
            previousButtonClicked();
        else if (actionEvent.getSource() == nextButton)
            nextButtonClicked();
        else if (actionEvent.getSource() == resetButton)
            resetButtonClicked();
    }

    /**
     * Abstract method which will be implemented in children's classes.
     */
    public abstract void searchButtonClicked();

    /**
     * Showing a next available car from seller's warehouse if have any.
     */
    private void nextButtonClicked() {
        if (currentIndex < carList.size() - 1)
        {
            currentIndex++;

            carComponents.displayDetails(carList.get(currentIndex));
        }
        else
            JOptionPane.showMessageDialog(seller, "There are no cars left to show.", "Alert", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Showing a previous available car from seller's warehouse if have any.
     */
    private void previousButtonClicked() {
        if (currentIndex > 0)
        {
            currentIndex--;
            carComponents.displayDetails(carList.get(currentIndex));
        }
        else
            JOptionPane.showMessageDialog(seller, "There are no cars left to show.", "Alert", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Method which resets the attributes to it begging state.
     */
    public void resetButtonClicked() {
        currentIndex = 0;
        carList = null;
        carComponents.setVisible(false);
    }

    /**
     * Show the information of one car from carList on the currentIndex.
     */
    public void showCars(){
        if (carList.size() > 0) {
            currentIndex = 0;
            carComponents.setVisible(true);
            carComponents.displayDetails(carList.get(0));

            if (carList.size() == 1) {
                nextButton.setEnabled(false);
                previousButton.setEnabled(false);
            } else {
                nextButton.setEnabled(true);
                previousButton.setEnabled(true);
            }

        } else {
            JOptionPane.showMessageDialog(getSeller(), "There are no cars with required age.", "Search failed", JOptionPane.WARNING_MESSAGE);
            resetButtonClicked();
        }
    }
}
