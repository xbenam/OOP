package Classes;

/**
 * Class which store all car components.
 */
public class Car implements java.io.Serializable {
    private String brand;
    private String model;
    private int year;
    private int price;
    private double traveledDistance;


    /**
     * Empty Car constructor.
     */
    public Car() {
    }

    /**
     * Getting the brand component.
     *
     * @return String brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Setting the brand component.
     *
     * @param brand String which will be set as a brand.
     */
    public void setBrand(String brand) {
        this.brand = brand.toUpperCase();
    }

    /**
     * Getting the model component.
     *
     * @return String model
     */
    public String getModel() {
        return model;
    }

    /**
     * Setting the brand component.
     *
     * @param model String which will be set as a model.
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Getting the year component.
     *
     * @return int year
     */
    public int getYear() {
        return year;
    }

    /**
     * Setting the year component.
     *
     * @param year Int which will be set as a year.
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Getting the price component.
     *
     * @return int price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Setting the price component.
     *
     * @param price Int which will be set as a price.
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Getting the traveled distance component.
     *
     * @return Double traveled distance
     */
    public double getTraveledDistance() {
        return traveledDistance;
    }

    /**
     * Setting the traveled distance component.
     *
     * @param traveledDistance Double which will be set as a traveled distance.
     */
    public void setTraveledDistance(double traveledDistance) {
        this.traveledDistance = traveledDistance;
    }
}
