package Classes;

import java.util.*;

/**
 * Class which stores list of cars.
 */
public class Warehouse implements java.io.Serializable{
	private List<Car> cars;

	/**
	 * Constructor which creat cars attribute and fill it from parameter cars.
	 * @param car List of car.
	 */
	public Warehouse(List<Car> car) {
		this.cars = car;
	}

	/**
	 * Setting list of cars into cars attribute.
	 * @param cars List of cars.
	 */
	public void setCars(List<Car> cars) {
		this.cars = cars;
	}


	/**
	 * Adding a new car into a list of cars.
	 * @param c Car which will be added.
	 */
	public void addCar(Car c) {
		cars.add(c);
	}

	/**
	 * Removing a car from list of cars.
	 * @param c Car which will be removed.
	 */
	public void sellCar(Car c){
		cars.remove(c);
	}

	/**
	 * Returns car count.
	 * @return Int, size of car list.
	 */
	public int carCount()
	{
		return cars.size();
	}

	/**
	 * Getter for cars attribute.
	 * @return List of cars.
	 */
	public List<Car> getAllCars()
	{
		return cars;
	}

	/**
	 * Return a list of brand form car list where are no duplicated allowed.
	 * @return HashSet of all brands in car list.
	 */
	public HashSet<String> getAllBrands(){
		HashSet<String> allBrands = new HashSet<>();
		for (Car car:cars) {
			allBrands.add(car.getBrand());
		}
		return allBrands;
	}

}