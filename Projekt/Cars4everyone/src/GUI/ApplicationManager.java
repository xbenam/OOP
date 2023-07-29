package GUI;

import Classes.Car;
import Classes.Seller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.List;

/**
 * This class is the main core of the GUI part of the while application.
 * Here are all main panels created and called.
 */
public class ApplicationManager extends JFrame {

	private final String file;
	private final Seller seller;

	/**
	 * Getting the seller which act as a main core of non GUI part.
	 * @return Seller
	 */
	public Seller getSeller() {
		return seller;
	}


	/**
	 * Constructor which create main window of the application.
	 * @param f .dat "database" file which store information about all cars.
	 * @throws IOException Exception for IO errors.
	 * @throws ClassNotFoundException Stream exception.
	 */
	public ApplicationManager(String f) throws IOException, ClassNotFoundException {
		super("Cars 4 everyone");

		Closer close = new Closer();
		addWindowListener(close);

		setSize(640, 480);
		setResizable(false);

		Container c = getContentPane();
		seller = new Seller();
		file = f;

		// Loading the file content into a seller's warehouse.
		try {
			seller.loadCars(file);
			JOptionPane.showMessageDialog(this, "Data were successfully loaded.", "Success", JOptionPane.INFORMATION_MESSAGE);
		}
		catch (java.io.EOFException exp){
			JOptionPane.showMessageDialog(this, "Warehouse is completely empty.","Empty stock", JOptionPane.WARNING_MESSAGE);
		}
		catch (java.io.FileNotFoundException exp){
			JOptionPane.showMessageDialog(this,"The data file, 'cars.dat' doesn't exist.\nThe new data file was created.","Missing data file", JOptionPane.ERROR_MESSAGE);
			seller.resetCars(file);
		}
		catch (Exception exp) {
			JOptionPane.showMessageDialog(this,"Content of 'cars.dat' may be corrupted.\nThe new data file was created.","Corrupted data", JOptionPane.ERROR_MESSAGE);
			seller.resetCars(file);
		}

		// Creating each panel which will be displayed on the main window.
		CarSellingPanel addCarPanel = new CarSellingPanel(this);
		WarehousePanel warehousePanel = new WarehousePanel(this);
		SearchByAgePanel searchByAgePanel = new SearchByAgePanel(this);
		SearchByBrandPanel searchByBrandPanel = new SearchByBrandPanel(this);
		CarBuyingPanel carBuyingPanel = new CarBuyingPanel(this);

		// Adding the panels into panel tab on the main window.
		JTabbedPane theTab = new JTabbedPane(JTabbedPane.LEFT);
		theTab.add("Add a Car", addCarPanel);
		theTab.add("Warehouse", warehousePanel);
		theTab.add("Search by age", searchByAgePanel);
		theTab.add("Search by brand", searchByBrandPanel);
		theTab.add("Buy a car", carBuyingPanel);

		theTab.setSelectedIndex(0);
		c.add(theTab, "Center");
	}


	/**
	 * Method for adding a new car.
	 * @param c Car which will be added.
	 * @return Int which tells if the adding process was successful.
	 * @throws IOException Exception for IO errors.
	 */
	public int addNewCar(Car c) throws IOException {
		int a = seller.addCar(c);
		if (a == 0)
			seller.saveCars(file);
		return a;
	}

	/**
	 * Method for removing car from warehouse.
	 * @param c Car which will be removed.
	 * @throws IOException Exception for IO errors.
	 */
	public void sellACar(Car c) throws IOException {
		int a = seller.sellCar(c);
		if (a == 0)
			seller.saveCars(file);
	}

	/**
	 * Method which calls sellers's search method for age in interval.
	 * @param minAge Int, minimum of interval.
	 * @param maxAge Int, maximum of interval.
	 * @return List of cars from given interval.
	 */
	public List<Car> search(int minAge, int maxAge){
		return seller.search(minAge, maxAge);
	}

	/**
	 * Method which calls sellers's search method for brand.
	 * @param brand String brand of the cars to be find.
	 * @return List of cars with the given brand.
	 */
	public List<Car> search(String brand){
		return seller.search(brand);
	}


	public static void main(String[] args) throws IOException, ClassNotFoundException {
		ApplicationManager carSales = new ApplicationManager("cars.dat");
		carSales.setVisible(true);
	}


	/**
	 * Method which handles closing the program after closing application window.
	 */
	static class Closer extends WindowAdapter {
		/**
		 * End the program when the window is closed.
		 * @param ev Event of closed window.
		 */
		public void windowClosing(WindowEvent ev)
		{
			System.exit(0);
		}
	}

}