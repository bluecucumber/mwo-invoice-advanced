package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.HashMap;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
	
	private BigDecimal subtotal;
	private BigDecimal tax;
	private BigDecimal total;
	private HashMap<Product, Integer> productMap;
	
	public Invoice(){
		
		this.subtotal =BigDecimal.ZERO;
		this.tax =BigDecimal.ZERO;
		this.total =BigDecimal.ZERO;
		this.productMap = new HashMap<Product, Integer>();
	}
	
	public void addProduct(Product product) {
		productMap.put(product, 1);
	}

	public void addProduct(Product product, Integer quantity) {
		productMap.put(product, quantity);
	}

	public BigDecimal getSubtotal() {
		for (HashMap.Entry<Product, Integer> entry : productMap.entrySet())
		{
			subtotal = subtotal
					.add(entry.getKey().getPrice())
					.multiply(new BigDecimal(entry.getValue()));
		}
		return subtotal;
	}

	public BigDecimal getTax() {
		for (HashMap.Entry<Product, Integer> entry : productMap.entrySet())
		{
			tax = tax
					.add(entry.getKey().getPrice()
					.multiply(entry.getKey().getTaxPercent())
					.multiply(new BigDecimal(entry.getValue())));
			
		}
		return tax;
	}

	public BigDecimal getTotal() {
		for (HashMap.Entry<Product, Integer> entry : productMap.entrySet())
		{
			total = total
					.add(entry.getKey().getPriceWithTax())
					.multiply(new BigDecimal(entry.getValue()));
		}
		return total;
	}
}
