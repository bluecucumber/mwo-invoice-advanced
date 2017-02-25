package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
	private Map<Product, Integer> products = new HashMap<Product, Integer>();
	private static int No = 0;
	private int invoiceNumber = 0;

	public Invoice(){
		this.invoiceNumber = No;
		No++;
	}
	
	public void addProduct(Product product) {
		
		addProduct(product, 1);
	}

	public void addProduct(Product product, Integer quantity) {

		if (product == null || quantity <= 0) {
			throw new IllegalArgumentException();
		}
		if(products.containsKey(product.getName())){
		
		}
		else{
			products.put(product, quantity);
		}
	}

	public BigDecimal getNetTotal() {
		BigDecimal totalNet = BigDecimal.ZERO;
		for (Product product : products.keySet()) {
			BigDecimal quantity = new BigDecimal(products.get(product));
			totalNet = totalNet.add(product.getPrice().multiply(quantity));
		}
		return totalNet;
	}

	public BigDecimal getTaxTotal() {
		return getGrossTotal().subtract(getNetTotal());
	}

	public BigDecimal getGrossTotal() {
		BigDecimal totalGross = BigDecimal.ZERO;
		for (Product product : products.keySet()) {
			BigDecimal quantity = new BigDecimal(products.get(product));
			totalGross = totalGross.add(product.getPriceWithTax().multiply(quantity));
		}
		return totalGross;
	}
	
	public int getInvoiceNumber(){
		return this.invoiceNumber;
	}
	
	public String prePrint(){
		String txt = "";
		
		return txt;
	}


	public String printedVersion() {
		String printed = String.valueOf(invoiceNumber);
		for(Product product : products.keySet()){
			printed += "\n" + product.getName(); 
			printed += ", " + product.getClass().getName();
			printed += ", " + products.get(product);
		}
		printed += "\n" + "Liczba produktów: " + products.size();
		return printed;
	}
	
}
