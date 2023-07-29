package GUI;


import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * Class which extends the abstract class search for searching by age.
 */
public class SearchByAgePanel extends SearchPanel{

    private final String[] age = {"0","5", "10", "15", "20", "25"};

    private JLabel ageLabel = new JLabel("Car Age:");
    private JLabel split = new JLabel("-");

    private JComboBox ageComboMin = new JComboBox(age);
    private JComboBox ageComboMax = new JComboBox(age);

    private JPanel agePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

    /**
     * Constructor which add JComboBoxes for age and labels into given panel.
     * @param seller Seller.
     */
    public SearchByAgePanel(ApplicationManager seller) {
        super(seller, "Search by Age");

        getSearchButton().setToolTipText("To show cars which are just older then specific year, set only left boundary" +
                " to be minimal age and keep right to 0.");
        agePanel.add(ageLabel);
        agePanel.add(ageComboMin);
        agePanel.add(split);
        agePanel.add(ageComboMax);

        getTopPanel().add(agePanel);
    }

    /**
     * Overriding the method which will set JComboBoxes to 0 value when the reset button is pressed.
     */
    @Override
    public void resetButtonClicked() {
        super.resetButtonClicked();
        ageComboMin.setSelectedIndex(0);
        ageComboMax.setSelectedIndex(0);
    }

    /**
     * Overriding the abstract method to search only for cars which meets the age requirements.
     */
    @Override
    public void searchButtonClicked() {
        int minAge = Integer.parseInt((String) Objects.requireNonNull(ageComboMin.getSelectedItem()));
        int maxAge = Integer.parseInt((String) Objects.requireNonNull(ageComboMax.getSelectedItem()));

        if (minAge <= maxAge)
            setCarList(getSeller().search(minAge, maxAge));
        else if(maxAge == 0)
            setCarList(getSeller().search(minAge, Integer.MAX_VALUE));
        else{
            JOptionPane.showMessageDialog(getSeller(), "Invalid years.\n\"Minimum\" should by less then \"Maximum\"", "Invalid year",JOptionPane.ERROR_MESSAGE);
            return;
        }
        showCars();
    }
}
