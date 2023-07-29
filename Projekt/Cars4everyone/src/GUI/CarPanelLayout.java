package GUI;

import Classes.Car;

import javax.swing.*;
import java.awt.*;

/**
 * This class act as a template for car components.
 */
public class CarPanelLayout extends JPanel {

	private JLabel brandLabel = new JLabel("Brand");
	private JLabel modelLabel = new JLabel("Model");
	private JLabel yearLabel = new JLabel("Year");
	private JLabel priceLabel = new JLabel("Price");
	private JLabel currencyLabel = new JLabel("€");
	private JLabel kmLabel = new JLabel("Km Traveled");
	private JLabel metricLabel = new JLabel("Km");
	private JTextField brandTextField = new JTextField();
	private JTextField modelTextField = new JTextField();
	private JTextField yearTextField = new JTextField();
	private JTextField priceTextField = new JTextField();
	private JTextField traveledDistanceTextField = new JTextField();

	/**
	 * Constructing the template for cars components.
	 */
	public CarPanelLayout() {
		GridBagConstraints gridBagConstraints = new GridBagConstraints();

		JPanel compPanel = new JPanel(new GridBagLayout());

		labelSetter(brandLabel, 0, 0, gridBagConstraints, compPanel);
		labelSetter(modelLabel, 0, 1, gridBagConstraints, compPanel);
		labelSetter(yearLabel, 0, 2, gridBagConstraints, compPanel);
		labelSetter(priceLabel, 0, 3, gridBagConstraints, compPanel);
		labelSetter(currencyLabel, 2, 3, gridBagConstraints, compPanel);
		labelSetter(kmLabel, 0, 4, gridBagConstraints, compPanel);
		labelSetter(metricLabel, 2, 4, gridBagConstraints, compPanel);

		textFieldSetter(brandTextField, 1, 0, gridBagConstraints, compPanel);
		textFieldSetter(modelTextField, 1, 1, gridBagConstraints, compPanel);
		textFieldSetter(yearTextField, 1, 2, gridBagConstraints, compPanel);
		textFieldSetter(priceTextField, 1, 3, gridBagConstraints, compPanel);
		textFieldSetter(traveledDistanceTextField, 1, 4, gridBagConstraints, compPanel);

        add(compPanel, "North");
	}

	/**
	 * Setting the position of label in grid.
	 * @param label Label to be set.
	 * @param x Row position in grid.
	 * @param y Col position in grid.
	 * @param gridBagConstraints Grid.
	 * @param compPanel Panel where will new label be placed.
	 */
	public void labelSetter(JLabel label, int x, int y, GridBagConstraints gridBagConstraints, JPanel compPanel){
		setLayout(new BorderLayout(0, 20));
		String currentFont = label.getFont().getName();

		label.setFont(new Font(currentFont, Font.BOLD, 12));
		gridBagConstraints.gridx = x;
		gridBagConstraints.gridy = y;
		gridBagConstraints.insets = new Insets(0, 15, 0, 30);
		compPanel.add(label, gridBagConstraints);
	}

	/**
	 * Setting the position of text field in grid.
	 * @param text Text field to be set.
	 * @param x Row position in grid.
	 * @param y Col position in grid.
	 * @param gridBagConstraints Grid.
	 * @param compPanel Panel where will new text field be placed.
	 */
	public void textFieldSetter (JTextField text, int x, int y, GridBagConstraints gridBagConstraints,JPanel compPanel){
		text.setPreferredSize(new Dimension(250,20));
		gridBagConstraints.gridx = x;
		gridBagConstraints.gridy = y;
		gridBagConstraints.gridwidth = GridBagConstraints.RELATIVE;
		gridBagConstraints.weightx = 1.0;
		compPanel.add(text, gridBagConstraints);
	}

	/**
	 * Clearing the text fields.
	 */
	public void clearTextFields() {
		brandTextField.setText("");
		yearTextField.setText("");
		modelTextField.setText("");
		priceTextField.setText("");
		traveledDistanceTextField.setText("");
	}

	/**
	 * Filling the the car components of given car into corresponded text fields.
	 * @param c Car.
	 */
	public void displayDetails(Car c) {
		brandTextField.setText(c.getBrand());
		modelTextField.setText(c.getModel());
		yearTextField.setText(Integer.toString(c.getYear()));
		priceTextField.setText(Integer.toString(c.getPrice()));
		traveledDistanceTextField.setText(Double.toString(c.getTraveledDistance()));

		brandTextField.setEditable(false);
		modelTextField.setEditable(false);
		yearTextField.setEditable(false);
		priceTextField.setEditable(false);
		traveledDistanceTextField.setEditable(false);
	}

	/**
	 * Getting the brand as a text.
	 * @return String brand.
	 */
	public String getBrandText()
	{
		return brandTextField.getText();
	}

	/**
	 * Getting the model as a text.
	 * @return String model.
	 */
	public String getModelText()
	{
		return modelTextField.getText();
	}

	/**
	 * Getting the year as a text.
	 * @return String year.
	 */
	public String getYearText()
	{
		return yearTextField.getText();
	}

	/**
	 * Getting the price as a text.
	 * @return String price.
	 */
	public String getPriceText()
	{
		return priceTextField.getText();
	}

	/**
	 * Getting the traveled distance as a text.
	 * @return String traveled distance.
	 */
	public String getTraveledDistanceText()
	{
		return traveledDistanceTextField.getText();
	}
}
