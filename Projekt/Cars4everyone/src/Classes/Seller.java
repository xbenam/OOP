package Classes;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Main class which handle all cars in warehouse.
 */
public class Seller
{
	public static final int CARS_MAXIMUM_REACHED = 1;
	private static final int maxCars = 20;

	private final Warehouse warehouse = new Warehouse(new ArrayList<>());

	/**
	 *	Empty constructor.
	 */
	public Seller(){}

	/**
	 * Methode to add a new car into a warehouse.
	 * @param c Car class type which will be added.
	 * @return Int information about if the car was added or not.
	 */
	public int addCar(Car c) {
		int result = 0;

		if (warehouse.carCount() < maxCars)
			warehouse.addCar(c);
		else
			result = CARS_MAXIMUM_REACHED;

		return result;
	}

	/**
	 * Methode to remove a car from a warehouse
	 * @param c Car class type which will be removed.
	 * @return Int information about if the car was removed successfully.
	 */
	public int sellCar(Car c){
		warehouse.sellCar(c);
		return 0;
}

	/**
	 * Method to load cars from database into a warehouse.
	 * @param file .dat file which stores serialized information of all cars.
	 * @throws IOException Exception for IO errors.
	 * @throws ClassNotFoundException Stream exception.
	 */
	public void loadCars(String file) throws IOException, ClassNotFoundException {
		ObjectInputStream inp = new ObjectInputStream(new FileInputStream(file));
		warehouse.setCars((List<Car>)inp.readObject());
		inp.close();
	}

	/**
	 * Method to reset data file.
	 * @param file .dat file.
	 * @throws IOException Exception for IO errors.
	 */
	public void resetCars(String file) throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
		out.close();
	}

	/**
	 * Methode which save cars into a .dat "database" file.
	 * @param file .dat file which stores serialized information of all cars.
	 * @throws IOException Exception for IO errors.
	 */
	public void saveCars(String file) throws IOException {
		if (warehouse.carCount() >= 0)
		{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(warehouse.getAllCars());
			out.close();
		}
	}

	/**
	 * Search methode used to fined cars in given interval of age.
	 * @param minAge Int, minimum of interval.
	 * @param maxAge Int, maximum of interval.
	 * @return List of cars from given interval.
	 */
	public List<Car> search(int minAge, int maxAge){
		List<Car> carList = new ArrayList<>();
		int currentYear = new GregorianCalendar().get(Calendar.YEAR);

		for (Car car: warehouse.getAllCars()) {
			if (currentYear - car.getYear() < maxAge && currentYear - car.getYear() >= minAge){
				carList.add(car);
			}
		}
		return carList;
	}

	/**
	 * Search methode used to fiend cars with given brand.
	 * @param brand String brand of the cars to be find.
	 * @return List of cars with the given brand.
	 */
	public List<Car> search(String brand){
		List<Car> carList = new ArrayList<>();

		for (Car car: warehouse.getAllCars()) {
			if (brand.equals(car.getBrand())){
				carList.add(car);
			}
		}
		return carList;
	}

	/**
	 * Getting the warehouse.
	 * @return Warehouse warehosue
	 */
	public Warehouse getWarehouse() {
		return warehouse;
	}
}