package GUI;

import Classes.Car;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Class for selling a car to a seller. (adding a car into warehouse)
 */
public class CarSellingPanel extends JPanel {
	private ApplicationManager seller;
	private JLabel headingLabel = new JLabel("Add a Cars");
	private JButton resetButton = new JButton("Reset");
	private JButton saveButton = new JButton("Save");
	private JPanel buttonPanel = new JPanel();
	private CarPanelLayout carComponents = new CarPanelLayout();

	/**
	 * Constructor which create a panel for selling a car to a seller.
	 * @param seller Main seller with warehouse.
	 */
	public CarSellingPanel(ApplicationManager seller) {
		this.seller = seller;

		resetButton.addActionListener(this::actionPerformed);
		saveButton.addActionListener(this::actionPerformed);

		headingLabel.setFont(new Font(getFont().getName(),Font.PLAIN, 18));
		headingLabel.setAlignmentX(0.5f);

		buttonPanel.add(resetButton);
		buttonPanel.add(saveButton);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		add(Box.createVerticalStrut(10));
		add(headingLabel);
		add(Box.createVerticalStrut(15));
		carComponents.add(buttonPanel, "Center");
		add(carComponents);
	}

	/**
	 * Implemented method which handle action events such as clicking on buttons.
	 * @param actionEvent Action event which happened.
	 */
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource() == resetButton)
			resetButtonClicked();
		else if (actionEvent.getSource() == saveButton) {
			try {
				saveButtonClicked();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Resets the text parts.
	 */
	private void resetButtonClicked()
	{
		carComponents.clearTextFields();
	}

	/**
	 * Updating the "database" (car.dat) and seller's warehouse.
	 * @throws IOException Exception if something broke while saving.
	 */
	private void saveButtonClicked() throws IOException {
		String brand = "";
		String model = "";
		double kilometers = 0.0;
		int price = 0;
		int year = 0;
		boolean valid = false;

		try {
			brand = carComponents.getBrandText().trim();
			model = carComponents.getModelText().trim();
			year = Integer.parseInt(carComponents.getYearText().trim());
			price = Integer.parseInt(carComponents.getPriceText().trim());
			kilometers = Double.parseDouble(carComponents.getTraveledDistanceText().trim());

			if (brand.length() != 0 && model.length() !=0) {
				if (year >= 1886 && year <= new GregorianCalendar().get(Calendar.YEAR)){
					if (price >= 0) {
						if (kilometers >= 0)
							valid = true;
						else
							JOptionPane.showMessageDialog(seller, "Traveled kilometers cannot by negative.",
									"Invalid traveled kilometers", JOptionPane.ERROR_MESSAGE);
					}
					else
						JOptionPane.showMessageDialog(seller, "Price for the car cannot by negative.",
							"Invalid price", JOptionPane.ERROR_MESSAGE);
				}
				else
					JOptionPane.showMessageDialog(seller, "Invalid year!\nThe 1st car was developed in" +
							" 1886 and current year is " + new GregorianCalendar().get(Calendar.YEAR) + ". \nYear" +
							" which was written is not in between those years.", "Invalid year",
							JOptionPane.ERROR_MESSAGE);
			}
			else
				JOptionPane.showMessageDialog(seller, "Brand and model of the car should contain at least 1" +
						" character", "Invalid brand", JOptionPane.ERROR_MESSAGE);

		}
		catch (NumberFormatException exp) {
			JOptionPane.showMessageDialog(seller, "Some numerical attribute are wrong. \nPossible errors:\n -" +
					"\"Year\" is not an integer number.\n -\"Price\" is not an integer number.\n -\"Km Traveled\" "+
					"is not a valid floating number.", "Invalid number field",
					JOptionPane.ERROR_MESSAGE);
		}

		if (valid) {
			Car myCar = new Car();
			myCar.setBrand(brand);
			myCar.setModel(model);
			myCar.setYear(year);
			myCar.setPrice(price);
			myCar.setTraveledDistance(kilometers);

			int result = seller.addNewCar(myCar);

			if (result == 0) {
				JOptionPane.showMessageDialog(seller, "Car added.", "Confirmation",
						JOptionPane.INFORMATION_MESSAGE);
				resetButtonClicked();
			}
			else
				JOptionPane.showMessageDialog(seller, "Car cannot be added due to the warehouse capacity.",
						"Problem adding car", JOptionPane.WARNING_MESSAGE);
		}
	}
}