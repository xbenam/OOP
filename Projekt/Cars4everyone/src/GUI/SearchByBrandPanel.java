package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 *
 */
public class SearchByBrandPanel extends SearchPanel {
	private String[] brand = getSeller().getSeller().getWarehouse().getAllBrands().toArray(new String[0]);

	private JLabel brandLabel = new JLabel("Car Brand:");
	private JComboBox brandCombo = new JComboBox(brand);
	private JPanel brandPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

	/**
	 * Constructor which add JComboBox for brand and label into given panel.
	 * @param seller Seller
	 */
	public SearchByBrandPanel(ApplicationManager seller) {
		super(seller, "Search by Brand");
		brandCombo.setToolTipText("To reload brand choicer press 'Reset' button.");
		brandPanel.add(brandLabel);
		brandPanel.add(brandCombo);

		getTopPanel().add(brandPanel);
	}

	/**
	 * Overriding the method to update JComboBox when the list of cars in seller's warehouse is updated.
	 */
	@Override
	public void resetButtonClicked() {
		brand = getSeller().getSeller().getWarehouse().getAllBrands().toArray(new String[0]);
		brandCombo.removeAllItems();
		brandCombo.setModel(new DefaultComboBoxModel(brand));
		super.resetButtonClicked();
		if (brand.length != 0)
			brandCombo.setSelectedIndex(0);
	}

	/**
	 * Overriding the abstract method to search only for cars with chosen brand from JComboBox.
	 */
	@Override
	public void searchButtonClicked() {
		brand = getSeller().getSeller().getWarehouse().getAllBrands().toArray(new String[0]);
		if (brand.length == 0){
			JOptionPane.showMessageDialog(getSeller(), "There are no cars to show.", "Search failed", JOptionPane.WARNING_MESSAGE);
			return;
		}
		String brand = (String) Objects.requireNonNull(brandCombo.getSelectedItem());

		setCarList(getSeller().search(brand));

		showCars();
	}
}